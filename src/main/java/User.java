import java.io.*;

public class User {
  private final String name;
  private final String surname;
  private final String username;
  private final String password;
  private double wallet;
  private BankAccount bankAccount;

  private final File directory; //
  private final File stats; // data saves for each registered user

  User(String name, String surname, String username, String password) {

    this.name = name;
    this.surname = surname;
    this.username = username;
    this.password = password;

    //
    this.directory =
        new File(
            "src/main/resources/users/" + this.name + "-" + this.surname + "-" + this.username);

    // if user dir doesn't exist, then create
    if (directory.mkdirs()) {
      System.out.println("user dir created");
    } else {
      System.out.println("users dir already exists");
    }

    //
    this.stats =
        new File(
            directory.getAbsolutePath()
                + "/"
                + this.name
                + "-"
                + this.surname
                + "-"
                + this.username
                + ".csv");

    // for each user, a stats file is defined
    try {
      //
      if (stats.createNewFile()) {
        try (BufferedWriter outFile = new BufferedWriter(new FileWriter(this.stats))) {

          //
          this.bankAccount = new BankAccount();
          this.wallet = 0.0;

          //
          outFile.write("balance;" + bankAccount.getBalance());
          outFile.write("\nwallet;" + wallet);
          outFile.write("\nname;" + name);
          outFile.write("\nsurname;" + surname);
        }

        System.out.println("users stats created");
      } else {
        //
        String balanceValue;
        String walletValue;

        //
        try (BufferedReader inFile = new BufferedReader(new FileReader(this.stats))) {
          balanceValue = inFile.readLine().split(";")[1];
          walletValue = inFile.readLine().split(";")[1];
        }

        this.bankAccount = new BankAccount(Double.parseDouble(balanceValue));
        this.wallet = Double.parseDouble(walletValue);

        System.out.println("users stats already exists");
      }
    } catch (IOException e) {
      System.err.println("user stats error");
    }
  }

  public void monthlyIncome() {
    wallet += 100;
  }

  public double getWallet() {
    return wallet;
  }

  public void setWallet(double wallet) {
    this.wallet = wallet;
  }

  public String getName() {
    return name;
  }

  public String getSurname() {
    return surname;
  }

  public String getUsername() {
    return username;
  }

  public File getDirectory() {
    return directory;
  }

  public File getFile() {
    return stats;
  }

  public BankAccount getBankAccount() {
    return bankAccount;
  }

  @Override
  public String toString() {
    return surname + ", " + name;
  }
}
