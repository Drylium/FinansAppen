import java.awt.*;
import java.time.LocalDate;
import java.time.temporal.WeekFields;
import java.util.InputMismatchException;
import java.util.Scanner;

public class FinanceImpl implements Finance {
    private final Scanner scanner;

    public FinanceImpl(Scanner scanner) {
        this.scanner = scanner;
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

            Transaction transaction = new Transaction(type, date, sum);
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
    public void viewBalance(Account account) { //ta fram saldot
        System.out.printf("Ditt konto: %s%n", account.getBalance());

    }

    @Override
    public void viewSpendingsByYear(Account account) { //få fram årlig spendering
        try {
            System.out.println("Ange år (yyyy):");
            String years = scanner.next();
            for (Transaction transaction : account.getTransactions()) {
                String transactionDate = transaction.getDate().toString();
                if (transactionDate.contains(years)) {
                    if (transaction.getSum() < 0) {
                        System.out.println(transaction);
                    }
                }
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid year");
        }

    }

    @Override
    public void viewSpendingsByMonth(Account account) {
        try {
            System.out.println("Ange år och månad (yyyy-mm):");
            String months = scanner.next();
            for (Transaction transaction : account.getTransactions()) {
                String transactionMonth = transaction.getDate().toString();
                if (transactionMonth.contains(months)) {
                    if (transaction.getSum() < 0) {
                        System.out.println(transaction);
                    }
                }
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid Month");
        }
    }

    @Override
    public void viewSpendingsByWeek(Account account) {
        try {
            System.out.println("Ange år (YYYY):");
            String years = scanner.next();
            System.out.println("Ange vecka (VV):");
            String weeks = scanner.next();
            for (Transaction transaction : account.getTransactions()) {
                String transactionDate = transaction.getDate().toString();
                if (transactionDate.contains(years)) {
                    String weekNumber = String.valueOf(transaction.getDate().get(WeekFields.ISO.weekOfWeekBasedYear()));
                    if (weekNumber.equals(weeks)) {
                        if (transaction.getSum() < 0) {
                            System.out.println(transaction);
                        }
                    }
                }
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid Week");
        }

    }

    @Override
    public void viewSpendingsByDay(Account account) {
        try {
            System.out.println("Ange år,månad och dag (yyyy-mm-dd):");
            String days = scanner.next();
            for (Transaction transaction : account.getTransactions()) {
                String transactionMonth = transaction.getDate().toString();
                if (transactionMonth.contains(days)) {
                    if (transaction.getSum() < 0) {
                        System.out.println(transaction);
                    }
                }
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid Day");
        }

    }

    @Override
    public void viewIncomeByYear(Account account) {
        try {
            System.out.println("Ange år (yyyy):");
            String years = scanner.next();
            for (Transaction transaction : account.getTransactions()) {
                String transactionDate = transaction.getDate().toString();
                if (transactionDate.contains(years)) {
                    if (transaction.getSum() > 0) {
                        System.out.println(transaction);
                    }
                }
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid year");
        }

    }

    @Override
    public void viewIncomeByMonth(Account account) {
        try {
            System.out.println("Ange år och månad (yyyy-mm):");
            String months = scanner.next();
            for (Transaction transaction : account.getTransactions()) {
                String transactionDate = transaction.getDate().toString();
                if (transactionDate.contains(months)) {
                    if (transaction.getSum() > 0) {
                        System.out.println(transaction);
                    }
                }
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid month");
        }

    }

    @Override
    public void viewIncomeByWeek(Account account) {
        try {
            System.out.println("Ange år (YYYY):");
            String years = scanner.next();
            System.out.println("Ange vecka (VV):");
            String weeks = scanner.next();
            for (Transaction transaction : account.getTransactions()) {
                String transactionDate = transaction.getDate().toString();
                if (transactionDate.contains(years)) {
                    String weekNumber = String.valueOf(transaction.getDate().get(WeekFields.ISO.weekOfWeekBasedYear()));
                    if (weekNumber.equals(weeks)) {
                        if (transaction.getSum() > 0) {
                            System.out.println(transaction);
                        }
                    }
                }
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid Week");
        }

    }

    @Override
    public void viewIncomeByDay(Account account) {
        try {
            System.out.println("Ange år, månad och dag(yyyy-mm-dd):");
            String days = scanner.next();
            for (Transaction transaction : account.getTransactions()) {
                String transactionDate = transaction.getDate().toString();
                if (transactionDate.contains(days)) {
                    if (transaction.getSum() > 0) {
                        System.out.println(transaction);
                    }
                }
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid day");
        }

    }
}