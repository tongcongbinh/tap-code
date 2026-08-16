package Controller;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;


public class Connection {
	
	public static java.sql.Connection getConnection() {
		final String url = "jdbc:mysql://localhost:3306/quanlynhasach";
        final String user = "root";
        final String password = "";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            return DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
	}
	
}
