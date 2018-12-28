 package ud;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class insert {
	Statement st;

	public insert() {

	}

	public int insertRow(String sql) {
		try (Connection connection = DriverManager.getConnection(
				"jdbc:mysql://127.0.0.1:3306/salon?serverTimezone=Europe/Moscow&useSSL=false", "root", "")) {
			try {

				
				st = connection.createStatement();

				int i = st.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
				if (i > 0) {
					JOptionPane.showMessageDialog(null, "Выполнено!");
					ResultSet rs = st.getGeneratedKeys();
					if (rs.next()) {
						return rs.getInt(1);
					}
				} else {
					JOptionPane.showMessageDialog(null, "Невозможно выпонить команду!");
				}
				return i;
			} catch (SQLException e) {
				e.printStackTrace();
				return -1;
			}
		} catch (Exception ex) {

			JOptionPane.showMessageDialog(null, "Connection failed..." + ex);
			System.out.println(ex);
			return -1;
		}
	}

}
