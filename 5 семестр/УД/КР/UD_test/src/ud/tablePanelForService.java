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

public class tablePanelForService extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	static JTable table;
	private JTextField textFieldTime;
	private JTextField textFieldCost;
	private JTextField textFieldName;
	private JTextField textFieldForSearch;
	public String idServiceForWorker;
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					tablePanelForService frame = new tablePanelForService(1);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public static boolean isNumeric(String x)
	{
	    Pattern p = Pattern.compile("^\\d+(?:\\.\\d+)?$");
	    Matcher m = p.matcher(x);
	    return m.matches();
	}
	public static boolean isWorld(String x)
	{
	    Pattern p = Pattern.compile("^[а-яА-ЯёЁ]+$");
	    Matcher m = p.matcher(x);
	    return m.matches();
	}
	
	public  String getIDForWorker(String x)
	{
	return idServiceForWorker;
	}
	/**
	 * Create the frame.
	 */
	public tablePanelForService(int choose) {
		if (choose==1)
			loadTwo();
		else load();
	}
	public void load() {
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE );
		setBounds(100, 100, 600, 350);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		getContentPane().setLayout(null);
		contentPane.setLayout(null);
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 584, 311);
		getContentPane().add(panel);
		final LinkedList<service> tbl = new LinkedList<service>();
		final ServiceTableModel ServiceT_obj = new ServiceTableModel(tbl);
		JButton btnAdd = new JButton("\u0414\u043E\u0431\u0430\u0432\u0438\u0442\u044C");
		btnAdd.setBounds(485, 209, 89, 23);
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try (Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/salon?serverTimezone=Europe/Moscow&useSSL=false", "root","")) 
				{
					String n=textFieldName.getText();
					String c=textFieldCost.getText();
					String t=textFieldTime.getText();
					int flagN = 1;
					int flagC = 1;
					int flagT = 1;

					if (!isWorld(n)) {
						flagN = 0;
						JOptionPane.showMessageDialog(null, "Введите Название!");
					}
					
					if (!isNumeric(c)) {
						flagC = 0;
						JOptionPane.showMessageDialog(null, "Введите Стоимость!");
					}
					if (!isNumeric(t)) {
						flagT = 0;
						JOptionPane.showMessageDialog(null, "Введите Длительность!");
					}

					if (flagN == 1 && flagC == 1 && flagT == 1) {
					String query="INSERT INTO `salon`.`sevice` (`id_Service`, `Name`, `Cost`, `Time`)"
					+" VALUES (NULL, '"+n+"', '"+c+"', '"+t+"');";
					

					insert it=new insert();
					int newId = it.insertRow(query);
					if(newId > 0 ) {
						ServiceT_obj.addRow(new service(newId,n,Integer.parseInt(textFieldCost.getText()), 
							Integer.parseInt(textFieldTime.getText())));
					}	
					}
				} catch (Exception ex) {
					
					JOptionPane.showMessageDialog(null, "Connection failed..."+ex);
					System.out.println(ex);
				}

			}
	
		});
		panel.setLayout(null);
		panel.add(btnAdd);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 72, 554, 120);
		panel.add(scrollPane);
		
		table = new JTable(ServiceT_obj);
		table.getColumnModel().getColumn(1).setPreferredWidth(98);
		scrollPane.setViewportView(table);
		
		JLabel lblTime = new JLabel("\u0412\u0440\u0435\u043C\u044F");
		lblTime.setBounds(340, 203, 50, 14);
		panel.add(lblTime);
		
		JLabel lblCost = new JLabel("\u0421\u0442\u043E\u0438\u043C\u043E\u0441\u0442\u044C");
		lblCost.setBounds(250, 203, 70, 14);
		panel.add(lblCost);
		
		textFieldTime = new JTextField();
		textFieldTime.setBounds(340, 220, 80, 20);
		panel.add(textFieldTime);
		textFieldTime.setColumns(10);
		
		textFieldCost = new JTextField();
		textFieldCost.setBounds(250, 220, 80, 20);
		textFieldCost.setColumns(10);
		panel.add(textFieldCost);
		
		JLabel lblName = new JLabel("\u041D\u0430\u0437\u0432\u0430\u043D\u0438\u0435");
		lblName.setBounds(160, 203, 70, 14);
		panel.add(lblName);
		
		textFieldName = new JTextField();
		textFieldName.setBounds(160, 220, 80, 20);
		panel.add(textFieldName);
		textFieldName.setColumns(10);
		table.addMouseListener(new MouseListener() {
			public void mouseClicked(MouseEvent e) {
				int row = table.getSelectedRow();
				textFieldName.setText(table.getValueAt(row, 1).toString());
				textFieldCost.setText(table.getValueAt(row, 2).toString());
				textFieldTime.setText(table.getValueAt(row, 3).toString());
				idServiceForWorker=table.getValueAt(row, 0).toString();
				// сделать кнопку обновления недоступной(пока нет изменений)
				//btnUpd.setEnabled(false);
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
		
		JButton btnUpdate = new JButton("\u0418\u0437\u043C\u0435\u043D\u0438\u0442\u044C");
		btnUpdate.setBounds(485, 243, 89, 23);
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (table.getSelectedRow() >= 0) {
					String n=textFieldName.getText();
					String c=textFieldCost.getText();
					String t=textFieldTime.getText();
					int flagN = 1;
					int flagC = 1;
					int flagT = 1;

					if (!isWorld(n)) {
						flagN = 0;
						JOptionPane.showMessageDialog(null, "Введите Название!");
					}
					
					if (!isNumeric(c)) {
						flagC = 0;
						JOptionPane.showMessageDialog(null, "Введите Стоимость!");
					}
					if (!isNumeric(t)) {
						flagT = 0;
						JOptionPane.showMessageDialog(null, "Введите Длительность!");
					}

					int row = table.getSelectedRow();
					int i = (int) table.getValueAt(row, 0);

					if (flagN == 1 && flagC == 1 && flagT == 1) {
						
						String query = "UPDATE `salon`.`sevice` SET `Name` = '" + n + "', `Cost` = '" + c
								+ "', `Time` = '" + t + "'"
								+ " WHERE `sevice`.`id_Service` = " + table.getValueAt(row, 0).toString() + ";";
						insert it = new insert();
						int newId = it.insertRow(query);
					
						if (newId > 0) {
							ServiceT_obj.updateRow(table.getSelectedRow(), new service(i, n,Integer.parseInt(textFieldCost.getText()), 
									Integer.parseInt(textFieldTime.getText())));
							textFieldName.setText(null);
							textFieldCost.setText(null);
							textFieldTime.setText(null);

						}
					}
				}

			}
		});
		panel.add(btnUpdate);
		
		JButton btnDelete = new JButton("\u0423\u0434\u0430\u043B\u0438\u0442\u044C");
		btnDelete.setBounds(485, 277, 89, 23);
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = table.getSelectedRow();
				int i = (int) table.getValueAt(row, 0);
				String query = "DELETE FROM  `salon`. `sevice` WHERE `sevice`.`id_Service` = " + table.getValueAt(row, 0).toString() + ";";
				insert it = new insert();
				int newId = it.insertRow(query);
				if (newId > 0) {
					ServiceT_obj.deleteRow(i);
				}
			
			}
		});
		panel.add(btnDelete);
		
		JButton btnSearch = new JButton("\u041D\u0430\u0439\u0442\u0438");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String search= textFieldForSearch.getText();
				try (Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/salon?serverTimezone=Europe/Moscow&useSSL=false", "root","")) 
				{
					PreparedStatement statement = connection.prepareStatement("SELECT *  FROM `sevice` "
							+ "WHERE `Name` LIKE '" + search + "' OR `Cost` LIKE '" + search + "' OR `"
							+ "Time`LIKE '" + search + "'");
					ResultSet res = statement.executeQuery();
					//if (!res.next()) {
						//JOptionPane.showMessageDialog(null, "Ничего не найдено...");
					// }

						ServiceT_obj.deleteAll();
						while (res.next()) {
							ServiceT_obj.addRow(new service(res.getInt(1), res.getString(2),res.getInt(3),res.getInt(4)));
					}
					
				} catch (Exception ex) {

					JOptionPane.showMessageDialog(null, "Connection failed..."+ex);
					System.out.println(ex);
				}
			}
		});
		btnSearch.setBounds(485, 12, 89, 23);
		panel.add(btnSearch);
		
		JButton btnCancel = new JButton("\u041E\u0442\u043C\u0435\u043D\u0430");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ServiceT_obj.deleteAll();
				textFieldForSearch.setText(null);
				try (Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/salon?serverTimezone=Europe/Moscow&useSSL=false", "root","")) 
				{
					PreparedStatement  statement = connection.prepareStatement("SELECT * \r\n" + 
							"FROM  `sevice`");
					ResultSet res = statement.executeQuery();
					while(res.next()) {
						ServiceT_obj.addRow(new service(res.getInt(1), res.getString(2),res.getInt(3),res.getInt(4)));
						
					}
				} catch (Exception ex) {
					
					JOptionPane.showMessageDialog(null, "Connection failed..."+ex);
					System.out.println(ex);
				}

			}
		});
		btnCancel.setBounds(485, 38, 89, 23);
		panel.add(btnCancel);
		
		textFieldForSearch = new JTextField();
		textFieldForSearch.setBounds(20, 12, 455, 20);
		panel.add(textFieldForSearch);
		textFieldForSearch.setColumns(10);
		try (Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/salon?serverTimezone=Europe/Moscow&useSSL=false", "root","")) 
		{
			PreparedStatement  statement = connection.prepareStatement("SELECT * \r\n" + 
					"FROM  `sevice`");
			ResultSet rest = statement.executeQuery();
			while(rest.next()) {
				ServiceT_obj.addRow(new service(rest.getInt("id_Service"),rest.getString("Name"), rest.getInt("Cost"),
						rest.getInt("Time")));
				
			}
		} catch (Exception ex) {
			
			JOptionPane.showMessageDialog(null, "Connection failed..."+ex);
			System.out.println(ex);
		}
		table.getColumnModel().getColumn(0).setMinWidth(0);
		   table.getColumnModel().getColumn(0).setMaxWidth(0);
		   table.getColumnModel().getColumn(0).setWidth(0);
	}
	public String idConn;
    public void setIDConn(String idConn) {
        this.idConn = idConn;
    }
	
	public void loadTwo() {
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE );
		setBounds(100, 100, 600, 350);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		getContentPane().setLayout(null);
		contentPane.setLayout(null);
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 584, 311);
		getContentPane().add(panel);
		final LinkedList<service> tbl = new LinkedList<service>();
		final ServiceTableModel ServiceT_obj = new ServiceTableModel(tbl);
		JButton btnAdd = new JButton("\u0414\u043E\u0431\u0430\u0432\u0438\u0442\u044C");
		btnAdd.setBounds(485, 209, 89, 23);
		btnAdd.setEnabled(false);
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try (Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/salon?serverTimezone=Europe/Moscow&useSSL=false", "root","")) 
				{
					int row = table.getSelectedRow();
					String iy = table.getValueAt(row, 0).toString();
					String query="INSERT INTO `salon`.`service_worker` (`id_Service`, `id_Worker`) "
					+" VALUES ('"+iy+"', '"+idConn+"');";
					insert it=new insert();
					it.insertRow(query);
				} catch (Exception ex) {
					
					JOptionPane.showMessageDialog(null, "Connection failed..."+ex);
					System.out.println(ex);
				}
				dispose();
			}
	
		});
		panel.setLayout(null);
		panel.add(btnAdd);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 72, 554, 120);
		panel.add(scrollPane);
		
		table = new JTable(ServiceT_obj);
		table.getColumnModel().getColumn(1).setPreferredWidth(98);
		scrollPane.setViewportView(table);
		
		table.addMouseListener(new MouseListener() {
			public void mouseClicked(MouseEvent e) {
				btnAdd.setEnabled(true);
				int row = table.getSelectedRow();
				table.getValueAt(row, 0).toString();
			
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
					"FROM  `sevice`");
			ResultSet rest = statement.executeQuery();
			while(rest.next()) {
				ServiceT_obj.addRow(new service(rest.getInt("id_Service"),rest.getString("Name"), rest.getInt("Cost"),
						rest.getInt("Time")));
				
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
