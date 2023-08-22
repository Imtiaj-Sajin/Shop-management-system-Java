import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.RandomAccessFile;

public class DashboardWithSidebar extends JFrame {
    private JPanel contentPanel;
    private JPanel currentBodyPanel;

    public DashboardWithSidebar() {
        setTitle("Admin Dashboard");
        setSize(1000, 700);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Create sidebar
        JPanel sidebar = new JPanel();
        sidebar.setPreferredSize(new Dimension(200, getHeight()));
        sidebar.setBackground(new Color(33, 37, 41));

        // Create sidebar buttons with icons
        JButton productAllButton = createSidebarButton("All Products", "images/all.png");
        JButton productAddButton = createSidebarButton("Product Add", "images/add.png");
        JButton productDeleteButton = createSidebarButton("Product Delete", "images/bin.png");
        JButton sellerUpdateButton = createSidebarButton("Seller Update", "images/update.png");
        JButton sellerAddButton = createSidebarButton("Seller Add", "images/add.png");
        JButton sellerDeleteButton = createSidebarButton("Seller Delete", "images/bin.png");
        JButton settingsButton = createSidebarButton("Settings", "images/settings.png");

        sidebar.setLayout(new GridLayout(0, 1));
        sidebar.add(productAllButton);
        sidebar.add(productAddButton);
        sidebar.add(productDeleteButton);
        sidebar.add(sellerUpdateButton);
        sidebar.add(sellerAddButton);
        sidebar.add(sellerDeleteButton);
        sidebar.add(settingsButton);

        // Set a default body panel
        currentBodyPanel = new JPanel();
        currentBodyPanel.setLayout(new BorderLayout());
        JLabel defaultLabel = new JLabel("             have a nice day!                ");
        defaultLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
        currentBodyPanel.add(defaultLabel, BorderLayout.CENTER);

        // Create main content panel
        contentPanel = new JPanel(new BorderLayout());
        contentPanel.add(currentBodyPanel, BorderLayout.CENTER);

        // Create header panel
        JPanel headerPanel = new JPanel();
        JLabel headerLabel = new JLabel("Admin Site");
        headerLabel.setFont(new Font("Tahoma", Font.BOLD, 24));
        headerPanel.setBackground(new Color(0, 123, 255));
        headerPanel.add(headerLabel);

        // Add components to main content panel
        contentPanel.add(headerPanel, BorderLayout.NORTH);
        contentPanel.add(currentBodyPanel, BorderLayout.CENTER);

        // Add sidebar and main content panel to the frame
        setLayout(new BorderLayout());
        add(sidebar, BorderLayout.WEST);
        add(contentPanel, BorderLayout.CENTER);

        // Add action listeners to sidebar buttons
        productAddButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switchToBodyPanel(new AddProductPanel());
            }
        });

        productDeleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switchToBodyPanel(new DeleteProductPanel());
            }
        });
        sellerAddButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switchToBodyPanel(new SellerAddPanel());
            }
        });
        settingsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switchToBodyPanel(new AdvertisementPanel());
            }
        });
        sellerUpdateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switchToBodyPanel(new SellerUpdatePanel());
            }
        });
        // Add action listeners to sidebar buttons
        productAllButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
        switchToBodyPanel(new AllProductsPanel());
    }
});


    }

     private void reduceProductQuantityInFile(String productName, int quantityToReduce) {
        try {
            RandomAccessFile file = new RandomAccessFile("products.txt", "rw");

            String line;
            long pointer = 0;
            while ((line = file.readLine()) != null) {
                String[] productInfo = line.split(",");
                if (productInfo.length >= 5 && productInfo[0].equalsIgnoreCase(productName)) {
                    int currentQuantity = Integer.parseInt(productInfo[2]);
                    int newQuantity = currentQuantity - quantityToReduce;
                    if (newQuantity < 0) {
                        newQuantity = 0;
                    }
                    productInfo[2] = Integer.toString(newQuantity);
                    String updatedLine = String.join(",", productInfo);
                    file.seek(pointer);
                    file.writeBytes(updatedLine + System.lineSeparator());
                    break;
                }
                pointer = file.getFilePointer();
            }

            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private JButton createSidebarButton(String text, String iconPath) {
        JButton button = new JButton(text, new ImageIcon(iconPath));
        button.setVerticalTextPosition(SwingConstants.BOTTOM);
        button.setHorizontalTextPosition(SwingConstants.CENTER);
        button.setPreferredSize(new Dimension(180, 60));
        button.setFont(new Font("Tahoma", Font.PLAIN, 14));
        button.setForeground(Color.WHITE);
        button.setBackground(new Color(33, 37, 41));
        button.setBorderPainted(false);
        return button;
    }

    private void switchToBodyPanel(JPanel panel) {
        currentBodyPanel.removeAll();
        currentBodyPanel.add(panel, BorderLayout.CENTER);
        currentBodyPanel.revalidate();
        currentBodyPanel.repaint();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new DashboardWithSidebar().setVisible(true);
            }
        });
    }
}
