public class User {
    private String name;
    private String surname;

    private double wallet;

    private String username;
    private String password;

    private final String ID;

    private static int userCounterID = 1000;


    User() {
        name = null;
        surname = null;

        wallet = 0.0;

        username = null;
        password = null;

        this.ID = String.valueOf(userCounterID);
        userCounterID++;
    }

    User(String name, String surname) {
        this.name = name;
        this.surname = surname;
        this.ID = String.valueOf(userCounterID);
        userCounterID++;
    }

    public String toString() {
        return surname + ", " + name;
    }

    public String getID() {
        return ID;
    }
}
