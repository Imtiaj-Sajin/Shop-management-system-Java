import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OrderDetails extends JPanel {
    private JLabel label;
    private JButton backButton;
    private JButton printButton;

    public OrderDetails(String s) {
        this.setLayout(new BorderLayout());

        label = new JLabel("<html>"+s+"</html>");
        label.setVerticalAlignment(JLabel.TOP); 
        label.setHorizontalAlignment(JLabel.LEFT); 
        label.setFont(new Font("new times roman", 1,20) );
        
        this.add(label, BorderLayout.CENTER);

        backButton = new JButton("Back");
        printButton = new JButton("Print");

        JPanel buttonPanel = new JPanel(); 
        buttonPanel.add(backButton);
        buttonPanel.add(printButton);
        add(buttonPanel, BorderLayout.SOUTH);

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeAll();
                add(new SellProduct(), BorderLayout.CENTER);
                revalidate();
                repaint();
            }
        });

        printButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(OrderDetails.this, "Printing");
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                JFrame frame = new JFrame("Custom Panel Example");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

                OrderDetails customPanel = new OrderDetails("s");
                frame.getContentPane().add(customPanel);

                frame.pack();
                frame.setVisible(true);
            }
        });
    }
    
}