import java.sql.*;
import java.util.List;
import java.util.Scanner;

public class AccountLogicImpl implements AccountLogic {

    private final Scanner scanner;
    private final Connection connection;

    AccountLogicImpl(Scanner scanner) throws SQLException {
        String dbUrl = System.getenv("DATABASE_URL");
        String dbUser = System.getenv("DATABASE_USER");
        String dbPassword = System.getenv("DATABASE_PASSWORD");
        this.scanner = scanner;
        this.connection = DriverManager.getConnection(dbUrl, dbUser, dbPassword);

        try (Statement statement = connection.createStatement()) {
            // statement.execute("DROP TABLE IF EXISTS account");
            statement.execute("CREATE TABLE IF NOT EXISTS account (" +
                    "id SERIAL PRIMARY KEY," +
                    "balance NUMERIC)");
        }
    }

    @Override
    public void add() throws SQLException {
        System.out.println("Skapar nytt konto....");
        String sql = "INSERT INTO account VALUES (DEFAULT, 0)";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            System.out.println("Kör sql: " + statement.toString());
            if (statement.executeUpdate() != 1) {
                throw new SQLException("Failed to insert user");
            }
        }
    }

    @Override
    public void delete() {

    }

    @Override
    public Account getAccount(int id) {
        return null;
    }

    @Override
    public List<Account> getAllAccounts() {
        return List.of();
    }
}
