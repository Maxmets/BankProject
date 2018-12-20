import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;


/**
 * The bank frame will create the main screen of the program.
 * It will display the persons name, Balance, Balance Graph.
 * It will also allow the user to deposit, withdraw their money.
 */


public class BankFrame extends JFrame
{
    private static final int  TEXT_AREA_WIDTH = 20;
    private static final int TEXT_AREA_HEIGHT = 20;

    private static final int TEXT_FIELD_WIDTH = 20;

    private static final int TIMER_INTERVAL = 1000;

   private BankAccount PersonsAccount = new BankAccount(0);

   private DecimalFormat moneyFormat = new DecimalFormat("##.#");
   private  SimpleDateFormat dateFormat = new SimpleDateFormat("E MMM d hh:mm:ss Y");








    private JLabel Usersname = new JLabel(PersonsAccount.getFullName());
    private JLabel BalanceLabel = new JLabel( "$" + moneyFormat.format(PersonsAccount.getBalance()));
    private JLabel SavingsLabel = new JLabel("$" + moneyFormat.format(PersonsAccount.getSavingsBalance()));
    private JLabel time = new JLabel();



    private JTextArea recentTransactions = new JTextArea(TEXT_AREA_WIDTH, TEXT_AREA_HEIGHT);
    private JTextArea savingsTranscations = new JTextArea(TEXT_AREA_WIDTH, TEXT_AREA_HEIGHT);

    private JLabel accountNumber = new JLabel( "Account Number: " + PersonsAccount.getAccountNumber());

    private JLabel transactionTimes = new JLabel();
    private JLabel savingsTitle = new JLabel("Savings");

    private JPanel mainPanel = new JPanel(new BorderLayout());

    private JTextField moneyField = new JTextField(TEXT_FIELD_WIDTH);

    private JButton deposit = new JButton("Deposit");
    private JButton withdraw = new JButton("Withdraw");
    private JButton toSavings = new JButton("Transfer to Savings");
    private JButton toBalance = new JButton("Transfer to Balance");






    public BankFrame()
    {
        EastPanel();
        WestPanel();
        CentrePanel();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void EastPanel()
    {

        recentTransactions.setEditable(false);
        savingsTranscations.setEditable(false);

        JPanel eastTopPanel = new JPanel(new BorderLayout());
        JPanel eastPanel = new JPanel(new BorderLayout());
        JPanel eastBottomPanel = new JPanel(new BorderLayout());

        accountNumber.setFont(new Font("Cambria",Font.BOLD, 35));
        savingsTitle.setFont(new Font("Cambria", Font.BOLD, 20));


        transactionTimes.setText("Transactions used: " + PersonsAccount.getTransactionTimes());


        eastTopPanel.add(accountNumber, BorderLayout.NORTH);
        eastTopPanel.add(recentTransactions, BorderLayout.CENTER);
        eastTopPanel.add(transactionTimes, BorderLayout.SOUTH);
        eastBottomPanel.add(savingsTitle,BorderLayout.NORTH);
        eastBottomPanel.add(savingsTranscations, BorderLayout.CENTER);
        eastPanel.add(eastTopPanel, BorderLayout.EAST);
        eastPanel.add(eastBottomPanel, BorderLayout.AFTER_LAST_LINE);

        JScrollPane scrollPaneSavings = new JScrollPane(savingsTranscations);
        eastBottomPanel.add(scrollPaneSavings);

        JScrollPane scrollPaneTranscations = new JScrollPane(recentTransactions);
        eastTopPanel.add(scrollPaneTranscations);

        mainPanel.add(eastPanel, BorderLayout.EAST);
        add(mainPanel);

    }

    public void WestPanel()
    {

        class Clock
        {
            SimpleDateFormat dateFormat = new SimpleDateFormat("hh:mm:ss");


            Timer myTimer = new Timer();
            TimerTask task = new TimerTask() {
                @Override
                public void run()
                {
                    Date date = new Date();
                    time.setText( dateFormat.format(date));
                }
            };

            public void start()
            {
                myTimer.scheduleAtFixedRate(task,0000,TIMER_INTERVAL);
            }
        }

            Usersname.setFont(new Font("Cambria", Font.BOLD, 35));
            BalanceLabel.setFont(new Font("Cambria", Font.BOLD, 35));
            SavingsLabel.setFont(new Font("Cambria", Font.BOLD, 35));
            time.setFont(new Font("Cambria",Font.BOLD,20));

            Clock currentClock = new Clock();
            currentClock.start();

            JPanel westTopPanel = new JPanel(new BorderLayout());
            westTopPanel.add(Usersname , BorderLayout.NORTH);
            westTopPanel.add(BalanceLabel, BorderLayout.CENTER);
            westTopPanel.add(SavingsLabel, BorderLayout.SOUTH);
            westTopPanel.add(time, BorderLayout.AFTER_LAST_LINE);

            JPanel westPanel = new JPanel(new BorderLayout());
            westPanel.add(westTopPanel, BorderLayout.NORTH);

            mainPanel.add(westPanel, BorderLayout.WEST);
            add(mainPanel);
    }

    public void CentrePanel() {


        JPanel northTopPanel = new JPanel();
        northTopPanel.add(moneyField);

        JPanel northPanel = new JPanel();
        northPanel.add(deposit);
        northPanel.add(withdraw);
        northPanel.add(toSavings);
        northPanel.add(toBalance);

        JPanel completeNorthPanel = new JPanel(new BorderLayout());
        completeNorthPanel.add(northTopPanel, BorderLayout.NORTH);
        completeNorthPanel.add(northPanel, BorderLayout.CENTER);


        mainPanel.add(completeNorthPanel, BorderLayout.CENTER);

        add(mainPanel);

        class depositListener implements ActionListener {
            public void actionPerformed(ActionEvent event) {
                if (!moneyField.getText().isEmpty()) {

                    Date timeDate = new Date();

                    double amount = Double.parseDouble(moneyField.getText());
                    PersonsAccount.deposit(amount);
                    recentTransactions.append("$ " + moneyFormat.format(PersonsAccount.getBalance()) + "  +$ " + moneyField.getText() + " Deposited " + dateFormat.format(timeDate) + "\n");
                    BalanceLabel.setText("$" + moneyFormat.format(PersonsAccount.getBalance()));
                    if (PersonsAccount.getTransactionTimes() > 10) {
                        transactionTimes.setText("You are over the limit of transactions: " + PersonsAccount.getTransactionTimes());
                    } else {
                        transactionTimes.setText("Transactions used: " + PersonsAccount.getTransactionTimes());

                    }

                } else {
                    JOptionPane.showMessageDialog(null, "No amount was entered in the field", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }

        ActionListener depositActionListener = new depositListener();
        deposit.addActionListener(depositActionListener);


        class withdrawListener implements ActionListener {
            public void actionPerformed(ActionEvent event) {
                if (!moneyField.getText().isEmpty()) {

                    Date timeDate = new Date();

                    double amount = Double.parseDouble(moneyField.getText());
                    PersonsAccount.withdraw(amount);
                    recentTransactions.append("$ " + moneyFormat.format(PersonsAccount.getBalance()) + "   -$ " + moneyField.getText() + " Withdrawn " + dateFormat.format(timeDate) +"\n");
                    BalanceLabel.setText("$" + moneyFormat.format(PersonsAccount.getBalance()));
                    if (PersonsAccount.getTransactionTimes() > 10) {
                        transactionTimes.setText("You are over the limit of transactions: " + PersonsAccount.getTransactionTimes());
                    } else {
                        transactionTimes.setText("Transactions used: " + PersonsAccount.getTransactionTimes());

                    }


                } else {
                    JOptionPane.showMessageDialog(null, "No amount was entered in the field", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }

        ActionListener withdrawActionListener = new withdrawListener();
        withdraw.addActionListener(withdrawActionListener);

        class toSavingsListener implements ActionListener {
            public void actionPerformed(ActionEvent event) {
                if (!moneyField.getText().isEmpty()) {

                    Date timeDate = new Date();

                    if (PersonsAccount.getBalance() > 0) {
                        double amount = Double.parseDouble(moneyField.getText());
                        PersonsAccount.moveBalancetoSavings(amount);
                        recentTransactions.append("$ " + moneyFormat.format(PersonsAccount.getBalance()) + "   -$ " + moneyField.getText() + " Transferred to Savings " + dateFormat.format(timeDate) +"\n");
                        savingsTranscations.append("$ " + moneyFormat.format(PersonsAccount.getSavingsBalance()) + "  +$ " + moneyField.getText() + " Transferred from Balance " + dateFormat.format(timeDate) +"\n");
                        BalanceLabel.setText("$" + moneyFormat.format(PersonsAccount.getBalance()));
                        SavingsLabel.setText("$" + moneyFormat.format(PersonsAccount.getSavingsBalance()));
                        if (PersonsAccount.getTransactionTimes() > 10) {
                            transactionTimes.setText("You are over the limit of transactions: " + PersonsAccount.getTransactionTimes());
                        } else {
                            transactionTimes.setText("Transactions used: " + PersonsAccount.getTransactionTimes());

                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Transfer exceeds Balance", "Transfer Error", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "No amount was entered in the field", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }


        ActionListener ActionListenertoSavingsListener = new toSavingsListener();
        toSavings.addActionListener(ActionListenertoSavingsListener);

        class toBalanceListener implements ActionListener {
            public void actionPerformed(ActionEvent event) {
                if (!moneyField.getText().isEmpty()) {

                    Date timeDate = new Date();

                    if (PersonsAccount.getSavingsBalance() > 0) {
                        double amount = Double.parseDouble(moneyField.getText());
                        PersonsAccount.moveSavingstoBalance(amount);
                        recentTransactions.append("$ " + moneyFormat.format(PersonsAccount.getBalance()) + "   +$ " + moneyField.getText() + " Transferred from Savings " + dateFormat.format(timeDate) +"\n");
                        savingsTranscations.append("$ " + moneyFormat.format(PersonsAccount.getSavingsBalance()) + "  -$ " + moneyField.getText() + " Transferred to Balance " + dateFormat.format(timeDate) +"\n");

                        BalanceLabel.setText("$" + moneyFormat.format(PersonsAccount.getBalance()));
                        SavingsLabel.setText("$" + moneyFormat.format(PersonsAccount.getSavingsBalance()));
                        if (PersonsAccount.getTransactionTimes() > 10) {
                            transactionTimes.setText("You are over the limit of transactions: " + PersonsAccount.getTransactionTimes());
                        } else {
                            transactionTimes.setText("Transactions used: " + PersonsAccount.getTransactionTimes());
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Transfer exceeds Savings", "Transfer Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
                else
                    {
                        JOptionPane.showMessageDialog(null, "No amount was entered in the field", "Error", JOptionPane.ERROR_MESSAGE);
                    }

            }
        }

        ActionListener ActionListenertoBalanceListener = new toBalanceListener();
        toBalance.addActionListener(ActionListenertoBalanceListener);
    }


}
