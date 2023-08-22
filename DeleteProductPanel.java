import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DeleteProductPanel extends JPanel {

    private JTable table;
    private List<String[]> products;

    public DeleteProductPanel() {
        setLayout(new BorderLayout());
        setBackground(new Color(240, 240, 240)); // Light gray background

        // Create the table model and table
        DefaultTableModel tableModel = new DefaultTableModel();
        table = new JTable(tableModel);
        table.setBackground(new Color(255, 255, 255)); // White background

        // Add columns to the table
        tableModel.addColumn("Product Name");
        tableModel.addColumn("Price");
        tableModel.addColumn("Quantity");
        tableModel.addColumn("Category");
        tableModel.addColumn("Product ID");

        // Read product data from file and populate the table
        products = readProductsFromFile();
        for (String[] product : products) {
            tableModel.addRow(product);
        }

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createEmptyBorder()); // Remove border
        add(scrollPane, BorderLayout.CENTER);

        JButton deleteButton = new JButton("Delete Selected Product");
        deleteButton.setBackground(new Color(220, 20, 60)); // Crimson red color
        deleteButton.setForeground(Color.WHITE);
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) {
                    int confirmResult = JOptionPane.showConfirmDialog(
                            DeleteProductPanel.this,
                            "Are you sure you want to delete this product?",
                            "Confirm Deletion",
                            JOptionPane.YES_NO_OPTION
                    );

                    if (confirmResult == JOptionPane.YES_OPTION) {
                        products.remove(selectedRow);
                        updateTableData(tableModel);
                        writeProductsToFile();
                    }
                }
            }
        });

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.setBackground(new Color(240, 240, 240)); // Light gray background
        buttonPanel.add(deleteButton);

        add(buttonPanel, BorderLayout.SOUTH);
    }

    private List<String[]> readProductsFromFile() {
        List<String[]> products = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader("products.txt"));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] productInfo = line.split(",");
                products.add(productInfo);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return products;
    }

    private void updateTableData(DefaultTableModel tableModel) {
        tableModel.setRowCount(0); // Clear the table
        for (String[] product : products) {
            tableModel.addRow(product);
        }
    }

    private void writeProductsToFile() {
        try {
            FileWriter writer = new FileWriter("products.txt");
            for (String[] product : products) {
                writer.write(String.join(",", product) + "\n");
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
