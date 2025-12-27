import java.sql.SQLException;
import java.util.Scanner;

public class MainMenu {
    public void print(Scanner scanner, Banking finance, Account account) throws SQLException {
        String output = """
                1. Lägg till transaktion
                2. Ta bort transaktion
                3. Se konto balans
                4. Se utgifter
                5. Se inkomster
                6. Skapa konto
                7. Avsluta
                """;
        System.out.println(output);
        int choice = scanner.nextInt();
        handle(choice, finance, account, scanner);
    }

    private void handle(int choice, Banking finance, Account account, Scanner scanner) throws SQLException {
        switch (choice) {
            case 1:
                finance.add(account);
                break;
            case 2:
                finance.delete(account);
                break;
            case 3:
                finance.viewBalance(account);
                break;
            case 4:
                MainMenu transaction = new TransactionMenu();
                transaction.print(scanner, finance, account);
                break;
            case 5:
                MainMenu income = new IncomeMenu();
                income.print(scanner, finance, account);
                break;
            case 6:
                AccountLogic accountLogic = new AccountLogicImpl(scanner);
                accountLogic.add();
            case 7:
                System.exit(0);
            default:
                break;
        }
    }


}
