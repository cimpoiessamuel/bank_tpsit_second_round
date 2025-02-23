import java.io.*;

public class User {
  private String name;
  private String surname;
  private double wallet;
  private BankAccount bankAccount;
  private String username;
  private String password;
  private final String ID;
  private static int userCounterID = 1000;
  private File stats; // data saves for each registered user

  //    User() {
  //        name = null;
  //        surname = null;
  //        wallet = 0.0;
  //        bankAccount = new BankAccount();
  //        username = null;
  //        password = null;
  //        this.ID = String.valueOf(userCounterID);
  //        userCounterID++;
  //    }

  User(String name, String surname, String username, String password) {
    this.name = name;
    this.surname = surname;
    this.username = username;
    this.password = password;
    this.bankAccount = new BankAccount();
    this.ID = String.valueOf(userCounterID);
    userCounterID++;

    // users directory path
    File users_dir = new File("src/main/resources/users");

    // if dir doesn't exist, then create
    if (users_dir.mkdir()) {
      System.out.println("users dir created");
    } else {
      System.out.println("users dir already exists");
    }

    this.stats =
        new File(
            "src/main/resources/users/"
                + this.name
                + "-"
                + this.surname
                + "-"
                + this.username
                + ".csv");

    // for each user, a stats file is defined
    try {
      //
      if (this.stats.createNewFile()) {
        try (BufferedWriter outFile = new BufferedWriter(new FileWriter(this.stats))) {
          //
          outFile.write("balance;" + bankAccount.getBalance());
          outFile.write("\nwallet;" + wallet);
          outFile.write("\nname;" + name);
          outFile.write("\nsurname;" + surname);
        }

        System.out.println("users stats created");
      } else {
        System.out.println("users stats already exists");
      }
    } catch (IOException e) {
      System.err.println("user stats error");
    }
  }

  User(String name, String surname, String username, String password, File stats) {
    this(name, surname, username, password);
    this.stats = stats;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getSurname() {
    return surname;
  }

  public void setSurname(String surname) {
    this.surname = surname;
  }

  public double getWallet() {
    return wallet;
  }

  public void setWallet(double wallet) {
    this.wallet = wallet;
  }

  public File getFile() {
    return stats;
  }

  public String getUsername() {
    return username;
  }

  public String getID() {
    return ID;
  }

  public BankAccount getBankAccount() {
    return bankAccount;
  }

  public void setBankAccount(BankAccount b) {
    this.bankAccount = b;
  }

  public void setStats(File s) {
    this.stats = s;
  }

  public String toString() {
    return surname + ", " + name;
  }
}
