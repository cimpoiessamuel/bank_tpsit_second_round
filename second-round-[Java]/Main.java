public class Main {

    public static void main(String[] args) {
        User u = new User("pino", "d'angiò");
        BankAccount ba = new BankAccount(u);
        GUI gui = new GUI(ba);
    }
}