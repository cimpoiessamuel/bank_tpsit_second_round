import java.io.FileWriter;

public class BankAccount {
    private double balance;
    private User user;
    private String ID;

    private static int bankAccountCounterID = 100;


    // default constructor
    BankAccount() {
        this.user = null;
        this.balance = 0.0;
        this.ID = String.valueOf(bankAccountCounterID);
        bankAccountCounterID++;
    }

    BankAccount(User user) {
        this.user = user;
        this.balance = Math.round((Math.random() * 1000) * 100.0) / 100.0; // random decimal number between 0 and 999.99
        this.ID = String.valueOf(bankAccountCounterID);
        bankAccountCounterID++;
    }

    public boolean deposit(double s) {
        balance += s;

        return true;
    }

    public boolean withdraw(double s) {
        if (balance - s >= 0) {
            balance -= s;
            return true;
        } else {
            return false;
        }
    }

    public double getBalance() {
        return balance;
    }

    public User getUser() {
        return user;
    }
}
