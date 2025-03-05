import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class BankAccountTest {

  @Test
  void deposit() {
    User user = new User("name", "surname", "username", "password");
    MainFrame.setSessionUser(user);

    double depositedValue = 10.0;

    // wallet value at start is 0
    // wallet = 20
    user.setWallet(depositedValue);

    double previousBalance = user.getBankAccount().getBalance();
    double previousWallet = user.getWallet();

    // balance += 20 ; wallet = 0
    boolean depositResult = user.getBankAccount().deposit(depositedValue);

    double newBalance = user.getBankAccount().getBalance();
    double newWallet = user.getWallet();

    // wallet value
    assertEquals(
        previousWallet - depositedValue, newWallet, "After depositing 20, wallet should result 0");

    // deposit state
    assertTrue(depositResult, "Deposit should result successfully done");

    // balance value
    assertEquals(
        previousBalance + depositedValue,
        newBalance,
        "Previous balance plus the deposited amount(20) should result as the new balance");
  }

  @Test
  void withdraw() {
    User user = new User("name", "surname", "username", "password");
    MainFrame.setSessionUser(user);

    double withdrawValue = 10.0;

    double previousBalance = user.getBankAccount().getBalance();
    double previousWallet = user.getWallet();

    boolean withdrawResult = user.getBankAccount().withdraw(withdrawValue);

    double newBalance = user.getBankAccount().getBalance();
    double newWallet = user.getWallet();

    // wallet value
    assertEquals(
        previousWallet + withdrawValue,
        newWallet,
        "Wallet starts at 0, after withdrawing 20 should result 20");

    // withdraw state
    assertTrue(withdrawResult, "Withdraw should result successfully done");

    // balance value
    assertEquals(
        previousBalance - withdrawValue,
        newBalance,
        "Previous balance minus withdraw amount(20) should result as the new balance");
  }

  @Test
  void lowRiskInvest() {
    User user = new User("name", "surname", "username", "password");
    MainFrame.setSessionUser(user);

    double investedValue = 10.0;

    double previousBalance = user.getBankAccount().getBalance();

    boolean investmentStatus = user.getBankAccount().invest(investedValue, "Short", "Low");

    double investmentResult =
        user.getBankAccount().getBalance() - (previousBalance - investedValue);

    // inv. status
    assertTrue(investmentStatus, "Investment should result successfully done");

    // investment result
    assertTrue(
        (investmentResult >= investedValue * 0.7),
        "Due to low risk, investment result should not be less than the invested amount * 0.7");
  }

  @Test
  void mediumRiskInvest() {
    User user = new User("name", "surname", "username", "password");
    MainFrame.setSessionUser(user);

    double investedValue = 10.0;

    double previousBalance = user.getBankAccount().getBalance();

    boolean investmentStatus = user.getBankAccount().invest(investedValue, "Short", "Medium");

    double investmentResult =
        user.getBankAccount().getBalance() - (previousBalance - investedValue);

    // inv. status
    assertTrue(investmentStatus, "Investment should result successfully done");

    // investment result
    assertTrue(
        (investmentResult >= investedValue * 0.4),
        "Due to medium risk, investment result should not be less than the invested amount * 0.4");
  }
}
