package graph_route;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;





public class Client {
	private Socket socket;
    private DataInputStream dataInputStream;
    private DataOutputStream dataOutputStream;
   
    public Client(String serverAddress, int serverPort) {
        try {
        	socket = new Socket(serverAddress, serverPort);
            dataInputStream = new DataInputStream(socket.getInputStream());
            dataOutputStream = new DataOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void sendMessage(String message) {
        try {
        	dataOutputStream.writeUTF(message);
            dataOutputStream.flush();
            System.out.println("send");

            if (message.equalsIgnoreCase("Exit")) {
            	socket.close();
                dataInputStream.close();
                dataOutputStream.close();
                System.exit(0);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public String receiveMessage() {
        try {
        	String response = dataInputStream.readUTF();

           
            return response;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

    	
    } 
    
//    public void sendLogin(String message) {
//        try {
//        	dataOutputStream.writeUTF(message);
//            dataOutputStream.flush();
//            System.out.println("Login");
//
//            if (message.equalsIgnoreCase("Exit")) {
//            	socket.close();
//                dataInputStream.close();
//                dataOutputStream.close();
//                System.exit(0);
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//    
//    public String receiveLogin() {
//        try {
//        	String response = dataInputStream.readUTF();
//
//            return response;
//        } catch (IOException e) {
//            e.printStackTrace();
//            return null;
//        }
//    }
//    
}
