import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class ATMFrame extends JFrame {
    private BankAccount account;
    private JLabel balanceLabel;
    private JTextField amountField;
    private JTextArea messageArea;

    public ATMFrame(BankAccount account) {
        this.account = account;
        createUI();
    }
    public void createUI(){
        setTitle("ATM");
        setSize(500,500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel balancePanel = new JPanel();
        balanceLabel=new JLabel("Current Balance: $"+account.getBalance());
        balancePanel.add(balanceLabel);

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new FlowLayout());
        inputPanel.add(new JLabel("Amount: "));
        amountField = new JTextField(10);
        inputPanel.add(amountField);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());

        JButton depositButton = new JButton("Deposit");
        depositButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deposit();
            }
        });
        buttonPanel.add(depositButton);

        JButton withdrawButton = new JButton("Withdraw");
        withdrawButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                withdraw();
            }
        });
        buttonPanel.add(withdrawButton);

        JButton checkBalanceButton = new JButton("Check Balance");
        checkBalanceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkBalance();
            }
        });
        buttonPanel.add(checkBalanceButton);

        JButton clearTransactionsButton = new JButton("Clear Transactions");
        clearTransactionsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearTransactions();
            }
        });
        buttonPanel.add(clearTransactionsButton);



        messageArea = new JTextArea(5, 30);
        messageArea.setEditable(false);
        JScrollPane messageScrollPane = new JScrollPane(messageArea);

        add(balancePanel, BorderLayout.NORTH);
        add(inputPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
        add(messageScrollPane, BorderLayout.EAST);
    }
    private void deposit(){
        try{
            double amount = Double.parseDouble(amountField.getText());
            account.deposit(amount);
            balanceLabel.setText("Current Balance: $"+account.getBalance());
            messageArea.append("Deposited $"+amount+"\n");
        }
        catch(NumberFormatException e){
            messageArea.append("Invalid amount\n");
        }
    }
    private void withdraw(){
        try{
            double amount = Double.parseDouble(amountField.getText());
            if(account.withdraw(amount)){
                balanceLabel.setText("Current Balance: $"+account.getBalance());
                messageArea.append("Withdrawn $"+amount+"\n");
            }
            else{
                messageArea.append("Insufficient amount\n");
            }

        }catch(NumberFormatException e){
            messageArea.append("Invalid amount\n");
        }
    }
    private void clearTransactions() {
        messageArea.setText("");
    }
    private void checkBalance(){
        balanceLabel.setText("Current Balance: $"+account.getBalance());
        messageArea.append("Checked Balance: $"+account.getBalance()+"\n");
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                BankAccount account=new BankAccount(1500.00);
                ATMFrame frame=new ATMFrame(account);
                frame.setVisible(true);
            }
        });
    }

}
