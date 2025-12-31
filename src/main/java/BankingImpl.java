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
    }



    public BankingImpl(Scanner scanner) throws SQLException {
        String url = System.getenv("DATABASE_URL");
        String user = System.getenv("DATABASE_USER");
        String password = System.getenv("DATABASE_PASSWORD");
        this.scanner = scanner;
        Connection connection = DriverManager.getConnection(url, user, password);

        try (Statement statement = connection.createStatement()) {
            statement.execute("CREATE TABLE IF NOT EXISTS transaction (" +
                    "id UUID PRIMARY KEY," +
                    "type TEXT," +
                    "date DATE," +
                    "sum NUMERIC," +
                    "account_id UUID not null)");
        }
    }

    // , String type, double sum, Date date
    @Override
    public void add(Account account) {  //Funktion att lägga till saldo, både positivt och negativt
        try {
            System.out.println("Ange transaktionstyp: ");
            String type = scanner.next();

            System.out.println("Ange datum: ");
            LocalDate date = LocalDate.parse(scanner.next());

            System.out.println("Ange summa (negativt för utgifter): ");
            double sum = scanner.nextDouble();

            Transaction transaction = new Transaction(type, date, sum, account);
            account.addTransaction(transaction);
            System.out.printf("You have added transcation with id %d%n", transaction.getId());
            System.out.printf("Nuvarande kontobalans: %s%n", account.getBalance());
        } catch (InputMismatchException e) {
            System.out.println("Invalid transcationtype");
        }
    }

    @Override
    public void delete(Account account) { //för att ta bort en transaktion genom att ange det ID den fick
        try {
            System.out.println("Ange ID på transaktion du vill ta bort ");
            int id = scanner.nextInt();
            account.getTransactions().removeIf(transaction -> transaction.getId() == id);
            account.updateBalance();
            System.out.println("Transaktion borttagen");

        } catch (InputMismatchException e) {
            System.out.println("Invalid ID number");
        }

    }

    @Override
    public void viewBalance(Account account)  {
        String query = "SELECT * FROM balance";
        try (PreparedStatement statement = getConnection().prepareStatement(query)) {
            statement.execute("SELECT balance FROM account where id = (account.getId())");
            System.out.printf("Ditt konto: %s%n", account.getBalance());
        }
        catch (InputMismatchException | SQLException e) {
            System.out.println("Try again");
        }
    }


    @Override
    public void viewSpendingsByYear(Account account)  { //få fram årlig spendering
        String query = "SELECT * FROM transaction WHERE balance < 0 and date like CONCAT(?, '%');";
        try (PreparedStatement statement = getConnection().prepareStatement(query)) {
            System.out.println("Ange år (yyyy):");
            String years = scanner.next();
            statement.setString(1, years);
            ResultSet result = statement.executeQuery();
            printTransactions(account, result);
        } catch (InputMismatchException | SQLException e) {
            System.out.println("Invalid year");
        }
    }


    @Override
    public void viewSpendingsByMonth(Account account) {
        String query = "SELECT * FROM transaction WHERE balance < 0 and date like CONCAT(?, ?, '%');";
        try (PreparedStatement statement = getConnection().prepareStatement(query)) {
            System.out.println("Ange år och månad (yyyy mm):");
            String years = scanner.next();
            statement.setString(1, years);
            ResultSet result = statement.executeQuery();
            printTransactions(account, result);
        } catch (InputMismatchException | SQLException e) {
            System.out.println("Invalid Month");
        }
    }

    @Override
    public void viewSpendingsByWeek(Account account) {
        String query = "SELECT * FROM transaction WHERE balance < 0 and date like CONCAT(?, ?, ?, '%');";
        try (PreparedStatement statement = getConnection().prepareStatement(query)) {
            System.out.println("Ange år, månad och vecka (yyyy mm ww):");
            String years = scanner.next();
            statement.setString(1, years);
            ResultSet result = statement.executeQuery();
            printTransactions(account, result);
        } catch (InputMismatchException | SQLException e) {
            System.out.println("Invalid Week");
        }

    }

    @Override
    public void viewSpendingsByDay(Account account) {
        String query = "SELECT * FROM transaction WHERE balance < 0 and date like CONCAT(?, ?, ?, ?, '%');";
        try (PreparedStatement statement = getConnection().prepareStatement(query)) {
            System.out.println("Ange år, månad, vecka och dag (yyyy mm ww dd):");
            String years = scanner.next();
            statement.setString(1, years);
            ResultSet result = statement.executeQuery();
            printTransactions(account, result);
        } catch (InputMismatchException | SQLException e) {
            System.out.println("Invalid Day");
        }

    }

    @Override
    public void viewIncomeByYear(Account account) {
            String query = "SELECT * FROM transaction WHERE balance > 0 and date like CONCAT(?, '%');";
            try (PreparedStatement statement = getConnection().prepareStatement(query)) {
                System.out.println("Ange år (yyyy):");
                String years = scanner.next();
                statement.setString(1, years);
                ResultSet result = statement.executeQuery();
                printTransactions(account, result);
        } catch (InputMismatchException | SQLException e) {
            System.out.println("Invalid year");
        }

    }

    @Override
    public void viewIncomeByMonth(Account account) {
        String query = "SELECT * FROM transaction WHERE balance > 0 and date like CONCAT(?, ?, '%');";
        try (PreparedStatement statement = getConnection().prepareStatement(query)) {
            System.out.println("Ange år och månad (yyyy MM):");
            String years = scanner.next();
            statement.setString(1, years);
            ResultSet result = statement.executeQuery();
            printTransactions(account, result);
        } catch (InputMismatchException | SQLException e) {
            System.out.println("Invalid month");
        }

    }

    @Override
    public void viewIncomeByWeek(Account account) {
        String query = "SELECT * FROM transaction WHERE balance > 0 and date like CONCAT(?, ?, ?, '%');";
        try (PreparedStatement statement = getConnection().prepareStatement(query)) {
            System.out.println("Ange år, månad och vecka (yyyy mm ww):");
            String years = scanner.next();
            statement.setString(1, years);
            ResultSet result = statement.executeQuery();
            printTransactions(account, result);
        } catch (InputMismatchException | SQLException e) {
            System.out.println("Invalid Week");
        }

    }

    @Override
    public void viewIncomeByDay(Account account) {
        String query = "SELECT * FROM transaction WHERE balance > 0 and date like CONCAT(?, ?, ?, ?, '%');";
        try (PreparedStatement statement = getConnection().prepareStatement(query)) {
            System.out.println("Ange år, månad, vecka och dag (yyyy mm ww dd):");
            String years = scanner.next();
            statement.setString(1, years);
            ResultSet result = statement.executeQuery();
            printTransactions(account, result);
        } catch (InputMismatchException | SQLException e) {
            System.out.println("Invalid day");
        }

    }
}