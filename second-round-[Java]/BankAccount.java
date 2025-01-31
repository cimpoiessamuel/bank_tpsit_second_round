import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class BankAccount {
    private double balance;
    private User user;
    private String ID;

    private static int bankAccountCounterID = 1000;

    //public FileWriter trans = new FileWriter("movements.txt");

    BankAccount() {
        this.user = null;
        this.balance = 0.0;
        this.ID = String.valueOf(bankAccountCounterID);
        bankAccountCounterID++;
    }

    BankAccount(User user, double balance) {
        this.user = user;
        this.balance = balance;
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
}
