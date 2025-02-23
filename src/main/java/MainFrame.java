import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatLightLaf;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.*;

public class MainFrame {

  public static User sessionUser;

  // main-frame instanced for effective use
  MainFrame() {

    // theme
    if (!setTheme("white")) {
      System.err.println("theme init failure");
    }

    // initialising main frame
    JFrame mainFrame = new JFrame("Volksbank App");
    mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    mainFrame.setSize(1600, 1024);
    mainFrame.setLayout(new BorderLayout());
    mainFrame.setLocationRelativeTo(null);
    mainFrame.setIconImage(
        new ImageIcon("src/main/resources/images/vBank2-rounded.png").getImage());

    // main frame font
    Font font = new Font("Arial", Font.PLAIN, 20);

    // profile ImageIcon
    ImageIcon profileAvatar =
        new ImageIcon("src/main/resources/images/default_user_avatar_80x80_rounded.png");

    // profile label
    JLabel profileLabel = new JLabel();
    profileLabel.setText(sessionUser.toString());
    profileLabel.setIcon(profileAvatar);
    profileLabel.setFont(font);
    profileLabel.setHorizontalTextPosition(JLabel.RIGHT);
    profileLabel.setVerticalTextPosition(JLabel.CENTER);

    //
    JPanel actionsPanel = new JPanel();
    actionsPanel.setLayout(new GridBagLayout());
    actionsPanel.setBackground(new Color(0, 0, 0, 0));
    actionsPanel.setPreferredSize(new Dimension(mainFrame.getWidth(), mainFrame.getHeight() / 9));

    //
    GridBagConstraints gbc = new GridBagConstraints();
    gbc.fill = GridBagConstraints.BOTH; // grows horizontally
    gbc.insets = new Insets(3, 3, 3, 3); // padding
    gbc.weightx = 1;
    gbc.weighty = 1;

    // deposit button
    JButton depositButton = new JButton("Make a deposit");
    depositButton.setFont(font);
    gbc.gridx = 0; // col
    gbc.gridy = 0; // row
    actionsPanel.add(depositButton, gbc);

    // invest button
    JButton investButton = new JButton("Make an investment");
    investButton.setFont(font);
    gbc.gridx = 1; // col
    actionsPanel.add(investButton, gbc);

    // log out button
    JButton logoutButton = new JButton("Log out");
    logoutButton.setFont(font);
    gbc.gridx = 2; // col
    actionsPanel.add(logoutButton, gbc);

    // withdraw button
    JButton withDrawButton = new JButton("Make a withdraw");
    withDrawButton.setFont(font);
    gbc.gridx = 0; // col
    gbc.gridy = 1; // row
    actionsPanel.add(withDrawButton, gbc);

    // transactions button
    JButton showTransactionsButton = new JButton("Show transactions");
    showTransactionsButton.setFont(font);
    gbc.gridx = 1; // col
    actionsPanel.add(showTransactionsButton, gbc);

    // exit button
    JButton exitButton = new JButton("Exit");
    exitButton.setFont(font);
    gbc.gridx = 2; // col
    actionsPanel.add(exitButton, gbc);

    // main panel, image as background
    JPanel mainPanel =
        new JPanel() {
          @Override
          protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            ImageIcon icon = new ImageIcon("src/main/resources/images/vBank1-nobg.png");
            g.drawImage(
                icon.getImage(), 0, 0, mainFrame.getWidth(), mainFrame.getHeight(), mainFrame);
          }
        };

    mainPanel.setLayout(new BorderLayout());

    mainPanel.add(profileLabel, BorderLayout.NORTH);
    mainPanel.add(actionsPanel, BorderLayout.SOUTH);

    // adding components to the main frame
    mainFrame.add(mainPanel);

    // setting the main-frame visible
    mainFrame.setVisible(true);

    //
    depositButton.addActionListener(
        new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
            InternalFrame internalFrame = new InternalFrame(mainFrame);
          }
        });
  }

  // main-frame instanced for authentication
  MainFrame(File users_info) {

    // theme
    if (!setTheme("black")) {
      System.err.println("theme init failure");
    }

    // initialising main frame
    JFrame mainFrame = new JFrame("Volksbank Authentication");

    mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    mainFrame.setSize(1600, 1024);
    mainFrame.setLayout(null);
    mainFrame.setLocationRelativeTo(null);
    mainFrame.setIconImage(
        new ImageIcon("src/main/resources/images/vBank2-rounded.png").getImage());

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

  public static void setSessionUser(User u) {
    sessionUser = u;
  }

  private boolean setTheme(String theme) {
    switch (theme) {
      case "black":
        {
          if (!FlatDarkLaf.setup()) {
            return false;
          }
          break;
        }

      case "white":
        {
          if (!FlatLightLaf.setup()) {
            return false;
          }
          break;
        }

      default:
        {
          try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
          } catch (Exception e) {
            System.out.println("default theme init failed");
          }
        }
    }

    return true;
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
