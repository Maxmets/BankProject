import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;

public class BankAccount extends RegisterFrame    //MOVE THIS TO THE BANKFRAME IT MUST CREATE THE OBJECT WITH THE PERSONS NAME IN THE BANKFRAME
{
    private String name;

    private double balance;
    private double savingsBalance;

    private int accountNumber;
    private static int transactionTimes;
    private static final double TRANSACTION_FEE = 5.20;

    private String firstName;
    private String lastName;
    private String accountNumberString;


    public BankAccount()
    {
        try
        {
            File filereader = new File("LoginInfo.txt");

            Scanner in = new Scanner(filereader);

            firstName = in.next();
            lastName = in.next();
            accountNumberString = in.next();

        }
        catch (FileNotFoundException e)
        {
            System.out.println("File was not Found");
        }

        balance = 0;
        savingsBalance = 0;
        accountNumber = 0;
        transactionTimes = 0;
    }


    public BankAccount(double balance)
    {


        try
        {
            File filereader = new File("LoginInfo.txt");

            Scanner in = new Scanner(filereader);

            firstName = in.next();
            lastName = in.next();
            accountNumberString = in.next();


        }
        catch (FileNotFoundException e)
        {
            System.out.println("File was not Found");
        }

        this.balance = balance;
        savingsBalance = 0;
        transactionTimes = 0;

        accountNumber = Integer.parseInt(accountNumberString);
    }



    public String getFullName()
    {
        return firstName + " " + lastName;
    }

    public void deposit(double money)
    {
        balance += money;
        transactionTimes++;
        if(transactionTimes > 10)
        {
            balance -= TRANSACTION_FEE;
        }
    }

    public void withdraw(double money)
    {
        balance -= money;
        transactionTimes++;
        if(transactionTimes > 10)
        {
            balance -= TRANSACTION_FEE;
        }
    }

    public double getBalance()
    {
        return balance;
    }

    public double getSavingsBalance()
    {
        return savingsBalance;
    }

    public void moveBalancetoSavings(double money)
    {
        balance -= money;
        savingsBalance += money;
        if(transactionTimes > 10)
        {
            balance -= TRANSACTION_FEE;
        }

    }

    public void moveSavingstoBalance(double money)
    {
        savingsBalance -= money;
        balance += money;
        transactionTimes++;
        if(transactionTimes > 10)
        {
            balance -= TRANSACTION_FEE;
        }

    }

    public int getTransactionTimes()
    {
        return transactionTimes;
    }

    public int getAccountNumber()
    {
        return accountNumber;
    }




}
