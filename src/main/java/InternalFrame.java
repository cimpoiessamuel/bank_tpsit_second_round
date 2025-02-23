import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.io.File;
import javax.swing.*;
import javax.swing.border.Border;

public class InternalFrame {

  // place-holders
  public static String nameTextFieldPlaceHolder = "Type your name";
  public static String surnameTextFieldPlaceHolder = "Type your surname";
  public static String usernameTextFieldPlaceHolder = "Type your username";
  public static String passwordTextFieldPlaceHolder = "Type your password";

  InternalFrame(JFrame mainFrame) {

    // instancing the internal frame container
    JDesktopPane subWindowPane = subWindowPaneInit(mainFrame);

    // setting sub-frames container visible
    subWindowPane.setVisible(true);

    // main internal frame
    JInternalFrame internalFrame = internalFrameInit(subWindowPane);

    // setting internal frame visible
    internalFrame.setVisible(true);

    // everytime the main-frame is resized, the internal-frame is re-centered
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

    // everytime the internal-frame is moved, it re-center itself
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

    // everytime the internal-frame is moved, it re-center itself
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

  public JDesktopPane subWindowPaneInit(JFrame mainFrame) {
    // initialising the sub-frames container
    JDesktopPane subWindowPane = new JDesktopPane();
    subWindowPane.setBounds(0, 0, mainFrame.getWidth(), mainFrame.getHeight());

    // adding subWindow to the main-frame
    mainFrame.add(subWindowPane);

    return subWindowPane;
  }

  public JInternalFrame internalFrameInit(JDesktopPane subWindowPane) {
    // initialising main sub-frame
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

  public static JLabel mainTitle(JInternalFrame internalFrame, String label) {
    // initialising main title
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

  public static JTextField textFieldInit(
      JInternalFrame internalFrame, String placeHolder, Font font, int y) {
    // initialising generic text-field
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

  public static JPasswordField passwordTextFieldInit(
      JInternalFrame internalFrame, String placeHolder, Font font, int y) {
    // initialising generic password-text-field
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

  public static Border borderInit(JTextField textField) {
    // initialising generic text-field border
    Border textFieldBorder = BorderFactory.createLineBorder(Color.GRAY, 2, true);
    textField.setBorder(textFieldBorder);

    return textFieldBorder;
  }

  public static JLabel labelInit(
      JInternalFrame internalFrame, JTextField textField, Font font, String purpose, int y) {
    // initialising generic text-field label
    JLabel textFieldLabel = new JLabel(purpose);

    textFieldLabel.setBounds(0, 0, 250, 50);
    textFieldLabel.setLocation(
        (internalFrame.getWidth() - textField.getWidth()) / 2,
        ((internalFrame.getHeight() - textField.getHeight()) / 2) + y);
    textFieldLabel.setFont(font);

    return textFieldLabel;
  }

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

  public static JButton showHidePasswordButton(
      JInternalFrame internalFrame,
      JTextField passwordTextField,
      ImageIcon hidePasswordIcon,
      int x,
      int y) {
    // show/hide password button
    JButton showHidePasswordButton = new JButton(hidePasswordIcon);

    showHidePasswordButton.setBounds(0, 0, 45, 45);
    showHidePasswordButton.setLocation(
        ((internalFrame.getWidth() - passwordTextField.getWidth()) / 2) + x,
        ((internalFrame.getHeight() - passwordTextField.getHeight()) / 2) + y);

    return showHidePasswordButton;
  }
}
