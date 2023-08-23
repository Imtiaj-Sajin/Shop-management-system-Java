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

public class SellerDeletePanel extends JPanel {

    private JTable table;
    private List<String[]> sellers;

    public SellerDeletePanel() {
        setLayout(new BorderLayout());
        setBackground(new Color(240, 240, 240)); // Light gray background

        // Create the table model and table
        DefaultTableModel tableModel = new DefaultTableModel();
        table = new JTable(tableModel);
        table.setBackground(new Color(255, 255, 255)); // White background

        // Add columns to the table
        tableModel.addColumn("Name");
        tableModel.addColumn("User ID");
        tableModel.addColumn("Password");
        tableModel.addColumn("Birthdate");
        tableModel.addColumn("Address");
        tableModel.addColumn("Phone");
        tableModel.addColumn("Email");

        // Read seller data from file and populate the table
        sellers = readsellersFromFile();
        for (String[] seller : sellers) {
            tableModel.addRow(seller);
        }

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createEmptyBorder()); // Remove border
        add(scrollPane, BorderLayout.CENTER);

        JButton deleteButton = new JButton("Delete Selected seller");
        deleteButton.setBackground(new Color(220, 20, 60)); // Crimson red color
        deleteButton.setForeground(Color.WHITE);
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) {
                    int confirmResult = JOptionPane.showConfirmDialog(
                            SellerDeletePanel.this,
                            "Are you sure you want to delete this seller?",
                            "Confirm Deletion",
                            JOptionPane.YES_NO_OPTION
                    );

                    if (confirmResult == JOptionPane.YES_OPTION) {
                        sellers.remove(selectedRow);
                        updateTableData(tableModel);
                        writesellersToFile();
                    }
                }
            }
        });

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.setBackground(new Color(240, 240, 240)); // Light gray background
        buttonPanel.add(deleteButton);

        add(buttonPanel, BorderLayout.SOUTH);
    }

    private List<String[]> readsellersFromFile() {
        List<String[]> sellers = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader("sellers.txt"));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] sellerInfo = line.split(",");
                sellers.add(sellerInfo);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sellers;
    }

    private void updateTableData(DefaultTableModel tableModel) {
        tableModel.setRowCount(0); // Clear the table
        for (String[] seller : sellers) {
            tableModel.addRow(seller);
        }
    }

    private void writesellersToFile() {
        try {
            FileWriter writer = new FileWriter("sellers.txt");
            for (String[] seller : sellers) {
                writer.write(String.join(",", seller) + "\n");
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
