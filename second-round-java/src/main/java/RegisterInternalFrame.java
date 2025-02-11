import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;

import java.io.BufferedReader;
import java.io.BufferedWriter;

public class RegisterInternalFrame {

    RegisterInternalFrame(JInternalFrame internalFrame, BufferedWriter outFile, BufferedReader inFile) {

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


        // initialising name text-field
        JTextField nameTextField = new JTextField();

        nameTextField.setBounds(0, 0, 250, 45);
        nameTextField.setText("Type your name");
        nameTextField.setFont(new Font("Arial", Font.PLAIN, 16));
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
        nameTextFieldLabel.setFont(new Font("Arial", Font.PLAIN, 16));


        // initialising surname text-field
        JTextField surnameTextField = new JTextField();

        surnameTextField.setBounds(0, 0, 250, 45);
        surnameTextField.setText("Type your surname");
        surnameTextField.setFont(new Font("Arial", Font.PLAIN, 16));
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
        surnameTextFieldLabel.setFont(new Font("Arial", Font.PLAIN, 16));


        // initialising username text-field
        JTextField usernameTextField = new JTextField();

        usernameTextField.setBounds(0, 0, 250, 45);
        usernameTextField.setText("Type your username");
        usernameTextField.setFont(new Font("Arial", Font.PLAIN, 16));
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
        usernameTextFieldLabel.setFont(new Font("Arial", Font.PLAIN, 16));


        // initialising password text-field
        JTextField passwordTextField = new JTextField();

        passwordTextField.setBounds(0, 0, 250, 45);
        passwordTextField.setText("Type your password");
        passwordTextField.setFont(new Font("Arial", Font.PLAIN, 16));
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
        passwordTextFieldLabel.setFont(new Font("Arial", Font.PLAIN, 16));


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
    }
}
