import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

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

  public boolean invest(double amount, String period, String risk) {
    if (amount > balance || balance <= 0) {
      return false;
    }

    //
    balance -= amount;

    //
    int _period =
        switch (period) {
          case "Short" -> 90;
          case "Medium" -> 180;
          case "Long" -> 360;
          default -> 0;
        };

    //
    double _risk =
        switch (risk) {
          case "Low" -> amount * 3;
          case "Medium" -> amount * 6;
          case "High" -> amount * 11;
          default -> 0;
        };

    //
    double _amount = amount;

    //
    for (int i = 1; i <= _period; i++) {
      //
      _amount = new Random().nextDouble(_risk);

      //
      if (new Random().nextInt(1000) % 2 == 0) {
        _amount = -_amount;
      }

      //
      if (i % 30 == 0) {
        MainFrame.walletMonthlyIncome();
      }
    }

    if (risk.equals("Low") && (_amount < 0.7 * amount)) {
      //
      _amount = Math.abs(_amount) * 0.7;

    } else if (risk.equals("Medium") && (_amount < 0.4 * amount)) {
      //
      _amount = Math.abs(amount) * 0.4;
    }

    //
    balance += _amount;

    return true; // LocalDateTime.now().getMonthValue();
  }

  public double getBalance() {
    return balance;
  }

  public ArrayList<Transaction> getTransactions() {
    return transactions;
  }

  public void addTransaction(Transaction t) {
    transactions.add(t);
  }
}
