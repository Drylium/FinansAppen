import java.util.Scanner;

public class MainMenu {
    public void print(Scanner scanner, Finance finance, Account account) {
        String output = """
                1. Lägg till transaktion
                2. Ta bort transaktion
                3. Se konto balans
                4. Se utgifter
                5. Se inkomster
                6. Avsluta
                """;
        System.out.println(output);
        int choice = scanner.nextInt();
        handle(choice, finance, account, scanner);
    }

    private void handle(int choice, Finance finance, Account account, Scanner scanner) {
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
                System.exit(0);
            default:
                break;
        }
    }
}
