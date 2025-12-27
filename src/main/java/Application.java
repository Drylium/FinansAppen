import java.sql.SQLException;
import java.util.Scanner;

public class Application {
    private final Scanner scanner;
    private final Banking finance;
    private final Account account;

    public Application() throws SQLException {
        this.scanner = new Scanner(System.in);
        this.finance = new BankingImpl(scanner);
        this.account = new Account();
    }

    public void run() throws SQLException {
        MainMenu menu = new MainMenu();
        while (true) {  //Kör programmet tills du väljer att avsluta
            menu.print(scanner, finance, account);
        }
    }
}
