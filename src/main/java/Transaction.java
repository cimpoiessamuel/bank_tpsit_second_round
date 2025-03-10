import java.io.*;

public class Transaction {
  private final double amount;
  private final String date;
  private final String description;
  private final int ID;

  private static int IDCounter;

  // creating existing transaction
  public Transaction(int ID, double amount, String date, String description) {
    this.amount = amount;
    this.date = date;
    this.description = description;
    this.ID = ID;
  }

  // creating new transaction
  public Transaction(double amount, String date, String description) {

    this.amount = amount;
    this.date = date;
    this.description = description;

    //
    IDCounter++;

    //
    this.ID = IDCounter;

    //
    try (BufferedWriter outFile =
        new BufferedWriter(new FileWriter(MainFrame.getSessionUser().getTransRecordFile(), true))) {
      //
      outFile.write(ID + ";" + date + ";" + amount + ";" + description);
      outFile.newLine();

      //
      outFile.flush();

      System.out.println("transaction written");

    } catch (IOException e) {
      System.err.println("transactions writing failed");
    }
  }

  public double getAmount() {
    return amount;
  }

  public String getDescription() {
    return description;
  }

  public int getID() {
    return ID;
  }

  public static int getIDCounter() {
    return IDCounter;
  }

  public static void setIDCounter(int IDC) {
    IDCounter = IDC;
  }

  @Override
  public String toString() {
    return "<html><b>Description:</b><br>"
        + description
        + "<br><br><b>Date: </b>"
        + date
        + "<br><b>ID: </b>"
        + ID
        + "</html>";
  }
}
