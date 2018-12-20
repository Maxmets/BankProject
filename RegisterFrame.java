import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;


/**
 * This is the register frame that takes in the user input
 * and turns them into credentials necessary to sign in and create
 * an account
 */


public class RegisterFrame extends JFrame
{
    private static final int TEXT_FIELD_WIDTH = 20;

    private JLabel name = new JLabel("First Name: ");
    private JLabel familyName = new JLabel("Last Name: ");
    private JLabel username = new JLabel("Username: ");
    private JLabel password = new JLabel("Password: ");

    private JTextField firstName = new JTextField(TEXT_FIELD_WIDTH);
    private JTextField lastName = new JTextField(TEXT_FIELD_WIDTH);
    private JTextField userNameEntry = new JTextField(TEXT_FIELD_WIDTH);
    private JTextField passwordEntry = new JTextField(TEXT_FIELD_WIDTH);

    private JButton register = new JButton("Register");
    private JButton back = new JButton("Back");


    private JPanel registerPanel = new JPanel();





    public RegisterFrame()
    {
        RegisterComponents();
    }

    public void RegisterComponents()
    {
            JPanel registerNamePanel = new JPanel();

            registerNamePanel.add(name);
            registerNamePanel.add(firstName);

            JPanel registerLastnamePanel = new JPanel();

            registerLastnamePanel.add(familyName);
            registerLastnamePanel.add(lastName);

            JPanel registerUsernamePanel = new JPanel();

            registerUsernamePanel.add(username);
            registerUsernamePanel.add(userNameEntry);

            JPanel registerPasswordPanel = new JPanel();

            registerPasswordPanel.add(password);
            registerPasswordPanel.add(passwordEntry);

            JPanel registerButtonPanel = new JPanel();
            registerButtonPanel.add(register);
            registerButtonPanel.add(back);

        /**
         * This class writes to a txt file the user inputted information
         */

        class RegisterScreenListener implements ActionListener
            {
                public void actionPerformed(ActionEvent event)
                {
                    try
                    {

                        PrintWriter writer = new PrintWriter("LoginInfo.txt");

                        Random accountNumberGenerator = new Random();

                        int accountNumberGenerated = accountNumberGenerator.nextInt(100000000) + 10000000;

                        if (!firstName.getText().isEmpty() && !lastName.getText().isEmpty() && !userNameEntry.getText().isEmpty() && !passwordEntry.getText().isEmpty())
                        {
                            writer.write(firstName.getText() + "\n");
                            writer.write(lastName.getText() + "\n");
                            writer.write(Integer.toString(accountNumberGenerated) + "\n");
                            writer.write(userNameEntry.getText() + "\n");
                            writer.write(passwordEntry.getText() + "\n");
                            writer.close();
                        }
                        else
                        {
                            if(firstName.getText().isEmpty())
                            {
                                JOptionPane.showMessageDialog(null, "Enter first name", "Register Incomplete", JOptionPane.WARNING_MESSAGE);

                            }

                            if (lastName.getText().isEmpty())
                            {
                                JOptionPane.showMessageDialog(null, "Enter last name", "Register Incomplete", JOptionPane.WARNING_MESSAGE);
                            }

                            if (userNameEntry.getText().isEmpty())
                            {
                                JOptionPane.showMessageDialog(null, "Enter username", "Register Incomplete", JOptionPane.WARNING_MESSAGE);
                            }

                            if (passwordEntry.getText().isEmpty())
                            {
                                JOptionPane.showMessageDialog(null, "Enter password", "Register Incomplete", JOptionPane.WARNING_MESSAGE);
                            }
                        }
                     }
                     catch (FileNotFoundException e)
                     {
                         System.out.println("File not Found ");
                     }
                }
            }

            ActionListener registerScreenListener = new RegisterScreenListener();
            register.addActionListener(registerScreenListener);

            class BackListener implements ActionListener
            {
                public void actionPerformed(ActionEvent event)
                {
                    dispose();
                }
            }

            ActionListener backToLogin = new BackListener();
            back.addActionListener(backToLogin);

            registerPanel.add(registerNamePanel);
            registerPanel.add(registerLastnamePanel);
            registerPanel.add(registerUsernamePanel);
            registerPanel.add(registerPasswordPanel);
            registerPanel.add(registerButtonPanel);
            add(registerPanel);
    }

}
