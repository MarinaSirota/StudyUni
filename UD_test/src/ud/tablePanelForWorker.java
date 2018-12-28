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

public class tablePanelForWorker extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	static JTable table;
	private JTextField textFieldName;
	private JTextField textFieldSecondName;
	private JTextField textFieldFatherName;
	private JTextField textFieldPhone;
	private JTextField textFieldProssent;
	private JTextField textFieldDate;
	private JTextField textFieldForSearch;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					tablePanelForWorker frame = new tablePanelForWorker();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public static boolean isNumeric(String x) {
		Pattern p = Pattern.compile("^\\d{11}$");
		Matcher m = p.matcher(x);
		return m.matches();
	}

	public static boolean isWorld(String x) {
		Pattern p = Pattern.compile("^[а-яА-ЯёЁ]+$");
		Matcher m = p.matcher(x);
		return m.matches();
	}

	public static boolean isDate(String x) {
		Pattern p = Pattern.compile("^20\\d{2}-\\d{1,2}-\\d{1,2}$");
		Matcher m = p.matcher(x);
		return m.matches();
	}

	/**
	 * Create the frame.
	 */
	public tablePanelForWorker() {
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 600, 350);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		getContentPane().setLayout(null);
		contentPane.setLayout(null);
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 584, 311);
		getContentPane().add(panel);
		final LinkedList<worker> tbl = new LinkedList<worker>();
		final WorkerTableModel WorkerT_obj = new WorkerTableModel(tbl);
		JButton btnAdd = new JButton("\u0414\u043E\u0431\u0430\u0432\u0438\u0442\u044C");
		btnAdd.setBounds(482, 209, 91, 23);
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try (Connection connection = DriverManager.getConnection(
						"jdbc:mysql://127.0.0.1:3306/salon?serverTimezone=Europe/Moscow&useSSL=false", "root", "")) {

					String sn = textFieldSecondName.getText();
					String n = textFieldName.getText();
					String fn = textFieldFatherName.getText();
					String p = textFieldPhone.getText();
					String pr = textFieldProssent.getText();
					String d = textFieldDate.getText();
					int flagSn = 1;
					int flagN = 1;
					int flagFn = 1;
					int flagP = 1;
					int flagPr = 1;
					int flagD = 1;

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
					if (!isNumeric(p)) {
						flagP = 0;
						JOptionPane.showMessageDialog(null, "Введите Телефон!");
					}

					if (!isDate(d)) {
						flagD = 0;
						JOptionPane.showMessageDialog(null, "Введите Телефон!");
					}

					if (flagSn == 1 && flagN == 1 && flagFn == 1 && flagP == 1 && flagPr == 1 && flagD == 1) {
						String query = "INSERT INTO `salon`.`worker` (`id_Worker`, `Second_Name`, `Name`, `Father_Name`, `Phone`, `Prossent`, `Start_Day`) "
								+ " VALUES (NULL, '" + sn + "', '" + n + "', '" + fn + "', '" + p + "', '" + pr + "', '"
								+ d + "');";

						insert it = new insert();
						int newId = it.insertRow(query);
						if (newId > 0) {
							WorkerT_obj.addRow(
									new worker(newId, sn, n, fn, p, Float.parseFloat(textFieldProssent.getText()), d));
						}
						int row = table.getSelectedRow();
						String idWorkerForInsertToTableWorkerService = table.getValueAt(row, 0).toString();
						try {
							tablePanelForService frame = new tablePanelForService(1);
							frame.setVisible(true);
							frame.setIDConn(idWorkerForInsertToTableWorkerService);
						} catch (Exception e1) {
							e1.printStackTrace();
						}
					}

				} catch (Exception ex) {

					JOptionPane.showMessageDialog(null, "Connection failed..." + ex);
					System.out.println(ex);
				}

			}

		});
		panel.setLayout(null);
		panel.add(btnAdd);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 67, 554, 110);
		panel.add(scrollPane);

		table = new JTable(WorkerT_obj);
		table.getColumnModel().getColumn(1).setPreferredWidth(98);
		scrollPane.setViewportView(table);

		JLabel lblName = new JLabel("\u0418\u043C\u044F");
		lblName.setBounds(110, 185, 36, 14);
		panel.add(lblName);

		JLabel lblSecondName = new JLabel("\u0424\u0430\u043C\u0438\u043B\u0438\u044F");
		lblSecondName.setBounds(20, 185, 70, 14);
		panel.add(lblSecondName);

		JLabel lblFatherName = new JLabel("\u041E\u0442\u0447\u0435\u0441\u0442\u0432\u043E");
		lblFatherName.setBounds(200, 185, 70, 14);
		panel.add(lblFatherName);

		JLabel lblPhone = new JLabel("\u0422\u0435\u043B\u0435\u0444\u043E\u043D");
		lblPhone.setBounds(20, 235, 70, 14);
		panel.add(lblPhone);

		textFieldName = new JTextField();
		textFieldName.setBounds(110, 200, 70, 20);
		panel.add(textFieldName);
		textFieldName.setColumns(10);

		textFieldSecondName = new JTextField();
		textFieldSecondName.setBounds(20, 200, 70, 20);
		textFieldSecondName.setColumns(10);
		panel.add(textFieldSecondName);

		textFieldFatherName = new JTextField();
		textFieldFatherName.setBounds(200, 200, 70, 20);
		textFieldFatherName.setColumns(10);
		panel.add(textFieldFatherName);

		textFieldPhone = new JTextField();
		textFieldPhone.setBounds(20, 250, 70, 20);
		textFieldPhone.setColumns(10);
		panel.add(textFieldPhone);

		JLabel lblProssent = new JLabel("\u041F\u0440\u043E\u0446\u0435\u043D\u0442");
		lblProssent.setBounds(110, 235, 70, 14);
		panel.add(lblProssent);

		textFieldProssent = new JTextField();
		textFieldProssent.setBounds(110, 250, 70, 20);
		panel.add(textFieldProssent);
		textFieldProssent.setColumns(10);

		JLabel label = new JLabel("\u0414\u0430\u0442\u0430 \u041F\u0440\u0438\u043D\u044F\u0442\u0438\u044F");
		label.setBounds(200, 235, 91, 14);
		panel.add(label);

		textFieldDate = new JTextField();
		textFieldDate.setBounds(200, 250, 70, 20);
		panel.add(textFieldDate);
		textFieldDate.setColumns(10);

		JButton btnUpdate = new JButton("\u0418\u0437\u043C\u0435\u043D\u0438\u0442\u044C");
		btnUpdate.setBounds(482, 243, 89, 23);
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (table.getSelectedRow() >= 0) {

					String sn = textFieldSecondName.getText();
					String n = textFieldName.getText();
					String fn = textFieldFatherName.getText();
					String p = textFieldPhone.getText();
					String pr = textFieldProssent.getText();
					String d = textFieldDate.getText();
					int flagSn = 1;
					int flagN = 1;
					int flagFn = 1;
					int flagP = 1;
					int flagPr = 1;
					int flagD = 1;

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
					if (!isNumeric(p)) {
						flagP = 0;
						JOptionPane.showMessageDialog(null, "Введите Телефон!");
					}

					int row = table.getSelectedRow();
					int i = (int) table.getValueAt(row, 0);

					if (flagSn == 1 && flagN == 1 && flagFn == 1 && flagP == 1 && flagPr == 1 && flagD == 1) {

						String query = "UPDATE `salon`.`worker` SET `Second_Name` = '" + sn + "', `Name` = '" + n
								+ "', `Father_Name` = '" + fn + "', `Phone` = '" + p + "'," + "`Prossent` = '" + pr
								+ "',`Start_Day` = '" + d + "'" + " WHERE `worker`.`id_Worker` = "
								+ table.getValueAt(row, 0).toString() + ";";
						insert it = new insert();

						int newId = it.insertRow(query);

						if (newId > 0) {
							WorkerT_obj.updateRow(table.getSelectedRow(),
									new worker(i, sn, n, fn, p, Float.parseFloat(textFieldProssent.getText()), d));
							textFieldSecondName.setText(null);
							textFieldName.setText(null);
							textFieldFatherName.setText(null);
							textFieldPhone.setText(null);
							textFieldProssent.setText(null);
							textFieldDate.setText(null);

						}
					}
				}

			}
		});
		panel.add(btnUpdate);

		JButton btnDelete = new JButton("\u0423\u0434\u0430\u043B\u0438\u0442\u044C");
		btnDelete.setBounds(482, 277, 89, 23);
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = table.getSelectedRow();
				int i = (int) table.getValueAt(row, 0);
				String query = "DELETE FROM  `salon`. `worker` WHERE `worker`.`id_Worker` = "
						+ table.getValueAt(row, 0).toString() + ";";
				insert it = new insert();
				int newId = it.insertRow(query);
				if (newId > 0) {
					WorkerT_obj.deleteRow(i);
				}

			}
		});
		panel.add(btnDelete);

		textFieldForSearch = new JTextField();
		textFieldForSearch.setBounds(20, 11, 452, 20);
		panel.add(textFieldForSearch);
		textFieldForSearch.setColumns(10);

		JButton btnSearch = new JButton("\u041D\u0430\u0439\u0442\u0438");
		btnSearch.setBounds(482, 10, 89, 23);
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String search = textFieldForSearch.getText();
				try (Connection connection = DriverManager.getConnection(
						"jdbc:mysql://127.0.0.1:3306/salon?serverTimezone=Europe/Moscow&useSSL=false", "root", "")) {
					PreparedStatement statement = connection
							.prepareStatement("SELECT *  FROM `worker` " + "WHERE `Second_Name` LIKE '" + search
									+ "' OR `Name` LIKE '" + search + "' OR `" + "Father_Name` LIKE '" + search
									+ "' OR `Phone` LIKE '" + search + "'" + "OR  `Prossent` LIKE '" + search + "'");
					ResultSet res = statement.executeQuery();
					// if (!res.next()) {
					// JOptionPane.showMessageDialog(null, "Ничего не найдено...");
					// }

					WorkerT_obj.deleteAll();
					while (res.next()) {
						WorkerT_obj.addRow(new worker(res.getInt(1), res.getString(2), res.getString(3),
								res.getString(4), res.getString(5), res.getFloat(6), res.getString(7)));

					}

				} catch (Exception ex) {

					JOptionPane.showMessageDialog(null, "Connection failed..." + ex);
					System.out.println(ex);
				}
			}
		});
		panel.add(btnSearch);

		JButton btnCancel = new JButton("\u041E\u0442\u043C\u0435\u043D\u0430");
		btnCancel.setBounds(482, 34, 89, 23);
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				WorkerT_obj.deleteAll();
				textFieldForSearch.setText(null);
				try (Connection connection = DriverManager.getConnection(
						"jdbc:mysql://127.0.0.1:3306/salon?serverTimezone=Europe/Moscow&useSSL=false", "root", "")) {
					PreparedStatement statement = connection.prepareStatement("SELECT * \r\n" + "FROM  `worker`");
					ResultSet res = statement.executeQuery();
					while (res.next()) {
						WorkerT_obj.addRow(new worker(res.getInt(1), res.getString(2), res.getString(3),
								res.getString(4), res.getString(5), res.getFloat(6), res.getString(7)));

					}
				} catch (Exception ex) {

					JOptionPane.showMessageDialog(null, "Connection failed..." + ex);
					System.out.println(ex);
				}
			}
		});
		panel.add(btnCancel);

		JButton btnSelect = new JButton("\u0412\u044B\u0431\u0440\u0430\u0442\u044C");
		btnSelect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = table.getSelectedRow();
				String idWorkerForInsertToTableWorkerService = table.getValueAt(row, 0).toString();
				try {
					tablePanelForService frame = new tablePanelForService(1);
					frame.setVisible(true);
					frame.setIDConn(idWorkerForInsertToTableWorkerService);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		btnSelect.setBounds(356, 249, 89, 23);
		panel.add(btnSelect);
		table.addMouseListener(new MouseListener() {
			public void mouseClicked(MouseEvent e) {
				int row = table.getSelectedRow();
				textFieldSecondName.setText(table.getValueAt(row, 1).toString());
				textFieldName.setText(table.getValueAt(row, 2).toString());
				textFieldFatherName.setText(table.getValueAt(row, 3).toString());
				textFieldPhone.setText(table.getValueAt(row, 4).toString());
				textFieldProssent.setText(table.getValueAt(row, 5).toString());
				textFieldDate.setText(table.getValueAt(row, 6).toString());

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

		try (Connection connection = DriverManager.getConnection(
				"jdbc:mysql://127.0.0.1:3306/salon?serverTimezone=Europe/Moscow&useSSL=false", "root", "")) {
			PreparedStatement statement = connection.prepareStatement("SELECT * \r\n" + "FROM  `worker`");
			ResultSet res = statement.executeQuery();
			while (res.next()) {
				WorkerT_obj.addRow(new worker(res.getInt(1), res.getString(2), res.getString(3), res.getString(4),
						res.getString(5), res.getFloat(6), res.getString(7)));

			}
		} catch (Exception ex) {

			JOptionPane.showMessageDialog(null, "Connection failed..." + ex);
			System.out.println(ex);
		}
		table.getColumnModel().getColumn(0).setMinWidth(0);
		   table.getColumnModel().getColumn(0).setMaxWidth(0);
		   table.getColumnModel().getColumn(0).setWidth(0);
	}
}
