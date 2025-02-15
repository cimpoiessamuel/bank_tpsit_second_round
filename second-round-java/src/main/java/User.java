public class User {
    private String name;
    private String surname;
    private double wallet;
    private final BankAccount bankAccount;
    private String username;
    private String password;
    private final String ID;
    private static int userCounterID = 1000;


    User() {
        name = null;
        surname = null;
        wallet = 0.0;
        bankAccount = null;
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

    public String toString() {
        return surname + ", " + name;
    }
}
