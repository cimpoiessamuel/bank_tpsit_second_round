import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatLightLaf;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.*;

public class MainFrame {

  private static User sessionUser;

  // main-frame instanced for effective use
  MainFrame() {

    // theme
    if (!setTheme("l")) {
      System.err.println("theme init failure");
    }

    // initialising main frame
    JFrame mainFrame = new JFrame("Volksbank App");
    mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    mainFrame.setSize(1600, 1024);
    mainFrame.setLayout(new BorderLayout());
    mainFrame.setLocationRelativeTo(null);
    mainFrame.setMinimumSize(new Dimension(1280, 800));
    mainFrame.setIconImage(
        new ImageIcon("src/main/resources/images/vBank2-rounded.png").getImage());

    // main frame font
    Font font = InternalFrame.fontInit(20);

    // profile panel
    JPanel profilePanel = new JPanel();
    profilePanel.setLayout(new BorderLayout());
    profilePanel.setBackground(new Color(0, 0, 0, 0));
    profilePanel.setPreferredSize(new Dimension(mainFrame.getWidth(), mainFrame.getHeight() / 9));

    // profile label
    JLabel profileLabel = new JLabel();
    profileLabel.setText(sessionUser.toString());
    profileLabel.setIcon(
        new ImageIcon("src/main/resources/images/default_user_avatar_80x80_rounded.png"));
    profileLabel.setFont(font);
    profileLabel.setHorizontalTextPosition(JLabel.RIGHT);
    profileLabel.setVerticalTextPosition(JLabel.CENTER);

    //
    JPanel southPanel = new JPanel();
    southPanel.setLayout(new FlowLayout());

    // balance label
    JLabel balanceDisplay = new JLabel();
    balanceDisplay.setText(
        "Balance   "
            + sessionUser.getBankAccount().getBalance()
            + "€           Wallet   "
            + sessionUser.getWallet()
            + "€");
    balanceDisplay.setFont(font);
    balanceDisplay.setHorizontalAlignment(JLabel.CENTER);

    //
    JButton skipMonthButton = new JButton("Skip to next month");
    skipMonthButton.setFont(InternalFrame.fontInit(16));
    skipMonthButton.setPreferredSize(new Dimension(200, 45));

    //
    JLabel counterLabel = new JLabel();
    counterLabel.setFont(font);

    //
    southPanel.add(balanceDisplay);
    southPanel.add(Box.createRigidArea(new Dimension(100, 0))); //
    southPanel.add(skipMonthButton);
    southPanel.add(Box.createRigidArea(new Dimension(50, 0))); //
    southPanel.add(counterLabel);

    // adding to profile panel
    profilePanel.add(profileLabel, BorderLayout.WEST);
    profilePanel.add(southPanel, BorderLayout.SOUTH);

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
            g.drawImage(
                new ImageIcon("src/main/resources/images/vBank1-nobg.png").getImage(),
                0,
                0,
                mainFrame.getWidth(),
                mainFrame.getHeight(),
                mainFrame);
          }
        };

    mainPanel.setLayout(new BorderLayout());

    // adding to main panel
    mainPanel.add(profilePanel, BorderLayout.NORTH);
    mainPanel.add(actionsPanel, BorderLayout.SOUTH);

    //
    mainFrame.setContentPane(mainPanel);

    // setting the main-frame visible
    mainFrame.setVisible(true);

    //
    depositButton.addActionListener(
        new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
            //
            new InternalFrame(mainFrame, balanceDisplay, "Deposit");
          }
        });

    //
    withDrawButton.addActionListener(
        new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
            //
            new InternalFrame(mainFrame, balanceDisplay, "Withdraw");
          }
        });

    //
    investButton.addActionListener(
        new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
            //
            new InternalFrame(mainFrame, balanceDisplay, "Invest");
          }
        });

    //
    showTransactionsButton.addActionListener(
        new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
            //
            new InternalFrame(mainFrame);
          }
        });

    //
    logoutButton.addActionListener(
        new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
            //
            File users_info = new File("src/main/resources/users-info.csv");

            //
            ArrayList<String> fileContent = new ArrayList<>();

            try {
              //
              try (BufferedReader inFile = new BufferedReader(new FileReader(users_info))) {
                //
                String line;
                while ((line = inFile.readLine()) != null) {
                  fileContent.add(line);
                }

                //
                fileContent.set(0, "default;");
              }

              //
              try (BufferedWriter outFile = new BufferedWriter(new FileWriter(users_info, false))) {
                //
                for (String i : fileContent) {
                  outFile.write(i + "\n");
                }
              }
            } catch (IOException exc) {
              System.err.println("log-out default user failure");
            }

            // arresting current main frame
            mainFrame.dispose();

            // instancing new main frame for authentication
            new MainFrame(users_info);
          }
        });

    //
    exitButton.addActionListener(
        new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
            // arresting current main frame
            mainFrame.dispose();
          }
        });

    //
    skipMonthButton.addActionListener(
        new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
            //
            skipMonthButton.setEnabled(false);
            logoutButton.setEnabled(false);
            exitButton.setEnabled(false);
            mainFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

            //
            walletMonthlyIncome();

            //
            balanceDisplay.setText(
                "Balance   "
                    + Math.round(sessionUser.getBankAccount().getBalance() * 100.0) / 100.0
                    + "€           Wallet   "
                    + Math.round(sessionUser.getWallet() * 100.0) / 100.0
                    + "€");

            //
            Timer timer = new Timer();
            TimerTask timerTask =
                new TimerTask() {
                  int seconds = 120;

                  @Override
                  public void run() {
                    if (seconds > 0) {
                      //
                      counterLabel.setText("Time left: " + seconds);

                      System.out.println("remaining: " + seconds);
                      seconds--;
                    } else {

                      //
                      counterLabel.setText("");

                      System.out.println("time done");
                      timer.cancel();

                      //
                      skipMonthButton.setEnabled(true);
                      logoutButton.setEnabled(true);
                      exitButton.setEnabled(true);
                      mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    }
                  }
                };

            //
            timer.scheduleAtFixedRate(timerTask, 0, 1000);
          }
        });
  }

  // main-frame instanced for authentication
  MainFrame(File users_info) {

    // theme
    if (!setTheme("l")) {
      System.err.println("theme init failure");
    }

    // initialising main frame
    JFrame mainFrame = new JFrame("Volksbank Authentication");

    mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    mainFrame.setSize(1600, 1024);
    mainFrame.setLayout(null);
    mainFrame.setLocationRelativeTo(null);
    mainFrame.setMinimumSize(new Dimension(700, 800));
    mainFrame.setIconImage(
        new ImageIcon("src/main/resources/images/vBank2-rounded.png").getImage());

    subWindowPaneInit(mainFrame);

    // set main-frame visible
    mainFrame.setVisible(true);

    // check if there is a default user
    try (BufferedReader inFile = new BufferedReader(new FileReader(users_info))) {

      // if there is not a defined user, then launch the login window
      if (inFile.readLine().equals("default;")) {

        // instantiating the main-sub-frame
        new InternalFrame(mainFrame, users_info);
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
      case "d": // dark theme
        {
          if (!FlatDarkLaf.setup()) {
            return false;
          }
          break;
        }

      case "l": // light theme
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

  // initialising the sub-frames container
  public static void subWindowPaneInit(JFrame mainFrame) {
    mainFrame.setContentPane(new JDesktopPane());
    mainFrame.getContentPane().setBounds(0, 0, mainFrame.getWidth(), mainFrame.getHeight());
    mainFrame.getContentPane().setVisible(true);
  }

  public static void walletMonthlyIncome() {
    //
    sessionUser.monthlyIncome();

    //
    try {
      //
      ArrayList<String> fileContent = new ArrayList<>();

      try (BufferedReader inFile = new BufferedReader(new FileReader(sessionUser.getFile()))) {
        //
        String line;
        while ((line = inFile.readLine()) != null) {
          fileContent.add(line);
        }

        //
        fileContent.set(1, "wallet;" + sessionUser.getWallet());
      }

      try (BufferedWriter outFile =
          new BufferedWriter(new FileWriter(sessionUser.getFile(), false))) {
        //
        for (String i : fileContent) {
          outFile.write(i + "\n");
        }
      }

      //
      sessionUser
          .getBankAccount()
          .addTransaction(
              new Transaction(
                  100.0,
                  LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")),
                  InternalFrame.monthlyIncomeDefault,
                  sessionUser));

    } catch (IOException e) {
      System.err.println("monthly income failure");
    }
  }
}
