import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.HttpRetryException;

public class GUI implements ActionListener {
    public BankAccount bankAcc;

    public JFrame frame;

    public JTextField balanceDisplay;
    public JTextField balanceModifier;

    public Font font;

    public JButton deposit;
    public JButton withdraw;

    GUI(BankAccount b) {
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
