import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;
import javax.swing.*;

public class LoginInternalFrame {

  LoginInternalFrame(JFrame loginSignInMainFrame, JInternalFrame internalFrame, File users_info) {

    // setting login window title
    internalFrame.setTitle("Login");

    // initialising login main title
    JLabel loginMainTitle = InternalFrame.mainTitle(internalFrame, "LOGIN");

    // login internal-frame font
    Font font = InternalFrame.fontInit(16);

    // initialising username text-field
    JTextField usernameTextField =
        InternalFrame.textFieldInit(
            internalFrame, InternalFrame.usernameTextFieldPlaceHolder, font, -100);

    // initialising username text-field border
    InternalFrame.borderInit(usernameTextField);

    // initialising username text-field label
    JLabel usernameTextFieldLabel =
        InternalFrame.labelInit(internalFrame, usernameTextField, font, "Username", -135);

    // initialising password text-field
    JPasswordField passwordTextField =
        InternalFrame.passwordTextFieldInit(
            internalFrame, InternalFrame.passwordTextFieldPlaceHolder, font, 0);
    passwordTextField.setEchoChar((char) 0);

    // initialising password text-field border
    InternalFrame.borderInit(passwordTextField);

    // initialising password text-field label
    JLabel passwordTextFieldLabel =
        InternalFrame.labelInit(internalFrame, passwordTextField, font, "Password", -35);

    // show/hide button imageIcon
    ImageIcon showPasswordIcon = new ImageIcon("src/main/resources/images/show-password.png");
    ImageIcon hidePasswordIcon = new ImageIcon("src/main/resources/images/hide-password.png");

    // show/hide password button
    JButton showHidePasswordButton =
        InternalFrame.showHidePasswordButton(
            internalFrame, passwordTextField, hidePasswordIcon, 260, 0);

    // login button
    JButton loginButton = InternalFrame.buttonInit(internalFrame, font, "Login", 90);

    // register button
    JButton registerButton = InternalFrame.buttonInit(internalFrame, font, "Register", 150);

    // remember-me check-box
    JCheckBox rememberMe = new JCheckBox("Keep me logged");

    rememberMe.setBounds(0, 0, 200, 20);
    rememberMe.setLocation(
        (internalFrame.getWidth() - passwordTextField.getWidth()) / 2,
        ((internalFrame.getHeight() - passwordTextField.getHeight()) / 2) + 50);
    rememberMe.setFont(InternalFrame.fontInit(14));

    // remove all the components of the previous register interface
    internalFrame.getContentPane().removeAll();

    // adding components to the login internal-frame
    internalFrame.add(loginMainTitle);

    internalFrame.add(usernameTextField);
    internalFrame.add(passwordTextField);

    internalFrame.add(usernameTextFieldLabel);
    internalFrame.add(passwordTextFieldLabel);

    internalFrame.add(loginButton);
    internalFrame.add(registerButton);
    internalFrame.add(showHidePasswordButton);

    internalFrame.add(rememberMe);

    // refreshing the internal-frame
    internalFrame.revalidate();
    internalFrame.repaint();

    // setting the internal-frame visible
    internalFrame.setVisible(true);

    // log-in inside your account
    loginButton.addActionListener(
        new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
            try (BufferedReader inFile = new BufferedReader(new FileReader(users_info))) {
              String userUsername;
              String userPassword;

              while ((userUsername = inFile.readLine()) != null) {

                if ((!userUsername.isEmpty() && !userUsername.equals("default;"))
                    && ((StartApp.decrypt(userUsername.split(";")[1], StartApp.getKey())
                            .equals(usernameTextField.getText()))
                        && StartApp.decrypt(
                                (userPassword = inFile.readLine()).split(";")[1], StartApp.getKey())
                            .equals(String.valueOf(passwordTextField.getPassword())))) {

                  userUsername = StartApp.decrypt(userUsername.split(";")[1], StartApp.getKey());
                  userPassword = StartApp.decrypt(userPassword.split(";")[1], StartApp.getKey());

                  String userName = inFile.readLine().split(";")[1];
                  String userSurname = inFile.readLine().split(";")[1];

                  // instancing logged user
                  MainFrame.setSessionUser(
                      new User(userName, userSurname, userUsername, userPassword));

                  inFile.close();

                  if (rememberMe.isSelected()) {
                    ArrayList<String> fileContent =
                        MainFrame.getFileContent(StartApp.getUsersInfo());

                    fileContent.set(
                        0,
                        "default;"
                            + StartApp.crypt(userUsername, StartApp.getKey())
                            + ";"
                            + StartApp.crypt(userPassword, StartApp.getKey())
                            + ";"
                            + userName
                            + ";"
                            + userSurname);

                    MainFrame.writeFileContent(fileContent, StartApp.getUsersInfo());
                  }

                  // closing the login/sign-in window
                  loginSignInMainFrame.dispose();

                  // instancing main frame for effective use
                  new MainFrame();

                  return;
                }
              }

              // if user not found, then notify
              JOptionPane.showInternalMessageDialog(internalFrame, "User not found");
            } catch (IOException exc) {
              System.err.println("login failure");
            }
          }
        });

    // switch to the sign-in interface
    registerButton.addActionListener(
        new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
            new RegisterInternalFrame(loginSignInMainFrame, internalFrame, users_info);
          }
        });

    // username text-field placeholder
    usernameTextField.addFocusListener(
        new FocusAdapter() {
          @Override
          public void focusGained(FocusEvent e) {
            if (usernameTextField.getText().equals(InternalFrame.usernameTextFieldPlaceHolder)) {
              usernameTextField.setText("");
            }
          }

          @Override
          public void focusLost(FocusEvent e) {
            if (usernameTextField.getText().isEmpty()) {
              usernameTextField.setText(InternalFrame.usernameTextFieldPlaceHolder);
            }
          }
        });

    // password text-field placeholder
    passwordTextField.addFocusListener(
        new FocusAdapter() {
          @Override
          public void focusGained(FocusEvent e) {
            if (String.valueOf(passwordTextField.getPassword())
                .equals(InternalFrame.passwordTextFieldPlaceHolder)) {
              passwordTextField.setText("");
              passwordTextField.setEchoChar('*');
            }
          }

          @Override
          public void focusLost(FocusEvent e) {
            if (passwordTextField.getPassword().length == 0) {
              passwordTextField.setEchoChar((char) 0);
              passwordTextField.setText(InternalFrame.passwordTextFieldPlaceHolder);
              showHidePasswordButton.setIcon(hidePasswordIcon);
            }
          }
        });

    // show/hide password
    showHidePasswordButton.addActionListener(
        new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
            if (String.valueOf(passwordTextField.getPassword())
                .equals(InternalFrame.passwordTextFieldPlaceHolder)) {
              return;
            }

            if (showHidePasswordButton.getIcon() == hidePasswordIcon) {
              showHidePasswordButton.setIcon(showPasswordIcon);
              passwordTextField.setEchoChar((char) 0);
            } else {
              showHidePasswordButton.setIcon(hidePasswordIcon);
              passwordTextField.setEchoChar('*');
            }
          }
        });
  }
}
