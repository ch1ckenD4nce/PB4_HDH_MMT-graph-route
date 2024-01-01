package graph_route;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;





public class Server_GUI {
	private List<Socket> clientSockets = new ArrayList<>();
	
	private JFrame frame;

    private JTextArea logTextArea;
    private ServerSocket serverSocket;
    private boolean isServerRunning;
    public static final int port = 1234;
    static Connection con = null;
	ResultSet rs = null;
	static PreparedStatement pst = null;
    
    
    
    public static void main(String[] args) {
    EventQueue.invokeLater(new Runnable() {
        public void run() {
            try {
                Server_GUI window = new Server_GUI();
                window.frame.setVisible(true);
               
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    });
}
    
    public Server_GUI() {
        initialize();
        con = database.mycon();
     
    }
    
    private void initialize() {
    	 frame = new JFrame();
         frame.setTitle("Server");
         frame.setBounds(100, 100, 748, 454);
         frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         frame.getContentPane().setLayout(null);
         frame.setBackground(Color.WHITE);



         JButton startButton = new JButton("START");
         startButton.setBackground(new Color(0x48b281));

         startButton.addActionListener(new ActionListener() {
             public void actionPerformed(ActionEvent e) {
                 startServer();
             }
         });
//         startButton.setBounds(357, 64, 100, 36);
         startButton.setBounds(200, 64, 90, 36);
         startButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
         frame.getContentPane().add(startButton);

         JButton stopButton = new JButton("STOP");
         stopButton.setBackground(new Color(0x48b281));
         stopButton.addActionListener(new ActionListener() {
             public void actionPerformed(ActionEvent e) {
                 stopServer();
             }
         });
         stopButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
         stopButton.setBounds(450, 64, 90, 36);
         frame.getContentPane().add(stopButton);

         logTextArea = new JTextArea();
         logTextArea.setEditable(false);
         JScrollPane scrollPane = new JScrollPane(logTextArea);
         scrollPane.setBounds(140, 126, 441, 236);
         frame.getContentPane().add(scrollPane);
    }
    
    
    private void startServer() {
        if (!isServerRunning) {
           
        	 try {
                 serverSocket = new ServerSocket(port);
                 logTextArea.append("Server is running on port " + port + ". Waiting for clients...\n");
                 
                 isServerRunning = true;

                 new Thread(new Runnable() {
                     @Override
                     public void run() {
                         while (isServerRunning) {
                             try {
                                 Socket clientSocket = serverSocket.accept();
                                 
                                 logTextArea.append("Client connected: " + clientSocket + "\n");
                                 clientSockets.add(clientSocket);
                                 DataInputStream dis = new DataInputStream(clientSocket.getInputStream()); 
                             	
                	             DataOutputStream dos = new DataOutputStream(clientSocket.getOutputStream()); 
                	          logTextArea.append("Assigning new thread for this client\n");
                	             System.out.println("Assigning new thread for this client"); 

                	             // create a new thread object 
                	
                	             Thread t = new ClientHandler(clientSocket, dis, dos); 

                	             // Invoking the start() method 
                	             t.start(); 

                             } catch (IOException e) {
                                 e.printStackTrace();
                             }
                         }
                     }
                 }).start();
             } catch (IOException e) {
             	
                 e.printStackTrace();
             }
        }
    }
    
    
    private void stopServer() {
    	 if (isServerRunning) {
             try {
                 isServerRunning = false;
                 serverSocket.close();
                 
                 // Đóng tất cả các kết nối cụ thể với máy khách
                 for (Socket clientSocket : clientSockets) {
                     clientSocket.close();
                 }
                 
                 logTextArea.append("Server stopped.\n");
             } catch (IOException e) {
                 e.printStackTrace();
             }
         }
    }
    
    class ClientHandler extends Thread  
    { 

    	 final DataInputStream dis; 
    	
    	 final DataOutputStream dos; 
    	
    	 final Socket s; 
    
	 // Constructor 
	
	 public ClientHandler(Socket s, DataInputStream dis, DataOutputStream dos)  
	
	 { 
	
	     this.s = s; 
	
	     this.dis = dis; 
	
	     this.dos = dos; 
	
	 } 



@Override

public void run()  

{ 

    String received; 


    while (true)  
    { 

    	   try {
	             // Kiểm tra xem Socket có đang mở không
	             if (!s.isClosed()) {
	                 received = dis.readUTF();
	                 logTextArea.append("\n"+ s + " Input: " + received + "\n");
	                 
	                 if (received.equals("Exit")) {
	                     logTextArea.append("\nClient " + this.s + " sends exit...\n");
	                     System.out.println("Client " + this.s + " sends exit...");
	                     
	                     System.out.println("Closing this connection.");

	                     this.s.close();
	                     logTextArea.append("\nConnection closed: " + this.s + "\n");
	                     System.out.println("Connection closed");

	                     break;
	                 }

	                 String ans = process(received);
	                 
	     			
	        		 logTextArea.append(s +": " + ans);
	        		dos.writeUTF(ans);
	        		dos.flush();

	            	
	             } else {
	                 // Socket đã đóng, thoát khỏi vòng lặp
	                 break;
	             }
	         } catch (IOException e) {
	             if (e instanceof SocketException && e.getMessage().equals("Socket closed")) {
	                 // Socket đã đóng, thoát khỏi vòng lặp
	                 break;
	             } else {
	                 e.printStackTrace();
	             }
	         }
    }       

    
    try

    { 

        this.dis.close(); 

        this.dos.close(); 

    }catch(IOException e){ 

        e.printStackTrace(); 

    } 

} 

private static String process(String message) {
	String ans = "";
	String[] strArray = message.split("\\s+");
	String status = strArray[0];
	if(status.equals("Login")) {
		String un = strArray[1];
		String ps = strArray[2];
		System.out.println(message + "\n" + un + ", " + ps);
		

			try {
				
				String sql = "SELECT * FROM user WHERE username = ? AND password = ?";
				pst = con.prepareStatement(sql);
				pst.setString(1, un); // user
				pst.setString(2, ps);//pass
				
				ResultSet rs = pst.executeQuery();
				if(rs.next()) {
					ans = "login is correct";
					System.out.println("\ncorrect" );
					
				}else {
					ans = "login is incorrect";
					System.out.println("\nIncorrect" );
					
					
				}
			} catch ( Exception e1) {
				// TODO: handle exception
				System.out.println(e1);
			}
			
	}else if(status.equals("Register")) {

		try {
		    String un = strArray[1];
		    String ps = strArray[2];

		    // Check if the username already exists
		    String checkUsernameQuery = "SELECT COUNT(*) FROM user WHERE username=?";
		    pst = con.prepareStatement(checkUsernameQuery);
		    pst.setString(1, un);
		    ResultSet resultSet = pst.executeQuery();

		    if (resultSet.next() && resultSet.getInt(1) > 0) {
		        // Username already exists, return an appropriate message
		        ans = "Choose a different username";
		        System.out.println("\nUsername already exists, choose a different one");
		    } else {
		        // Username is unique, proceed with the insert
		        String insertQuery = "INSERT INTO user (username, password) VALUES (?, ?)";
		        pst = con.prepareStatement(insertQuery);
		        pst.setString(1, un);
		        pst.setString(2, ps);

		        int rowsAffected = pst.executeUpdate();

		        if (rowsAffected > 0) {
		            ans = "Register is correct";
		            System.out.println("\nRegistration is correct");
		        } else {
		            ans = "Register is incorrect";
		            System.out.println("\nRegistration is incorrect");
		        }
		    }

		} catch (Exception e2) {
		    e2.printStackTrace();
		    // Handle the exception
		}

		
	}else if(status.equals("Dij")) {
		String un = strArray[1];
		
		
		try {
			
			String sql = "INSERT INTO data (user, matrix ) VALUES (?,?)";
			pst = con.prepareStatement(sql);
			pst.setString(1, un); // user
			pst.setString(2, message.substring(un.length() + 4)); // message without Dij + " " + un + " "
			
			
			int rowsAffected = pst.executeUpdate();

	        if (rowsAffected > 0) {
	            System.out.println("\nInsert is correct");
	        } else {
	            System.out.println("\nInsert is incorrect");
	        }
	        
		} catch ( Exception e1) {
			// TODO: handle exception
			System.out.println(e1);
		}
		
		int S = Integer.parseInt(strArray[2]);
		int F = Integer.parseInt(strArray[3]);
		int n = Integer.parseInt(strArray[4]);
		
		System.out.println(message);
		
		int[][] graph = new int[n + 5][n + 5];
		int cnt = 4;
		for(int i = 1; i <= n; i++) {
			for(int j = 1; j <= n; j++) {
				cnt++;
				graph[i][j] = Integer.parseInt(strArray[cnt]);
			}
		}
		
		 ans += "Dij" + " " +  Dijkstra(S, F, n, graph);
		 System.out.println("dij: "+ ans);
		
	}
	
	return ans;
}


private static String Dijkstra(int S, int F, int n, int graph[][]) {
	boolean[] visited = new boolean[1000 + 5];
	int[] trace = new int[1000 + 5];
	int[] distances = new int[1000 + 5];
	int MAX_VALUE = 9999;
	
	for(int i = 1; i <= n; i++) 
		for(int j = 1; j <= n; j++) if(graph[i][j] == 0)
			graph[i][j] = MAX_VALUE;

	
	for (int node = 1; node <= n; node++) {
		distances[node] = MAX_VALUE;
		visited[node] = false;
	}
	

	
	distances[S] = 0;
	trace[S] = S;
	for (int i = 1; i <= n; i++) {
		int uBest = 0; // tìm đỉnh u chưa dùng, có khoảng cách nhỏ nhất
		int Max = MAX_VALUE;
		for (int u = 1; u <= n; u++) {
            if(distances[u] <= Max && visited[u] == false) {
            	uBest = u;
            	Max = distances[u];
            }
		}
		//System.out.println(i + ": " + uBest);
	    // cải tiến các đường đi qua u
	    int u = uBest;
	    visited[u] = true;
	    for(int v = 1; v <= n; v++) if(!visited[v]){
	        if(v == u) continue;
	        int w = graph[u][v];
	        if(distances[v] > distances[u] + w) {
	            distances[v] = distances[u] + w;
	            trace[v] = u;
	        }
	    }

	
	}
	
	String ret ="";
	if(distances[F] == MAX_VALUE) {
		 ret = "Minimun distance between " + S + " and " + F + ": NOT FOUND\n";
		
	}
	else  ret = "Minimun distance between " + S + " and " + F + ": " + distances[F] + "\n";
	
	for(int i = F; i <= F; i++) {
		int u = i;
		if(distances[u] == MAX_VALUE) {
			ret = ret + i + ": NOT FOUND!\n";
			continue;
		}
		int[] kq = new int[1000 + 1];
		int cnt = 0;
		while(u != trace[u]) {
			kq[cnt] = u;
			cnt++;
			u = trace[u];
		}
		kq[cnt] = u;
		ret = ret + i + ": ";
		for(int j = cnt; j > 0; j--)
			ret = ret + kq[j] + "->";
		ret = ret + kq[0] + "\n";
	}
	ret = ret + "\n";
	
	for(int i = 1; i <= n; i++) {
		if(i == F) continue;
		int u = i;
		if(distances[u] == MAX_VALUE) {
			ret = ret + i + ": NOT FOUND!\n";
			continue;
		}
		int[] kq = new int[n + 1];
		int cnt = 0;
		while(u != trace[u]) {
			kq[cnt] = u;
			cnt++;
			u = trace[u];
		}
		kq[cnt] = u;
		ret = ret + i + ": ";
		for(int j = cnt; j > 0; j--)
			ret = ret + kq[j] + " -> ";
		ret = ret + kq[0] + "\n";
	}
	
	
	
	return ret;
}
    

    
    }
}
