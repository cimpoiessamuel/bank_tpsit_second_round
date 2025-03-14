import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Random;

public class BankAccount {
  private double balance;
  private final ArrayList<Transaction> transactions;

  // new BankAccount for new User
  BankAccount() {
    this.balance =
        Math.round((Math.random() * 1000) * 100.0)
            / 100.0; // random decimal number between 0 and 999.99
    this.transactions = new ArrayList<>();
  }

  // BankAccount for existing User
  BankAccount(double balance) {
    this.balance = balance;
    this.transactions = new ArrayList<>();
  }

  public boolean deposit(double s) {
    if (s > 0 && s <= MainFrame.getSessionUser().getWallet()) {
      balance += Math.round(s * 100.0) / 100.0;
      MainFrame.getSessionUser().setWallet(MainFrame.getSessionUser().getWallet() - s);

      return true;
    }

    return false;
  }

  public boolean withdraw(double s) {
    if (s > 0 && balance - s >= 0) {
      balance -= Math.round(s * 100.0) / 100.0;
      MainFrame.getSessionUser().setWallet(MainFrame.getSessionUser().getWallet() + s);

      return true;
    }

    return false;
  }

  public boolean invest(double amount, String period, String risk) {
    if (amount <= 0 || balance <= 0 || amount > balance) {
      return false;
    }

    // save the last inv. to keep trace of the ID
    Transaction lastInvestment =
        new Transaction(
            amount,
            LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")),
            InternalFrame.investmentTransactionDefault);

    MainFrame.getSessionUser().getBankAccount().addTransaction(lastInvestment);

    balance -= amount;

    MainFrame.getSessionUser().updateTrend();

    int _period =
        switch (period) {
          case "Short" -> 90;
          case "Medium" -> 180;
          case "Long" -> 360;
          default -> 0;
        };

    double _risk =
        switch (risk) {
          case "Low" -> amount * 3;
          case "Medium" -> amount * 6;
          case "High" -> amount * 11;
          default -> 0;
        };

    // for every inv. a record file is created -> 'investment-#.csv'
    File newInvFile =
        new File(
            MainFrame.getSessionUser().getDirectory().getAbsolutePath()
                + "/investment-"
                + lastInvestment.getID()
                + ".csv");

    try {
      if (newInvFile.createNewFile()) {
        System.out.println("investment file created");
      } else {
        System.err.println("investment file already exists (impossible exc for tests)");
      }
    } catch (IOException e) {
      System.err.println("investment file creation failed");
    }

    // working amount
    double _amount = amount;

    // every movement in written
    try (BufferedWriter outFile = new BufferedWriter(new FileWriter(newInvFile))) {
      // legend x(day);y(value)
      outFile.write("x;y");
      outFile.newLine();

      outFile.write("0;" + _amount);
      outFile.newLine();

      for (int i = 1; i <= _period; i++) {
        _amount = new Random().nextDouble(_risk);

        outFile.write(i + ";" + (Math.round(_amount * 100.0) / 100.0));
        outFile.newLine();

        // every month
        if (i % 30 == 0) {
          MainFrame.walletMonthlyIncome();
        }
      }

      // if risk is low or medium, then you must be insured of your profits
      if (risk.equals("Low") && (_amount < (0.7 * amount))) {
        // you can't lose less than the 70% of your inv.
        _amount = amount * 0.7;

      } else if (risk.equals("Medium") && (_amount < (0.4 * amount))) {
        // you can't lose less than the 40% of your inv.
        _amount = amount * 0.4;
      }

      // final import
      outFile.write((_period + 1) + ";" + (Math.round(_amount * 100.0) / 100.0));

    } catch (IOException e) {
      System.err.println("investment file writing failure");
    }

    // profit transaction
    MainFrame.getSessionUser()
        .getBankAccount()
        .addTransaction(
            new Transaction(
                (Math.round(_amount * 100.0) / 100.0),
                LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")),
                InternalFrame.investmentProfitTransactionDefault));

    balance += Math.round(_amount * 100.0) / 100.0;

    MainFrame.getSessionUser().updateTrend();

    return true;
  }

  // read every trans. written in transRecord.csv
  public void readTransactions() {
    try (BufferedReader inFile =
        new BufferedReader(new FileReader(MainFrame.getSessionUser().getTransRecordFile()))) {
      String line;
      while ((line = inFile.readLine()) != null) {
        if (!line.isEmpty() && Character.isDigit(line.charAt(0))) {
          System.out.println("transaction read");

          transactions.add(
              new Transaction(
                  Integer.parseInt(line.split(";")[0]), // ID
                  Double.parseDouble(line.split(";")[2]), // amount
                  line.split(";")[1], // date
                  line.split(";")[3])); // description
        }
      }

    } catch (IOException e) {
      System.err.println("transaction reading failed");
    }
  }

  public double getBalance() {
    return balance;
  }

  public void setBalance(double s) {
    balance = s;
  }

  public ArrayList<Transaction> getTransactions() {
    return transactions;
  }

  public void addTransaction(Transaction t) {
    transactions.add(t);
  }
}
