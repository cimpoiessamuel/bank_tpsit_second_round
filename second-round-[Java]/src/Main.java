import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        User u = new User("pino", "d'angiò");
        BankAccount ba = new BankAccount(u);

        LoginGUI login = new LoginGUI();
    }
}