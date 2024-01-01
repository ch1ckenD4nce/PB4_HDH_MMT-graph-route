package graph_route;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class database {
	public static Connection mycon() {
		Connection con = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/graph", "root", "123456789");
            System.out.println("Thành công");
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO: handle exception
			System.out.println(e);
		}
		return con;
		
	}

}
