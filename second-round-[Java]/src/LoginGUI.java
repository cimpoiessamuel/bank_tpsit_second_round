import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class LoginGUI {
    public JFrame frame;

    public JPanel loginPanel;

    public JTextField usernameField;
    public JTextField passwordField;

    public JButton loginButton;
    public JButton registerButton;

    public JDesktopPane loginWindow;
    public JInternalFrame loginFrame;

    LoginGUI() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        frame = new JFrame("Login");
        frame.setSize(1500, 1000);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //frame.setFocusableWindowState(false);
        //frame.setLayout(null);

        loginPanel = new JPanel();
        loginPanel.setLayout(null);
        loginPanel.setBounds(10, 10, 300, 200);
        loginPanel.setBackground(Color.GRAY);

        usernameField = new JTextField();
        usernameField.setBounds(0, 0, 100, 20);
        usernameField.setText("Type your username");
        usernameField.setEditable(true);

        passwordField = new JTextField();
        passwordField.setBounds(110, 0, 100, 20);
        passwordField.setText("Type your password");
        usernameField.setEditable(true);

        loginPanel.add(usernameField);
        loginPanel.add(passwordField);

        loginWindow = new JDesktopPane();
        loginFrame = new JInternalFrame("Login", false, false, false, false);
        loginFrame.setSize(500, 700);
        loginFrame.setLocation(150, 150);
        loginFrame.setVisible(true);
        loginFrame.setBackground(Color.GRAY);

        loginWindow.add(loginFrame);

        frame.add(loginWindow);
        //frame.add(loginPanel);
        frame.setVisible(true);


        usernameField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                usernameField.setText("");
            }

            @Override
            public void focusLost(FocusEvent e) {
                usernameField.setText("Type your username");
            }
        });

        passwordField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                passwordField.setText("");
            }

            @Override
            public void focusLost(FocusEvent e) {
                passwordField.setText("Type your password");
            }
        });
    }


}
