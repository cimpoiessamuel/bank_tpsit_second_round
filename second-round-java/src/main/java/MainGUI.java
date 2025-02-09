import javax.swing.*;
import java.awt.*;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

public class MainGUI {

    MainGUI(BankAccount b, BufferedWriter outFile, BufferedReader inFile) {

        // initialising main frame
        JFrame mainFrame = new JFrame("Bank App");

        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(1600, 1024);
        mainFrame.setLayout(null);
        mainFrame.setLocationRelativeTo(null);


        // main frame font
        Font font = new Font("Arial", Font.PLAIN, 20);


        // current balance display
        JTextField balanceDisplay = new JTextField();

        balanceDisplay.setText(String.valueOf(b.getBalance()));
        balanceDisplay.setBounds(800, 25, 100, 50);
        balanceDisplay.setFont(font);
        balanceDisplay.setEditable(false);
        balanceDisplay.setFocusable(false);


        // balance modifier
        JTextField balanceModifier = new JTextField();

        balanceModifier.setBounds(512, 800, 100, 50);
        balanceModifier.setFont(font);
        balanceModifier.setEditable(true);
        balanceModifier.setFocusable(true);


        // deposit button
        JButton deposit = new JButton("Deposit");

        deposit.setFocusable(false);
        deposit.setBounds(150, 430, 100, 50);


        // withdraw button
        JButton withdraw = new JButton("Withdraw");

        withdraw.setFocusable(false);
        withdraw.setBounds(250, 430, 100, 50);

//        deposit.addActionListener(this);
//        withdraw.addActionListener(this);


        // profile ImageIcon
        ImageIcon profileAvatar = new ImageIcon("../resources/default_user_avatar_100x100_rounded.png");


        // profile section container
        JLabel profileLabel = new JLabel();

        profileLabel.setBounds(10, 10, 500, 300);
        profileLabel.setText(b.getUser().toString().toUpperCase());
        profileLabel.setIcon(profileAvatar);
        profileLabel.setHorizontalTextPosition(JLabel.RIGHT);
        profileLabel.setVerticalTextPosition(JLabel.CENTER);


        // adding components to the main frame
        mainFrame.add(profileLabel);
        mainFrame.add(deposit);
        mainFrame.add(withdraw);
        mainFrame.add(balanceDisplay);
        mainFrame.add(balanceModifier);


        try {
            if (inFile.readLine().equals("default: ")) {
                LoginGUI loginGUI = new LoginGUI(mainFrame, outFile, inFile);
            }
        } catch (IOException e) {
            System.out.println("error");
        }


        mainFrame.setVisible(true);
    }

//    @Override
//    public void actionPerformed(ActionEvent e) {
//        if (e.getSource() == deposit) {
//            bankAcc.deposit(Double.parseDouble(balanceModifier.getText()));
//            balanceDisplay.setText(String.valueOf(bankAcc.getBalance()));
//        }
//
//        if (e.getSource() == withdraw) {
//            bankAcc.withdraw(Double.parseDouble(balanceModifier.getText()));
//            balanceDisplay.setText(String.valueOf(bankAcc.getBalance()));
//        }
//    }
}
