import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class User {
  private final String name;
  private final String surname;
  private final String username;
  private final String password;
  private double wallet;
  private BankAccount bankAccount;

  private final File directory; // every user has its own directory name 'name-surname-username'
  private final File stats; // data saved for each registered user (balance and wallet)
  private final File transRecord; // every transaction is written here
  private final File trendRecord; // every balance and wallet change is written here

  User(String name, String surname, String username, String password) {

    this.name = name;
    this.surname = surname;
    this.username = username;
    this.password = password;

    directory =
        new File(
            "src/main/resources/users/" + this.name + "-" + this.surname + "-" + this.username);

    stats = new File(directory.getAbsolutePath() + "/stats.csv");

    transRecord = new File(directory.getAbsolutePath() + "/trans-record.csv");

    trendRecord = new File(directory.getAbsolutePath() + "/trend-record.csv");

    try {
      // if user dir doesn't exist, then create
      if (directory.mkdirs()) {
        System.out.println("user dir created");

        // for each user, a stats file is defined
        if (stats.createNewFile()) {
          System.out.println("user stats created");

          try (BufferedWriter outFile = new BufferedWriter(new FileWriter(stats))) {

            // new user -> new bankAccount (random value)
            bankAccount = new BankAccount();
            wallet = 0.0;

            outFile.write("balance;" + bankAccount.getBalance());
            outFile.write("\nwallet;" + wallet);
            outFile.write("\nname;" + name);
            outFile.write("\nsurname;" + surname);

            outFile.flush();
          }
        }

        // creating transRecord
        if (transRecord.createNewFile()) {
          System.out.println("user trans-record created");
        }

        // creating trendRecord
        if (trendRecord.createNewFile()) {
          System.out.println("user trend-record created");

          // legend -> x(date);y(balance);z(wallet)
          try (BufferedWriter outFile = new BufferedWriter(new FileWriter(trendRecord))) {
            outFile.write("x;y;z");
            outFile.newLine();
            outFile.write(
                LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM HH:mm:ss"))
                    + ";"
                    + getBankAccount().getBalance()
                    + ";"
                    + getWallet());

            outFile.newLine();
          }
        }

      } else {
        System.out.println("user dir already exists");
        System.out.println("user stats already exists");
        System.out.println("user trans-record already exists");

        String balanceValue;
        String walletValue;

        // read existing values for balance and wallet
        try (BufferedReader inFile = new BufferedReader(new FileReader(stats))) {
          balanceValue = inFile.readLine().split(";")[1];
          walletValue = inFile.readLine().split(";")[1];
        }

        bankAccount = new BankAccount(Double.parseDouble(balanceValue));
        wallet = Double.parseDouble(walletValue);
      }
    } catch (IOException e) {
      System.err.println("new user data writing failed");
    }
  }

  // update the trendRecord
  public void updateTrend() {
    try (BufferedWriter outFile = new BufferedWriter(new FileWriter(trendRecord, true))) {
      outFile.write(
          LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM HH:mm:ss"))
              + ";"
              + Math.round(getBankAccount().getBalance() * 100.0) / 100.0
              + ";"
              + Math.round(getWallet() * 100.0) / 100.0);

      outFile.newLine();

    } catch (IOException e) {
      System.err.println("balance update failed");
    }
  }

  public void monthlyIncome() {
    wallet += 100;

    updateTrend();
  }

  public double getWallet() {
    return wallet;
  }

  public void setWallet(double wallet) {
    this.wallet = Math.round(wallet * 100.0) / 100.0;
  }

  public File getDirectory() {
    return directory;
  }

  public File getStatsFile() {
    return stats;
  }

  public File getTransRecordFile() {
    return transRecord;
  }

  public BankAccount getBankAccount() {
    return bankAccount;
  }

  @Override
  public String toString() {
    return surname + ", " + name;
  }
}
