import com.formdev.flatlaf.FlatDarkLaf;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;

public class LoginGUI {
    // main frame
    private final JFrame frame;


    // sub-frames container
    private final JDesktopPane subWindowPane;


    // login sub-frame
    private final JInternalFrame loginInternalFrame;


    // username and pwd text-fields
    private final JTextField usernameTextField;
    private final JTextField passwordTextField;


    // text fields borders
    private final Border usernameTextFieldBorder;
    private final Border passwordTextFieldBorder;


    // text fields labels
    JLabel passwordTextFieldLabel;
    JLabel usernameTextFieldLabel;


    // login and sign-in buttons
    private JButton loginButton;
    private JButton registerButton;


    // user info file
    //BufferedWriter

    LoginGUI() {
        // default app look
//        try {
//            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
//        } catch (Exception e) {
//            e.printStackTrace();
//        }


        // light app look
//        try {
//            UIManager.setLookAndFeel(new FlatLightLaf());
//        } catch (Exception e) {
//            System.err.println("LaF initialization failed");
//        }


        // dark app look
        try {
            UIManager.setLookAndFeel(new FlatDarkLaf());
        } catch (Exception e) {
            System.err.println("LaF initialization failed");
        }


        // initialising the main frame
        frame = new JFrame("Login");
        frame.setSize(1600, 1024);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //frame.setFocusableWindowState(false);
        frame.setLayout(null);


        // initialising the sub-frames container
        subWindowPane = new JDesktopPane();
        subWindowPane.setBounds(0, 0, 1600, 1024);


        // initialising login sub-frame
        loginInternalFrame = new JInternalFrame("Login", false, false, false, false);
        loginInternalFrame.setSize(500, 700);
        loginInternalFrame.setVisible(true);
        loginInternalFrame.setLayout(null);
        loginInternalFrame.setFocusable(true);


        // initialising username text-field
        usernameTextField = new JTextField();
        usernameTextField.setBounds(0, 0, 250, 45);
        usernameTextField.setText("Type your username");
        usernameTextField.setFont(new Font("Arial", Font.PLAIN, 16));
        usernameTextField.setEditable(true);
        usernameTextField.setHorizontalAlignment(JTextField.LEFT);
        usernameTextField.setLocation((loginInternalFrame.getWidth() - usernameTextField.getWidth()) / 2, ((loginInternalFrame.getHeight() - usernameTextField.getHeight()) / 2) - 100);


        // initialising username text-field border
        usernameTextFieldBorder = BorderFactory.createLineBorder(Color.GRAY, 2, true);
        usernameTextField.setBorder(usernameTextFieldBorder);


        // initialising username text-field label
        usernameTextFieldLabel = new JLabel("Username");
        usernameTextFieldLabel.setBounds(0, 0, 250, 50);
        usernameTextFieldLabel.setLocation((loginInternalFrame.getWidth() - usernameTextField.getWidth()) / 2, ((loginInternalFrame.getHeight() - usernameTextField.getHeight()) / 2) - 135);
        usernameTextFieldLabel.setFont(new Font("Arial", Font.PLAIN, 16));


        // initialising password text-field
        passwordTextField = new JTextField();
        passwordTextField.setBounds(0, 0, 250, 45);
        passwordTextField.setText("Type your password");
        passwordTextField.setFont(new Font("Arial", Font.PLAIN, 16));
        passwordTextField.setEditable(true);
        passwordTextField.setHorizontalAlignment(JTextField.LEFT);
        passwordTextField.setLocation((loginInternalFrame.getWidth() - passwordTextField.getWidth()) / 2, (loginInternalFrame.getHeight() - passwordTextField.getHeight()) / 2);


        // initialising password text-field border
        passwordTextFieldBorder = BorderFactory.createLineBorder(Color.GRAY, 2, true);
        passwordTextField.setBorder(passwordTextFieldBorder);


        // initialising password text-field label
        passwordTextFieldLabel = new JLabel("Password");
        passwordTextFieldLabel.setBounds(0, 0, 250, 50);
        passwordTextFieldLabel.setLocation((loginInternalFrame.getWidth() - passwordTextField.getWidth()) / 2, ((loginInternalFrame.getHeight() - passwordTextField.getHeight()) / 2) - 35);
        passwordTextFieldLabel.setFont(new Font("Arial", Font.PLAIN, 16));


        // login button
        loginButton = new JButton("Login");
        loginButton.setBounds(0, 0, 250, 45);
        loginButton.setLocation((loginInternalFrame.getWidth() - passwordTextField.getWidth()) / 2, ((loginInternalFrame.getHeight() - passwordTextField.getHeight()) / 2) + 60);
        loginButton.setFont(new Font("Arial", Font.PLAIN, 16));


        // register button
        registerButton = new JButton("Not a member? Register now");
        registerButton.setBounds(0, 0, 250, 45);
        registerButton.setLocation((loginInternalFrame.getWidth() - passwordTextField.getWidth()) / 2, ((loginInternalFrame.getHeight() - passwordTextField.getHeight()) / 2) + 120);
        registerButton.setFont(new Font("Arial", Font.PLAIN, 16));


        // adding components to the login sub-frame
        loginInternalFrame.add(usernameTextField);
        loginInternalFrame.add(passwordTextField);

        loginInternalFrame.add(usernameTextFieldLabel);
        loginInternalFrame.add(passwordTextFieldLabel);

        loginInternalFrame.add(loginButton);
        loginInternalFrame.add(registerButton);


        // adding components to the sub-frame container
        subWindowPane.add(loginInternalFrame);


        // adding components to the main frame
        frame.add(subWindowPane);


        frame.setVisible(true);


        //
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //RegisterGUI r = new RegisterGUI();
            }
        });




        // if username text-field is empty, place a placeholder
        usernameTextField.addFocusListener(new FocusAdapter() {
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
        });


        // if password text-field is empty, place a placeholder
        passwordTextField.addFocusListener(new FocusAdapter() {
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
        });


        // everytime the frame is resized, the login sub-frame is re-centered
        frame.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                int x = (frame.getWidth() - loginInternalFrame.getWidth()) / 2;
                int y = (frame.getHeight() - loginInternalFrame.getHeight()) / 2;

                loginInternalFrame.setLocation(x, y);
            }
        });


        // everytime the login sub-frame is moved, it re-center itself
        loginInternalFrame.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentMoved(ComponentEvent e) {
                int x = (frame.getWidth() - loginInternalFrame.getWidth()) / 2;
                int y = (frame.getHeight() - loginInternalFrame.getHeight()) / 2;

                loginInternalFrame.setLocation(x, y);
            }
        });


//        SwingUtilities.invokeLater(() -> {
//            int newX = (subWindowPane.getWidth() - subWindowPane.getWidth()) / 2;
//            int newY = (subWindowPane.getHeight() - subWindowPane.getHeight()) / 2;
//            loginInternalFrame.setLocation(newX, newY);
//        });

    }
}


//JOptionPane.showInternalMessageDialog(loginInternalFrame, "ciao");
//JOptionPane.showMessageDialog(frame, "Button was pressed!");
