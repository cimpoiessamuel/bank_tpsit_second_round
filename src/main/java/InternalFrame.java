import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.*;
import javax.swing.border.Border;

public class InternalFrame {

  // place-holders
  public static String nameTextFieldPlaceHolder = "Type your name";
  public static String surnameTextFieldPlaceHolder = "Type your surname";
  public static String usernameTextFieldPlaceHolder = "Type your username";
  public static String passwordTextFieldPlaceHolder = "Type your password";

  InternalFrame(JFrame mainFrame, JPanel mainPanel, String action) {

    //
    Component[] components = mainPanel.getComponents();

    // remove all the components of the main frame
    mainFrame.getContentPane().removeAll();

    // initialising internal frames container
    JDesktopPane subWindowPane = InternalFrame.subWindowPaneInit(mainFrame);

    // main internal frame
    JInternalFrame internalFrame = internalFrameInit(subWindowPane);

    //
    internalFrame.setTitle("Deposit");

    //
    JTextField balanceModifier =
        InternalFrame.textFieldInit(internalFrame, "Import to deposit", null, -100);

    //
    JButton depositButton =
        InternalFrame.buttonInit(internalFrame, balanceModifier, null, "Deposit", 50);

    //
    internalFrame.add(balanceModifier);
    internalFrame.add(depositButton);

    // setting the internal-frame visible
    internalFrame.setVisible(true);

    // everytime the main-frame is resized, the internal-frame is re-centered
    InternalFrame.autoCenter(mainFrame, internalFrame, subWindowPane);

    // everytime the internal-frame is moved, it re-center itself
    InternalFrame.noMove(mainFrame, internalFrame);

    //
    depositButton.addActionListener(
        new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
            internalFrame.dispose();
            mainFrame.dispose();
            MainFrame mainFrame = new MainFrame();
          }
        });
  }

  InternalFrame(JFrame mainFrame, File users_info) {

    // initialising the sub-frames container
    JDesktopPane subWindowPane = subWindowPaneInit(mainFrame);

    // setting sub-frames container visible
    subWindowPane.setVisible(true);

    // initialising main sub-frame
    JInternalFrame internalFrame = internalFrameInit(subWindowPane);

    // instantiating login interface -> the default sub-frame displayed if default user is missing
    LoginInternalFrame loginInternalFrame =
        new LoginInternalFrame(mainFrame, internalFrame, users_info);

    // everytime the main-frame is resized, the internal-frame is re-centered
    InternalFrame.autoCenter(mainFrame, internalFrame, subWindowPane);

    // everytime the internal-frame is moved, it re-center itself
    InternalFrame.noMove(mainFrame, internalFrame);
  }

  // initialising the sub-frames container
  public static JDesktopPane subWindowPaneInit(JFrame mainFrame) {
    JDesktopPane subWindowPane = new JDesktopPane();
    subWindowPane.setBounds(0, 0, mainFrame.getWidth(), mainFrame.getHeight());

    // adding subWindow to the main-frame
    mainFrame.add(subWindowPane, BorderLayout.CENTER);

    // replacing default content pane
    mainFrame.setContentPane(subWindowPane);

    return subWindowPane;
  }

  // initialising main sub-frame
  public static JInternalFrame internalFrameInit(JDesktopPane subWindowPane) {
    JInternalFrame internalFrame = new JInternalFrame("", false, false, false, false);

    internalFrame.setSize(500, 700);
    internalFrame.setLayout(null);
    internalFrame.setFocusable(true);
    internalFrame.setLocation(
        (subWindowPane.getWidth() - internalFrame.getWidth()) / 2,
        (subWindowPane.getHeight() - internalFrame.getHeight()) / 2);

    // adding the internalFrame to the sub-frames container
    subWindowPane.add(internalFrame);

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
  public static Border borderInit(JTextField textField) {
    Border textFieldBorder = BorderFactory.createLineBorder(Color.GRAY, 2, true);
    textField.setBorder(textFieldBorder);

    return textFieldBorder;
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
  public static JButton buttonInit(
      JInternalFrame internalFrame, JTextField textField, Font font, String purpose, int y) {
    JButton button = new JButton(purpose);

    button.setBounds(0, 0, 250, 45);
    button.setLocation(
        (internalFrame.getWidth() - textField.getWidth()) / 2,
        ((internalFrame.getHeight() - textField.getHeight()) / 2) + y);
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

  //
  //  public static List<Component> getAllComponents(Container container, ArrayList<Component>
  // components) {
  //    for (Component comp : container.getComponents()) {
  //      components.add(comp);
  //
  //      if (comp instanceof Container) {
  //        getAllComponents((Container) comp, components);
  //      }
  //    }
  //  }

  // everytime the main-frame is resized, the internal-frame is re-centered
  public static void autoCenter(
      JFrame mainFrame, JInternalFrame internalFrame, JDesktopPane subWindowPane) {
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

            subWindowPane.setBounds(0, 0, xB, yB);
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
