import java.time.LocalDate;
import java.util.Date;
import java.util.Random;

public class Transaction {
    private int id;
    private String type;
    private LocalDate date;
    private double sum;

    public Transaction(String type, LocalDate date, double sum) {
        this.type = type;
        this.date = date;
        this.sum = sum;
        Random ran = new Random();
        this.id = ran.nextInt(0, 10000);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public double getSum() {
        return sum;
    }

    public void setSum(double sum) {
        this.sum = sum;
    }

    @Override
    public String toString() {
        return String.format("typ: %s - datum: %s - summa: %f", type, date.toString(), sum);
    }
}
