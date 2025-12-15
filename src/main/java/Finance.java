
public interface Finance {
    void add(Account account);

    void delete(Account account);

    void viewBalance(Account account);

    void viewSpendingsByYear(Account account);

    void viewSpendingsByMonth(Account account);

    void viewSpendingsByWeek(Account account);

    void viewSpendingsByDay(Account account);

    void viewIncomeByYear(Account account);

    void viewIncomeByMonth(Account account);

    void viewIncomeByWeek(Account account);

    void viewIncomeByDay(Account account);
}

//Abstractions
