import java.util.Scanner;

public class TransactionMenu extends MainMenu {
    public void print(Scanner scanner, Finance finance, Account account) {
        String output = """
                1. Se transaktion årligt
                2. Se transaktion månadsvis
                3. Se transaktion veckovis
                4. Se transaktion dagsvis
                """;
        System.out.println(output);
        int choice = scanner.nextInt();
        handle(choice, finance, account);
    }

    private void handle(int choice, Finance finance, Account account) {
        switch (choice) {
            case 1:
                finance.viewSpendingsByYear(account);
                break;
            case 2:
                finance.viewSpendingsByMonth(account);
                break;
            case 3:
                finance.viewSpendingsByWeek(account);
                break;
            case 4:
                finance.viewSpendingsByDay(account);
                break;
            default:
                break;
        }
    }
}
