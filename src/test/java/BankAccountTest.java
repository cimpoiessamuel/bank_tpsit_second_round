import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class BankAccountTest {

  // DELETE EVERY USER FOLDER BEFORE RUN TEST

  @Test
  void depositWithValidImport() {
    User user = new User("name", "surname", "username", "password");
    MainFrame.setSessionUser(user);

    double depositedValue = 10.0;

    // wallet value at start is 0
    // wallet = 10
    user.setWallet(depositedValue);

    double previousBalance = user.getBankAccount().getBalance();
    double previousWallet = user.getWallet();

    // balance += 10 ; wallet = 0
    boolean depositResult = user.getBankAccount().deposit(depositedValue);

    double newBalance = user.getBankAccount().getBalance();
    double newWallet = user.getWallet();

    // wallet value
    assertEquals(
        previousWallet - depositedValue, newWallet, "After depositing 10, wallet should result 0");

    // deposit state
    assertTrue(depositResult, "Deposit should result successfully done");

    // balance value
    assertEquals(
        previousBalance + depositedValue,
        newBalance,
        "Previous balance plus the deposited amount(10) should result as the new balance");
  }

  @Test
  void depositWithInvalidImport() {
    User user = new User("name", "surname", "username", "password");
    MainFrame.setSessionUser(user);

    double depositedValue = -10.0;

    user.setWallet(depositedValue);

    boolean depositResult = user.getBankAccount().deposit(depositedValue);

    // deposit state
    assertFalse(depositResult, "Deposit should result not successfully done");
  }

  @Test
  void depositWithNullImport() {
    User user = new User("name", "surname", "username", "password");
    MainFrame.setSessionUser(user);

    double depositedValue = 0.0;

    user.setWallet(depositedValue);

    boolean depositResult = user.getBankAccount().deposit(depositedValue);

    // deposit state
    assertFalse(depositResult, "Deposit should result not successfully done");
  }

  @Test
  void withdrawWithValidImport() {
    User user = new User("name", "surname", "username", "password");
    MainFrame.setSessionUser(user);

    double withdrawValue = 10.0;

    MainFrame.getSessionUser().getBankAccount().setBalance(10.0);

    double previousBalance = user.getBankAccount().getBalance();
    double previousWallet = user.getWallet();

    boolean withdrawResult = user.getBankAccount().withdraw(withdrawValue);

    double newBalance = user.getBankAccount().getBalance();
    double newWallet = user.getWallet();

    // wallet value
    assertEquals(previousWallet + withdrawValue, newWallet);

    // withdraw state
    assertTrue(withdrawResult, "Withdraw should result successfully done");

    // balance value
    assertEquals(previousBalance - withdrawValue, newBalance);
  }

  @Test
  void withdrawWithInvalidImport() {
    User user = new User("name", "surname", "username", "password");
    MainFrame.setSessionUser(user);

    double withdrawValue = -10.0;

    MainFrame.getSessionUser().getBankAccount().setBalance(10.0);

    boolean withdrawResult = user.getBankAccount().withdraw(withdrawValue);

    // withdraw state
    assertFalse(withdrawResult, "Withdraw should result not successfully done");
  }

  @Test
  void withdrawWithNullImport() {
    User user = new User("name", "surname", "username", "password");
    MainFrame.setSessionUser(user);

    double withdrawValue = 0.0;

    MainFrame.getSessionUser().getBankAccount().setBalance(10.0);

    boolean withdrawResult = user.getBankAccount().withdraw(withdrawValue);

    // withdraw state
    assertFalse(withdrawResult, "Withdraw should result not successfully done");
  }

  @Test
  void investWithValidImport() {
    User user = new User("name", "surname", "username", "password");
    MainFrame.setSessionUser(user);

    double investedValue = 10.0;

    MainFrame.getSessionUser().getBankAccount().setBalance(10.0);

    boolean investmentStatus = user.getBankAccount().invest(investedValue, "Short", "Low");

    // inv. status
    assertTrue(investmentStatus, "Investment should result successfully done");
  }

  @Test
  void investWithInvalidImport() {
    User user = new User("name", "surname", "username", "password");
    MainFrame.setSessionUser(user);

    double investedValue = -10.0;

    MainFrame.getSessionUser().getBankAccount().setBalance(10.0);

    boolean investmentStatus = user.getBankAccount().invest(investedValue, "Short", "Low");

    // inv. status
    assertFalse(investmentStatus, "Investment should result not successfully done");
  }

  @Test
  void investWithNullImport() {
    User user = new User("name", "surname", "username", "password");
    MainFrame.setSessionUser(user);

    double investedValue = 0.0;

    MainFrame.getSessionUser().getBankAccount().setBalance(10.0);

    boolean investmentStatus = user.getBankAccount().invest(investedValue, "Short", "Low");

    // inv. status
    assertFalse(investmentStatus, "Investment should result not successfully done");
  }

  @Test
  void lowRiskInvest() {
    User user = new User("name", "surname", "username", "password");
    MainFrame.setSessionUser(user);

    double investedValue = 10.0;

    MainFrame.getSessionUser().getBankAccount().setBalance(10.0);

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

    MainFrame.getSessionUser().getBankAccount().setBalance(10.0);

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
