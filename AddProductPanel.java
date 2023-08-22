import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;

public class AddProductPanel extends JPanel {

    public AddProductPanel() {
        setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel(new GridLayout(6, 2, 10, 10));
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        JLabel nameLabel = new JLabel("Product Name:");
        JTextField nameField = new JTextField();

        JLabel priceLabel = new JLabel("Price:");
        JTextField priceField = new JTextField();

        JLabel quantityLabel = new JLabel("Quantity:");
        JTextField quantityField = new JTextField();

        JLabel categoryLabel = new JLabel("Category:");
        JComboBox<String> categoryComboBox = new JComboBox<>(new String[]{"Electronics", "Clothing", "Home Appliances", "Books"});

        JLabel idLabel = new JLabel("Product ID:");
        JTextField idField = new JTextField();

        JButton addButton = new JButton("Add Product");
        JButton cancelButton = new JButton("Cancel");

        inputPanel.add(nameLabel);
        inputPanel.add(nameField);
        inputPanel.add(priceLabel);
        inputPanel.add(priceField);
        inputPanel.add(quantityLabel);
        inputPanel.add(quantityField);
        inputPanel.add(categoryLabel);
        inputPanel.add(categoryComboBox);
        inputPanel.add(idLabel);
        inputPanel.add(idField);

        buttonPanel.add(addButton);
        buttonPanel.add(cancelButton);

        add(inputPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String productName = nameField.getText();
                String priceText = priceField.getText();
                String quantityText = quantityField.getText();
                String category = (String) categoryComboBox.getSelectedItem();
                String productIdText = idField.getText();

                if (productName.isEmpty() || priceText.isEmpty() || quantityText.isEmpty() || productIdText.isEmpty()) {
                    JOptionPane.showMessageDialog(AddProductPanel.this, "Please fill in all fields.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                double price;
                int quantity;
                int productId;

                try {
                    price = Double.parseDouble(priceText);
                    quantity = Integer.parseInt(quantityText);
                    productId = Integer.parseInt(productIdText);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(AddProductPanel.this, "Invalid numeric input.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Store the product information in a comma-separated format
                storeProductToFile(productName, price, quantity, category, productId);

                // Display a confirmation message
                String confirmation = "Product added and stored in file.";
                JOptionPane.showMessageDialog(AddProductPanel.this, confirmation, "Product Added", JOptionPane.INFORMATION_MESSAGE);

                // Clear input fields after adding
                nameField.setText("");
                priceField.setText("");
                quantityField.setText("");
                idField.setText("");
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Optionally perform cleanup or other actions
                // For example: You can clear input fields here
            }
        });
    }

    private void storeProductToFile(String productName, double price, int quantity, String category, int productId) {
        String productInfo = productName + "," + price + "," + quantity + "," + category + "," + productId + "\n";
        try {
            // Replace "products.txt" with your desired file name and path
            FileWriter writer = new FileWriter("products.txt", true); // true for appending
            writer.write(productInfo);
            writer.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
