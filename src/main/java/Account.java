import java.util.ArrayList;
import java.util.List;

public class Account {
    private double balance;
    private final List<Transaction> transactions = new ArrayList<>();

    public Account() {
        this.balance = 0;
    }

    public double getBalance() {
        return balance;
    }

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
