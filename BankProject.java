import javax.swing.*;
import java.awt.*;

/**
 * This project of a bank account allows users to create
 * an account, sign in, and monitor their money.
 * There will also be a graph that shows the user
 * the amount of money that they deposited or withdrew.
 * It will also display the overall balance of the account.
 */

public class BankProject
{
    public static void main(String[] args)
    {
        JFrame frame = new LoginFrame();
        frame.setVisible(true);
        frame.setTitle("Bank Simulation");
        frame.setSize(550,400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Dimension screenDimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x =   ((screenDimension.width - frame.getWidth()) / 2);
        int y =   ((screenDimension.height - frame.getHeight()) / 2);

        frame.setLocation(x,y);

    }
}
