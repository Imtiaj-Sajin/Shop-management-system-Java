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
        setVisible(true);

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
        JButton logOutButton = createSidebarButton("logOut", "images/logOut.png");

        sidebar.setLayout(new GridLayout(0, 1));
        sidebar.add(productAllButton);
        sidebar.add(productAddButton);
        sidebar.add(productDeleteButton);
        sidebar.add(sellerUpdateButton);
        sidebar.add(sellerAddButton);
        sidebar.add(sellerDeleteButton);
        sidebar.add(logOutButton);

        // Set a default body panel
        currentBodyPanel = new JPanel();
        currentBodyPanel.setLayout(new BorderLayout());

        ImageIcon imageIcon = new ImageIcon("images/TazGrocery.png"); // Provide the correct path
        JLabel imageLabel = new JLabel(imageIcon);
        currentBodyPanel.add(imageLabel, BorderLayout.CENTER);

        // JLabel defaultLabel = new JLabel("             have a nice day!                ");
        // defaultLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
        // currentBodyPanel.add(defaultLabel, BorderLayout.CENTER);

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
                headerLabel.setText("Product Add");
            }
        });

        productDeleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switchToBodyPanel(new DeleteProductPanel());
                headerLabel.setText("Product Delete");
            }
        });
        sellerAddButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switchToBodyPanel(new SellerAddPanel());
                headerLabel.setText("Seller Add");
            }
        });
        logOutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                headerLabel.setText("Log Out");
                setVisible(false);
                new LoginGUI();

            }
        });
        sellerUpdateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switchToBodyPanel(new SellerUpdatePanel());
                headerLabel.setText("Seller Update");
            }
        });
        sellerDeleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switchToBodyPanel(new SellerDeletePanel());
                headerLabel.setText("Seller Delete");
            }
        });
        // Add action listeners to sidebar buttons
                productAllButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                switchToBodyPanel(new AllProductsPanel());
                headerLabel.setText("All Products");
            }
        });


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
