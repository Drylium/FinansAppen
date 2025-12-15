import java.util.Scanner;

public class Application {
    private final Scanner scanner;
    private final Finance finance;
    private final Account account;

    public Application() {
        this.scanner = new Scanner(System.in);
        this.finance = new FinanceImpl(scanner);
        this.account = new Account();
    }

    public void run() {
        MainMenu menu = new MainMenu();
        while (true) {  //Kör programmet tills du väljer att avsluta
            menu.print(scanner, finance, account);
        }
    }
}
