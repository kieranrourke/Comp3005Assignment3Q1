import java.sql.*;

public class PostgreSQLJDBCConnection {

	 public Connection startConnection () {
		  // JDBC & Database credentials
		  String url = "jdbc:postgresql://127.0.0.1:5432/assignment";
		  String user = "kieranrourke";
		  String password = "blckps2krn";
		  try { // Load PostgreSQL JDBC Driver
				Class.forName("org.postgresql.Driver");
				// Connect to the database
				Connection conn = DriverManager.getConnection(url, user, password);
				if (conn != null) {
					 System.out.println("Connected to PostgreSQL successfully!");
					 return conn;
				} else {
					 System.out.println("Failed to establish connection.");
				}
		  } catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
		  }
		  return null;
	 }

}
