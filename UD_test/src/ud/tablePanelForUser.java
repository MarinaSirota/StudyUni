package ud;


import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;


public class tablePanelForUser extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	static JTable table;
	private JTextField textFieldPassword;
	private JTextField textFieldLogin;
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					tablePanelForUser frame = new tablePanelForUser();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public static boolean isWorld(String x)
	{
	    Pattern p = Pattern.compile("^[A-Za-z0-9]+$");
	    Matcher m = p.matcher(x);
	    return m.matches();
	}
	
	public tablePanelForUser() {
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE );
		setBounds(100, 100, 436, 309);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		getContentPane().setLayout(null);
		contentPane.setLayout(null);
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 420, 270);
		getContentPane().add(panel);
		final LinkedList<user> tbl = new LinkedList<user>();
		final userTableModel UserT_obj = new userTableModel(tbl);
		JButton btnAdd = new JButton("\u0414\u043E\u0431\u0430\u0432\u0438\u0442\u044C");
		btnAdd.setBounds(210, 151, 90, 23);
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try (Connection connection = DriverManager.getConnection(
						"jdbc:mysql://127.0.0.1:3306/salon?serverTimezone=Europe/Moscow&useSSL=false", "root", "")) {

					String sn = textFieldLogin.getText();
					String n = textFieldPassword.getText();
					int flagSn = 1;
			
					if (!isWorld(sn)) {
						flagSn = 0;
						JOptionPane.showMessageDialog(null, "Введите логин!");
					}


					if (flagSn == 1) {
						String query = "INSERT INTO `salon`.`user` (`login`, `password`)"
								+ " VALUES ('" + sn + "', '" + n + "');";
						
						insert it = new insert();
						int newId = it.insertRow(query);
						if (newId > 0) {
							UserT_obj.addRow(new user(sn, n));
							textFieldLogin.setText(null);
							textFieldPassword.setText(null);
						}
					}

				} catch (Exception ex) {

					JOptionPane.showMessageDialog(null, "Неверные данные" + ex);
					System.out.println(ex);
				}

			}

		});
		panel.setLayout(null);
		panel.add(btnAdd);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 23, 280, 117);
		panel.add(scrollPane);
		
		table = new JTable(UserT_obj);
		table.getColumnModel().getColumn(1).setPreferredWidth(98);
		scrollPane.setViewportView(table);
		
		JLabel lblPassword = new JLabel("\u041F\u0430\u0440\u043E\u043B\u044C");
		lblPassword.setBounds(120, 151, 70, 14);
		panel.add(lblPassword);
		
		JLabel lblLogin = new JLabel("\u041B\u043E\u0433\u0438\u043D");
		lblLogin.setBounds(20, 151, 70, 14);
		panel.add(lblLogin);
		
		textFieldPassword = new JTextField();
		textFieldPassword.setBounds(120, 176, 80, 20);
		panel.add(textFieldPassword);
		textFieldPassword.setColumns(10);
		
		textFieldLogin = new JTextField();
		textFieldLogin.setBounds(20, 176, 80, 20);
		textFieldLogin.setColumns(10);
		panel.add(textFieldLogin);
		
		JButton btnUpdate = new JButton("\u0418\u0437\u043C\u0435\u043D\u0438\u0442\u044C");
		btnUpdate.setBounds(210, 185, 90, 23);
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (table.getSelectedRow() >= 0) {
					String sn = textFieldLogin.getText();
					String n = textFieldPassword.getText();
	
					int flagSn = 1;


					if (!isWorld(sn)) {
						flagSn = 0;
						JOptionPane.showMessageDialog(null, "Введите Фамилию!");
					}
					int row = table.getSelectedRow();
					if (flagSn == 1) {
						
						String query = "UPDATE `salon`.`user` SET `login` = '" + sn + "', `password` = '" + n
								+ "'"
								+ " WHERE `user`.`login` = '" + table.getValueAt(row, 0).toString() + "';";
						insert it = new insert();
					
						int newId = it.insertRow(query);
					
						if (newId > 0) {
							UserT_obj.updateRow(table.getSelectedRow(), new user(sn, n));
							textFieldLogin.setText(null);
							textFieldPassword.setText(null);


						}
					}
				}

			}
		});
		panel.add(btnUpdate);
		
		JButton btnDelete = new JButton("\u0423\u0434\u0430\u043B\u0438\u0442\u044C");
		btnDelete.setBounds(210, 219, 90, 23);
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int row = table.getSelectedRow();
				String i =  table.getValueAt(row, 0).toString();
				String query = "DELETE FROM  `salon`. `user` WHERE `user`.`login` = '" + table.getValueAt(row, 0).toString() + "';";
				insert it = new insert();
				int newId = it.insertRow(query);
				if (newId > 0) {
					UserT_obj.deleteRow(i);
				}
				textFieldLogin.setText(null);
				textFieldPassword.setText(null);
			
			}
		});
		panel.add(btnDelete);
		table.addMouseListener(new MouseListener() {
			public void mouseClicked(MouseEvent e) {
				int row = table.getSelectedRow();
				textFieldLogin.setText(table.getValueAt(row, 0).toString());
				textFieldPassword.setText(table.getValueAt(row, 1).toString());
				
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

		});
	
		
		try (Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/salon?serverTimezone=Europe/Moscow&useSSL=false", "root","")) 
		{
			PreparedStatement  statement = connection.prepareStatement("SELECT * \r\n" + 
					"FROM  `user`");
			ResultSet res = statement.executeQuery();
			while(res.next()) {
				UserT_obj.addRow(new user(res.getString(1), res.getString(2)));
				
			}
		} catch (Exception ex) {
			
			JOptionPane.showMessageDialog(null, "Connection failed..."+ex);
			System.out.println(ex);
		}
	}
}
