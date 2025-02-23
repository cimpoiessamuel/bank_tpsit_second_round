import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;
import javax.swing.border.Border;

public class LoginInternalFrame {

  LoginInternalFrame(JFrame loginSignInMainFrame, JInternalFrame internalFrame, File users_info) {

    // setting login window title
    internalFrame.setTitle("Login");

    // initialising login main title
    JLabel loginMainTitle = InternalFrame.mainTitle(internalFrame, "LOGIN");

    // login internal-frame font
    Font font = new Font("Arial", Font.PLAIN, 16);

    // initialising username text-field
    JTextField usernameTextField =
        InternalFrame.textFieldInit(
            internalFrame, InternalFrame.usernameTextFieldPlaceHolder, font, -100);

    // initialising username text-field border
    Border usernameTextFieldBorder = InternalFrame.borderInit(usernameTextField);

    // initialising username text-field label
    JLabel usernameTextFieldLabel =
        InternalFrame.labelInit(internalFrame, usernameTextField, font, "Username", -135);

    // initialising password text-field
    JPasswordField passwordTextField =
        InternalFrame.passwordTextFieldInit(
            internalFrame, InternalFrame.passwordTextFieldPlaceHolder, font, 0);
    passwordTextField.setEchoChar((char) 0);

    // initialising password text-field border
    Border passwordTextFieldBorder = InternalFrame.borderInit(passwordTextField);

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
    JButton loginButton =
        InternalFrame.buttonInit(internalFrame, usernameTextField, font, "Login", 90);

    // register button
    JButton registerButton =
        InternalFrame.buttonInit(internalFrame, usernameTextField, font, "Register", 150);

    // remember-me check-box
    JCheckBox rememberMe = new JCheckBox("Keep me logged");

    rememberMe.setBounds(0, 0, 200, 20);
    rememberMe.setLocation(
        (internalFrame.getWidth() - passwordTextField.getWidth()) / 2,
        ((internalFrame.getHeight() - passwordTextField.getHeight()) / 2) + 50);
    rememberMe.setFont(new Font("Arial", Font.PLAIN, 14));

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
              String line;

              while ((line = inFile.readLine()) != null) {

                String userUsername;
                String userPassword;

                if ((!((userUsername = line).isEmpty()) && !(line.equals("default;")))
                    && ((userUsername.split(";")[1].equals(usernameTextField.getText()))
                        && (userPassword = line = inFile.readLine())
                            .split(";")[1].equals(
                                String.valueOf(passwordTextField.getPassword())))) {

                  //
                  userUsername = userUsername.split(";")[1];
                  userPassword = userPassword.split(";")[1];

                  String userName = (line = inFile.readLine()).split(";")[1];
                  String userSurname = (line = inFile.readLine()).split(";")[1];

                  // instancing logged user
                  MainFrame.setSessionUser(
                      new User(
                          userName,
                          userSurname,
                          userUsername,
                          userPassword,
                          new File(
                              "src/main/resources/users/"
                                  + userName
                                  + "-"
                                  + userSurname
                                  + "-"
                                  + userUsername
                                  + ".csv")));

                  try (BufferedReader inInFile =
                      new BufferedReader(new FileReader(MainFrame.getSessionUser().getFile()))) {
                    //
                    MainFrame.getSessionUser()
                        .getBankAccount()
                        .setBalance(Double.parseDouble(inInFile.readLine().split(";")[1]));

                    //
                    MainFrame.getSessionUser()
                        .setWallet(Double.parseDouble(inInFile.readLine().split(";")[1]));
                  }

                  // closing the login/sign-in window
                  loginSignInMainFrame.dispose();

                  // instancing main frame for effective use
                  MainFrame realMainFrame = new MainFrame();

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
            RegisterInternalFrame registerInternalFrame =
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

    // if rememberMe is checked it saves the data
    //        rememberMe.addActionListener(e -> {
    //            if (rememberMe.isSelected()) {
    //                try {
    //                    outFile.write("Default");
    //                } catch (IOException ex) {
    //                    throw new RuntimeException(ex);
    //                }
    //            }
    //        });

    //        SwingUtilities.invokeLater(() -> {
    //            int newX = (subWindowPane.getWidth() - subWindowPane.getWidth()) / 2;
    //            int newY = (subWindowPane.getHeight() - subWindowPane.getHeight()) / 2;
    //            internalFrame.setLocation(newX, newY);
    //        });

  }
}

// JOptionPane.showInternalMessageDialog(internalFrame, "ciao");
// JOptionPane.showMessageDialog(frame, "Button was pressed!");
