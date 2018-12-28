package ud;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JTextPane;

public class visitAdd extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldSecondNameCustomer;
	private JTextField textFieldNameCustomer;
	private JTextField textFieldDay;
	public String idCustomerSelected;
	public String idServiceSelected;
	public String idWorkerSelected;
	public String daySelected;
	public String serviceIdSelected;
	public String workerIdSelected;
	public String timeSelected ;
	public String serviceNameWorkerNameSelected;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					visitAdd frame = new visitAdd();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public static boolean isWorld(String x)
	{
	    Pattern p = Pattern.compile("^[а-яА-ЯёЁ]+$");
	    Matcher m = p.matcher(x);
	    return m.matches();
	}
	public static boolean isDate(String x)
	{
	    Pattern p = Pattern.compile("^20\\d{2}-\\d{2}-\\d{2}$");
	    Matcher m = p.matcher(x);
	    return m.matches();
	}
	/**
	 * Create the frame.
	 */
	public void checkFio() {
		
		if(textFieldNameCustomer.getText()!=null && textFieldSecondNameCustomer.getText()!=null)
		{
			String sn=textFieldSecondNameCustomer.getText();
			String n=textFieldNameCustomer.getText();	
			int flagSn = 1;
			int flagN = 1;
			if (!isWorld(sn)) {
				flagSn = 0;
				JOptionPane.showMessageDialog(null, "Введите Фамилию!");
			}
			if (!isWorld(n)) {
				flagN = 0;
				JOptionPane.showMessageDialog(null, "Введите Имя!");
			}
			if (flagSn == 1 && flagN == 1) {
				
				try (Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/salon?serverTimezone=Europe/Moscow&useSSL=false", "root","")) 
				{
					PreparedStatement  statement = connection.prepareStatement("SELECT `id_Customer` \r\n" + 
							"FROM  `customer`"
							+ "where `Second_Name`='"+sn+"'AND `Name`='"+n+"'");
					ResultSet rest = statement.executeQuery();
					while(rest.next()) {
						
						idCustomerSelected=rest.getString(1);
						
						
					}
					
				} catch (Exception ex) {
					
					JOptionPane.showMessageDialog(null, "Connection failed..."+ex);
					System.out.println(ex);
				}
			}
			
		}
		
	
	}
	public int checkDateTime() {
		
		
		
		String d=textFieldDay.getText();
		String flagForCheckExistenceTimeAndDate=null;
		int flag=0;
		int flagD=1;if (!isDate(d)) {
			flagD = 0;
			JOptionPane.showMessageDialog(null, "Введите Телефон!");
		}
		
			
			if (flagD == 1) {
				
				try (Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/salon?serverTimezone=Europe/Moscow&useSSL=false", "root","")) 
				{
					PreparedStatement  statement = connection.prepareStatement("SELECT *  FROM `record` "
							+ "WHERE `id_Service` = '"+idServiceSelected+"' AND `id_Worker` = '"+idWorkerSelected+"' AND `Date` = '"+textFieldDay.getText()+"' AND `Hour` = '"+timeSelected+"'");
					ResultSet rest = statement.executeQuery();
					while(rest.next()) {
						
						flagForCheckExistenceTimeAndDate=rest.getString(1);
						flag=1;
						
						
					}
					
				} catch (Exception ex) {
					
					JOptionPane.showMessageDialog(null, "Connection failed..."+ex+flagForCheckExistenceTimeAndDate);
					System.out.println(ex);
				}
				
			}
			return flag;
			
		
	
	}
	public visitAdd() {
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 582, 222);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblDay = new JLabel("\u0414\u0430\u0442\u0430");
		lblDay.setBounds(321, 11, 31, 14);
		contentPane.add(lblDay);
		
		JLabel lblTime = new JLabel("\u0412\u0440\u0435\u043C\u044F");
		lblTime.setBounds(250, 42, 46, 14);
		contentPane.add(lblTime);
		
		JLabel lblSecondNameCustomer = new JLabel("\u0424\u0430\u043C\u0438\u043B\u0438\u044F");
		lblSecondNameCustomer.setBounds(10, 11, 46, 14);
		contentPane.add(lblSecondNameCustomer);
		
		JLabel lblNameCustomer = new JLabel("\u0418\u043C\u044F");
		lblNameCustomer.setBounds(171, 11, 31, 14);
		contentPane.add(lblNameCustomer);
		
		textFieldSecondNameCustomer = new JTextField();
		textFieldSecondNameCustomer.setBounds(65, 8, 86, 20);
		contentPane.add(textFieldSecondNameCustomer);
		textFieldSecondNameCustomer.setColumns(10);
		
		textFieldNameCustomer = new JTextField();
		textFieldNameCustomer.setBounds(210, 8, 86, 20);
		contentPane.add(textFieldNameCustomer);
		textFieldNameCustomer.setColumns(10);
		
		textFieldDay = new JTextField();
		textFieldDay.setBounds(362, 8, 86, 20);
		contentPane.add(textFieldDay);
		textFieldDay.setColumns(10);
		
		
				JComboBox<String> comboBoxServiceWorker = new JComboBox<String>();
				comboBoxServiceWorker.setBounds(10, 67, 230, 20);
		contentPane.add(comboBoxServiceWorker);
		try (Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/salon?serverTimezone=Europe/Moscow&useSSL=false", "root","")) 
		{
			PreparedStatement  statement = connection.prepareStatement("SELECT `sevice`.`Name`, `worker`.`Second_Name`, `worker`.`Name` FROM `sevice` \r\n" + 
					"inner join `service_worker` \r\n" + 
					"on (`sevice`.`id_Service`=`service_worker`.`id_Service`) \r\n" + 
					"inner join `worker` \r\n" + 
					"on (`worker`.`id_Worker`=`service_worker`.`id_worker`)\r\n" + 
					"\r\n" + 
					"");
			ResultSet rest = statement.executeQuery();
			while(rest.next()) {
				String concat= rest.getString(1) +" "+rest.getString(2)+" "+ rest.getString(3);
				comboBoxServiceWorker.addItem(concat);
				
			}
			
		} catch (Exception ex) {
			
			JOptionPane.showMessageDialog(null, "Connection failed..."+ex);
			System.out.println(ex);
		}
		comboBoxServiceWorker.setSelectedItem(null);
		
		
		JLabel lblServiceWorker = new JLabel("\u0423\u0441\u043B\u0443\u0433\u0430");
		lblServiceWorker.setBounds(10, 42, 46, 14);
		contentPane.add(lblServiceWorker);
		
		JComboBox<String> comboBoxTime = new JComboBox<String>();
		comboBoxTime.setBounds(250, 67, 63, 20);
		contentPane.add(comboBoxTime);
		comboBoxTime.addItem("8");
		comboBoxTime.addItem("10");
		comboBoxTime.addItem("12");
		comboBoxTime.addItem("14");
		comboBoxTime.addItem("16");
		comboBoxTime.addItem("18");
		comboBoxTime.setSelectedItem(null);
		
		comboBoxTime.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                @SuppressWarnings("unchecked")
                JComboBox<String> comboBoxTime = (JComboBox<String>)e.getSource();
                timeSelected = (String)comboBoxTime.getSelectedItem();
                JOptionPane.showMessageDialog(null, timeSelected);
            }
      		});
                
		comboBoxServiceWorker.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                @SuppressWarnings("unchecked")
                JComboBox<String> comboBoxService = (JComboBox<String>)e.getSource();
                serviceNameWorkerNameSelected = (String)comboBoxService.getSelectedItem();
                JOptionPane.showMessageDialog(null, serviceNameWorkerNameSelected);
                
                String str []=serviceNameWorkerNameSelected.split(" ");
                String s=str[0];
                String sn=str[1];
                String n=str[2];
                try (Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/salon?serverTimezone=Europe/Moscow&useSSL=false", "root","")) 
        		{
        			PreparedStatement  statement = connection.prepareStatement("SELECT `service_worker`.`id_Service`, `service_worker`.`id_Worker` FROM `service_worker` \r\n" + 
        					"inner join `sevice` \r\n" + 
        					"on (`sevice`.`id_Service`=`service_worker`.`id_Service` and `sevice`.`Name`='"+s+"' ) \r\n" + 
        					"inner join `worker` \r\n" + 
        					"on (`worker`.`id_Worker`=`service_worker`.`id_worker` and `worker`.`Name`='"+n+"' and `worker`.`Second_Name`='"+sn+"' )\r\n" + 
        					"");
        			ResultSet rest = statement.executeQuery();
        			while(rest.next()) {
        				
        				idServiceSelected=rest.getString(1);
        				idWorkerSelected=rest.getString(2);
        			}
        			
        		} catch (Exception ex) {
        			
        			JOptionPane.showMessageDialog(null, "Connection failed..."+ex);
        			System.out.println(ex);
        		}        
                
            }
		});

		JButton btnWriteRecord = new JButton("\u0417\u0430\u043F\u0438\u0441\u0430\u0442\u044C");
		btnWriteRecord.setBounds(467, 149, 89, 23);
		btnWriteRecord.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try (Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/salon?serverTimezone=Europe/Moscow&useSSL=false", "root","")) 
				{
					String d=textFieldDay.getText();
					int flagD=1;
					int flagC=1;
					int flagS=1;
					int flagW=1;
					String flagForCheckExistenceRecord=null;
					if (!isDate(d)) {
						flagD = 0;
						JOptionPane.showMessageDialog(null, "Введите Телефон!");
					}
					checkFio();
					if(idCustomerSelected==null)
					{
						flagC=0;
						JOptionPane.showMessageDialog(null,  "Такого клиента не существует.");
					}
					if(idServiceSelected==null)
					{
						flagS=0;
						JOptionPane.showMessageDialog(null,  "Выберете услугу!");
					}
					if(idWorkerSelected==null)
					{
						flagW=0;
						JOptionPane.showMessageDialog(null,  "Выберете услFFFугу!");
					}
					
					int t=checkDateTime();
					if (flagD==1 && flagC==1 && flagS==1 &&flagW==1 && t==0) {
					
					PreparedStatement  statement = connection.prepareStatement("SELECT `Status` FROM `record` where "
							+ "`id_Customer`='"+idCustomerSelected+"' "
							+ "and `id_Service`='"+idServiceSelected+"' and `id_Worker`= '"+idWorkerSelected+"'and `Date` = '"+d+"'and `Hour`='"+timeSelected+"'");
					ResultSet rest = statement.executeQuery();
					while(rest.next()) {
						flagForCheckExistenceRecord=rest.getString(1);
						
					}
					if(flagForCheckExistenceRecord==null) {
						insert it = new insert();
						it.insertRow("INSERT INTO  `salon`.`record` \r\n" + 
								"(`id_Customer` , `id_Service` , `id_Worker` , `Date` , `Hour` , `Status`)\r\n" + 
								"VALUES (\r\n" + 
								"'"+idCustomerSelected+"',  '"+idServiceSelected+"',  '"+idWorkerSelected+"',  '"+d+"',  '"+timeSelected+"',  '0')");
		                String str []=serviceNameWorkerNameSelected.split(" ");
		                String serviceTemp=str[0];
		                String workerSNameTemp=str[1];
		                String workerNameTemp=str[2];
						JTextPane textPane = new JTextPane();
						textPane.setBounds(10, 108, 447, 80);
						contentPane.add(textPane);
						textPane.setText("Клиент:"+textFieldSecondNameCustomer.getText()+" "+textFieldNameCustomer.getText()
								+" \nУслуга: "+serviceTemp+" \n"+"Сотрудник: "+workerSNameTemp+" "
								+workerNameTemp+"\n Дата:"+textFieldDay.getText()
								+"\nВремя:"+timeSelected);
						
					}
					else if(flagForCheckExistenceRecord!=null) {
						JOptionPane.showMessageDialog(null, "Это время занято! Выберете другое.");
					}
					}
					else {
						JOptionPane.showMessageDialog(null, "Это время занято! Выберете другое345434r3t.");
					}
				} catch (Exception ex) {
					
					JOptionPane.showMessageDialog(null, "Connection failed..."+ex);
					System.out.println(ex);
				}    
				dispose();

			}
		});
		contentPane.add(btnWriteRecord);
		
		
	}
}
