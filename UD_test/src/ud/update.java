package ud;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class update {
	Statement st;

	public update() {

	}

	public int insertRow(String r) {
		try (Connection connection = DriverManager.getConnection(
				"jdbc:mysql://127.0.0.1:3306/salon?serverTimezone=Europe/Moscow&useSSL=false", "root", "")) {
			try {

				String sql = r;
				st = connection.createStatement();

				int i = st.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
				if (i > 0) {
					JOptionPane.showMessageDialog(null, "Row Insert");
					ResultSet rs = st.getGeneratedKeys();
					if (rs.next()) {
						return rs.getInt(1);
					}
				} else {
					JOptionPane.showMessageDialog(null, "NOOO");
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

	public static void main(String[] args) {
		update it = new update();
		it.insertRow(
				"INSERT INTO `salon`.`customer` (`id_Customer`, `Second_Name`, `Name`, `Father_Name`, `Phone`, `Visit`) "
						+ "VALUES ('6', 'rr', 'rr', 'rr', '1234567891', '123');");
	}
}
