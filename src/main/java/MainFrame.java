import com.formdev.flatlaf.FlatDarkLaf;

import javax.swing.*;
import java.awt.*;

import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;

public class MainFrame {

  public static User sessionUser;

  // main-frame instanced fot effective use
  MainFrame() {

    // default app look
    //        try {
    //            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
    //        } catch (Exception e) {
    //            e.printStackTrace();
    //        }

    // light theme
    //        if (!FlatLightLaf.setup()) {
    //            System.err.println("Laf Initialization failed");
    //        }

    // black theme
    if (!FlatDarkLaf.setup()) {
      System.err.println("Laf Initialization failed");
    }

    // initialising main frame
    JFrame mainFrame = new JFrame("Bank App");

    mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    mainFrame.setSize(1600, 1024);
    mainFrame.setLayout(null);
    mainFrame.setLocationRelativeTo(null);
    mainFrame.setIconImage(
        new ImageIcon("src/main/resources/images/vBank2-rounded-16x16.png").getImage());

    // main frame font
    Font font = new Font("Arial", Font.PLAIN, 20);

    // current balance display
    JTextField balanceDisplay = new JTextField();

    balanceDisplay.setText(String.valueOf(sessionUser.getBankAccount().getBalance()));
    balanceDisplay.setBounds(800, 25, 100, 50);
    balanceDisplay.setFont(font);
    balanceDisplay.setEditable(false);
    balanceDisplay.setFocusable(false);

    // balance modifier
    JTextField balanceModifier = new JTextField();

    balanceModifier.setBounds(512, 800, 100, 50);
    balanceModifier.setFont(font);
    balanceModifier.setEditable(true);
    balanceModifier.setFocusable(true);

    // deposit button
    JButton deposit = new JButton("Deposit");

    deposit.setFocusable(false);
    deposit.setBounds(150, 430, 100, 50);

    // withdraw button
    JButton withdraw = new JButton("Withdraw");

    withdraw.setFocusable(false);
    withdraw.setBounds(250, 430, 100, 50);

    // profile ImageIcon
    ImageIcon profileAvatar =
        new ImageIcon("src/main/resources/images/default_user_avatar_100x100_rounded.png");

    // profile section container
    JLabel profileLabel = new JLabel();

    profileLabel.setBounds(10, 10, 500, 300);
    profileLabel.setText(sessionUser.toString());
    profileLabel.setIcon(profileAvatar);
    profileLabel.setHorizontalTextPosition(JLabel.RIGHT);
    profileLabel.setVerticalTextPosition(JLabel.CENTER);

    // adding components to the main frame
    mainFrame.add(profileLabel);

    mainFrame.add(deposit);
    mainFrame.add(withdraw);

    mainFrame.add(balanceDisplay);
    mainFrame.add(balanceModifier);

    // setting the internal-frame visible
    mainFrame.setVisible(true);
  }

  // main-frame instanced for authentication
  MainFrame(File users_info) {

    // default app look
    //        try {
    //            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
    //        } catch (Exception e) {
    //            e.printStackTrace();
    //        }

    // light theme
    //        if (!FlatLightLaf.setup()) {
    //            System.err.println("Laf Initialization failed");
    //        }

    // black theme
    if (!FlatDarkLaf.setup()) {
      System.err.println("Laf Initialization failed");
    }

    // initialising main frame
    JFrame mainFrame = new JFrame("Volksbank Authentication");

    mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    mainFrame.setSize(1600, 1024);
    mainFrame.setLayout(null);
    mainFrame.setLocationRelativeTo(null);
    mainFrame.setIconImage(
        new ImageIcon("src/main/resources/images/vBank2-rounded-16x16.png").getImage());

    // set main-frame visible
    mainFrame.setVisible(true);

    // check if there is a default user
    try (BufferedReader inFile = new BufferedReader(new FileReader(users_info))) {

      // if there is not a defined user, then launch the login window
      if (inFile.readLine().equals("default;")) {

        // instantiating the main-sub-frame
        InternalFrame internalFrame = new InternalFrame(mainFrame, users_info);
      }
    } catch (IOException e) {
      System.err.println("error");
    }
  }

  public static User getSessionUser() {
    return sessionUser;
  }

  public static void setUser(User u) {
    sessionUser = u;
  }

  //    @Override
  //    public void actionPerformed(ActionEvent e) {
  //        if (e.getSource() == deposit) {
  //            bankAcc.deposit(Double.parseDouble(balanceModifier.getText()));
  //            balanceDisplay.setText(String.valueOf(bankAcc.getBalance()));
  //        }
  //
  //        if (e.getSource() == withdraw) {
  //            bankAcc.withdraw(Double.parseDouble(balanceModifier.getText()));
  //            balanceDisplay.setText(String.valueOf(bankAcc.getBalance()));
  //        }
  //    }
}
