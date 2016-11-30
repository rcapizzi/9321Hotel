package businessLogic.jdbc;
import java.sql.*;

public class MysqlOperation {
	public Connection DBConnect() {
		try {
				Class.forName("com.mysql.jdbc.Driver");    
				System.out.println("Success loading Mysql Driver!");
				Connection connect = DriverManager.getConnection(
			            "jdbc:mysql://127.0.0.1:3306/hotel","root","");
				return connect;	
		    }
		    catch (Exception e) {
		    	System.out.print("Error loading Mysql Driver!");
		    	e.printStackTrace();
		    }
		return null;		
	}
		
	public void closeDB(Connection connection) {
		if (connection != null) {
			try {
				connection.close();
				System.out.println("Mysql server closed!");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public ResultSet searchDB(Connection connection, String str) {
		try {
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(str);
			return rs;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	public void updateDB(Connection connection, String str) {
		try {
			Statement stmt = connection.createStatement();
			stmt.executeUpdate(str);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

}
