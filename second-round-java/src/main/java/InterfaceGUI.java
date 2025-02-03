import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class InterfaceGUI implements ActionListener {
    private BankAccount bankAcc;

    private JFrame frame;

    private JTextField balanceDisplay;
    private JTextField balanceModifier;

    private Font font;

    private JButton deposit;
    private JButton withdraw;

    private JLabel profileLabel;

    private ImageIcon profileAvatar;


    InterfaceGUI(BankAccount b) {
        bankAcc = b;

        frame = new JFrame("Bank App");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1024, 920);
        frame.setLayout(null);

        font = new Font("Arial", Font.PLAIN, 20);

        balanceDisplay = new JTextField();
        balanceDisplay.setText(String.valueOf(bankAcc.getBalance()));
        balanceDisplay.setBounds(800, 25, 100, 50);
        balanceDisplay.setFont(font);
        balanceDisplay.setEditable(false);
        balanceDisplay.setFocusable(false);

        balanceModifier = new JTextField();
        balanceModifier.setBounds(512, 800, 100, 50);
        balanceModifier.setFont(font);
        balanceModifier.setEditable(true);
        balanceModifier.setFocusable(true);

        deposit = new JButton("Deposit");
        withdraw = new JButton("Withdraw");

        deposit.setFocusable(false);
        withdraw.setFocusable(false);

        deposit.setBounds(150, 430, 100, 50);
        withdraw.setBounds(250, 430, 100, 50);

        deposit.addActionListener(this);
        withdraw.addActionListener(this);

        profileAvatar = new ImageIcon("miscellaneous/default_user_avatar_100x100_rounded.png");

        profileLabel = new JLabel();
        profileLabel.setBounds(10,10,500,300);
        profileLabel.setText(b.getUser().toString().toUpperCase());
        profileLabel.setIcon(profileAvatar);
        profileLabel.setHorizontalTextPosition(JLabel.RIGHT);
        profileLabel.setVerticalTextPosition(JLabel.CENTER);

        frame.add(profileLabel);
        frame.add(deposit);
        frame.add(withdraw);
        frame.add(balanceDisplay);
        frame.add(balanceModifier);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == deposit) {
            bankAcc.deposit(Double.parseDouble(balanceModifier.getText()));
            balanceDisplay.setText(String.valueOf(bankAcc.getBalance()));
        }

        if (e.getSource() == withdraw) {
            bankAcc.withdraw(Double.parseDouble(balanceModifier.getText()));
            balanceDisplay.setText(String.valueOf(bankAcc.getBalance()));
        }
    }
}
