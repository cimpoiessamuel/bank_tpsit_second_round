import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;

import java.io.*;

public class LoginInternalFrame {

    LoginInternalFrame(JFrame loginSignInMainFrame, JInternalFrame internalFrame, File users_info) {

        // setting login window title
        internalFrame.setTitle("Login");


        // login window logo
        ImageIcon loginBankLogo = new ImageIcon("src/main/resources/images/vBank2-rounded.png");


        // initialising login title
        JLabel loginMainTitle = new JLabel("LOGIN");

        loginMainTitle.setBounds(0, 0, 300, 180);

        loginMainTitle.setVerticalAlignment(JLabel.CENTER);
        loginMainTitle.setHorizontalAlignment(JLabel.CENTER);

        loginMainTitle.setHorizontalTextPosition(JLabel.CENTER);
        loginMainTitle.setVerticalTextPosition(JLabel.BOTTOM);

        loginMainTitle.setFont(new Font("Arial", Font.BOLD, 40));
        loginMainTitle.setIcon(loginBankLogo);
        loginMainTitle.setLocation((internalFrame.getWidth() - loginMainTitle.getWidth()) / 2, ((internalFrame.getHeight() - loginMainTitle.getHeight()) / 2) - 230);


        // login internal-frame font
        Font font = new Font("Arial", Font.PLAIN, 16);


        // place-holders
        String usernameTextFieldPlaceHolder = "Type your username";
        String passwordTextFieldPlaceHolder = "Type your password";


        // initialising username text-field
        JTextField usernameTextField = new JTextField();

        usernameTextField.setBounds(0, 0, 250, 45);
        usernameTextField.setText(usernameTextFieldPlaceHolder);
        usernameTextField.setFont(font);
        usernameTextField.setEditable(true);
        usernameTextField.setHorizontalAlignment(JTextField.CENTER);
        usernameTextField.setLocation((internalFrame.getWidth() - usernameTextField.getWidth()) / 2, ((internalFrame.getHeight() - usernameTextField.getHeight()) / 2) - 100);


        // initialising username text-field border
        Border usernameTextFieldBorder = BorderFactory.createLineBorder(Color.GRAY, 2, true);
        usernameTextField.setBorder(usernameTextFieldBorder);


        // initialising username text-field label
        JLabel usernameTextFieldLabel = new JLabel("Username");

        usernameTextFieldLabel.setBounds(0, 0, 250, 50);
        usernameTextFieldLabel.setLocation((internalFrame.getWidth() - usernameTextField.getWidth()) / 2, ((internalFrame.getHeight() - usernameTextField.getHeight()) / 2) - 135);
        usernameTextFieldLabel.setFont(font);


        // initialising password text-field
        JPasswordField passwordTextField = new JPasswordField();

        passwordTextField.setBounds(0, 0, 250, 45);
        passwordTextField.setText(passwordTextFieldPlaceHolder);
        passwordTextField.setFont(font);
        passwordTextField.setEditable(true);
        passwordTextField.setHorizontalAlignment(JTextField.CENTER);
        passwordTextField.setLocation((internalFrame.getWidth() - passwordTextField.getWidth()) / 2, (internalFrame.getHeight() - passwordTextField.getHeight()) / 2);
        passwordTextField.setEchoChar((char) 0);


        // initialising password text-field border
        Border passwordTextFieldBorder = BorderFactory.createLineBorder(Color.GRAY, 2, true);
        passwordTextField.setBorder(passwordTextFieldBorder);


        // initialising password text-field label
        JLabel passwordTextFieldLabel = new JLabel("Password");

        passwordTextFieldLabel.setBounds(0, 0, 250, 50);
        passwordTextFieldLabel.setLocation((internalFrame.getWidth() - passwordTextField.getWidth()) / 2, ((internalFrame.getHeight() - passwordTextField.getHeight()) / 2) - 35);
        passwordTextFieldLabel.setFont(font);


        // show/hide button imageIcon
        ImageIcon showPasswordIcon = new ImageIcon("src/main/resources/images/show-password.png");
        ImageIcon hidePasswordIcon = new ImageIcon("src/main/resources/images/hide-password.png");


        // show/hide password button
        JButton showHidePasswordButton = new JButton(hidePasswordIcon);

        showHidePasswordButton.setBounds(0, 0, 45, 45);
        showHidePasswordButton.setLocation(((internalFrame.getWidth() - passwordTextField.getWidth()) / 2) + 260, ((internalFrame.getHeight() - passwordTextField.getHeight()) / 2));


        // login button
        JButton loginButton = new JButton("Login");

        loginButton.setBounds(0, 0, 250, 45);
        loginButton.setLocation((internalFrame.getWidth() - passwordTextField.getWidth()) / 2, ((internalFrame.getHeight() - passwordTextField.getHeight()) / 2) + 90);
        loginButton.setFont(font);


        // register button
        JButton registerButton = new JButton("Sign in");

        registerButton.setBounds(0, 0, 250, 45);
        registerButton.setLocation((internalFrame.getWidth() - passwordTextField.getWidth()) / 2, ((internalFrame.getHeight() - passwordTextField.getHeight()) / 2) + 150);
        registerButton.setFont(font);


        // remember-me check-box
        JCheckBox rememberMe = new JCheckBox("Keep me logged");

        rememberMe.setBounds(0, 0, 200, 20);
        rememberMe.setLocation((internalFrame.getWidth() - passwordTextField.getWidth()) / 2, ((internalFrame.getHeight() - passwordTextField.getHeight()) / 2) + 50);
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
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try (BufferedReader inFile = new BufferedReader(new FileReader(users_info))) {
                    String line;

                    while ((line = inFile.readLine()) != null) {

                        String userUsername;
                        String userPassword;

                        if (((userUsername = line).contains(usernameTextField.getText())) && ((userPassword = line = inFile.readLine()).contains(String.valueOf(passwordTextField.getPassword())))) {

                            //
                            userUsername = userUsername.split(";")[1];
                            userPassword = userPassword.split(";")[1];
                            String userName = (line = inFile.readLine()).split(";")[1];
                            String userSurname = (line = inFile.readLine()).split(";")[1];


                            // instancing logged user
                            MainFrame.setUser(new User(userName, userSurname, userUsername, userPassword, new File("src/main/resources/users/" + userName + "-" + userSurname + "-" + userUsername + ".csv")));


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
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RegisterInternalFrame registerInternalFrame = new RegisterInternalFrame(loginSignInMainFrame, internalFrame, users_info);
            }
        });


        // username text-field placeholder
        usernameTextField.addFocusListener(new FocusAdapter() {
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
        passwordTextField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (String.valueOf(passwordTextField.getPassword()).equals(passwordTextFieldPlaceHolder)) {
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
        showHidePasswordButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (String.valueOf(passwordTextField.getPassword()).equals(passwordTextFieldPlaceHolder)) {
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


//JOptionPane.showInternalMessageDialog(internalFrame, "ciao");
//JOptionPane.showMessageDialog(frame, "Button was pressed!");
