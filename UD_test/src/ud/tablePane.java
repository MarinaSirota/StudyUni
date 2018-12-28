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


public class tablePane extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	static JTable table;
	private JTextField textFieldName;
	private JTextField textFieldSecondName;
	private JTextField textFieldFatherName;
	private JTextField textFieldPhone;
	private JTextField textFieldForSearch;
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					tablePane frame = new tablePane();
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
	public static boolean isNumeric(String x)
	{
	    Pattern p = Pattern.compile("^\\d{11}$");
	    Matcher m = p.matcher(x);
	    return m.matches();
	}
	public static boolean isWorld(String x)
	{
	    Pattern p = Pattern.compile("^[а-яА-ЯёЁ]+$");
	    Matcher m = p.matcher(x);
	    return m.matches();
	}
	
	public tablePane() {
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE );
		setBounds(100, 100, 600, 350);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		getContentPane().setLayout(null);
		contentPane.setLayout(null);
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 574, 311);
		getContentPane().add(panel);
		final LinkedList<customer> tbl = new LinkedList<customer>();
		final CustomerTableModel CustomerT_obj = new CustomerTableModel(tbl);
		JButton btnAdd = new JButton("\u0414\u043E\u0431\u0430\u0432\u0438\u0442\u044C");
		btnAdd.setBounds(470, 209, 90, 23);
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try (Connection connection = DriverManager.getConnection(
						"jdbc:mysql://127.0.0.1:3306/salon?serverTimezone=Europe/Moscow&useSSL=false", "root", "")) {

					String sn = textFieldSecondName.getText();
					String n = textFieldName.getText();
					String fn = textFieldFatherName.getText();
					String p = textFieldPhone.getText();
					int flagSn = 1;
					int flagN = 1;
					int flagFn = 1;
					int flagP = 1;

					if (!isWorld(sn)) {
						flagSn = 0;
						JOptionPane.showMessageDialog(null, "Введите Фамилию!");
					}
					if (!isWorld(n)) {
						flagN = 0;
						JOptionPane.showMessageDialog(null, "Введите Имя!");
					}
					if (!isWorld(fn)) {
						flagFn = 0;
						JOptionPane.showMessageDialog(null, "Введите Отчество!");
					}
					if (!isNumeric(p) && sn.length() != 11) {
						flagP = 0;
						JOptionPane.showMessageDialog(null, "Введите Телефон!");
					}

					if (flagSn == 1 && flagN == 1 && flagFn == 1 && flagP == 1) {
						String query = "INSERT INTO `salon`.`customer` (`id_Customer`, `Second_Name`, `Name`, `Father_Name`, `Phone`, `Visit`)"
								+ " VALUES (NULL, '" + sn + "', '" + n + "', '" + fn + "', '" + p + "', '0');";
						
						insert it = new insert();
						int newId = it.insertRow(query);
						if (newId > 0) {
							CustomerT_obj.addRow(new customer(newId, sn, n, fn, p, 0));
							textFieldSecondName.setText(null);
							textFieldName.setText(null);
							textFieldFatherName.setText(null);
							textFieldPhone.setText(null);
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
		scrollPane.setBounds(20, 68, 544, 117);
		panel.add(scrollPane);
		
		table = new JTable(CustomerT_obj);
		table.getColumnModel().getColumn(1).setPreferredWidth(98);
		scrollPane.setViewportView(table);
		
		JLabel lblName = new JLabel("\u0418\u043C\u044F");
		lblName.setBounds(210, 185, 70, 14);
		panel.add(lblName);
		
		JLabel lblSecondName = new JLabel("\u0424\u0430\u043C\u0438\u043B\u0438\u044F");
		lblSecondName.setBounds(110, 185, 70, 14);
		panel.add(lblSecondName);
		
		JLabel lblFatherName = new JLabel("\u041E\u0442\u0447\u0435\u0441\u0442\u0432\u043E");
		lblFatherName.setBounds(300, 185, 70, 14);
		panel.add(lblFatherName);
		
		JLabel lblPhone = new JLabel("\u0422\u0435\u043B\u0435\u0444\u043E\u043D");
		lblPhone.setBounds(390, 185, 70, 14);
		panel.add(lblPhone);
		
		textFieldName = new JTextField();
		textFieldName.setBounds(200, 210, 80, 20);
		panel.add(textFieldName);
		textFieldName.setColumns(10);
		
		textFieldSecondName = new JTextField();
		textFieldSecondName.setBounds(110, 210, 80, 20);
		textFieldSecondName.setColumns(10);
		panel.add(textFieldSecondName);
		
		textFieldFatherName = new JTextField();
		textFieldFatherName.setBounds(290, 210, 80, 20);
		textFieldFatherName.setColumns(10);
		panel.add(textFieldFatherName);
		
		textFieldPhone = new JTextField();
		textFieldPhone.setBounds(380, 210, 80, 20);
		textFieldPhone.setColumns(10);
		panel.add(textFieldPhone);
		
		JButton btnUpdate = new JButton("\u0418\u0437\u043C\u0435\u043D\u0438\u0442\u044C");
		btnUpdate.setBounds(470, 243, 90, 23);
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (table.getSelectedRow() >= 0) {
					String sn = textFieldSecondName.getText();
					String n = textFieldName.getText();
					String fn = textFieldFatherName.getText();
					String p = textFieldPhone.getText();
					int flagSn = 1;
					int flagN = 1;
					int flagFn = 1;
					int flagP = 1;

					if (!isWorld(sn)) {
						flagSn = 0;
						JOptionPane.showMessageDialog(null, "Введите Фамилию!");
					}
					if (!isWorld(n)) {
						flagN = 0;
						JOptionPane.showMessageDialog(null, "Введите Имя!");
					}
					if (!isWorld(fn)) {
						flagFn = 0;
						JOptionPane.showMessageDialog(null, "Введите Отчество!");
					}
					if (!isNumeric(p) && sn.length() != 11) {
						flagP = 0;
						JOptionPane.showMessageDialog(null, "Введите Телефон!");
					}

					int row = table.getSelectedRow();
					int i = (int) table.getValueAt(row, 0);

					if (flagSn == 1 && flagN == 1 && flagFn == 1 && flagN == 1 && flagP == 1) {
						
						String query = "UPDATE `salon`.`customer` SET `Second_Name` = '" + sn + "', `Name` = '" + n
								+ "', `Father_Name` = '" + fn + "', `Phone` = '" + p + "'"
								+ " WHERE `customer`.`id_Customer` = " + table.getValueAt(row, 0).toString() + ";";
						insert it = new insert();
					
						int newId = it.insertRow(query);
					
						if (newId > 0) {
							CustomerT_obj.updateRow(table.getSelectedRow(), new customer(i, sn, n, fn, p, 0));
							textFieldSecondName.setText(null);
							textFieldName.setText(null);
							textFieldFatherName.setText(null);
							textFieldPhone.setText(null);

						}
					}
				}

			}
		});
		panel.add(btnUpdate);
		
		JButton btnDelete = new JButton("\u0423\u0434\u0430\u043B\u0438\u0442\u044C");
		btnDelete.setBounds(470, 277, 90, 23);
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int row = table.getSelectedRow();
				int i = (int) table.getValueAt(row, 0);
				String query = "DELETE FROM  `salon`. `customer` WHERE `customer`.`id_Customer` = " + table.getValueAt(row, 0).toString() + ";";
				insert it = new insert();
				int newId = it.insertRow(query);
				if (newId > 0) {
					CustomerT_obj.deleteRow(i);
				}
			
			}
		});
		panel.add(btnDelete);
		
		JButton btnSearch = new JButton("\u041D\u0430\u0439\u0442\u0438");
		btnSearch.setBounds(470, 10, 90, 23);
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String search= textFieldForSearch.getText();
				try (Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/salon?serverTimezone=Europe/Moscow&useSSL=false", "root","")) 
				{
					PreparedStatement statement = connection.prepareStatement("SELECT *  FROM `customer` "
							+ "WHERE `Second_Name` LIKE '" + search + "' OR `Name` LIKE '" + search + "' OR `"
							+ "Father_Name`LIKE '" + search + "' OR `Phone`LIKE '" + search + "'");
					ResultSet res = statement.executeQuery();
					//if (!res.next()) {
						//JOptionPane.showMessageDialog(null, "Ничего не найдено...");
					// }

						CustomerT_obj.deleteAll();
						while (res.next()) {
							CustomerT_obj.addRow(new customer(res.getInt(1), res.getString(2), res.getString(3),
									res.getString(4), res.getString(5), res.getInt(6)));
					}
					
				} catch (Exception ex) {

					JOptionPane.showMessageDialog(null, "Connection failed..."+ex);
					System.out.println(ex);
				}

			}
		});
		panel.add(btnSearch);
		
		textFieldForSearch = new JTextField();
		textFieldForSearch.setBounds(20, 11, 440, 20);
		panel.add(textFieldForSearch);
		textFieldForSearch.setColumns(10);
		
		JButton btnCancel = new JButton("\u041E\u0442\u043C\u0435\u043D\u0430");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CustomerT_obj.deleteAll();
				textFieldForSearch.setText(null);
				try (Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/salon?serverTimezone=Europe/Moscow&useSSL=false", "root","")) 
				{
					PreparedStatement  statement = connection.prepareStatement("SELECT * \r\n" + 
							"FROM  `customer`");
					ResultSet res = statement.executeQuery();
					while(res.next()) {
						CustomerT_obj.addRow(new customer(res.getInt(1), res.getString(2),
								res.getString(3),res.getString(4),res.getString(5),res.getInt(6)));
						
					}
				} catch (Exception ex) {
					
					JOptionPane.showMessageDialog(null, "Connection failed..."+ex);
					System.out.println(ex);
				}
			}
		});
		btnCancel.setBounds(470, 34, 89, 23);
		panel.add(btnCancel);
		table.addMouseListener(new MouseListener() {
			public void mouseClicked(MouseEvent e) {
				int row = table.getSelectedRow();
				textFieldSecondName.setText(table.getValueAt(row, 1).toString());
				textFieldName.setText(table.getValueAt(row, 2).toString());
				textFieldFatherName.setText(table.getValueAt(row, 3).toString());
				textFieldPhone.setText(table.getValueAt(row, 4).toString());
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
					"FROM  `customer`");
			ResultSet res = statement.executeQuery();
			while(res.next()) {
				CustomerT_obj.addRow(new customer(res.getInt(1), res.getString(2),
						res.getString(3),res.getString(4),res.getString(5),res.getInt(6)));
				
			}
		} catch (Exception ex) {
			
			JOptionPane.showMessageDialog(null, "Connection failed..."+ex);
			System.out.println(ex);
		}
		  table.getColumnModel().getColumn(0).setMinWidth(0);
		   table.getColumnModel().getColumn(0).setMaxWidth(0);
		   table.getColumnModel().getColumn(0).setWidth(0);
		
	}
}
