import com.formdev.flatlaf.FlatDarkLaf;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class LoginGUI {
    // member: frame
    private final JFrame frame;


    // member: sub-frames container
    private final JDesktopPane subWindowPane;


    // member: login sub-frame
    private final JInternalFrame loginInternalFrame;


    // members: username and pwd text-fields
    private final JTextField usernameTextField;
    private final JTextField passwordTextField;


    // members: login and sign-in buttons
    private JButton loginButton;
    private JButton registerButton;

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
        frame.setSize(1500, 1000);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //frame.setFocusableWindowState(false);
        //frame.setLayout(null);


        // initialising the sub-frames container
        subWindowPane = new JDesktopPane();


        // initialising the login sub-frame
        loginInternalFrame = new JInternalFrame("Login", false, false, false, false);
        loginInternalFrame.setSize(600, 900);
        loginInternalFrame.setVisible(true);
        loginInternalFrame.setLayout(null);


        // initialising the username text-field
        usernameTextField = new JTextField();
        usernameTextField.setBounds(0, 0, 200, 60);
        usernameTextField.setText("Type your username");
        usernameTextField.setEditable(true);
        usernameTextField.setHorizontalAlignment(JTextField.RIGHT);


        // initialising the password text-field
        passwordTextField = new JTextField();
        passwordTextField.setBounds(0, 100, 200, 60);
        passwordTextField.setText("Type your password");
        usernameTextField.setEditable(true);


        // adding components to the login sub-frame
        loginInternalFrame.add(usernameTextField);
        loginInternalFrame.add(passwordTextField);


        // adding components to the sub-frame container
        subWindowPane.add(loginInternalFrame);


        // adding components to the main frame
        frame.add(subWindowPane);
        frame.setVisible(true);


        //
        usernameTextField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                usernameTextField.setText("");
            }

            @Override
            public void focusLost(FocusEvent e) {
                usernameTextField.setText("Type your username");
            }
        });


        //
        passwordTextField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                passwordTextField.setText("");
            }

            @Override
            public void focusLost(FocusEvent e) {
                passwordTextField.setText("Type your password");
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
