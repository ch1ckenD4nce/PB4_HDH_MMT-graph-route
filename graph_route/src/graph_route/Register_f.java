package graph_route;
import java.awt.Color;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;



import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;

public class Register_f {
	private Client client;

	public JFrame frmSignup;
	private JTextField tf_username;
	private JPasswordField tf_password;
	private JPasswordField tf_rePassword;



	/**
	 * Create the application.
	 */
	public Register_f() {
		initialize();
	}
	public Register_f(Client client) {
		this.client = client;
		initialize();
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmSignup = new JFrame();
		frmSignup.setTitle("Sign up");
		frmSignup.setBounds(100, 100, 591, 400);
		frmSignup.setBackground(Color.WHITE);
		frmSignup.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmSignup.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Sign up");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 22));
		lblNewLabel.setBounds(218, 24, 100, 45);
		frmSignup.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Username");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_1.setBounds(66, 80, 83, 34);
		frmSignup.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Password");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_1_1.setBounds(66, 138, 95, 34);
		frmSignup.getContentPane().add(lblNewLabel_1_1);
		
		tf_username = new JTextField();
		tf_username.setFont(new Font("Tahoma", Font.PLAIN, 15));
		tf_username.setBounds(228, 79, 264, 34);
		frmSignup.getContentPane().add(tf_username);
		tf_username.setColumns(10);
		
		tf_password = new JPasswordField();
		tf_password.setBounds(228, 138, 264, 34);
		frmSignup.getContentPane().add(tf_password);
		
		tf_rePassword = new JPasswordField();
		tf_rePassword.setBounds(228, 186, 264, 34);
		frmSignup.getContentPane().add(tf_rePassword);
		JButton btnNewButton = new JButton("Sign up");
		btnNewButton.setBackground(new Color(0x48b281));
		
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String un = tf_username.getText();
				String ps = tf_password.getText();
				String re_ps = tf_rePassword.getText();
				
//				System.out.println(un + ", " + ps + " , " + re_ps );
//				String out = un + ", " + ps + " , " + re_ps;
				 if(!ps.equals(re_ps)) {
						JOptionPane.showMessageDialog(null, "Password and confirm password do not match");
						tf_password.setText("");
						tf_rePassword.setText("");
					}
					else if(ps.equals(re_ps)) {
						sendRegister();
					}
	
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnNewButton.setBounds(203, 255, 157, 40);
		frmSignup.getContentPane().add(btnNewButton);
		
		JLabel lblNewLabel_2 = new JLabel("Have an account login? Log in");
		lblNewLabel_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//new Login_form();
				Login_form window = new Login_form(client);
				window.frmLogin.setVisible(true);
				frmSignup.setVisible(false);
			}
		});
		lblNewLabel_2.setFont(new Font("Tahoma", Font.ITALIC, 12));
		lblNewLabel_2.setBounds(314, 317, 196, 20);
		frmSignup.getContentPane().add(lblNewLabel_2);
		
		
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Confirm password");
		lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_1_1_1.setBounds(66, 186, 150, 34);
		frmSignup.getContentPane().add(lblNewLabel_1_1_1);
	}
	
	private void sendRegister() {

		String un = tf_username.getText();
		String ps = tf_password.getText();
		String out = "Register" + " " + un + " " + ps;

		System.out.println("Client: " + out);
		client.sendMessage(out);
		 if (!out.equalsIgnoreCase("Exit")) {
	            String response = client.receiveMessage();
	          
	            if(response.equals("Register is correct")) {
					System.out.println("\nRegister is correct" );
					  JOptionPane.showMessageDialog(null, "Register success");
			           
					
					frmSignup.setVisible(false);
			           
		            Login_form login =  new Login_form(client);
		            login.frmLogin.setVisible(true);
				}else if(response.equals("Register is incorrect")) {
					System.out.println("\nRegister is incorrect" );
					JOptionPane.showMessageDialog(null, "Register is unsuccessful");
					
				}else if(response.equals("Choose a different username")) {
					JOptionPane.showMessageDialog(null, "Username is available. Please choose another username!");
					
				}
	        }
		 
		 
		 
		
			}


}
