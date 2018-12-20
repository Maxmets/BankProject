import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * First frame that opens in which the user can either sign up
 * or register.
 */


public class LoginFrame extends JFrame
{

    private static int TEXT_FIELD_WIDTH = 20;

   private JLabel username = new JLabel("Username:");
    private JLabel password = new JLabel("Password:");

    private JTextField usernameEntry = new JTextField(TEXT_FIELD_WIDTH);
    private JTextField passwordEntry = new JTextField(TEXT_FIELD_WIDTH);

    private JButton login = new JButton("Login");
    private JButton register = new JButton("Click Here to Register");

    private JPanel mainPanel = new JPanel(new BorderLayout());


    private String firstName;
    private String lastName;
    private String usernameLoginEntry;
    private String passwordLoginEntry;
    private String accountNumber;



    public LoginFrame()
    {
        Components();
    }

    public void Components()
    {
         JPanel topPanel = new JPanel();

        topPanel.add(username);
        topPanel.add(usernameEntry);

        JPanel middlePanel = new JPanel();

        middlePanel.add(password);
        middlePanel.add(passwordEntry);

        JPanel southPanel = new JPanel();

        southPanel.add(login);
        southPanel.add(register);


        /**
         * This class is used to read the user credentials from a file
         * and store it in a txt file
         */

        class LoginListener implements ActionListener
        {
            public void actionPerformed(ActionEvent event)
            {
                File fileReader = new File("LoginInfo.txt");
                try
                {
                    Scanner in = new Scanner(fileReader);

                    while(in.hasNext())
                    {
                        firstName = in.next();
                        lastName = in.next();
                        accountNumber = in.next();
                        usernameLoginEntry = in.next();
                        passwordLoginEntry = in.next();


                    }

                    if(usernameLoginEntry.equals(usernameEntry.getText()) && passwordLoginEntry.equals(passwordEntry.getText()))
                    {


                        dispose();
                        BankFrame bankFrame = new BankFrame();
                        bankFrame.setSize(1050,600);
                        bankFrame.setTitle(firstName + "" + lastName + " Bank Account");
                        bankFrame.setVisible(true);

                        Dimension screenDimension = Toolkit.getDefaultToolkit().getScreenSize();
                        int x = (int) ((screenDimension.width - bankFrame.getWidth()) / 2);
                        int y = (int) ((screenDimension.height - bankFrame.getHeight()) / 2);

                        bankFrame.setLocation(x,y);


                    }
                    else
                    {
                        JOptionPane.showMessageDialog(null , "Incorrect username or password" , "Login Error", JOptionPane.ERROR_MESSAGE);
                    }


                }
                catch (FileNotFoundException e)
                {
                    System.out.println("File was not found ");
                }

            }
        }

        /**
         * If the user does not have an account this will redirect
         * the user to the register frame
         */

        ActionListener loginListner = new LoginListener();

        login.addActionListener(loginListner);

        class RegisterListener implements ActionListener
        {
            public void actionPerformed(ActionEvent event)
            {
                    setEnabled(true);
                    RegisterFrame rF = new RegisterFrame();
                    rF.setVisible(true);
                    rF.setSize(550,400);
                    rF.setTitle("Register");

                Dimension screenDimension = Toolkit.getDefaultToolkit().getScreenSize();
                int x =   ((screenDimension.width - rF.getWidth()) / 2);
                int y =   ((screenDimension.height - rF.getHeight()) / 2);

                rF.setLocation(x,y);
            }
        }

        ActionListener registerListener = new RegisterListener();

        register.addActionListener(registerListener);

        mainPanel.add(topPanel, BorderLayout.NORTH);
        mainPanel.add(middlePanel, BorderLayout.CENTER);
        mainPanel.add(southPanel, BorderLayout.SOUTH);

        add(mainPanel);
    }
}
