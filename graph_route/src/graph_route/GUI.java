package graph_route;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;


import javax.swing.BorderFactory;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;




import javax.swing.JFrame;

public class GUI  {
	//private static final long serialVersionUID = 1L;
	private Client client;
	
	public JFrame frame;
	
	private JPanel topPanel, centerPanel, bottomPanel, topSJPanel, centerChucNangJPanel, centerChucNangJPanelTop, centerChucNangJPanelCenter;

	private DrawPanel centerDrawJPanel;

	private JRadioButton r1;

	private JButton duongDiButton, addEdgeBtn, newBtn, deleteVertexBtn, printMatrixButton, deleteEdgeBtn, logoutBtn, chooseFileButton;
	private JLabel dauJLabel, cuoiJLabel, trongJLabel, tenLabel, sourceLabel, destinationLabel, label_username ;
	private JTextField dauField, cuoiField;

	public static JTextField trongField;

	public JTextField tenField;

	public JTextField soureField;

	public JTextField destinationField;

	private JTextArea  textAreaCenter;
	private JScrollPane drawJScrollPane, centerText;
	public GUI() {
		initialize();
	}
	
	public GUI(String un, Client client) {
		initialize();
		label_username.setText(un);
		this.client = client;
	}
	
	private void initialize() {
		
		frame = new JFrame();
		frame.setLayout(new BorderLayout());

		// TOP
		topPanel = new JPanel();
		topPanel.setLayout(new BorderLayout());
//		
		r1 = new JRadioButton("Undirected Graph"); 
		new JRadioButton("Directed Graph"); 

		
		
		topSJPanel = new JPanel();
		topSJPanel.setLayout(null);
		topSJPanel.setBorder(BorderFactory.createTitledBorder("Find the shortest route"));
		topSJPanel.setPreferredSize(new Dimension(660, 105));
//		topSJPanel.setBounds(2, 2, 660, 110);
		
		label_username = new JLabel("Username");
		label_username.setBounds(40,25,70,28);
		
		logoutBtn = new JButton("Log out");
		logoutBtn.setBackground(new Color(0x48b281));
		logoutBtn.setBounds(40, 63, 78, 28);
		
		
		sourceLabel = new JLabel("Source:");
		sourceLabel.setBounds(160, 25, 70, 28);
		
		soureField = new JTextField();
		soureField.setBounds(220, 25, 110, 28);
//		
		destinationLabel = new JLabel("Destination:");
		destinationLabel.setBounds(450, 25, 70, 28);
//		
		destinationField = new JTextField();
		destinationField.setBounds(530, 25, 110, 28);
//
		duongDiButton = new JButton("Dijkstra");
		duongDiButton.setBackground(new Color(0x48b281));
		duongDiButton.setBounds(370, 63, 110, 28);
//		
		
		topSJPanel.add(label_username);
		topSJPanel.add(logoutBtn);
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


		newBtn = new JButton("Clear All");


		deleteVertexBtn = new JButton("Delete Vertex");
	
		printMatrixButton = new JButton("Print Matrix");
		
		chooseFileButton = new JButton("Upload File");
		
		addEdgeBtn.setBackground(new Color(0x48b281));
		deleteEdgeBtn.setBackground(new Color(0x48b281));
		newBtn.setBackground(new Color(0x48b281));
		deleteVertexBtn.setBackground(new Color(0x48b281));
		printMatrixButton.setBackground(new Color(0x48b281));
		chooseFileButton.setBackground(new Color(0x48b281));

		centerChucNangJPanelCenter.add(addEdgeBtn);
		centerChucNangJPanelCenter.add(deleteEdgeBtn);
		
		centerChucNangJPanelCenter.add(deleteVertexBtn);
		centerChucNangJPanelCenter.add(newBtn);
		centerChucNangJPanelCenter.add(printMatrixButton);
		centerChucNangJPanelCenter.add(chooseFileButton);


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

		
		frame.setTitle("Graph Route ");
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
		
		printMatrixButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				textAreaCenter.setText(" ");
				String result = centerDrawJPanel.printARowOfMatrix();
				textAreaCenter.append(result + "\n");
				
				
			
				
			}
		});
		
		deleteVertexBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (!tenField.getText().equals("")) {
					centerDrawJPanel.graph.danhSachKq.clear();
					int n = Integer.parseInt(tenField.getText());
					if (n > -1 && n <= centerDrawJPanel.graph.getMtk().size()) {
						centerDrawJPanel.deleteVertex(centerDrawJPanel.graph.isVertex(n));
						
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
					centerDrawJPanel.graph.danhSachKq.clear();
					int d1 = Integer.parseInt(dauField.getText());
					int d2 = Integer.parseInt(cuoiField.getText());
					if (d1 > -1 && d1 <= centerDrawJPanel.graph.getMtk().size() && d2 > -1
							&& d2 <= centerDrawJPanel.graph.getMtk().size()) {
						centerDrawJPanel.deleteEdge(centerDrawJPanel.graph.isVertex(d1),
								centerDrawJPanel.graph.isVertex(d2));
					
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
		        if (!dauField.getText().equals("") && !cuoiField.getText().equals("") && !trongField.getText().equals("")) {
		            int d = Integer.parseInt(dauField.getText());
		            int c = Integer.parseInt(cuoiField.getText());
		            String w = trongField.getText();
		            
		            if (d > -1 && d <= centerDrawJPanel.graph.getMtk().size() && c > -1 && c <= centerDrawJPanel.graph.getMtk().size() && Integer.parseInt(w) > 0 && w != null ) {
		                if (centerDrawJPanel.drawEdge(d, c, w)) {
		                   
		                    // Clear the text fields after successful addition
		                    dauField.setText("");
		                    cuoiField.setText("");
		                    trongField.setText("");
		                } else {
		                    JOptionPane.showMessageDialog(null, "Error!", "Error", JOptionPane.WARNING_MESSAGE);
		                }
		            } else {
		                JOptionPane.showMessageDialog(null, "Point1, point2, weight are invalid. Please re-enter.", "Error",
		                        JOptionPane.WARNING_MESSAGE);
		                dauField.setText("");
		                cuoiField.setText("");
		                trongField.setText("");
		            }
		        } else {
		            JOptionPane.showMessageDialog(null, "First fill point1, point2 and then press add again, please!",
		                    "Error", JOptionPane.WARNING_MESSAGE);
		        }
		    }
		});
	


		logoutBtn.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		       
		        int option = JOptionPane.showConfirmDialog(frame, "Are you sure you want to logout?", "Logout Confirmation", JOptionPane.YES_NO_OPTION);
		        
		      
		        if (option == JOptionPane.YES_OPTION) {
		           
		            Login_form window = new Login_form(client);
		            window.frmLogin.setVisible(true);
		            frame.setVisible(false);
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
//						
//						String out = S + " " + F + " " + centerDrawJPanel.printARowOfMatrix();
						//System.out.println(out);
						textAreaCenter.setText("");
						centerDrawJPanel.graph.danhSachKq.clear();
						sendMessage();
						
						
					}
					
				} else
					JOptionPane.showMessageDialog(null, "First fill source vertex and press again please!!!",
							"Here is notice", JOptionPane.WARNING_MESSAGE);
			}
		});
		
		

		
		chooseFileButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				JFileChooser fileChooser;
				File selectedFile;
				fileChooser = new JFileChooser();
				selectedFile = null;
				int response = fileChooser.showOpenDialog(null);
				if(response == JFileChooser.APPROVE_OPTION) {
					selectedFile = fileChooser.getSelectedFile();
					String filename = selectedFile.getName();
					//fileNameLabel.setText(filename);
					textAreaCenter.setText(filename + "\n");
				}
				if(selectedFile != null) {
					try {
						FileReader fileReader = new FileReader(selectedFile);
						BufferedReader bufferedReader = new BufferedReader(fileReader);
						String matrixString = "";
						String line;
 
			            // Đọc từng dòng từ file và thêm vào chuỗi content
			            while ((line = bufferedReader.readLine()) != null) {
			                matrixString = matrixString + line + "\n";
			            }
			            bufferedReader.close();
			            fileReader.close();
			            
			            System.out.println(matrixString);
			            
			            String[] parts = matrixString.split("\\s+");
			            
			            if (parts.length < 3) {
		                    JOptionPane.showMessageDialog(null, "The file does not have enough data!!!", "Error", JOptionPane.WARNING_MESSAGE);
		                    return;
		                }

		                int numVertices;
		                try {
		                    numVertices = Integer.parseInt(parts[0]);
		                } catch (NumberFormatException ex) {
		                    JOptionPane.showMessageDialog(null, "Invalid number of vertices!!!", "Error", JOptionPane.WARNING_MESSAGE);
		                    return;
		                }

		                if (parts.length != numVertices * numVertices + 3) {
		                    JOptionPane.showMessageDialog(null, "Insufficient or excess data!!!", "Error", JOptionPane.WARNING_MESSAGE);
		                    return;
		                }
			            
			            int[][] adjacencyMatrix = new int[numVertices][numVertices];
			            for (int i = 0; i < numVertices; i++) {
			                for (int j = 0; j < numVertices; j++) {
			                    adjacencyMatrix[i][j] = Integer.parseInt(parts[i * numVertices + j + 1 + 2]);
			                }
			            }
			            try {
		                    int sourceVertex = Integer.parseInt(parts[1]);
		                    int destinationVertex = Integer.parseInt(parts[2]);

		                    if (sourceVertex < 1 || sourceVertex > numVertices || destinationVertex < 1 || destinationVertex > numVertices) {
		                        JOptionPane.showMessageDialog(null, "Invalid source or destination vertex!!!", "Error", JOptionPane.WARNING_MESSAGE);
		                        return;
		                    }

		                    soureField.setText(parts[1]);
		                    destinationField.setText(parts[2]);

		                    int index = 3;
		                    for (int i = 0; i < numVertices; i++) {
		                        for (int j = 0; j < numVertices; j++) {
		                            adjacencyMatrix[i][j] = Integer.parseInt(parts[index++]);
		                        }
		                    }

		                    centerDrawJPanel.drawGraphFromMatrix(adjacencyMatrix);
		                } catch (NumberFormatException ex) {
		                    JOptionPane.showMessageDialog(null, "Data is not an integer!!!", "Error", JOptionPane.WARNING_MESSAGE);
		                }
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						JOptionPane.showMessageDialog(null, "Error reading file: " + e1.getMessage(), "Error", JOptionPane.WARNING_MESSAGE);
						e1.printStackTrace();
					}
		            
				}
			}
		});
		
		
		
		
	}
	
	
	

	
private void sendMessage() {
		
		String out ="Dij" + " " + label_username.getText() + " " + soureField.getText() + " " + destinationField.getText() + " " + centerDrawJPanel.printARowOfMatrix();
		System.out.println("Client: " + out);
		
        client.sendMessage(out);
      
        if (!out.equalsIgnoreCase("Exit") ) {
            String response = client.receiveMessage();
            String[] strArray = response.split("\\s+");
        	String status = strArray[0];
        	if(status.equals("Dij")) {
        		System.out.println("Kq:" + strArray[9]);
        		
        		 textAreaCenter.append(response.substring(4) + "\n");
        		 
        		 String[] strKq = strArray[9].split("->");
        			System.out.println("\nsplit: " + strKq[0] );
        		
        	        for (int i = 0; i < strKq.length; i++) {
        	         
        	            if (i + 1 < strKq.length) {
        	                centerDrawJPanel.drawEdgeKq(Integer.parseInt(strKq[i]), Integer.parseInt(strKq[i + 1]));
        	              
        	            }
        	        }

        	}
            
        }
    }


}
