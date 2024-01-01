package graph_route;
import java.awt.Color;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;


import java.awt.Font;


import javax.swing.JButton;
import javax.swing.JTextField;


import java.awt.event.ActionListener;

import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPasswordField;

public class Login_form {
	
	private Client client;

	public JFrame frmLogin;
	private JTextField tf_username;
	private JPasswordField tf_password;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Client client = new Client("localhost", 1234);
					Login_form window = new Login_form(client);
					window.frmLogin.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Login_form() {
		initialize();
	}
	public Login_form(Client client) {
		this.client = client;
		initialize();

	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmLogin = new JFrame();
		frmLogin.setTitle("Login");
		frmLogin.setBounds(100, 100, 529, 335);
		frmLogin.setBackground(Color.WHITE);
		frmLogin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmLogin.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Login");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 22));
		lblNewLabel.setBounds(218, 24, 83, 45);
		frmLogin.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Username");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_1.setBounds(66, 80, 83, 34);
		frmLogin.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Password");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_1_1.setBounds(66, 138, 95, 34);
		frmLogin.getContentPane().add(lblNewLabel_1_1);
		
		tf_username = new JTextField();
		tf_username.setFont(new Font("Tahoma", Font.PLAIN, 15));
		tf_username.setBounds(168, 82, 264, 34);
		frmLogin.getContentPane().add(tf_username);
		tf_username.setColumns(10);
		
		tf_password = new JPasswordField();
		tf_password.setBounds(171, 138, 261, 38);
		frmLogin.getContentPane().add(tf_password);
		JButton btnNewButton = new JButton("Login");
		
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String un = tf_username.getText();
				String ps = tf_password.getText();
				System.out.println(un + ", " + ps);
//				String out = un + ", " + ps;
				sendLogin();
//			
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnNewButton.setBounds(186, 207, 157, 40);
		btnNewButton.setBackground(new Color(0x48b281));
		frmLogin.getContentPane().add(btnNewButton);
		
		JLabel lblNewLabel_2 = new JLabel("Haven't an account login? Sign up");
		lblNewLabel_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Register_f regis = new Register_f(client);
				regis.frmSignup.setVisible(true);
				frmLogin.setVisible(false);
			}
		});
		lblNewLabel_2.setFont(new Font("Tahoma", Font.ITALIC, 12));
		lblNewLabel_2.setBounds(298, 257, 192, 28);
		frmLogin.getContentPane().add(lblNewLabel_2);
		
		
	}
	
	
	private void sendLogin() {

		String un = tf_username.getText();
		String ps = tf_password.getText();
		String out = "Login" + " " + un + " " + ps;

		System.out.println("Client: " + out);
		client.sendMessage(out);
		 if (!out.equalsIgnoreCase("Exit")) {
	            String response = client.receiveMessage();
	          
	            if(response.equals("login is correct")) {
					System.out.println("\ncorrect" );
					
					GUI window = new GUI(un,client);
					window.frame.setVisible(true);
					frmLogin.setVisible(false);
				}else if(response.equals("login is incorrect")) {
					System.out.println("\nIncorrect" );
					JOptionPane.showMessageDialog(null, "Username or password incorrect.");
					
				}
	        }
//		
    }
	
}
