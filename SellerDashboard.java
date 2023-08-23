import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SellerDashboard extends JFrame implements ActionListener {
    private JPanel contentPanel;
    private JPanel currentBodyPanel;
    JButton sellAllButton = createSidebarButton("Sell product", "images/all.png");
    JButton recentSaleButton = createSidebarButton("Recent sales", "images/add.png");
    JButton productAllButton = createSidebarButton("Product Delete", "images/bin.png");
    JButton sellerWages = createSidebarButton("Seller Update", "images/update.png");


    public SellerDashboard() {
        this.setTitle("Seller Dashboard");
        this.setSize(1000, 700);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);

        JPanel sidebar = new JPanel();
        sidebar.setPreferredSize(new Dimension(200, getHeight()));
        sidebar.setBackground(new Color(33, 37, 41));

  

        sidebar.setLayout(new GridLayout(0, 1));
        sidebar.add(sellAllButton);
        sidebar.add(recentSaleButton);
        sidebar.add(sellerWages);
        sidebar.add(productAllButton);

        currentBodyPanel = new JPanel();
        currentBodyPanel.setLayout(new BorderLayout());
        JLabel defaultLabel = new JLabel("             have a nice day!                ");
        defaultLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
        currentBodyPanel.add(defaultLabel, BorderLayout.CENTER);

        contentPanel = new JPanel(new BorderLayout());
        contentPanel.add(currentBodyPanel, BorderLayout.CENTER);

        JPanel headerPanel = new JPanel();
        JLabel headerLabel = new JLabel("Seller Site");
        headerLabel.setFont(new Font("Tahoma", Font.BOLD, 24));
        headerPanel.setBackground(new Color(0, 123, 0));
        headerPanel.add(headerLabel);

        
        contentPanel.add(headerPanel, BorderLayout.NORTH);
        contentPanel.add(currentBodyPanel, BorderLayout.CENTER);

        
        setLayout(new BorderLayout());
        add(sidebar, BorderLayout.WEST);
        add(contentPanel, BorderLayout.CENTER);
        // Add action listeners to sidebar buttons
sellAllButton.addActionListener(this);
recentSaleButton.addActionListener(this);
productAllButton.addActionListener(this);
sellerWages.addActionListener(this);

        




        // Add action listeners to sidebar buttons
        


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

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==sellAllButton)
		{
            switchToBodyPanel(new SellProduct());
        }
        else if(e.getSource()==recentSaleButton)
		{
            switchToBodyPanel(new AllProductsPanel());
        }
        else if(e.getSource()==productAllButton)
		{
            switchToBodyPanel(new AllProductsPanel());
        }
        else if(e.getSource()==sellerWages)
		{
            switchToBodyPanel(new SellProduct());
        }
    }


}