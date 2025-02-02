public class User {
    private String name;
    private String surname;
    private String ID;
    private double wallet;

    private static int userCounterID = 0;


    User() {
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
}
