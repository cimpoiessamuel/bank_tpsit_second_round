import java.util.ArrayList;

public class BankAccount {
  private double balance;
  private final User user;

  // private ArrayList<Transaction> transactions;

  //
  BankAccount(User user) {
    this.user = user;
    this.balance =
        Math.round((Math.random() * 1000) * 100.0)
            / 100.0; // random decimal number between 0 and 999.99
  }

  //
  BankAccount(User user, double balance) {
    this.user = user;
    this.balance = balance;
  }

  public boolean deposit(double s) {
    if (s <= MainFrame.getSessionUser().getWallet()) {
      balance += s;
      user.setWallet(user.getWallet() - s);
      return true;
    }

    return false;
  }

  public boolean withdraw(double s) {
    if (balance - s >= 0) {
      balance -= s;
      return true;
    }

    return false;
  }

  public double getBalance() {
    return balance;
  }

  public void setBalance(double balance) {
    this.balance = balance;
  }
}
