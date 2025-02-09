//import com.formdev.flatlaf.FlatDarkLaf;
//import com.formdev.flatlaf.FlatLightLaf;

public class Main {

    public static void main(String[] args) {
        //FlatDarkLaf.setup();

        User u = new User("pino", "angelo");
        BankAccount ba = new BankAccount(u);

        StartApp sa = new StartApp(ba);
    }
}