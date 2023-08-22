import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ShopManagementApp {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            createAndShowGUI();
        });
    }

    private static void createAndShowGUI() {
        JFrame frame = new JFrame("Shop Management App");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 700);

        JPanel mainPanel = new JPanel(new BorderLayout());

        JLabel titleLabel = new JLabel("Welcome to Shop Management App");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        mainPanel.add(titleLabel, BorderLayout.NORTH);

        JButton productsButton = new JButton("Manage Products");
        JButton customersButton = new JButton("Manage Customers");
        JButton salesButton = new JButton("Manage Sales");

        JPanel buttonsPanel = new JPanel(new GridLayout(3, 1, 10, 10));
        buttonsPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        buttonsPanel.add(productsButton);
        buttonsPanel.add(customersButton);
        buttonsPanel.add(salesButton);

        mainPanel.add(buttonsPanel, BorderLayout.CENTER);

        productsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Open the products management interface
                // Implement this part based on your application's needs
            }
        });

        customersButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Open the customers management interface
                // Implement this part based on your application's needs
            }
        });

        salesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Open the sales management interface
                // Implement this part based on your application's needs
            }
        });

        frame.add(mainPanel);
        frame.setVisible(true);
    }
}
