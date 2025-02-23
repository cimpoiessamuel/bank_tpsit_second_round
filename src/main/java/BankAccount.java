import java.util.ArrayList;

public class BankAccount {
  private double balance;
  private final String ID;
  private ArrayList<Transaction> transactions;
  private static int bankAccountCounterID = 100;

  // default constructor
  BankAccount() {
    this.balance =
        Math.round((Math.random() * 1000) * 100.0)
            / 100.0; // random decimal number between 0 and 999.99
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

  public void setBalance(double balance) {
    this.balance = balance;
  }
}
