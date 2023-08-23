import javax.swing.*;

import java.awt.Font;
import java.awt.event.*;  
import static javax.swing.JOptionPane.showMessageDialog;
public class LoginGUI implements ActionListener{  
    
	private JButton lgnBtn;
    private JButton signBtn;
    private JButton exBtn;
    private JLabel lusn;
    private JLabel welcom;
    private JLabel lpass;
    private JTextField uname;
    private JPasswordField upass;
	JFrame frame;
	
	//ASSOCIATION WITH USER CLASS
	// User u1, u2;
	// User users[];
	
    LoginGUI(){  
	
		// u1 = new User("Mazid", "1234");
		// u2 = new User("ABCD", "9876");
		// users = new User[4];
		// users[0] = u1;
		// users[1] = u2;
		
        //create frame
		frame = new JFrame ("System");
        //construct components
        lgnBtn = new JButton ("Login");
        signBtn = new JButton ("Sign Up");
        exBtn = new JButton ("Exit");
        lusn = new JLabel ("Username");
        lpass = new JLabel ("Password");
        welcom=new JLabel("Welcome");
        uname = new JTextField ();
        upass = new JPasswordField ();

        
		
        welcom.setBounds (230, 10, 160, 30);		
        lgnBtn.setBounds (150, 185, 140, 30);
        exBtn.setBounds (300, 185, 140, 30);
        lusn.setBounds (150, 105, 100, 25);
        lpass.setBounds (150, 145, 100, 25);
        uname.setBounds (300, 105, 140, 25);
        upass.setBounds (300, 145, 140, 25);

        welcom.setFont(new Font("times new roman", 0, 30));
		
		
		//addActionListener
		lgnBtn.addActionListener(this);

        //add components
        frame.add(welcom);
        frame.add (lgnBtn);
        frame.add (signBtn);
        frame.add (exBtn);
        frame.add (lusn);
        frame.add (lpass);
        frame.add (uname);
        frame.add (upass);

        
		
		//frame properties
		//adjust size and set layout
        frame.setSize (624, 400);
		frame.setLocationRelativeTo(null);//to center screen gui
        frame.setLayout (null);
		frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
        frame.getContentPane();
        frame.setVisible (true); 
    }         
    public void actionPerformed(ActionEvent e) {  
         
        if(e.getSource()==lgnBtn)
		{  
			String user = uname.getText();
			String pass = upass.getText();
			//FOR SINGLE USER, NOT A SMART WAY!!
			if(user.equals("s") && pass.equals("s"))
			{
				
				new SellerDashboard();
				frame.setVisible(false);
				
			}
            else if(user.equals("a") && pass.equals("a")){
				new DashboardWithSidebar();
				frame.setVisible(false);
            }
				
			else
			{
					showMessageDialog(null, "Invalid Username or password!");
			}
				
			
			//USING ARRAY FOR MULTIPLE USERS
			// for(int i = 0; i<users.length; i++)
			// {
			// 	if(user.equals(users[i].getUsername()) && pass.equals(users[i].getPassword()))
			// 	{
			// 		flag = 1;
			// 		break;
			// 		//new Dashboard();
			// 		//frame.setVisible(false);
				
			// 	}
				
			// }
            
			
        }  
    }  
}