import java.util.ArrayList;
import java.util.List;

public class Account {
    private int id;
    private double balance;
    private final List<Transaction> transactions = new ArrayList<>();

    public Account() {
        this.id = 1;
        this.balance = 0;
    }

    public double getBalance() {
        return balance;
    }

    public int getId() { return id;}

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void addTransaction(Transaction transaction) {
        this.balance = this.balance + transaction.getSum();
        this.transactions.add(transaction);
    }

    public void updateBalance() {
        this.balance = this.transactions.stream().mapToDouble(Transaction::getSum).sum();
    }
}
