import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AllProductsPanel extends JPanel {

    private JTable table;

    public AllProductsPanel() {
        setLayout(new BorderLayout());

        // Create the table model and table
        DefaultTableModel tableModel = new DefaultTableModel();
        table = new JTable(tableModel);

        // Add columns to the table
        tableModel.addColumn("Product Name");
        tableModel.addColumn("Price");
        tableModel.addColumn("Quantity");
        tableModel.addColumn("Category");
        tableModel.addColumn("Product ID");

        // Read product data from file and populate the table
        List<String[]> products = readProductsFromFile();
        for (String[] product : products) {
            tableModel.addRow(product);
        }

        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);
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
}
