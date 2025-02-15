public class Main {

    public static void main(String[] args) {

        BankAccount ba = new BankAccount();
        User u = new User("pino", "angelo", ba);

        StartApp sa = new StartApp(u);
    }
}