import java.io.*;

public class Transaction {
  private final double amount;
  private final String date;
  private final String description;

  // for creating existing transactions
  public Transaction(double amount, String date, String description) {
    this.amount = amount;
    this.date = date;
    this.description = description;
  }

  // for creating new transactions
  public Transaction(double amount, String date, String description, User user) {
    this.amount = amount;
    this.date = date;
    this.description = description;

    try (BufferedWriter outFile = new BufferedWriter(new FileWriter(user.getFile(), true))) {
      //
      outFile.write(date + ";" + amount + ";" + description);

    } catch (IOException e) {
      System.err.println("transactions writing failed");
    }
  }
}
