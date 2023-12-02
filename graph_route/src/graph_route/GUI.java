package graph_route;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import javax.swing.BorderFactory;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;



import javax.swing.JFrame;

public class GUI  {

	private Client client;
	
	private JFrame frame;
	
	private JPanel topPanel, centerPanel, bottomPanel, topSJPanel, centerChucNangJPanel, centerChucNangJPanelTop, centerChucNangJPanelCenter;

	 private DrawPanel centerDrawJPanel;

	private JRadioButton r1, r2;

	private JButton duongDiButton, addEdgeBtn, newBtn, deleteVertexBtn, printMatrixButton, deleteEdgeBtn;
	private JLabel dauJLabel, cuoiJLabel, trongJLabel, tenLabel, sourceLabel, destinationLabel;
	private JTextField dauField, cuoiField, trongField, tenField, soureField, destinationField;

	private JTextArea textAreaLeft, textAreaCenter;
	private JScrollPane drawJScrollPane, centerText;
	
	
	public GUI(Client client) {
		this.client = client;
		frame = new JFrame();
		frame.setLayout(new BorderLayout());

		// TOP
		topPanel = new JPanel();
		topPanel.setLayout(new BorderLayout());
//		
		r1 = new JRadioButton("Undirected Graph"); // vo huong
		r2 = new JRadioButton("Directed Graph"); // co huong

		
		
		topSJPanel = new JPanel();
		topSJPanel.setLayout(null);
		topSJPanel.setBorder(BorderFactory.createTitledBorder("Find the shortest route"));
		topSJPanel.setPreferredSize(new Dimension(660, 105));
//		topSJPanel.setBounds(2, 2, 660, 110);

		sourceLabel = new JLabel("Source:");
		sourceLabel.setBounds(60, 25, 70, 28);
		
		soureField = new JTextField();
		soureField.setBounds(120, 25, 110, 28);
//		
		destinationLabel = new JLabel("Destination:");
		destinationLabel.setBounds(370, 25, 70, 28);
//		
		destinationField = new JTextField();
		destinationField.setBounds(450, 25, 110, 28);
//
		duongDiButton = new JButton("Dijkstra");
		duongDiButton.setBackground(new Color(0x48b281));
		duongDiButton.setBounds(270, 65, 110, 28);
//		
		topSJPanel.add(sourceLabel);
		topSJPanel.add(soureField);
		topSJPanel.add(destinationLabel);
		topSJPanel.add(destinationField);

		topSJPanel.add(duongDiButton);

		//draw panel
		
		centerDrawJPanel = new DrawPanel();
		drawJScrollPane = new JScrollPane(centerDrawJPanel);
		topSJPanel.setBackground(Color.WHITE);
		topPanel.add(topSJPanel, BorderLayout.NORTH);
		topPanel.add(drawJScrollPane, BorderLayout.CENTER);
		

		// CENTER
		centerPanel = new JPanel();
		centerPanel.setLayout(new BorderLayout());

		// CENTER CHUCNANG
		centerChucNangJPanel = new JPanel();
		
		centerChucNangJPanel.setPreferredSize(new Dimension(300, 800));
		centerChucNangJPanel.setBorder(BorderFactory.createTitledBorder("Function"));
		centerChucNangJPanel.setLayout(new BorderLayout());
		centerChucNangJPanel.setBackground(Color.WHITE);

		// CENTER CHUCNANG TOP
		centerChucNangJPanelTop = new JPanel();
		centerChucNangJPanelTop.setBackground(Color.WHITE);
		centerChucNangJPanelTop.setPreferredSize(new Dimension(300, 200));
		centerChucNangJPanelTop.setBorder(BorderFactory.createTitledBorder("Edge & Vertex"));

		dauJLabel = new JLabel("Point 1:");
		cuoiJLabel = new JLabel("Point 2:");
		trongJLabel = new JLabel("Weight:");
		tenLabel = new JLabel("Name Vertex:");

		dauField = new JTextField(20);
		cuoiField = new JTextField(20);
		trongField = new JTextField(20);
		tenField = new JTextField(20);

		centerChucNangJPanelTop.setLayout(new GridLayout(4, 2));
		centerChucNangJPanelTop.add(dauJLabel);
		centerChucNangJPanelTop.add(dauField);
		centerChucNangJPanelTop.add(cuoiJLabel);
		centerChucNangJPanelTop.add(cuoiField);
		centerChucNangJPanelTop.add(trongJLabel);
		centerChucNangJPanelTop.add(trongField);
		centerChucNangJPanelTop.add(tenLabel);
		centerChucNangJPanelTop.add(tenField);

		// CENTER CHUCNANG CENTER
		centerChucNangJPanelCenter = new JPanel();
		centerChucNangJPanelCenter.setBackground(Color.WHITE);

		addEdgeBtn = new JButton("Add Edge");


		deleteEdgeBtn = new JButton("Delete Edge");


		newBtn = new JButton("New");


		deleteVertexBtn = new JButton("Delete Vertex");
	
		printMatrixButton = new JButton("Print");
		addEdgeBtn.setBackground(new Color(0x48b281));
		deleteEdgeBtn.setBackground(new Color(0x48b281));
		newBtn.setBackground(new Color(0x48b281));
		deleteVertexBtn.setBackground(new Color(0x48b281));
		printMatrixButton.setBackground(new Color(0x48b281));

		centerChucNangJPanelCenter.add(addEdgeBtn);
		centerChucNangJPanelCenter.add(deleteEdgeBtn);
		centerChucNangJPanelCenter.add(newBtn);
		centerChucNangJPanelCenter.add(deleteVertexBtn);
		centerChucNangJPanelCenter.add(printMatrixButton);


		centerChucNangJPanel.add(centerChucNangJPanelTop, BorderLayout.NORTH);
		centerChucNangJPanel.add(centerChucNangJPanelCenter, BorderLayout.CENTER);
	

		// BOTTOM
		bottomPanel = new JPanel();
		bottomPanel.setBackground(Color.WHITE);
		bottomPanel.setLayout(new BorderLayout());
		bottomPanel.setBorder(BorderFactory.createTitledBorder("Result"));
		
		// BOTTOM CENTER
		textAreaCenter = new JTextArea();
		textAreaCenter.setBackground(Color.WHITE);
		textAreaCenter.setFont(new Font("MV Boli", Font.PLAIN, 12));
		textAreaCenter.setForeground(Color.black);
//		textAreaCenter.setText("" + "\n");
		textAreaCenter.setBackground(Color.WHITE);
		textAreaCenter.setFocusable(false);
		
		centerText = new JScrollPane(textAreaCenter);
		centerText.setBackground(Color.WHITE);

		//Addddd
		bottomPanel.setPreferredSize(new Dimension(300, 450));
		bottomPanel.add(centerText, BorderLayout.CENTER);
		
		centerChucNangJPanel.add(bottomPanel, BorderLayout.SOUTH);
		
		// ADD
		frame.add(centerChucNangJPanel, BorderLayout.EAST);

		frame.add(topPanel, BorderLayout.CENTER);

		
		frame.setTitle("Graph Route");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setSize(1030, 800);
		frame.setVisible(true);
		r1.setEnabled(true);
		centerDrawJPanel.setStartUnDir(true);
		
		newBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				textAreaCenter.setText("");			
				r1.setEnabled(true);
				
				centerDrawJPanel.DrawNew();
				centerDrawJPanel.setStartDir(false);
				
			}
		});
		
		deleteVertexBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (!tenField.getText().equals("")) {
					int n = Integer.parseInt(tenField.getText());
					if (n > -1 && n <= centerDrawJPanel.graph.getMtk().size()) {
						centerDrawJPanel.deleteVertex(centerDrawJPanel.graph.isVertex(n));
						textAreaLeft.append("Vertex: " + tenField.getText() + " is deleted" + "\n");
						tenField.setText("");

					} else {
						JOptionPane.showMessageDialog(null, "Vertex name is invalid", "Error",
								JOptionPane.WARNING_MESSAGE);
						tenField.setText("");
					}

				} else {
					JOptionPane.showMessageDialog(null, "First fill name vertex and press delete again, please!",
							"Error", JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		
		deleteEdgeBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (!dauField.getText().equals("") && !cuoiField.getText().equals("")) {
					int d1 = Integer.parseInt(dauField.getText());
					int d2 = Integer.parseInt(cuoiField.getText());
					if (d1 > -1 && d1 <= centerDrawJPanel.graph.getMtk().size() && d2 > -1
							&& d2 <= centerDrawJPanel.graph.getMtk().size()) {
						centerDrawJPanel.deleteEdge(centerDrawJPanel.graph.isVertex(d1),
								centerDrawJPanel.graph.isVertex(d2));
						textAreaLeft
								.append("Edge: " + dauField.getText() + " - " + cuoiField.getText() + " is deleted" + "\n");
						dauField.setText("");
						cuoiField.setText("");

					} else {
						JOptionPane.showMessageDialog(null, "Vertex1, vertex2 are invalid", "Error",
								JOptionPane.WARNING_MESSAGE);
						dauField.setText("");
						cuoiField.setText("");
					}

				} else {
					JOptionPane.showMessageDialog(null, "First fill vertex1, vertex2 then press delete again. please!", "Error",
							JOptionPane.WARNING_MESSAGE);
				}
			
				
			}
		});
		
		addEdgeBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (!dauField.getText().equals("") && !cuoiField.getText().equals("") && !trongField.getText().equals("")) {
					int d = Integer.parseInt(dauField.getText());
					int c = Integer.parseInt(cuoiField.getText());
					String w = trongField.getText();
					if (d > -1 && d <= centerDrawJPanel.graph.getMtk().size() && c > -1
							&& c <= centerDrawJPanel.graph.getMtk().size()) {
						if (centerDrawJPanel.drawEdge(d, c, w)) {
							textAreaLeft.append("Add Edge from " + dauField.getText() + " to " + cuoiField.getText()
									+ " with weight is: " + trongField.getText() + "\n");
							centerDrawJPanel.setTrongso("");
							dauField.setText("");
							cuoiField.setText("");
							trongField.setText("");
						} else {
							JOptionPane.showMessageDialog(null, "Error!", "Error", JOptionPane.WARNING_MESSAGE);
							dauField.setText("");
							cuoiField.setText("");
							trongField.setText("");
						}

					} else {
						JOptionPane.showMessageDialog(null, "Point1, point2 are invalid. Please re-enter.", "Error",
								JOptionPane.WARNING_MESSAGE);
						dauField.setText("");
						cuoiField.setText("");

					}

				} else {
					JOptionPane.showMessageDialog(null, "First fill point1, point2 and then press add again, please!",
							"Error", JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		
	

		
		duongDiButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				for (Edge d : centerDrawJPanel.graph.getDanhSachCanh()) {
					if (d.weight == null) {
						textAreaCenter.setText("Weight is null");
					}
				}
				if (!soureField.getText().equals("")) {
					int S = Integer.parseInt(soureField.getText());
					int F = Integer.parseInt(destinationField.getText());
					
					int n = centerDrawJPanel.graph.getMtk().size();
					if(S<0 || F <0 || S > n || F>n) {
						JOptionPane.showMessageDialog(null, "Source point, destination point are invalid. Please re-reter!",
								"Error", JOptionPane.WARNING_MESSAGE);
						
					}else {
						String out = S + " " + F + " " + centerDrawJPanel.printARowOfMatrix();
						//System.out.println(out);
						sendMessage();
						//soureField.setText("");
						
					}
					
				
				} else
					JOptionPane.showMessageDialog(null, "First fill both of source point and destination point, then press agian, please!",
							"Error", JOptionPane.WARNING_MESSAGE);
			}
		});
	}
	
//

	private void sendMessage() {
		
		String out = soureField.getText() + " " + destinationField.getText() + " " + centerDrawJPanel.printARowOfMatrix();
		System.out.println("Client: " + out);
		// String message = inputTextField.getText();
        client.sendMessage(out);
        //System.out.println(out);
        if (!out.equalsIgnoreCase("Exit")) {
            String response = client.receiveMessage();
            textAreaCenter.append(response + "\n");
        }
    }
	public static void main(String[] args) {
		Client client = new Client("localhost", 5056);
		 SwingUtilities.invokeLater(() -> new GUI(client)); 
		
	}
}
