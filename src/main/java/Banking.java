import java.sql.SQLException;

public interface Banking {
    void add(Account account) throws SQLException;

    void delete(Account account) throws SQLException;

    void viewBalance(Account account) throws SQLException;

    void viewSpendingsByYear(Account account) throws SQLException;

    void viewSpendingsByMonth(Account account) throws SQLException;

    void viewSpendingsByWeek(Account account) throws SQLException;

    void viewSpendingsByDay(Account account) throws SQLException;

    void viewIncomeByYear(Account account) throws SQLException;

    void viewIncomeByMonth(Account account) throws SQLException;

    void viewIncomeByWeek(Account account) throws SQLException;

    void viewIncomeByDay(Account account) throws SQLException;
}

//Abstractions
