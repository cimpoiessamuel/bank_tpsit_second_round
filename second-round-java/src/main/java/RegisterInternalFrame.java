import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.Border;

import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;

public class RegisterInternalFrame {

    RegisterInternalFrame(JInternalFrame internalFrame, File users_info) {

        // setting login window title
        internalFrame.setTitle("Register");

        // register window logo
        ImageIcon loginBankLogo = new ImageIcon("src/main/resources/vBank2-rounded.png");


        // initialising sign-in title
        JLabel registerMainTitle = new JLabel("SIGN IN");

        registerMainTitle.setBounds(0, 0, 300, 180);

        registerMainTitle.setVerticalAlignment(JLabel.CENTER);
        registerMainTitle.setHorizontalAlignment(JLabel.CENTER);

        registerMainTitle.setHorizontalTextPosition(JLabel.CENTER);
        registerMainTitle.setVerticalTextPosition(JLabel.BOTTOM);

        registerMainTitle.setFont(new Font("Arial", Font.BOLD, 40));
        registerMainTitle.setIcon(loginBankLogo);
        registerMainTitle.setLocation((internalFrame.getWidth() - registerMainTitle.getWidth()) / 2, ((internalFrame.getHeight() - registerMainTitle.getHeight()) / 2) - 230);


        // register internal-frame font
        Font font = new Font("Arial", Font.PLAIN, 16);


        // initialising name text-field
        JTextField nameTextField = new JTextField();

        nameTextField.setBounds(0, 0, 250, 45);
        nameTextField.setText("Type your name");
        nameTextField.setFont(font);
        nameTextField.setEditable(true);
        nameTextField.setHorizontalAlignment(JTextField.CENTER);
        nameTextField.setLocation((internalFrame.getWidth() - nameTextField.getWidth()) / 2, ((internalFrame.getHeight() - nameTextField.getHeight()) / 2) - 100);


        // initialising name text-field border
        Border nameTextFieldBorder = BorderFactory.createLineBorder(Color.GRAY, 2, true);
        nameTextField.setBorder(nameTextFieldBorder);


        // initialising name text-field label
        JLabel nameTextFieldLabel = new JLabel("Name");

        nameTextFieldLabel.setBounds(0, 0, 250, 50);
        nameTextFieldLabel.setLocation((internalFrame.getWidth() - nameTextField.getWidth()) / 2, ((internalFrame.getHeight() - nameTextField.getHeight()) / 2) - 135);
        nameTextFieldLabel.setFont(font);


        // initialising surname text-field
        JTextField surnameTextField = new JTextField();

        surnameTextField.setBounds(0, 0, 250, 45);
        surnameTextField.setText("Type your surname");
        surnameTextField.setFont(font);
        surnameTextField.setEditable(true);
        surnameTextField.setHorizontalAlignment(JTextField.CENTER);
        surnameTextField.setLocation((internalFrame.getWidth() - surnameTextField.getWidth()) / 2, ((internalFrame.getHeight() - surnameTextField.getHeight()) / 2) - 20);


        // initialising surname text-field border
        Border surnameTextFieldBorder = BorderFactory.createLineBorder(Color.GRAY, 2, true);
        surnameTextField.setBorder(surnameTextFieldBorder);


        // initialising surname text-field label
        JLabel surnameTextFieldLabel = new JLabel("Surname");

        surnameTextFieldLabel.setBounds(0, 0, 250, 50);
        surnameTextFieldLabel.setLocation((internalFrame.getWidth() - surnameTextField.getWidth()) / 2, ((internalFrame.getHeight() - surnameTextField.getHeight()) / 2) - 55);
        surnameTextFieldLabel.setFont(font);


        // initialising username text-field
        JTextField usernameTextField = new JTextField();

        usernameTextField.setBounds(0, 0, 250, 45);
        usernameTextField.setText("Type your username");
        usernameTextField.setFont(font);
        usernameTextField.setEditable(true);
        usernameTextField.setHorizontalAlignment(JTextField.CENTER);
        usernameTextField.setLocation((internalFrame.getWidth() - usernameTextField.getWidth()) / 2, ((internalFrame.getHeight() - usernameTextField.getHeight()) / 2) + 60);


        // initialising username text-field border
        Border usernameTextFieldBorder = BorderFactory.createLineBorder(Color.GRAY, 2, true);
        usernameTextField.setBorder(usernameTextFieldBorder);


        // initialising username text-field label
        JLabel usernameTextFieldLabel = new JLabel("Username");

        usernameTextFieldLabel.setBounds(0, 0, 250, 50);
        usernameTextFieldLabel.setLocation((internalFrame.getWidth() - usernameTextField.getWidth()) / 2, ((internalFrame.getHeight() - usernameTextField.getHeight()) / 2) + 25);
        usernameTextFieldLabel.setFont(font);


        // initialising password text-field
        JTextField passwordTextField = new JTextField();

        passwordTextField.setBounds(0, 0, 250, 45);
        passwordTextField.setText("Type your password");
        passwordTextField.setFont(font);
        passwordTextField.setEditable(true);
        passwordTextField.setHorizontalAlignment(JTextField.CENTER);
        passwordTextField.setLocation((internalFrame.getWidth() - passwordTextField.getWidth()) / 2, ((internalFrame.getHeight() - passwordTextField.getHeight()) / 2) + 140);


        // initialising password text-field border
        Border passwordTextFieldBorder = BorderFactory.createLineBorder(Color.GRAY, 2, true);
        passwordTextField.setBorder(passwordTextFieldBorder);


        // initialising password text-field label
        JLabel passwordTextFieldLabel = new JLabel("Password");

        passwordTextFieldLabel.setBounds(0, 0, 250, 50);
        passwordTextFieldLabel.setLocation((internalFrame.getWidth() - passwordTextField.getWidth()) / 2, ((internalFrame.getHeight() - passwordTextField.getHeight()) / 2) + 105);
        passwordTextFieldLabel.setFont(font);


        // register button
        JButton registerButton = new JButton("Sign in");

        registerButton.setBounds(0, 0, 250, 45);
        registerButton.setLocation((internalFrame.getWidth() - passwordTextField.getWidth()) / 2, ((internalFrame.getHeight() - passwordTextField.getHeight()) / 2) + 220);
        registerButton.setFont(font);


        // go-back button
        JButton goBackButton = new JButton("Go back");

        goBackButton.setBounds(0, 0, 250, 45);
        goBackButton.setLocation((internalFrame.getWidth() - passwordTextField.getWidth()) / 2, ((internalFrame.getHeight() - passwordTextField.getHeight()) / 2) + 270);
        goBackButton.setFont(font);


        // remove all the components of the login interface
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


        // refreshing the internal-frame
        internalFrame.revalidate();
        internalFrame.repaint();


        // setting the internal-frame visible
        internalFrame.setVisible(true);


        // name text-field placeholder
        nameTextField.addFocusListener((new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (nameTextField.getText().equals("Type your name")) {
                    nameTextField.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (nameTextField.getText().isEmpty()) {
                    nameTextField.setText("Type your name");
                }
            }
        }));


        // surname text-field placeholder
        surnameTextField.addFocusListener((new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (surnameTextField.getText().equals("Type your surname")) {
                    surnameTextField.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (surnameTextField.getText().isEmpty()) {
                    surnameTextField.setText("Type your surname");
                }
            }
        }));


        // username text-field placeholder
        usernameTextField.addFocusListener((new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (usernameTextField.getText().equals("Type your username")) {
                    usernameTextField.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (usernameTextField.getText().isEmpty()) {
                    usernameTextField.setText("Type your username");
                }
            }
        }));


        // password text-field placeholder
        passwordTextField.addFocusListener((new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (passwordTextField.getText().equals("Type your password")) {
                    passwordTextField.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (passwordTextField.getText().isEmpty()) {
                    passwordTextField.setText("Type your password");
                }
            }
        }));


        // return back to log in interface
        goBackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LoginInternalFrame loginInternalFrame = new LoginInternalFrame(internalFrame, users_info);
            }
        });


        // write new user info on users_info.txt, then log in
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String newUserInfo = "\n\n";

                try (BufferedReader inFile = new BufferedReader(new FileReader(users_info));
                     BufferedWriter outFile = new BufferedWriter(new FileWriter(users_info, true))) {

                    String line = "";
                    boolean usernameTaken = false;

                    while ((line = inFile.readLine()) != null) {
                        if (line.contains(usernameTextField.getText())) {
                            usernameTaken = true;
                            JOptionPane.showInternalMessageDialog(internalFrame, "Username already taken");
                        }
                    }

                    if (!usernameTaken) {

                        // name field
                        if (!nameTextField.getText().isEmpty() && !nameTextField.getText().equals("Type your name") && nameTextField.getText().length() > 2) {
                            newUserInfo += "name: " + nameTextField.getText();
                        } else {
                            JOptionPane.showInternalMessageDialog(internalFrame, "Name must be at least 3 digits length");
                            newUserInfo = "";
                        }


                        // surname field
                        if (!surnameTextField.getText().isEmpty() && !surnameTextField.getText().equals("Type your surname") && surnameTextField.getText().length() > 2) {
                            newUserInfo += "\nsurname: " + surnameTextField.getText();
                        } else {
                            JOptionPane.showInternalMessageDialog(internalFrame, "Surname must be at least 3 digits length");
                            newUserInfo = "";
                        }


                        // username field
                        if (!usernameTextField.getText().isEmpty() && !usernameTextField.getText().equals("Type your username") && usernameTextField.getText().length() > 3) {
                            newUserInfo += "\nusername: " + usernameTextField.getText();
                        } else {
                            JOptionPane.showInternalMessageDialog(internalFrame, "Username must be at least 3 digits length");
                            newUserInfo = "";
                        }


                        // password field
                        if (!passwordTextField.getText().isEmpty() && !passwordTextField.getText().equals("Type your password") && passwordTextField.getText().length() > 7) {
                            newUserInfo += "\npassword: " + passwordTextField.getText();
                        } else {
                            JOptionPane.showInternalMessageDialog(internalFrame, "Password length must be at least 8 digits length");
                            newUserInfo = "";
                        }


                        // writing to users_info.txt
                        if (!newUserInfo.isEmpty()) {
                            outFile.write(newUserInfo);
                            outFile.flush();
                        }
                    }

                } catch (IOException exc) {
                    System.err.println("error");
                }
            }
        });
    }
}
