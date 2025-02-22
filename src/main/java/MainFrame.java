import com.formdev.flatlaf.FlatDarkLaf;
import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.*;

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
    mainFrame.setLayout(new BorderLayout());
    mainFrame.setLocationRelativeTo(null);
    mainFrame.setIconImage(
        new ImageIcon("src/main/resources/images/vBank2-rounded-16x16.png").getImage());

    // main frame font
    Font font = new Font("Arial", Font.PLAIN, 20);

    // profile ImageIcon
    ImageIcon profileAvatar =
        new ImageIcon("src/main/resources/images/default_user_avatar_100x100_rounded.png");

    // profile label
    JLabel profileLabel = new JLabel();
    profileLabel.setBounds(10, 10, 500, 300);
    profileLabel.setText(sessionUser.toString());
    profileLabel.setIcon(profileAvatar);
    profileLabel.setHorizontalTextPosition(JLabel.RIGHT);
    profileLabel.setVerticalTextPosition(JLabel.CENTER);

    // log-out button
    JButton logoutButton = new JButton("Log out");
    logoutButton.setFont(font);

    //
    JPanel profilePanel = new JPanel();
    profilePanel.setLayout(new FlowLayout(FlowLayout.LEFT));
    profilePanel.setBackground(Color.RED);

    profilePanel.add(profileLabel);
    profilePanel.add(logoutButton);

    // adding components to the main frame
    mainFrame.add(profilePanel, BorderLayout.NORTH);

    // setting the main-frame visible
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
