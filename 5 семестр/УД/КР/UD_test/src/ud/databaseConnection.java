package ud;

import java.sql.Connection;
import java.sql.DriverManager;

import javax.swing.JOptionPane;

public class databaseConnection {
	Connection connection;
	public databaseConnection() {
	}
	public Connection setConnection() {
		
		try (Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/salon?serverTimezone=Europe/Moscow&useSSL=false", "root","")) 
		{
		JOptionPane.showMessageDialog(null, "Connection to Store DB succesfull!");
	return connection;
		} catch (Exception ex) {
			
			JOptionPane.showMessageDialog(null, "Connection failed..."+ex);
			System.out.println(ex);
			return null;
		}

		
		
	}
	//public static void main(String[] args) {
		//databaseConnection dbconn;
		//Connection conn;
		//dbconn=new databaseConnection();
		//conn=dbconn.setConnection();
	//}
}
