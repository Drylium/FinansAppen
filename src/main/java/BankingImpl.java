import java.sql.*;
import java.time.LocalDate;
import java.time.temporal.WeekFields;
import java.util.InputMismatchException;
import java.util.Scanner;

public class BankingImpl implements Banking {
    private final Scanner scanner;

    private Connection getConnection () throws SQLException {
        String url = System.getenv("DATABASE_URL");
        String user = System.getenv("DATABASE_USER");
        String password = System.getenv("DATABASE_PASSWORD");
        Connection connection = DriverManager.getConnection(url, user, password);
        return connection;
    }

    private static void printTransactions(Account account, ResultSet result) throws SQLException {
        while (result.next()) {
            Transaction transaction = new Transaction(
                    result.getString("type"),
                    result.getDate("date").toLocalDate(),
                    result.getDouble("sum"),
                    account);
            transaction.setId(result.getInt("number"));
            System.out.println(transaction);
        }
        result.close();
    }



    public BankingImpl(Scanner scanner) throws SQLException {
        String url = System.getenv("DATABASE_URL");
        String user = System.getenv("DATABASE_USER");
        String password = System.getenv("DATABASE_PASSWORD");
        this.scanner = scanner;
        Connection connection = DriverManager.getConnection(url, user, password);

        try (Statement statement = connection.createStatement()) {
            statement.execute("CREATE TABLE IF NOT EXISTS transaction (" +
                    "id NUMERIC PRIMARY KEY," +
                    "type TEXT," +
                    "date DATE," +
                    "sum NUMERIC," +
                    "account_id NUMERIC not null)");
        }
    }

    // , String type, double sum, Date date
    @Override
    public void add(Account account) throws SQLException {
        String query  = "INSERT into transaction (id, type, date, sum, account_id) values (DEFAULT, ?, ?, ?, ?);";
         try (PreparedStatement statement = getConnection().prepareStatement(query)) {
            System.out.println("Ange transaktionstyp: ");
            String type = scanner.next();

            System.out.println("Ange datum: ");
            LocalDate date = LocalDate.parse(scanner.next());

            System.out.println("Ange summa (negativt för utgifter): ");
            double sum = scanner.nextDouble();

            statement.setString(1, type);
            statement.setDate(2, java.sql.Date.valueOf(date));
            statement.setDouble(3, sum);
            statement.setInt(4, account.getId());
            statement.execute();
        }
        catch (InputMismatchException e) {
            System.out.println("Try again");
        }
    }

    @Override
    public void delete(Account account) throws SQLException { //för att ta bort en transaktion genom att ange det ID den fick
        String query = "DELETE FROM transaction WHERE id = ?";
        try (PreparedStatement statement = getConnection().prepareStatement(query)) {
            System.out.println("Ange ID på transaktion du vill ta bort ");
            int id = scanner.nextInt();
            statement.setInt(1, id);
            System.out.println("Transaktion borttagen");

        } catch (InputMismatchException e) {
            System.out.println("Invalid ID number");
        }

    }

    @Override
    public void viewBalance(Account account) throws SQLException {
        String query = "SELECT * FROM account WHERE id = ?";
        try (PreparedStatement statement = getConnection().prepareStatement(query)) {
            statement.setInt(1, account.getId());
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                System.out.println(result.getDouble("balance"));
            }
        }
        catch (InputMismatchException e) {
            System.out.println("Try again");
        }
    }


    @Override
    public void viewSpendingsByYear(Account account) throws SQLException { //få fram årlig spendering
        String query = "SELECT * FROM transaction WHERE transaction < 0 and date like CONCAT(?, '%');";
        try (PreparedStatement statement = getConnection().prepareStatement(query)) {
            System.out.println("Ange år (yyyy):");
            String years = scanner.next();
            statement.setString(1, years);
            ResultSet result = statement.executeQuery();
            printTransactions(account, result);
        } catch (InputMismatchException e) {
            System.out.println("Invalid year");
        }
    }


    @Override
    public void viewSpendingsByMonth(Account account) throws SQLException {
        String query = "SELECT * FROM transaction WHERE transaction < 0 and date like CONCAT(?, '%');";
        try (PreparedStatement statement = getConnection().prepareStatement(query)) {
            System.out.println("Ange år och månad (yyyy-mm):");
            String years = scanner.next();
            statement.setString(1, years);
            ResultSet result = statement.executeQuery();
            printTransactions(account, result);
        } catch (InputMismatchException e) {
            System.out.println("Invalid Month");
        }
    }
    //String query = "SELECT * FROM transaction WHERE balance < 0 and date like CONCAT(?, '%');";

    @Override
    public void viewSpendingsByWeek(Account account) throws SQLException {
        String query = "SELECT * FROM transaction WHERE transaction < 0 and DATE_PART('year', date) = ? and DATE_PART('week', date) = ?;";
        try (PreparedStatement statement = getConnection().prepareStatement(query)) {
            System.out.println("Ange år (yyyy):");
            String years = scanner.next();
            statement.setString(1, years);

            System.out.println("Anger vecka (ww):");
            String week = scanner.next();
            statement.setString(2, week);

            ResultSet result = statement.executeQuery();
            printTransactions(account, result);
        } catch (InputMismatchException e) {
            System.out.println("Invalid Week");
        }

    }

    @Override
    public void viewSpendingsByDay(Account account) throws SQLException {
        String query = "SELECT * FROM transaction WHERE transaction < 0 and date like CONCAT(?, '%');";
        try (PreparedStatement statement = getConnection().prepareStatement(query)) {
            System.out.println("Ange år, månad och dag (yyyy-mm-dd):");
            String years = scanner.next();
            statement.setString(1, years);
            ResultSet result = statement.executeQuery();
            printTransactions(account, result);
        } catch (InputMismatchException e) {
            System.out.println("Invalid Day");
        }

    }

    @Override
    public void viewIncomeByYear(Account account) throws SQLException {
            String query = "SELECT * FROM transaction WHERE transaction > 0 and date like CONCAT(?, '%');";
            try (PreparedStatement statement = getConnection().prepareStatement(query)) {
                System.out.println("Ange år (yyyy):");
                String years = scanner.next();
                statement.setString(1, years);
                ResultSet result = statement.executeQuery();
                printTransactions(account, result);
        } catch (InputMismatchException e) {
            System.out.println("Invalid year");
        }

    }

    @Override
    public void viewIncomeByMonth(Account account) throws SQLException {
        String query = "SELECT * FROM transaction WHERE transaction > 0 and date like CONCAT(?, '%');";
        try (PreparedStatement statement = getConnection().prepareStatement(query)) {
            System.out.println("Ange år och månad (yyyy-MM):");
            String years = scanner.next();
            statement.setString(1, years);
            ResultSet result = statement.executeQuery();
            printTransactions(account, result);
        } catch (InputMismatchException e) {
            System.out.println("Invalid month");
        }

    }

    @Override
    public void viewIncomeByWeek(Account account) throws SQLException {
        String query = "SELECT * FROM transaction WHERE transaction > 0 and DATE_PART('year', date) = ? and DATE_PART('week', date) = ?;";
        try (PreparedStatement statement = getConnection().prepareStatement(query)) {
            System.out.println("Ange år (yyyy):");
            String years = scanner.next();
            statement.setString(1, years);

            System.out.println("Anger vecka (ww):");
            String week = scanner.next();
            statement.setString(2, week);

            ResultSet result = statement.executeQuery();
            printTransactions(account, result);
        } catch (InputMismatchException e) {
            System.out.println("Invalid Week");
        }

    }

    @Override
    public void viewIncomeByDay(Account account) throws SQLException {
        String query = "SELECT * FROM transaction WHERE transaction > 0 and date like CONCAT(?, '%');";
        try (PreparedStatement statement = getConnection().prepareStatement(query)) {
            System.out.println("Ange år, månad och dag (yyyy-mm-dd):");
            String years = scanner.next();
            statement.setString(1, years);
            ResultSet result = statement.executeQuery();
            printTransactions(account, result);
        } catch (InputMismatchException e) {
            System.out.println("Invalid day");
        }

    }
}