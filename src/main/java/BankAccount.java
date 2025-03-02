import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class BankAccount {
  private final User user;
  private double balance;
  private ArrayList<Transaction> transactions;

  //
  BankAccount(User user) {
    this.user = user;
    this.balance =
        Math.round((Math.random() * 1000) * 100.0)
            / 100.0; // random decimal number between 0 and 999.99
    this.transactions = new ArrayList<>();
  }

  //
  BankAccount(User user, double balance) {
    this.user = user;
    this.balance = balance;

    try (BufferedReader inFile = new BufferedReader(new FileReader(user.getFile()))) {
      //
      this.transactions = new ArrayList<>();

      String line;
      while ((line = inFile.readLine()) != null) {
        if (!line.isEmpty() && Character.isDigit(line.charAt(0))) {
          transactions.add(
              new Transaction(
                  Double.parseDouble(line.split(";")[1]), line.split(";")[0], line.split(";")[2]));
        }
      }

    } catch (IOException e) {
      System.err.println("transaction reading failed");
    }
  }

  public boolean deposit(double s) {
    if (s <= user.getWallet()) {
      balance += s;
      user.setWallet(user.getWallet() - s);
      return true;
    }

    return false;
  }

  public boolean withdraw(double s) {
    if (balance - s >= 0) {
      balance -= s;
      user.setWallet(user.getWallet() + s);
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

  public void addTransaction(Transaction t) {
    transactions.add(t);
  }
}
