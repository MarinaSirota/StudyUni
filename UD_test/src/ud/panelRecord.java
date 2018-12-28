package ud;

import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.SwingConstants;


public class panelRecord extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	static JTable table;
	public String idC;
	public String idW;
	public String idS;
	public JButton btnPrintCheck;
	private JTextField textFieldForSearch;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					panelRecord frame = new panelRecord();
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
	public panelRecord() {
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 1270, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		getContentPane().setLayout(null);
		contentPane.setLayout(null);
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 1250, 490);
		getContentPane().add(panel);
		final LinkedList<record> tblR = new LinkedList<record>();
		final RecordTableModel RecordT_obj = new RecordTableModel(tblR);
		panel.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 67, 1200, 300);
		panel.add(scrollPane);

		table = new JTable(RecordT_obj);
		table.getColumnModel().getColumn(1).setPreferredWidth(98);
		scrollPane.setViewportView(table);

		JButton btnUpdate = new JButton("\u041E\u043A\u0430\u0437\u0430\u043D\u043E");
		btnUpdate.setBounds(1115, 389, 105, 23);
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int row = table.getSelectedRow();

				JOptionPane.showMessageDialog(null, idC + idS + idS);

				if (table.getSelectedRow() >= 0) {

					String query = "update `record` set `Status`='1'" + "where `record`.`date`='"
							+ table.getValueAt(row, 9).toString() + "' And  `record`.`hour`='"
							+ table.getValueAt(row, 10).toString() + "' AND" + "`id_Customer`='" + idC
							+ "'and `id_service`='" + idS + "'and `id_worker`='" + idW + "'";
					String sql = "UPDATE `salon`.`customer` SET `Visit`=`visit`+'1'  WHERE `id_Customer`='" + idC + "'";
					insert it = new insert();
					int newId = it.insertRow(query);

					if (newId > 0) {
						RecordT_obj.updateRow(row,
								new record(table.getValueAt(row, 0).toString(), table.getValueAt(row, 1).toString(),
										table.getValueAt(row, 2).toString(), table.getValueAt(row, 3).toString(),
										table.getValueAt(row, 4).toString(), table.getValueAt(row, 5).toString(),
										table.getValueAt(row, 6).toString(), table.getValueAt(row, 7).toString(),
										table.getValueAt(row, 8).toString(), table.getValueAt(row, 9).toString(),
										Integer.parseInt(table.getValueAt(row, 10).toString()), 1));

					}
					insert vi = new insert();
					int r = vi.insertRow(sql);
					if (r > 0) {
						JOptionPane.showMessageDialog(null, "GOOd");
					}
					btnPrintCheck.setEnabled(true);
				}
			}

		});
		panel.add(btnUpdate);

		JButton btnDelete = new JButton("\u041D\u0435 \u043E\u043A\u0430\u0437\u0430\u043D\u043E");
		btnDelete.setBounds(1115, 423, 105, 23);
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int row = table.getSelectedRow();

				JOptionPane.showMessageDialog(null, idC + idS + idS);

				if (table.getSelectedRow() >= 0) {

					String query = "update `record` set `Status`='-1'" + "where `record`.`date`='"
							+ table.getValueAt(row, 9).toString() + "' And  `record`.`hour`='"
							+ table.getValueAt(row, 10).toString() + "' AND" + "`id_Customer`='" + idC
							+ "'and `id_service`='" + idS + "'and `id_worker`='" + idW + "'";
					
					insert it = new insert();
					int newId = it.insertRow(query);

					if (newId > 0) {
						RecordT_obj.updateRow(row,
								new record(table.getValueAt(row, 0).toString(), table.getValueAt(row, 1).toString(),
										table.getValueAt(row, 2).toString(), table.getValueAt(row, 3).toString(),
										table.getValueAt(row, 4).toString(), table.getValueAt(row, 5).toString(),
										table.getValueAt(row, 6).toString(), table.getValueAt(row, 7).toString(),
										table.getValueAt(row, 8).toString(), table.getValueAt(row, 9).toString(),
										Integer.parseInt(table.getValueAt(row, 10).toString()), -1));

					}
					
				}
				
			}
		});
		panel.add(btnDelete);

		btnPrintCheck = new JButton("\u041F\u0415\u0427\u0410\u0422\u042C \u0427\u0415\u041A\u0410");
		btnPrintCheck.setBounds(871, 389, 208, 57);
		btnPrintCheck.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int row = table.getSelectedRow();
				PrintWriter out = null;
				try {
					File file = new File("C:/УЧЕБА/5 семестр/УД/КР/1.txt");
					out = new PrintWriter(file);
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				out.print("Клиент:" + table.getValueAt(row, 0).toString() + " " + table.getValueAt(row, 1).toString()
						+ " " + table.getValueAt(row, 2).toString() + " " + ";\n" + "Сотрудник:"
						+ table.getValueAt(row, 4).toString() + " " + table.getValueAt(row, 5).toString() + " "
						+ table.getValueAt(row, 6).toString() + " " + "; \n" + "Услуга:"
						+ table.getValueAt(row, 8).toString() + "\nДата:" + table.getValueAt(row, 9).toString() + " "
						+ table.getValueAt(row, 10).toString() + ":00 \n");

				out.close();

			}
		});

		btnPrintCheck.setFont(new Font("Times New Roman", Font.BOLD, 16));
		panel.add(btnPrintCheck);

		JButton btnSave = new JButton("SaveFile");
		btnSave.setBounds(633, 408, 89, 23);
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooser = new JFileChooser();
				int returnVal = chooser.showSaveDialog(getParent());
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					System.out.println("Сохранение в файл - : " + chooser.getSelectedFile().getName());
					PrintWriter out = null;
					try {
						out = new PrintWriter(chooser.getSelectedFile().getAbsolutePath());
					} catch (FileNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					record[] myArray = {};
					myArray = tblR.toArray(new record[tblR.size()]);
					for (int i = 0; i < myArray.length; i++) {
						out.print("Клиент:" + myArray[i].secondNameCustomer + " " + myArray[i].nameCustomer + " "
								+ myArray[i].fatherNameCustomer + " " + myArray[i].phoneCustomer + ";\n" + "Сотрудник:"
								+ myArray[i].secondNameWorker + " " + myArray[i].nameWorker + " "
								+ myArray[i].fatherNameWorker + " " + myArray[i].phoneWorker + ";\n" + "Услуга:"
								+ myArray[i].nameService + "\nДата:" + myArray[i].dateService + " "
								+ myArray[i].hourService + ":00 \n");
					}
					out.close();

				}

			}
		});
		panel.add(btnSave);
		btnSave.setVisible(false);

		try (Connection connection = DriverManager.getConnection(
				"jdbc:mysql://127.0.0.1:3306/salon?serverTimezone=Europe/Moscow&useSSL=false", "root", "")) {
			PreparedStatement statement = connection.prepareStatement(
					"SELECT `customer`.`Second_Name`, `customer`.`Name`, `customer`.`Father_Name`, `customer`.`Phone`,\r\n"
							+ "`worker`.`Second_Name`, `worker`.`Name`, `worker`.`Father_Name`, `worker`.`Phone`,\r\n"
							+ "`sevice`.`Name`,`record`.`date`,`record`.`hour`, `record`.`status`, `record`.`id_customer`, `record`.`id_Service`, `record`.`id_Worker`"
							+ " FROM `customer` \r\n" + "inner join `record` \r\n"
							+ "on (`customer`.`id_Customer`=`record`.`id_Customer`) \r\n"
							+ "inner join `service_worker` \r\n"
							+ "on (`record`.`id_Worker`=`service_worker`.`id_worker` and`record`.`id_Service`=`service_worker`.`id_Service` )\r\n"
							+ "inner join `worker` \r\n" + "on (`worker`.`id_Worker`=`service_worker`.`id_worker`)\r\n"
							+ "inner join `sevice` \r\n"
							+ "on (`sevice`.`id_Service`=`service_worker`.`id_Service`)\r\n" + "\r\n" + "");
			ResultSet res = statement.executeQuery();
			while (res.next()) {
				RecordT_obj.addRow(new record(res.getString(1), res.getString(2), res.getString(3), res.getString(4),
						res.getString(5), res.getString(6), res.getString(7), res.getString(8), res.getString(9),
						res.getString(10), res.getInt(11), res.getInt(12)));

			}
		} catch (Exception ex) {

			JOptionPane.showMessageDialog(null, "Connection failed..." + ex);
			System.out.println(ex);
		}
		table.addMouseListener(new MouseListener() {
			public void mouseClicked(MouseEvent e) {
				int row = table.getSelectedRow();
				try (Connection connection = DriverManager.getConnection(
						"jdbc:mysql://127.0.0.1:3306/salon?serverTimezone=Europe/Moscow&useSSL=false", "root", "")) {
					PreparedStatement statement = connection.prepareStatement(
							"SELECT `record`.`id_customer`, `record`.`id_Service`, `record`.`id_Worker`\r\n"
									+ "FROM `record`     \r\n" + "inner join `customer`\r\n"
									+ "on (`record`.`id_Customer`=`customer`.`id_Customer` AND `customer`.`phone`='"
									+ table.getValueAt(row, 3).toString() + "' )   \r\n"
									+ "inner join `service_worker`   \r\n"
									+ "on (`record`.`id_Worker`=`service_worker`.`id_worker` and`record`.`id_Service`=`service_worker`.`id_Service` )  \r\n"
									+ "inner join `worker`   \r\n"
									+ "on (`worker`.`id_Worker`=`service_worker`.`id_worker` AND `worker`.`Phone`='"
									+ table.getValueAt(row, 7).toString() + "')  \r\n" + "inner join `sevice`   \r\n"
									+ "on (`sevice`.`id_Service`=`service_worker`.`id_Service`)"
									+ "where `record`.`date`='" + table.getValueAt(row, 9).toString()
									+ "' And  `record`.`hour`='" + table.getValueAt(row, 10).toString() + "'");
					ResultSet res = statement.executeQuery();
					while (res.next()) {
						idC = res.getString(1);
						idS = res.getString(2);
						idW = res.getString(3);
					}
				} catch (Exception ex) {

					JOptionPane.showMessageDialog(null, "Connection failed..." + ex);
					System.out.println(ex);
				}
				btnUpdate.setEnabled(true);
				btnDelete.setEnabled(true);
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
		btnUpdate.setEnabled(false);
		btnDelete.setEnabled(false);
		btnPrintCheck.setEnabled(false);
		
		textFieldForSearch = new JTextField();
		textFieldForSearch.setBounds(20, 23, 476, 20);
		panel.add(textFieldForSearch);
		textFieldForSearch.setColumns(10);
		
		
		JButton btnSearch = new JButton("\u041D\u0430\u0439\u0442\u0438");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String search = textFieldForSearch.getText();
				
				try (Connection connection = DriverManager.getConnection(
						"jdbc:mysql://127.0.0.1:3306/salon?serverTimezone=Europe/Moscow&useSSL=false", "root", "")) {
					String sql=null;
					int flagD=0;
					int flagW=0;
					if(isDate(search)) {
									sql="SELECT `customer`.`Second_Name`, `customer`.`Name`, `customer`.`Father_Name`, `customer`.`Phone`," + 
									"`worker`.`Second_Name`, `worker`.`Name`, `worker`.`Father_Name`, `worker`.`Phone`," + 
									"`sevice`.`Name`,`record`.`date`,`record`.`hour`, `record`.`status`" 
									+ "FROM `record` inner join `customer`"
									+ "on (`record`.`id_Customer`=`customer`.`id_Customer`)" + 
									"inner join `service_worker`" + 
									"on (`record`.`id_Worker`=`service_worker`.`id_worker` and`record`.`id_Service`=`service_worker`.`id_Service` )" + 
									"inner join `worker`" + 
									"on (`worker`.`id_Worker`=`service_worker`.`id_worker` )"+
									"inner join `sevice`" + 
									"on (`sevice`.`id_Service`=`service_worker`.`id_Service`)"
									+ "where `record`.`date` = '"+search+"'";
									flagD=1;
					}
					if(isNumeric(search) || isWorld(search)) {
						sql="SELECT `customer`.`Second_Name`, `customer`.`Name`, `customer`.`Father_Name`, `customer`.`Phone`," + 
						"`worker`.`Second_Name`, `worker`.`Name`, `worker`.`Father_Name`, `worker`.`Phone`," + 
						"`sevice`.`Name`,`record`.`date`,`record`.`hour`, `record`.`status`" 
						+ "FROM `record` inner join `customer`"
						+ "on (`record`.`id_Customer`=`customer`.`id_Customer`)" + 
						"inner join `service_worker`" + 
						"on (`record`.`id_Worker`=`service_worker`.`id_worker` and`record`.`id_Service`=`service_worker`.`id_Service` )" + 
						"inner join `worker`" + 
						"on (`worker`.`id_Worker`=`service_worker`.`id_worker` )"+
						"inner join `sevice`" + 
						"on (`sevice`.`id_Service`=`service_worker`.`id_Service`)"
						+ "where (`customer`.`phone` LIKE '" + 
						search + "' or `customer`.`Second_name` LIKE '"+search+"' or `customer`.`name` LIKE '"+search+"'" 
						+ " or `worker`.`phone` LIKE '" + 
						search + "' or `worker`.`Second_name` LIKE '"+search+"' or `worker`.`name` LIKE '"+search+"')";
						flagW=1;
		}
					if(flagD==1 || flagW==1) {
					PreparedStatement statement = connection.prepareStatement(sql);
									ResultSet res = statement.executeQuery();

					RecordT_obj.deleteAll();
					while (res.next()) {
						RecordT_obj.addRow(new record(res.getString(1), res.getString(2), res.getString(3), res.getString(4),
								res.getString(5), res.getString(6), res.getString(7), res.getString(8), res.getString(9),
								res.getString(10), res.getInt(11), res.getInt(12)));

					}}

				} catch (Exception ex) {

					JOptionPane.showMessageDialog(null, "Connection failed..." + ex);
					System.out.println(ex);
				}
			}
		});
		btnSearch.setBounds(522, 22, 89, 23);
		panel.add(btnSearch);
		
		
		JButton btnCancel = new JButton("\u041E\u0442\u043C\u0435\u043D\u0430");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				RecordT_obj.deleteAll();
				textFieldForSearch.setText(null);
				try (Connection connection = DriverManager.getConnection(
						"jdbc:mysql://127.0.0.1:3306/salon?serverTimezone=Europe/Moscow&useSSL=false", "root", "")) {
					PreparedStatement statement = connection.prepareStatement(
							"SELECT `customer`.`Second_Name`, `customer`.`Name`, `customer`.`Father_Name`, `customer`.`Phone`,\r\n"
									+ "`worker`.`Second_Name`, `worker`.`Name`, `worker`.`Father_Name`, `worker`.`Phone`,\r\n"
									+ "`sevice`.`Name`,`record`.`date`,`record`.`hour`, `record`.`status`, `record`.`id_customer`, `record`.`id_Service`, `record`.`id_Worker`"
									+ " FROM `customer` \r\n" + "inner join `record` \r\n"
									+ "on (`customer`.`id_Customer`=`record`.`id_Customer`) \r\n"
									+ "inner join `service_worker` \r\n"
									+ "on (`record`.`id_Worker`=`service_worker`.`id_worker` and`record`.`id_Service`=`service_worker`.`id_Service` )\r\n"
									+ "inner join `worker` \r\n" + "on (`worker`.`id_Worker`=`service_worker`.`id_worker`)\r\n"
									+ "inner join `sevice` \r\n"
									+ "on (`sevice`.`id_Service`=`service_worker`.`id_Service`)\r\n" + "\r\n" + "");
					ResultSet res = statement.executeQuery();
					while (res.next()) {
						RecordT_obj.addRow(new record(res.getString(1), res.getString(2), res.getString(3), res.getString(4),
								res.getString(5), res.getString(6), res.getString(7), res.getString(8), res.getString(9),
								res.getString(10), res.getInt(11), res.getInt(12)));
					}
				} catch (Exception ex) {

					JOptionPane.showMessageDialog(null, "Connection failed..." + ex);
					System.out.println(ex);
				}
			}
		});
		btnCancel.setHorizontalAlignment(SwingConstants.RIGHT);
		btnCancel.setBounds(633, 22, 89, 23);
		panel.add(btnCancel);

	}
}
