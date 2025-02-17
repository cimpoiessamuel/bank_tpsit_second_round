import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.File;
import java.io.IOException;

public class User {
    private String name;
    private String surname;
    private double wallet;
    private BankAccount bankAccount;
    private String username;
    private String password;
    private final String ID;
    private static int userCounterID = 1000;

    private File stats;


    User() {
        name = null;
        surname = null;
        wallet = 0.0;
        bankAccount = new BankAccount();
        username = null;
        password = null;
        this.ID = String.valueOf(userCounterID);
        userCounterID++;
    }

    User(String name, String surname, BankAccount ba) {
        this.name = name;
        this.surname = surname;
        this.bankAccount = ba;
        this.ID = String.valueOf(userCounterID);
        userCounterID++;

        this.stats = new File("second-round-java/src/main/resources/users/" + this.ID + "-" + this.name + "-" + this.surname + ".txt");


        // for each user, a file stats file is defined
        try (BufferedWriter outFile = new BufferedWriter(new FileWriter(this.stats))) {

            //
            if (this.stats.createNewFile()) {
                System.out.println("users stats created");

            } else {
                System.out.println("users stats already exists");
            }
        } catch (IOException e) {
            System.err.println("user stats error");
        }
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

    public String getID() {
        return ID;
    }

    public BankAccount getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(BankAccount b) {
        this.bankAccount = b;
    }

    public String toString() {
        return surname + ", " + name;
    }
}
