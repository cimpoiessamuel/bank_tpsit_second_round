public class BankAccount {
    private double balance;
    private User user;
    private String ID;

    private static int bankAccountCounterID = 1000;

    BankAccount() {
        this.user = null;
        this.balance = 0.0;
        this.ID = String.valueOf(bankAccountCounterID);
        bankAccountCounterID++;
    }

    BankAccount(User user) {
        this.user = user;
        this.balance = 0.0;
        this.ID = String.valueOf(bankAccountCounterID);
        bankAccountCounterID++;
    }

    BankAccount(User user, double balance) {
        this.user = user;
        this.balance = balance;
        this.ID = String.valueOf(bankAccountCounterID);
        bankAccountCounterID++;
    }
}
