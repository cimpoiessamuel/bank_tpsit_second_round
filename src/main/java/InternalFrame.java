import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.AbstractBorder;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.TitledBorder;

public class InternalFrame {

  // place-holders
  public static String nameTextFieldPlaceHolder = "Type your name";
  public static String surnameTextFieldPlaceHolder = "Type your surname";
  public static String usernameTextFieldPlaceHolder = "Type your username";
  public static String passwordTextFieldPlaceHolder = "Type your password";

  // transaction reasons
  public static String depositTransactionDefault = "Deposit done successfully";
  public static String withdrawTransactionDefault = "Withdraw done successfully";

  //
  InternalFrame(JFrame mainFrame) {

    // saving the default content pane
    JPanel mainPanel = (JPanel) mainFrame.getContentPane();

    //
    MainFrame.subWindowPaneInit(mainFrame);

    //
    JPanel internalPanel = new JPanel();
    internalPanel.setLayout(new BoxLayout(internalPanel, BoxLayout.Y_AXIS));

    //
    JPanel containerPanel = new JPanel();
    containerPanel.setLayout(new BoxLayout(containerPanel, BoxLayout.Y_AXIS));

    //
    containerPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

    // main internal frame
    JInternalFrame internalFrame = internalFrameInit(mainFrame);
    internalFrame.setContentPane(internalPanel);
    internalFrame.setTitle("Your Transactions");

    //
    JLabel mainTitle = mainTitle(internalFrame, "Transactions".toUpperCase());
    mainTitle.setAlignmentX(Component.CENTER_ALIGNMENT);

    //
    JButton goBackButton = new JButton("Go back");
    goBackButton.setPreferredSize(new Dimension(250, 45));
    goBackButton.setAlignmentX(Component.CENTER_ALIGNMENT);

    //
    ArrayList<Transaction> transactions =
        MainFrame.getSessionUser().getBankAccount().getTransactions();

    //
    for (Transaction t : transactions) {
      //
      JPanel panel = new JPanel();
      panel.setLayout(new FlowLayout());
      panel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 2));

      String imagePath = "src/main/resources/images/";

      switch (t.getDescription().split(" ")[0]) {
        case "Deposit":
          imagePath += "letter-D-90x90.png";
          break;
        case "Withdraw":
          imagePath += "letter-W-90x90.png";
          break;
        case "Investment":
          imagePath += "letter-I-90x90.png";
          break;
      }

      //
      JLabel purposeLabel = new JLabel(t.getDescription().split(" ")[0] + ": " + t.getAmount());
      purposeLabel.setIcon(new ImageIcon(imagePath));
      purposeLabel.setHorizontalTextPosition(JLabel.CENTER);
      purposeLabel.setVerticalTextPosition(JLabel.BOTTOM);

      //
      JLabel descLabel = new JLabel("Description: " + t.getDescription());

      //
      panel.add(purposeLabel);
      panel.add(descLabel);
      containerPanel.add(panel);
    }

    //
    internalPanel.add(mainTitle);
    internalPanel.add(new JScrollPane(containerPanel));
    internalPanel.add(goBackButton);

    // setting the internal-frame visible
    internalFrame.setVisible(true);

    // everytime the main-frame is resized, the internal-frame is re-centered
    InternalFrame.autoCenter(mainFrame, internalFrame);

    // everytime the internal-frame is moved, it re-center itself
    InternalFrame.noMove(mainFrame, internalFrame);

    //
    goBackButton.addActionListener(
        new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
            //
            mainFrame.setContentPane(mainPanel);

            //
            internalFrame.dispose();
          }
        });
  }

  //
  InternalFrame(JFrame mainFrame, JLabel balanceDisplay, String action) {

    // saving the default content pane
    JPanel mainPanel = (JPanel) mainFrame.getContentPane();

    //
    MainFrame.subWindowPaneInit(mainFrame);

    // main internal frame
    JInternalFrame internalFrame = internalFrameInit(mainFrame);

    //
    internalFrame.setTitle(action);

    //
    JLabel mainTitle = mainTitle(internalFrame, action.toUpperCase());

    //
    JTextField balanceModifier =
        InternalFrame.textFieldInit(internalFrame, "Import to " + action, null, -100);

    //
    JButton cancelButton = InternalFrame.buttonInit(internalFrame, null, "Cancel", 100);

    //
    JButton purposeButton = InternalFrame.buttonInit(internalFrame, null, action, 50);

    //
    internalFrame.add(mainTitle);
    internalFrame.add(balanceModifier);
    internalFrame.add(cancelButton);
    internalFrame.add(purposeButton);

    // setting the internal-frame visible
    internalFrame.setVisible(true);

    // everytime the main-frame is resized, the internal-frame is re-centered
    InternalFrame.autoCenter(mainFrame, internalFrame);

    // everytime the internal-frame is moved, it re-center itself
    InternalFrame.noMove(mainFrame, internalFrame);

    //
    purposeButton.addActionListener(
        new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {

            if (!balanceModifier.getText().isEmpty()) {

              try {
                //
                ArrayList<String> fileContent = new ArrayList<>();

                try (BufferedReader inFile =
                    new BufferedReader(new FileReader(MainFrame.getSessionUser().getFile()))) {
                  //
                  String line;
                  while ((line = inFile.readLine()) != null) {
                    fileContent.add(line);
                  }
                }

                // Deposit action
                if (action.equals("Deposit")) {

                  // check if deposit is valid
                  if (MainFrame.getSessionUser()
                      .getBankAccount()
                      .deposit(Double.parseDouble(balanceModifier.getText()))) {

                    //
                    balanceDisplay.setText(
                        "Balance   "
                            + MainFrame.getSessionUser().getBankAccount().getBalance()
                            + "€           Wallet   "
                            + MainFrame.getSessionUser().getWallet()
                            + "€");

                    //
                    try (BufferedWriter outFile =
                        new BufferedWriter(
                            new FileWriter(MainFrame.getSessionUser().getFile(), false))) {
                      //
                      fileContent.set(
                          0, "balance;" + MainFrame.getSessionUser().getBankAccount().getBalance());

                      //
                      fileContent.set(1, "wallet;" + MainFrame.getSessionUser().getWallet());

                      //
                      for (String i : fileContent) {
                        outFile.write(i + "\n");
                      }
                    }

                    //
                    MainFrame.getSessionUser()
                        .getBankAccount()
                        .addTransaction(
                            new Transaction(
                                Double.parseDouble(balanceModifier.getText()),
                                LocalDateTime.now()
                                    .format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")),
                                depositTransactionDefault,
                                MainFrame.getSessionUser()));

                  } else {
                    JOptionPane.showInternalMessageDialog(internalFrame, "Import not valid!");
                  }

                  // Withdraw action
                } else if (action.equals("Withdraw")) {

                  //
                  if (MainFrame.getSessionUser()
                      .getBankAccount()
                      .withdraw(Double.parseDouble(balanceModifier.getText()))) {

                    //
                    balanceDisplay.setText(
                        "Balance   "
                            + MainFrame.getSessionUser().getBankAccount().getBalance()
                            + "€           Wallet   "
                            + MainFrame.getSessionUser().getWallet()
                            + "€");

                    //
                    try (BufferedWriter outFile =
                        new BufferedWriter(
                            new FileWriter(MainFrame.getSessionUser().getFile(), false))) {
                      //
                      fileContent.set(
                          0, "balance;" + MainFrame.getSessionUser().getBankAccount().getBalance());

                      //
                      fileContent.set(1, "wallet;" + MainFrame.getSessionUser().getWallet());

                      //
                      for (String i : fileContent) {
                        outFile.write(i + "\n");
                      }
                    }

                    //
                    MainFrame.getSessionUser()
                        .getBankAccount()
                        .addTransaction(
                            new Transaction(
                                Double.parseDouble(balanceModifier.getText()),
                                LocalDateTime.now()
                                    .format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")),
                                withdrawTransactionDefault,
                                MainFrame.getSessionUser()));

                  } else {
                    JOptionPane.showInternalMessageDialog(internalFrame, "Import not valid!");
                  }
                }

              } catch (IOException exc) {
                System.err.println("balance update failed");
              }
            }

            //
            mainFrame.setContentPane(mainPanel);

            //
            internalFrame.dispose();
          }
        });

    //
    cancelButton.addActionListener(
        new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
            //
            mainFrame.setContentPane(mainPanel);

            //
            internalFrame.dispose();
          }
        });

    //
    balanceModifier.addKeyListener(
        new KeyAdapter() {
          @Override
          public void keyTyped(KeyEvent e) {
            char c = e.getKeyChar();
            if (!Character.isDigit(c) && c != '.') {
              e.consume();
            }
          }
        });

    // purpose button text-field placeholder
    balanceModifier.addFocusListener(
        new FocusAdapter() {
          @Override
          public void focusGained(FocusEvent e) {
            if (balanceModifier.getText().equals("Import to " + action)) {
              balanceModifier.setText("");
            }
          }

          @Override
          public void focusLost(FocusEvent e) {
            if (balanceModifier.getText().isEmpty()) {
              balanceModifier.setText("Import to " + action);
            }
          }
        });
  }

  //
  InternalFrame(JFrame mainFrame, File users_info) {

    //
    mainFrame.revalidate();
    mainFrame.repaint();

    // initialising main sub-frame
    JInternalFrame internalFrame = internalFrameInit(mainFrame);

    // instantiating login interface -> the default sub-frame displayed if default user is missing
    new LoginInternalFrame(mainFrame, internalFrame, users_info);

    // everytime the main-frame is resized, the internal-frame is re-centered
    InternalFrame.autoCenter(mainFrame, internalFrame);

    // everytime the internal-frame is moved, it re-center itself
    InternalFrame.noMove(mainFrame, internalFrame);
  }

  // initialising main sub-frame
  public static JInternalFrame internalFrameInit(JFrame mainFrame) {
    JInternalFrame internalFrame = new JInternalFrame("", false, false, false, false);

    internalFrame.setSize(500, 700);
    internalFrame.setLayout(null);
    internalFrame.setFocusable(true);
    internalFrame.setLocation(
        (mainFrame.getContentPane().getWidth() - internalFrame.getWidth()) / 2,
        (mainFrame.getContentPane().getHeight() - internalFrame.getHeight()) / 2);

    // adding the internalFrame to the sub-frames container
    mainFrame.getContentPane().add(internalFrame);

    // initializing the title-bar icon
    internalFrame.setFrameIcon(new ImageIcon("src/main/resources/images/vBank2-rounded-16x16.png"));

    return internalFrame;
  }

  // initialising main title
  public static JLabel mainTitle(JInternalFrame internalFrame, String label) {
    JLabel mainTitle = new JLabel(label);

    mainTitle.setBounds(0, 0, 300, 180);

    mainTitle.setVerticalAlignment(JLabel.CENTER);
    mainTitle.setHorizontalAlignment(JLabel.CENTER);

    mainTitle.setHorizontalTextPosition(JLabel.CENTER);
    mainTitle.setVerticalTextPosition(JLabel.BOTTOM);

    mainTitle.setFont(new Font("Arial", Font.BOLD, 40));
    mainTitle.setIcon(new ImageIcon("src/main/resources/images/vBank3-nobg-200x109.png"));
    mainTitle.setLocation(
        (internalFrame.getWidth() - mainTitle.getWidth()) / 2,
        ((internalFrame.getHeight() - mainTitle.getHeight()) / 2) - 230);

    return mainTitle;
  }

  // initialising generic text-field
  public static JTextField textFieldInit(
      JInternalFrame internalFrame, String placeHolder, Font font, int y) {
    JTextField textField = new JTextField();

    textField.setBounds(0, 0, 250, 45);
    textField.setText(placeHolder);
    textField.setFont(font);
    textField.setEditable(true);
    textField.setHorizontalAlignment(JTextField.CENTER);
    textField.setLocation(
        (internalFrame.getWidth() - textField.getWidth()) / 2,
        ((internalFrame.getHeight() - textField.getHeight()) / 2) + y);

    return textField;
  }

  // initialising generic password-text-field
  public static JPasswordField passwordTextFieldInit(
      JInternalFrame internalFrame, String placeHolder, Font font, int y) {
    JPasswordField passwordTextField = new JPasswordField();

    passwordTextField.setBounds(0, 0, 250, 45);
    passwordTextField.setText(placeHolder);
    passwordTextField.setFont(font);
    passwordTextField.setEditable(true);
    passwordTextField.setHorizontalAlignment(JTextField.CENTER);
    passwordTextField.setLocation(
        (internalFrame.getWidth() - passwordTextField.getWidth()) / 2,
        ((internalFrame.getHeight() - passwordTextField.getHeight()) / 2) + y);

    return passwordTextField;
  }

  // initialising generic text-field border
  public static void borderInit(JTextField textField) {
    textField.setBorder(BorderFactory.createLineBorder(Color.GRAY, 2, true));
  }

  // initialising generic text-field label
  public static JLabel labelInit(
      JInternalFrame internalFrame, JTextField textField, Font font, String purpose, int y) {
    JLabel textFieldLabel = new JLabel(purpose);

    textFieldLabel.setBounds(0, 0, 250, 50);
    textFieldLabel.setLocation(
        (internalFrame.getWidth() - textField.getWidth()) / 2,
        ((internalFrame.getHeight() - textField.getHeight()) / 2) + y);
    textFieldLabel.setFont(font);

    return textFieldLabel;
  }

  // generic button initialisation
  public static JButton buttonInit(JInternalFrame internalFrame, Font font, String purpose, int y) {
    JButton button = new JButton(purpose);

    button.setBounds(0, 0, 250, 45);
    button.setLocation(
        (internalFrame.getWidth() - button.getWidth()) / 2,
        ((internalFrame.getHeight() - button.getHeight()) / 2) + y);
    button.setFont(font);

    return button;
  }

  // show/hide password button
  public static JButton showHidePasswordButton(
      JInternalFrame internalFrame,
      JTextField passwordTextField,
      ImageIcon hidePasswordIcon,
      int x,
      int y) {
    JButton showHidePasswordButton = new JButton(hidePasswordIcon);

    showHidePasswordButton.setBounds(0, 0, 45, 45);
    showHidePasswordButton.setLocation(
        ((internalFrame.getWidth() - passwordTextField.getWidth()) / 2) + x,
        ((internalFrame.getHeight() - passwordTextField.getHeight()) / 2) + y);

    return showHidePasswordButton;
  }

  // everytime the main-frame is resized, the internal-frame is re-centered
  public static void autoCenter(JFrame mainFrame, JInternalFrame internalFrame) {
    mainFrame.addComponentListener(
        new ComponentAdapter() {
          @Override
          public void componentResized(ComponentEvent e) {
            int xA = (mainFrame.getWidth() - internalFrame.getWidth()) / 2;
            int yA = (mainFrame.getHeight() - internalFrame.getHeight()) / 2;

            internalFrame.setLocation(xA, yA);

            // everytime the main-frame is resized, the subWindowPane is resized too
            int xB = mainFrame.getWidth();
            int yB = mainFrame.getHeight();

            mainFrame.getContentPane().setBounds(0, 0, xB, yB);
          }
        });
  }

  // everytime the internal-frame is moved, it re-center itself
  public static void noMove(JFrame mainFrame, JInternalFrame internalFrame) {
    internalFrame.addComponentListener(
        new ComponentAdapter() {
          @Override
          public void componentMoved(ComponentEvent e) {
            int x = (mainFrame.getWidth() - internalFrame.getWidth()) / 2;
            int y = (mainFrame.getHeight() - internalFrame.getHeight()) / 2;

            internalFrame.setLocation(x, y);
          }
        });
  }
}
