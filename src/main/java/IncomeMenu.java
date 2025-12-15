import java.util.Scanner;

public class IncomeMenu extends MainMenu {


    public void print(Scanner scanner, Finance finance, Account account) {
        String output = """
                1. Se inkomst årligt
                2. Se inkomst månadsvis
                3. Se inkomst veckovis
                4. Se inkomst dagsvis
                """;
        System.out.println(output);
        int choice = scanner.nextInt();
        handle(choice, finance, account);
    }


    private void handle(int choice, Finance finance, Account account) {
        switch (choice) {
            case 1:
                finance.viewIncomeByYear(account);
                break;
            case 2:
                finance.viewIncomeByMonth(account);
                break;
            case 3:
                finance.viewIncomeByWeek(account);
                break;
            case 4:
                finance.viewIncomeByDay(account);
                break;
            default:
                break;
        }
    }
}
