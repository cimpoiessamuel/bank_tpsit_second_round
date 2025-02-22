import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.*;
import javax.swing.border.Border;

public class RegisterInternalFrame {

  RegisterInternalFrame(
      JFrame loginSignInMainFrame, JInternalFrame internalFrame, File users_info) {

    // setting login window title
    internalFrame.setTitle("Register");

    // register window logo
    ImageIcon loginBankLogo = new ImageIcon("src/main/resources/images/vBank2-rounded.png");

    // initialising sign-in title
    JLabel registerMainTitle = new JLabel("SIGN IN");

    registerMainTitle.setBounds(0, 0, 300, 180);

    registerMainTitle.setVerticalAlignment(JLabel.CENTER);
    registerMainTitle.setHorizontalAlignment(JLabel.CENTER);

    registerMainTitle.setHorizontalTextPosition(JLabel.CENTER);
    registerMainTitle.setVerticalTextPosition(JLabel.BOTTOM);

    registerMainTitle.setFont(new Font("Arial", Font.BOLD, 40));
    registerMainTitle.setIcon(loginBankLogo);
    registerMainTitle.setLocation(
        (internalFrame.getWidth() - registerMainTitle.getWidth()) / 2,
        ((internalFrame.getHeight() - registerMainTitle.getHeight()) / 2) - 230);

    // register internal-frame font
    Font font = new Font("Arial", Font.PLAIN, 16);

    // place-holders
    String nameTextFieldPlaceHolder = "Type your name";
    String surnameTextFieldPlaceHolder = "Type your surname";
    String usernameTextFieldPlaceHolder = "Type your username";
    String passwordTextFieldPlaceHolder = "Type your password";

    // initialising name text-field
    JTextField nameTextField = new JTextField();

    nameTextField.setBounds(0, 0, 250, 45);
    nameTextField.setText(nameTextFieldPlaceHolder);
    nameTextField.setFont(font);
    nameTextField.setEditable(true);
    nameTextField.setHorizontalAlignment(JTextField.CENTER);
    nameTextField.setLocation(
        (internalFrame.getWidth() - nameTextField.getWidth()) / 2,
        ((internalFrame.getHeight() - nameTextField.getHeight()) / 2) - 100);

    // initialising name text-field border
    Border nameTextFieldBorder = BorderFactory.createLineBorder(Color.GRAY, 2, true);
    nameTextField.setBorder(nameTextFieldBorder);

    // initialising name text-field label
    JLabel nameTextFieldLabel = new JLabel("Name");

    nameTextFieldLabel.setBounds(0, 0, 250, 50);
    nameTextFieldLabel.setLocation(
        (internalFrame.getWidth() - nameTextField.getWidth()) / 2,
        ((internalFrame.getHeight() - nameTextField.getHeight()) / 2) - 135);
    nameTextFieldLabel.setFont(font);

    // initialising surname text-field
    JTextField surnameTextField = new JTextField();

    surnameTextField.setBounds(0, 0, 250, 45);
    surnameTextField.setText(surnameTextFieldPlaceHolder);
    surnameTextField.setFont(font);
    surnameTextField.setEditable(true);
    surnameTextField.setHorizontalAlignment(JTextField.CENTER);
    surnameTextField.setLocation(
        (internalFrame.getWidth() - surnameTextField.getWidth()) / 2,
        ((internalFrame.getHeight() - surnameTextField.getHeight()) / 2) - 20);

    // initialising surname text-field border
    Border surnameTextFieldBorder = BorderFactory.createLineBorder(Color.GRAY, 2, true);
    surnameTextField.setBorder(surnameTextFieldBorder);

    // initialising surname text-field label
    JLabel surnameTextFieldLabel = new JLabel("Surname");

    surnameTextFieldLabel.setBounds(0, 0, 250, 50);
    surnameTextFieldLabel.setLocation(
        (internalFrame.getWidth() - surnameTextField.getWidth()) / 2,
        ((internalFrame.getHeight() - surnameTextField.getHeight()) / 2) - 55);
    surnameTextFieldLabel.setFont(font);

    // initialising username text-field
    JTextField usernameTextField = new JTextField();

    usernameTextField.setBounds(0, 0, 250, 45);
    usernameTextField.setText(usernameTextFieldPlaceHolder);
    usernameTextField.setFont(font);
    usernameTextField.setEditable(true);
    usernameTextField.setHorizontalAlignment(JTextField.CENTER);
    usernameTextField.setLocation(
        (internalFrame.getWidth() - usernameTextField.getWidth()) / 2,
        ((internalFrame.getHeight() - usernameTextField.getHeight()) / 2) + 60);

    // initialising username text-field border
    Border usernameTextFieldBorder = BorderFactory.createLineBorder(Color.GRAY, 2, true);
    usernameTextField.setBorder(usernameTextFieldBorder);

    // initialising username text-field label
    JLabel usernameTextFieldLabel = new JLabel("Username");

    usernameTextFieldLabel.setBounds(0, 0, 250, 50);
    usernameTextFieldLabel.setLocation(
        (internalFrame.getWidth() - usernameTextField.getWidth()) / 2,
        ((internalFrame.getHeight() - usernameTextField.getHeight()) / 2) + 25);
    usernameTextFieldLabel.setFont(font);

    // initialising password text-field
    JPasswordField passwordTextField = new JPasswordField();

    passwordTextField.setBounds(0, 0, 250, 45);
    passwordTextField.setText(passwordTextFieldPlaceHolder);
    passwordTextField.setFont(font);
    passwordTextField.setEditable(true);
    passwordTextField.setHorizontalAlignment(JTextField.CENTER);
    passwordTextField.setLocation(
        (internalFrame.getWidth() - passwordTextField.getWidth()) / 2,
        ((internalFrame.getHeight() - passwordTextField.getHeight()) / 2) + 140);
    passwordTextField.setEchoChar((char) 0);

    // initialising password text-field border
    Border passwordTextFieldBorder = BorderFactory.createLineBorder(Color.GRAY, 2, true);
    passwordTextField.setBorder(passwordTextFieldBorder);

    // initialising password text-field label
    JLabel passwordTextFieldLabel = new JLabel("Password");

    passwordTextFieldLabel.setBounds(0, 0, 250, 50);
    passwordTextFieldLabel.setLocation(
        (internalFrame.getWidth() - passwordTextField.getWidth()) / 2,
        ((internalFrame.getHeight() - passwordTextField.getHeight()) / 2) + 105);
    passwordTextFieldLabel.setFont(font);

    // show/hide button imageIcon
    ImageIcon showPasswordIcon = new ImageIcon("src/main/resources/images/show-password.png");
    ImageIcon hidePasswordIcon = new ImageIcon("src/main/resources/images/hide-password.png");

    // show/hide password button
    JButton showHidePasswordButton = new JButton(hidePasswordIcon);

    showHidePasswordButton.setBounds(0, 0, 45, 45);
    showHidePasswordButton.setLocation(
        ((internalFrame.getWidth() - passwordTextField.getWidth()) / 2) + 260,
        ((internalFrame.getHeight() - passwordTextField.getHeight()) / 2) + 140);

    // register button
    JButton registerButton = new JButton("Sign in");

    registerButton.setBounds(0, 0, 250, 45);
    registerButton.setLocation(
        (internalFrame.getWidth() - passwordTextField.getWidth()) / 2,
        ((internalFrame.getHeight() - passwordTextField.getHeight()) / 2) + 220);
    registerButton.setFont(font);

    // go-back button
    JButton goBackButton = new JButton("Go back");

    goBackButton.setBounds(0, 0, 250, 45);
    goBackButton.setLocation(
        (internalFrame.getWidth() - passwordTextField.getWidth()) / 2,
        ((internalFrame.getHeight() - passwordTextField.getHeight()) / 2) + 270);
    goBackButton.setFont(font);

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

              String newUserInfo = "\n\n";

              // username field
              if (!usernameTextField.getText().isEmpty()
                  && !usernameTextField.getText().equals(usernameTextFieldPlaceHolder)
                  && usernameTextField.getText().length() > 3) {
                newUserInfo += "\nusername;" + usernameTextField.getText();
              } else {
                JOptionPane.showInternalMessageDialog(
                    internalFrame, "Username must be at least 4 digits long");
                return;
              }

              // password field
              if (!String.valueOf(passwordTextField.getPassword()).isEmpty()
                  && !String.valueOf(passwordTextField.getPassword())
                      .equals(passwordTextFieldPlaceHolder)
                  && passwordTextField.getPassword().length > 7) {
                newUserInfo += "\npassword;" + String.valueOf(passwordTextField.getPassword());
              } else {
                JOptionPane.showInternalMessageDialog(
                    internalFrame, "Password must be at least 8 digits long");
                return;
              }

              // name field
              if (!nameTextField.getText().isEmpty()
                  && !nameTextField.getText().equals(nameTextFieldPlaceHolder)
                  && nameTextField.getText().length() > 2) {
                newUserInfo += "\nname;" + nameTextField.getText();
              } else {
                JOptionPane.showInternalMessageDialog(
                    internalFrame, "Name must be at least 3 digits long");
                return;
              }

              // surname field
              if (!surnameTextField.getText().isEmpty()
                  && !surnameTextField.getText().equals(surnameTextFieldPlaceHolder)
                  && surnameTextField.getText().length() > 2) {
                newUserInfo += "\nsurname;" + surnameTextField.getText();
              } else {
                JOptionPane.showInternalMessageDialog(
                    internalFrame, "Surname must be at least 3 digits long");
                return;
              }

              // check if the typed username already exists
              String line;

              while ((line = inFile.readLine()) != null) {
                if (!usernameTextField.getText().equals("name")
                    && !usernameTextField.getText().equals("surname")
                    && !usernameTextField.getText().equals("username")
                    && !usernameTextField.getText().equals("password")) {
                  if (!line.isEmpty()
                      && (line.split(";")[0].equals("username")
                          && line.split(";")[1].equals(usernameTextField.getText()))) {
                    JOptionPane.showInternalMessageDialog(internalFrame, "Username already taken");
                    return;
                  }
                } else {
                  JOptionPane.showInternalMessageDialog(
                      internalFrame,
                      "Banned usernames: \"name\", \"surname\", \"username\", \"password\"");
                  return;
                }
              }

              // instancing new registered user
              MainFrame.setUser(
                  new User(
                      nameTextField.getText(),
                      surnameTextField.getText(),
                      usernameTextField.getText(),
                      String.valueOf(passwordTextField.getPassword())));

              // closing the login/sign-in window
              loginSignInMainFrame.dispose();

              // instancing main frame for effective use
              MainFrame realMainFrame = new MainFrame();

              // writing in users_info.txt
              outFile.write(newUserInfo);
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
            LoginInternalFrame loginInternalFrame =
                new LoginInternalFrame(loginSignInMainFrame, internalFrame, users_info);
          }
        });

    // name text-field placeholder
    nameTextField.addFocusListener(
        new FocusAdapter() {
          @Override
          public void focusGained(FocusEvent e) {
            if (nameTextField.getText().equals(nameTextFieldPlaceHolder)) {
              nameTextField.setText("");
            }
          }

          @Override
          public void focusLost(FocusEvent e) {
            if (nameTextField.getText().isEmpty()) {
              nameTextField.setText(nameTextFieldPlaceHolder);
            }
          }
        });

    // surname text-field placeholder
    surnameTextField.addFocusListener(
        new FocusAdapter() {
          @Override
          public void focusGained(FocusEvent e) {
            if (surnameTextField.getText().equals(surnameTextFieldPlaceHolder)) {
              surnameTextField.setText("");
            }
          }

          @Override
          public void focusLost(FocusEvent e) {
            if (surnameTextField.getText().isEmpty()) {
              surnameTextField.setText(surnameTextFieldPlaceHolder);
            }
          }
        });

    // username text-field placeholder
    usernameTextField.addFocusListener(
        new FocusAdapter() {
          @Override
          public void focusGained(FocusEvent e) {
            if (usernameTextField.getText().equals(usernameTextFieldPlaceHolder)) {
              usernameTextField.setText("");
            }
          }

          @Override
          public void focusLost(FocusEvent e) {
            if (usernameTextField.getText().isEmpty()) {
              usernameTextField.setText(usernameTextFieldPlaceHolder);
            }
          }
        });

    // password text-field placeholder
    passwordTextField.addFocusListener(
        new FocusAdapter() {
          @Override
          public void focusGained(FocusEvent e) {
            if (String.valueOf(passwordTextField.getPassword())
                .equals(passwordTextFieldPlaceHolder)) {
              passwordTextField.setText("");
              passwordTextField.setEchoChar('*');
            }
          }

          @Override
          public void focusLost(FocusEvent e) {
            if (passwordTextField.getPassword().length == 0) {
              passwordTextField.setEchoChar((char) 0);
              passwordTextField.setText(passwordTextFieldPlaceHolder);
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
                .equals(passwordTextFieldPlaceHolder)) {
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
