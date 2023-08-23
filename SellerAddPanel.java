import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class SellerAddPanel extends JPanel {

    private ArrayList<Seller> sellers = new ArrayList<>();
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public SellerAddPanel() {
        Font labelFont = new Font("Tahoma", Font.PLAIN, 18); 
        Font fieldFont = new Font("Tahoma", Font.PLAIN, 16); 

        setLayout(new BorderLayout());
        setBackground(new Color(240, 240, 240)); 
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(Color.WHITE); 
        JPanel inputPanel = new JPanel(new GridLayout(7, 2, 10, 10));
        inputPanel.setBackground(Color.WHITE);

        JLabel nameLabel = new JLabel(" Seller Name:");
        nameLabel.setFont(labelFont); 
        JTextField nameField = new JTextField();
        nameField.setFont(fieldFont);

        JLabel userIdLabel = new JLabel(" User ID:");
        userIdLabel.setFont(labelFont); 
        JTextField userIdField = new JTextField();
        userIdField.setFont(fieldFont);

        JLabel passwordLabel = new JLabel(" Password:");
        passwordLabel.setFont(labelFont);
        JPasswordField passwordField = new JPasswordField();
        passwordField.setFont(fieldFont);

        JLabel birthdateLabel = new JLabel(" Birthdate:");
        birthdateLabel.setFont(labelFont);
        JFormattedTextField birthdateField = new JFormattedTextField(new Date());
        birthdateField.setFont(fieldFont);

        JLabel addressLabel = new JLabel(" Address:");
        addressLabel.setFont(labelFont);
        JTextField addressField = new JTextField();
        addressField.setFont(fieldFont);

        JLabel phoneLabel = new JLabel(" Phone Number:");
        phoneLabel.setFont(labelFont);
        JTextField phoneField = new JTextField();
        phoneField.setFont(fieldFont);

        JLabel emailLabel = new JLabel(" Email:");
        emailLabel.setFont(labelFont);
        JTextField emailField = new JTextField();
        emailField.setFont(fieldFont);

        inputPanel.add(nameLabel);
        inputPanel.add(nameField);
        inputPanel.add(userIdLabel);
        inputPanel.add(userIdField);
        inputPanel.add(passwordLabel);
        inputPanel.add(passwordField);
        inputPanel.add(birthdateLabel);
        inputPanel.add(birthdateField);
        inputPanel.add(addressLabel);
        inputPanel.add(addressField);
        inputPanel.add(phoneLabel);
        inputPanel.add(phoneField);
        inputPanel.add(emailLabel);
        inputPanel.add(emailField);

        mainPanel.add(inputPanel, BorderLayout.CENTER);

        JButton registerButton = new JButton("Register");
        registerButton.setBackground(new Color(0, 123, 255)); // Blue color
        registerButton.setForeground(Color.WHITE);
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                String userId = userIdField.getText();
                String password = new String(passwordField.getPassword());
                Date birthdate = (Date) birthdateField.getValue();
                String address = addressField.getText();
                String phone = phoneField.getText();
                String email = emailField.getText();

                if (name.isEmpty() || userId.isEmpty() || password.isEmpty() ||
                    birthdate == null || address.isEmpty() || phone.isEmpty() || email.isEmpty()) {
                    JOptionPane.showMessageDialog(SellerAddPanel.this, "Please fill in all fields.", "Missing Fields", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                // Create a new Seller instance
                Seller newSeller = new Seller(name, userId, password, birthdate, address, phone, email);

                // Add the seller to the list
                sellers.add(newSeller);
                writeSellersToFile();

                JOptionPane.showMessageDialog(SellerAddPanel.this, "Seller registered successfully!", "Registration Successful", JOptionPane.INFORMATION_MESSAGE);

                // Clear input fields after registration
                nameField.setText("");
                userIdField.setText("");
                passwordField.setText("");
                birthdateField.setValue(new Date());
                addressField.setText("");
                phoneField.setText("");
                emailField.setText("");
            }
        });

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.setBackground(Color.WHITE);
        buttonPanel.add(registerButton);

        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(mainPanel);
    }

    private void writeSellersToFile() {
        try {
            FileWriter writer = new FileWriter("sellers.txt", true); // Open in append mode
            for (Seller seller : sellers) {
                String line = String.format("%s,%s,%s,%s,%s,%s,%s",
                        seller.name, seller.userId, seller.password,
                        dateFormat.format(seller.birthdate), seller.address,
                        seller.phoneNumber, seller.email);
                writer.write(line + "\n");
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    

    // Seller class
    static class Seller {
        private String name;
        private String userId;
        private String password;
        private Date birthdate;
        private String address;
        private String phoneNumber;
        private String email;

        public Seller(String name, String userId, String password, Date birthdate, String address, String phoneNumber, String email) {
            this.name = name;
            this.userId = userId;
            this.password = password;
            this.birthdate = birthdate;
            this.address = address;
            this.phoneNumber = phoneNumber;
            this.email = email;
        }
    }
}
