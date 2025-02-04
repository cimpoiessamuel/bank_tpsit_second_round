//import com.formdev.flatlaf.FlatDarkLaf;
//import com.formdev.flatlaf.FlatLightLaf;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        //FlatDarkLaf.setup();

        User u = new User("pino", "angelo");
        BankAccount ba = new BankAccount(u);

        LoginGUI login = new LoginGUI();
    }
}