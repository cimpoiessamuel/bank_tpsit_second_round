import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Random;

public class BankAccount {
  private double balance;
  private final ArrayList<Transaction> transactions;

  //
  BankAccount() {
    this.balance =
        Math.round((Math.random() * 1000) * 100.0)
            / 100.0; // random decimal number between 0 and 999.99
    this.transactions = new ArrayList<>();
  }

  //
  BankAccount(double balance) {
    this.balance = balance;
    this.transactions = new ArrayList<>();
  }

  public boolean deposit(double s) {
    if (s <= MainFrame.getSessionUser().getWallet()) {
      balance += s;
      MainFrame.getSessionUser().setWallet(MainFrame.getSessionUser().getWallet() - s);
      return true;
    }

    return false;
  }

  public boolean withdraw(double s) {
    if (balance - s >= 0) {
      balance -= s;
      MainFrame.getSessionUser().setWallet(MainFrame.getSessionUser().getWallet() + s);
      return true;
    }

    return false;
  }

  public boolean invest(double amount, String period, String risk) {
    if (amount > balance || balance <= 0) {
      return false;
    }

    //
    Transaction lastInvestment =
        new Transaction(
            amount,
            LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")),
            InternalFrame.investmentTransactionDefault,
            MainFrame.getSessionUser());

    //
    MainFrame.getSessionUser().getBankAccount().addTransaction(lastInvestment);

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
    File newInvFile =
        new File(
            MainFrame.getSessionUser().getDirectory().getAbsolutePath()
                + "/investment-"
                + lastInvestment.getID()
                + ".csv");

    //
    try {
      if (newInvFile.createNewFile()) {
        System.out.println("investment file created");
      } else {
        System.err.println("investment file already exists (impossible exc for tests)");
      }
    } catch (IOException e) {
      System.err.println("investment file creation failed");
    }

    //
    double _amount = amount;

    //
    try (BufferedWriter outFile = new BufferedWriter(new FileWriter(newInvFile))) {
      //
      outFile.write("x;y\n");
      outFile.write("0;" + _amount + "\n");

      //
      for (int i = 1; i <= _period; i++) {
        //
        _amount = new Random().nextDouble(_risk);

        //        if (new Random().nextInt(1000) % 2 == 0) {
        //          _amount = -_amount;
        //        }

        //
        outFile.write(i + ";" + (Math.round(_amount * 100.0) / 100.0) + "\n");

        //
        if (i % 30 == 0) {
          MainFrame.walletMonthlyIncome();
        }
      }

      //
      if (risk.equals("Low") && (_amount < (0.7 * amount))) {
        //
        _amount = amount * 0.7;

      } else if (risk.equals("Medium") && (_amount < (0.4 * amount))) {
        //
        _amount = amount * 0.4;
      }

      //
      outFile.write((_period + 1) + ";" + (Math.round(_amount * 100.0) / 100.0));

    } catch (IOException e) {
      System.err.println("investment file writing failure");
    }

    //
    MainFrame.getSessionUser()
        .getBankAccount()
        .addTransaction(
            new Transaction(
                (Math.round(_amount * 100.0) / 100.0),
                LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")),
                InternalFrame.investmentProfitTransactionDefault,
                MainFrame.getSessionUser()));

    //
    balance += _amount;

    return true;
  }

  public void readTransactions() {
    //
    try (BufferedReader inFile =
        new BufferedReader(new FileReader(MainFrame.getSessionUser().getFile()))) {
      //
      String line;
      while ((line = inFile.readLine()) != null) {
        if (!line.isEmpty() && Character.isDigit(line.charAt(0))) {
          transactions.add(
              new Transaction(
                  Integer.parseInt(line.split(";")[0]),
                  Double.parseDouble(line.split(";")[2]),
                  line.split(";")[1],
                  line.split(";")[3]));
        }
      }

    } catch (IOException e) {
      System.err.println("transaction reading failed");
    }
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
