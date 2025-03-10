import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.*;

public class RegisterInternalFrame {

  RegisterInternalFrame(
      JFrame loginSignInMainFrame, JInternalFrame internalFrame, File users_info) {

    // setting sign-in window title
    internalFrame.setTitle("Register");

    // initialising sign-in main title
    JLabel registerMainTitle = InternalFrame.mainTitle(internalFrame, "SIGN IN");

    // register internal-frame font
    Font font = InternalFrame.fontInit(16);

    // initialising name text-field
    JTextField nameTextField =
        InternalFrame.textFieldInit(
            internalFrame, InternalFrame.nameTextFieldPlaceHolder, font, -100);

    // initialising name text-field border
    InternalFrame.borderInit(nameTextField);

    // initialising name text-field label
    JLabel nameTextFieldLabel =
        InternalFrame.labelInit(internalFrame, nameTextField, font, "Name", -135);

    // initialising surname text-field
    JTextField surnameTextField =
        InternalFrame.textFieldInit(
            internalFrame, InternalFrame.surnameTextFieldPlaceHolder, font, -20);

    // initialising surname text-field border
    InternalFrame.borderInit(surnameTextField);

    // initialising surname text-field label
    JLabel surnameTextFieldLabel =
        InternalFrame.labelInit(internalFrame, surnameTextField, font, "Surname", -55);

    // initialising username text-field
    JTextField usernameTextField =
        InternalFrame.textFieldInit(
            internalFrame, InternalFrame.usernameTextFieldPlaceHolder, font, 60);

    // initialising username text-field border
    InternalFrame.borderInit(usernameTextField);

    // initialising username text-field label
    JLabel usernameTextFieldLabel =
        InternalFrame.labelInit(internalFrame, usernameTextField, font, "Username", 25);

    // initialising password text-field
    JPasswordField passwordTextField =
        InternalFrame.passwordTextFieldInit(
            internalFrame, InternalFrame.passwordTextFieldPlaceHolder, font, 140);
    passwordTextField.setEchoChar((char) 0);

    // initialising password text-field border
    InternalFrame.borderInit(passwordTextField);

    // initialising password text-field label
    JLabel passwordTextFieldLabel =
        InternalFrame.labelInit(internalFrame, passwordTextField, font, "Password", 105);

    // show/hide button imageIcon
    ImageIcon showPasswordIcon = new ImageIcon("src/main/resources/images/show-password.png");
    ImageIcon hidePasswordIcon = new ImageIcon("src/main/resources/images/hide-password.png");

    // show/hide password button
    JButton showHidePasswordButton =
        InternalFrame.showHidePasswordButton(
            internalFrame, passwordTextField, hidePasswordIcon, 260, 140);

    // register button
    JButton registerButton = InternalFrame.buttonInit(internalFrame, font, "Register", 220);

    // go-back button
    JButton goBackButton = InternalFrame.buttonInit(internalFrame, font, "Go back", 270);

    // remove all the components of the previous login interface
    internalFrame.getContentPane().removeAll();

    // adding components to the register internal-frame
    internalFrame.add(registerMainTitle);

    internalFrame.add(nameTextField);
    internalFrame.add(surnameTextField);
    internalFrame.add(usernameTextField);
    internalFrame.add(passwordTextField);

    internalFrame.add(nameTextFieldLabel);
    internalFrame.add(surnameTextFieldLabel);
    internalFrame.add(usernameTextFieldLabel);
    internalFrame.add(passwordTextFieldLabel);

    internalFrame.add(registerButton);
    internalFrame.add(goBackButton);
    internalFrame.add(showHidePasswordButton);

    // refreshing the internal-frame
    internalFrame.revalidate();
    internalFrame.repaint();

    // setting the internal-frame visible
    internalFrame.setVisible(true);

    // write new user info on users_info.txt, then log in
    registerButton.addActionListener(
        new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {

            try (BufferedReader inFile = new BufferedReader(new FileReader(users_info));
                BufferedWriter outFile = new BufferedWriter(new FileWriter(users_info, true))) {

              String newUserInfo = "";

              // username field
              if (!usernameTextField.getText().isEmpty()
                  && !usernameTextField.getText().equals(InternalFrame.usernameTextFieldPlaceHolder)
                  && usernameTextField.getText().length() > 3) {
                newUserInfo +=
                    "username;" + StartApp.crypt(usernameTextField.getText(), StartApp.getKey());
              } else {
                JOptionPane.showInternalMessageDialog(
                    internalFrame, "Username must be at least 4 digits long");
                return;
              }

              // password field
              if (!String.valueOf(passwordTextField.getPassword()).isEmpty()
                  && !String.valueOf(passwordTextField.getPassword())
                      .equals(InternalFrame.passwordTextFieldPlaceHolder)
                  && passwordTextField.getPassword().length > 7) {
                newUserInfo +=
                    "\npassword;"
                        + StartApp.crypt(
                            String.valueOf(passwordTextField.getPassword()), StartApp.getKey());
              } else {
                JOptionPane.showInternalMessageDialog(
                    internalFrame, "Password must be at least 8 digits long");
                return;
              }

              // name field
              if (!nameTextField.getText().isEmpty()
                  && !nameTextField.getText().equals(InternalFrame.nameTextFieldPlaceHolder)
                  && nameTextField.getText().length() > 2) {
                newUserInfo += "\nname;" + nameTextField.getText();
              } else {
                JOptionPane.showInternalMessageDialog(
                    internalFrame, "Name must be at least 3 digits long");
                return;
              }

              // surname field
              if (!surnameTextField.getText().isEmpty()
                  && !surnameTextField.getText().equals(InternalFrame.surnameTextFieldPlaceHolder)
                  && surnameTextField.getText().length() > 2) {
                newUserInfo += "\nsurname;" + surnameTextField.getText();
              } else {
                JOptionPane.showInternalMessageDialog(
                    internalFrame, "Surname must be at least 3 digits long");
                return;
              }

              // check if the typed username already exists
              String line;

              if (!usernameTextField.getText().equals("name")
                  && !usernameTextField.getText().equals("surname")
                  && !usernameTextField.getText().equals("username")
                  && !usernameTextField.getText().equals("password")) {

                while ((line = inFile.readLine()) != null) {
                  if (!line.isEmpty()
                      && (line.split(";")[0].equals("username")
                          && StartApp.decrypt(line.split(";")[1], StartApp.getKey())
                              .equals(usernameTextField.getText()))) {
                    JOptionPane.showInternalMessageDialog(internalFrame, "Username already taken");
                    return;
                  }
                }
              } else {
                JOptionPane.showInternalMessageDialog(
                    internalFrame,
                    "Banned usernames: \"name\", \"surname\", \"username\", \"password\"");
                return;
              }

              // instancing new registered user
              MainFrame.setSessionUser(
                  new User(
                      nameTextField.getText(),
                      surnameTextField.getText(),
                      usernameTextField.getText(),
                      String.valueOf(passwordTextField.getPassword())));

              // closing the login/sign-in window
              loginSignInMainFrame.dispose();

              // instancing main frame for effective use
              new MainFrame();

              // writing in users_info.txt
              outFile.write(newUserInfo);
              outFile.newLine();
              outFile.newLine();

              //
              outFile.flush();

              //
              internalFrame.dispose();

            } catch (IOException exc) {
              System.err.println("writing on file error");
            }
          }
        });

    // return back to log in interface
    goBackButton.addActionListener(
        new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
            new LoginInternalFrame(loginSignInMainFrame, internalFrame, users_info);
          }
        });

    // name text-field placeholder
    nameTextField.addFocusListener(
        new FocusAdapter() {
          @Override
          public void focusGained(FocusEvent e) {
            if (nameTextField.getText().equals(InternalFrame.nameTextFieldPlaceHolder)) {
              nameTextField.setText("");
            }
          }

          @Override
          public void focusLost(FocusEvent e) {
            if (nameTextField.getText().isEmpty()) {
              nameTextField.setText(InternalFrame.nameTextFieldPlaceHolder);
            }
          }
        });

    // surname text-field placeholder
    surnameTextField.addFocusListener(
        new FocusAdapter() {
          @Override
          public void focusGained(FocusEvent e) {
            if (surnameTextField.getText().equals(InternalFrame.surnameTextFieldPlaceHolder)) {
              surnameTextField.setText("");
            }
          }

          @Override
          public void focusLost(FocusEvent e) {
            if (surnameTextField.getText().isEmpty()) {
              surnameTextField.setText(InternalFrame.surnameTextFieldPlaceHolder);
            }
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
