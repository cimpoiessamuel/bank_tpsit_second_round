import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import javax.swing.*;

public class InternalFrame {

  // place-holders
  public static String nameTextFieldPlaceHolder = "Type your name";
  public static String surnameTextFieldPlaceHolder = "Type your surname";
  public static String usernameTextFieldPlaceHolder = "Type your username";
  public static String passwordTextFieldPlaceHolder = "Type your password";

  // transaction reasons
  public static String depositTransactionDefault = "Deposit done successfully";
  public static String withdrawTransactionDefault = "Withdraw done successfully";
  public static String investmentTransactionDefault = "Investment done successfully";
  public static String investmentProfitTransactionDefault = "Profit from investment";
  public static String monthlyIncomeDefault = "Monthly wallet income";

  // internal frame just for showing trans. record in the app
  InternalFrame(JFrame mainFrame) {

    // saving the default content pane
    JPanel mainPanel = (JPanel) mainFrame.getContentPane();

    // internal frames must be contained in a JDesktopPane
    MainFrame.subWindowPaneInit(mainFrame);

    JPanel internalPanel = new JPanel();
    internalPanel.setLayout(new BoxLayout(internalPanel, BoxLayout.Y_AXIS));

    JPanel containerPanel = new JPanel();
    containerPanel.setLayout(new BoxLayout(containerPanel, BoxLayout.Y_AXIS));
    containerPanel.setAlignmentX(Component.CENTER_ALIGNMENT); //

    // main internal frame
    JInternalFrame internalFrame = internalFrameInit(mainFrame);
    internalFrame.setTitle("Your Transactions");
    internalFrame.setContentPane(internalPanel); //

    JLabel mainTitle = mainTitle(internalFrame, "Transactions".toUpperCase());
    mainTitle.setAlignmentX(Component.CENTER_ALIGNMENT);

    JButton goBackButton = new JButton("Go back");
    goBackButton.setFont(fontInit(16));
    goBackButton.setAlignmentX(Component.CENTER_ALIGNMENT);
    goBackButton.setMaximumSize(new Dimension(250, 45)); // size respected by the BoxLayout
    goBackButton.setMargin(new Insets(13, 0, 13, 0)); // internal button padding

    // read every transaction
    ArrayList<Transaction> transactions =
        MainFrame.getSessionUser().getBankAccount().getTransactions();

    // fonts for trans. cards
    Font font = fontInit(16);

    // iterate through every transaction
    for (Transaction t : transactions) {

      JPanel panel = new JPanel(new BorderLayout());
      panel.setBorder(
          BorderFactory.createCompoundBorder(
              BorderFactory.createLineBorder(Color.LIGHT_GRAY, 2),
              BorderFactory.createEmptyBorder(10, 10, 10, 10)));

      JLabel purposeLabel = new JLabel();
      purposeLabel.setFont(font);
      purposeLabel.setHorizontalTextPosition(JLabel.CENTER);
      purposeLabel.setVerticalTextPosition(JLabel.BOTTOM);

      // retrieve the reason of the transaction
      String imagePath = "";
      switch (t.getDescription().split(" ")[0]) { // extracting purpose
        case "Deposit":
          // set image
          imagePath = "src/main/resources/images/letter-D-90x90.png";

          // set label
          purposeLabel.setText("<html><b>+" + t.getAmount() + " €</b></html>");
          purposeLabel.setForeground(new Color(0, 175, 0));

          break;

        case "Withdraw":
          imagePath = "src/main/resources/images/letter-W-90x90.png";
          purposeLabel.setText("<html><b>-" + t.getAmount() + " €</b></html>");
          purposeLabel.setForeground(new Color(175, 0, 0));

          break;

        case "Investment":
          imagePath = "src/main/resources/images/letter-I-90x90.png";
          purposeLabel.setText("<html><b>-" + t.getAmount() + " €</b></html>");
          purposeLabel.setForeground(new Color(175, 0, 0));

          break;

        case "Profit":
          imagePath = "src/main/resources/images/letter-I-90x90.png";

          if (t.getAmount() > 0) {
            purposeLabel.setText("<html><b>+" + t.getAmount() + " €</b></html>");
            purposeLabel.setForeground(new Color(0, 175, 0));
          } else {
            purposeLabel.setText("<html><b>-" + t.getAmount() + " €</b></html>");
            purposeLabel.setForeground(new Color(175, 0, 0));
          }

          break;

        case "Monthly":
          imagePath = "src/main/resources/images/wallet-90x90.png";
          purposeLabel.setText("<html><b>+" + t.getAmount() + " €</b></html>");
          purposeLabel.setForeground(new Color(0, 175, 0));

          break;
      }

      purposeLabel.setIcon(new ImageIcon(imagePath));

      // label for description
      JLabel descLabel = new JLabel(t.toString());
      descLabel.setPreferredSize(new Dimension(250, 100)); // default size of each card
      descLabel.setFont(font);

      if (t.getDescription().split(" ")[0].equals("Investment")) {
        JPanel showGraphPanel = new JPanel(new FlowLayout());

        JButton showGraphButton =
            new JButton(new ImageIcon("src/main/resources/images/graph-50x50.png"));
        showGraphButton.setPreferredSize(new Dimension(50, 50));

        showGraphButton.addActionListener(
            new ActionListener() {
              @Override
              public void actionPerformed(ActionEvent e) {
                try {
                  // calls the py script
                  new ProcessBuilder(
                          "python",
                          "src/main/resources/graph.py",
                          MainFrame.getSessionUser().getDirectory()
                              + "/investment-"
                              + t.getID()
                              + ".csv")
                      .start();
                } catch (Exception exc) {
                  System.err.println("python script failure");
                }
              }
            });

        showGraphPanel.add(Box.createRigidArea(new Dimension(0, 100)));
        showGraphPanel.add(showGraphButton);

        panel.add(showGraphPanel, BorderLayout.CENTER);
      }

      panel.add(purposeLabel, BorderLayout.WEST);
      panel.add(descLabel, BorderLayout.EAST);

      containerPanel.add(panel);
    }

    internalPanel.add(mainTitle);
    internalPanel.add(Box.createRigidArea(new Dimension(0, 5))); // invisible separator
    internalPanel.add(
        new JScrollPane(containerPanel) {
          {
            // higher the scroll velocity
            getVerticalScrollBar().setUnitIncrement(25);
          }
        }); // transactions scroll area

    // go back button is separated by 2 invisible areas
    internalPanel.add(Box.createRigidArea(new Dimension(0, 10)));
    internalPanel.add(goBackButton);
    internalPanel.add(Box.createRigidArea(new Dimension(0, 10)));

    // setting the internal-frame visible
    internalFrame.setVisible(true);

    // everytime the main-frame is resized, the internal-frame is re-centered
    InternalFrame.autoCenter(mainFrame, internalFrame);

    // everytime the internal-frame is moved, it re-center itself
    InternalFrame.noMove(mainFrame, internalFrame);

    goBackButton.addActionListener(
        new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
            mainFrame.setContentPane(mainPanel);

            // close the internal frame
            internalFrame.dispose();
          }
        });
  }

  // internal frame for deposit, withdraw and invest
  InternalFrame(JFrame mainFrame, JLabel balanceDisplay, String action) {

    // saving the default content pane
    JPanel mainPanel = (JPanel) mainFrame.getContentPane();

    MainFrame.subWindowPaneInit(mainFrame);

    // main internal frame
    JInternalFrame internalFrame = internalFrameInit(mainFrame);

    internalFrame.setTitle(action);

    JLabel mainTitle = mainTitle(internalFrame, action.toUpperCase());

    Font font = fontInit(16);

    JTextField balanceModifier =
        InternalFrame.textFieldInit(internalFrame, "Import to " + action, font, -100);

    JButton purposeButton = InternalFrame.buttonInit(internalFrame, font, action, 130);

    JButton cancelButton = InternalFrame.buttonInit(internalFrame, font, "Cancel", 180);

    JComboBox<String> periodBox = new JComboBox<>(new String[] {"Short", "Medium", "Long"});
    JComboBox<String> riskBox = new JComboBox<>(new String[] {"Low", "Medium", "High"});

    if (action.equals("Invest")) {
      JLabel periodLabel = labelInit(internalFrame, balanceModifier, font, "Period", -60);

      periodBox.setFont(font);
      periodBox.setBounds(0, 0, 250, 45);
      periodBox.setLocation(
          (internalFrame.getWidth() - periodBox.getWidth()) / 2,
          ((internalFrame.getHeight() - periodBox.getHeight()) / 2) + (-25));

      internalFrame.add(periodLabel);
      internalFrame.add(periodBox);

      JLabel riskLabel = labelInit(internalFrame, balanceModifier, font, "Risk", 20);

      riskBox.setFont(font);
      riskBox.setBounds(0, 0, 250, 45);
      riskBox.setLocation(
          (internalFrame.getWidth() - riskBox.getWidth()) / 2,
          ((internalFrame.getHeight() - riskBox.getHeight()) / 2) + (55));

      internalFrame.add(riskLabel);
      internalFrame.add(riskBox);
    } else {
      periodBox.setVisible(false);
      riskBox.setVisible(false);
    }

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

    // changes based on the action
    purposeButton.addActionListener(
        new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {

            if (!balanceModifier.getText().isEmpty()) {

              switch (action) {
                case "Deposit":
                  // check if deposit is valid
                  if (MainFrame.getSessionUser()
                      .getBankAccount()
                      .deposit(Double.parseDouble(balanceModifier.getText()))) {

                    MainFrame.updateVisualBalance(balanceDisplay);

                    MainFrame.updateStats();

                    MainFrame.getSessionUser().updateTrend();

                    MainFrame.getSessionUser()
                        .getBankAccount()
                        .addTransaction(
                            new Transaction(
                                Double.parseDouble(balanceModifier.getText()),
                                LocalDateTime.now()
                                    .format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")),
                                depositTransactionDefault));

                  } else {
                    JOptionPane.showInternalMessageDialog(internalFrame, "Import not valid!");
                  }

                  break;

                case "Withdraw":
                  if (MainFrame.getSessionUser()
                      .getBankAccount()
                      .withdraw(Double.parseDouble(balanceModifier.getText()))) {

                    MainFrame.updateVisualBalance(balanceDisplay);

                    MainFrame.updateStats();

                    MainFrame.getSessionUser().updateTrend();

                    MainFrame.getSessionUser()
                        .getBankAccount()
                        .addTransaction(
                            new Transaction(
                                Double.parseDouble(balanceModifier.getText()),
                                LocalDateTime.now()
                                    .format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")),
                                withdrawTransactionDefault));

                  } else {
                    JOptionPane.showInternalMessageDialog(internalFrame, "Import not valid!");
                  }

                  break;

                case "Invest":
                  if (MainFrame.getSessionUser()
                      .getBankAccount()
                      .invest(
                          Double.parseDouble(balanceModifier.getText()),
                          (String) periodBox.getSelectedItem(),
                          (String) riskBox.getSelectedItem())) {

                    MainFrame.updateVisualBalance(balanceDisplay);

                    MainFrame.updateStats();

                  } else {
                    JOptionPane.showInternalMessageDialog(internalFrame, "Insufficient founds!");
                  }

                  break;
              }
            }

            mainFrame.setContentPane(mainPanel);
            internalFrame.dispose();
          }
        });

    cancelButton.addActionListener(
        new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
            mainFrame.setContentPane(mainPanel);
            internalFrame.dispose();
          }
        });

    // only digits and '.' allowed
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

  // internal frame for authentication
  InternalFrame(JFrame mainFrame, File users_info) {

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

  // standard font
  public static Font fontInit(int size) {
    return new Font("Arial", Font.PLAIN, size);
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
