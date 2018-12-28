package ud;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.awt.event.ActionEvent;
import java.awt.BorderLayout;
import javax.swing.JPasswordField;
import javax.swing.JRootPane;

public class Java_login extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField LoginTextField;
	private JLabel lblLogin;
	private JLabel lblPassword;
	private JPasswordField passwordTextField;
	public static boolean isAdmin(String x)
	{
	    Pattern p = Pattern.compile("^admin$");
	    Matcher m = p.matcher(x);
	    return m.matches();
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Java_login frame = new Java_login();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Java_login() {
		setBounds(100, 100, 450, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(new BorderLayout());

		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 434, 261);
		getContentPane().add(panel);
		panel.setLayout(null);

		JPanel panelMenu = new JPanel();
		panelMenu.setBounds(0, 0, 434, 261);
		panelMenu.setLayout(null);
		
		JButton btnCustomerButton = new JButton("\u041A\u043B\u0438\u0435\u043D\u0442\u044B");
		btnCustomerButton.setBounds(150, 10, 120, 30);
		btnCustomerButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
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
		});
		panelMenu.add(btnCustomerButton);

		JButton btnWorkerButton = new JButton("\u0421\u043E\u0442\u0440\u0443\u0434\u043D\u0438\u043A\u0438");
		btnWorkerButton.setBounds(150, 50, 120, 30);
		btnWorkerButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							tablePanelForWorker frame = new tablePanelForWorker ();
							frame.setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
			}
		});
		panelMenu.add(btnWorkerButton);

		JButton btnServiceButton = new JButton("\u0423\u0441\u043B\u0443\u0433\u0438");
		btnServiceButton.setBounds(150, 90, 120, 30);
		btnServiceButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							tablePanelForService frame = new tablePanelForService(2);
							frame.setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});

			}
		});
		panelMenu.add(btnServiceButton);

		JButton btnRecondButton = new JButton("Записать");
		btnRecondButton.setBounds(150, 130, 120, 30);
		btnRecondButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
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
		});
		panelMenu.add(btnRecondButton);
		
		JButton btnShowVisit = new JButton("Посещения");
		btnShowVisit.setBounds(150, 170, 120, 30);
		btnShowVisit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
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
		});
		panelMenu.add(btnShowVisit);

		JButton btnExitButton = new JButton("Выход");
		btnExitButton.setBounds(150, 210, 120, 30);
		btnExitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				getContentPane().remove(panelMenu);
				setContentPane(panel);
				LoginTextField.setText(null);
				passwordTextField.setText(null);
				panel.setLayout(null);
				update(getGraphics());
				/*dispose();
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							Java_login frame = new Java_login();
							frame.setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});*/
			}
		});
		panelMenu.add(btnExitButton);
		JButton btnUsers = new JButton("Пользователи");
		btnUsers.setBounds(10, 10, 130, 70);
		btnUsers.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
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
		});
		panelMenu.add(btnUsers);
		btnUsers.setVisible(false);

		LoginTextField = new JTextField();
		LoginTextField.setBounds(187, 57, 86, 20);

		panel.add(LoginTextField);
		//LoginTextField.setColumns(10);
		JButton btnSignUp = new JButton("Login");
		btnSignUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try (Connection connection = DriverManager.getConnection(
						"jdbc:mysql://127.0.0.1:3306/salon?serverTimezone=Europe/Moscow&useSSL=false", "root", "")) {
					// JOptionPane.showMessageDialog(null, "Connection to Store DB succesfull!");
					String uName = LoginTextField.getText();
					
					if(isAdmin(uName)){
						btnUsers.setVisible(true);
					}						
					
					@SuppressWarnings("deprecation")
					String query = "SELECT * FROM `user` WHERE `login`='"+LoginTextField.getText()+"' and `password`='"+passwordTextField.getText()+"'";
					PreparedStatement statement = connection.prepareStatement(query);
					//statement.setString(1, uName);
					//statement.setString(2, pass);

					ResultSet set = statement.executeQuery();
					if (set.next()) {
						getContentPane().remove(panel);
						// panel.removeAll();
						// panel.add(panelMenu);
						// getContentPane().add(panelMenu);
						setContentPane(panelMenu);
						panelMenu.setLayout(new BorderLayout());
						update(getGraphics());

					} else {
						JOptionPane.showMessageDialog(null, "Login uncorrect!");

					}

				} catch (Exception ex) {

					JOptionPane.showMessageDialog(null, "Connection failed..." + ex);
					System.out.println(ex);
				}

			}
		});

		passwordTextField = new JPasswordField();
		passwordTextField.setBounds(187, 88, 86, 20);
		panel.add(passwordTextField);
		passwordTextField.setColumns(10);
		btnSignUp.setBounds(187, 119, 86, 27);
		panel.add(btnSignUp);
		btnSignUp.setFont(new Font("Tahoma", Font.PLAIN, 15));

		lblPassword = new JLabel("Password");
		lblPassword.setBounds(74, 87, 61, 19);
		panel.add(lblPassword);
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 15));

		lblLogin = new JLabel("Login");
		lblLogin.setBounds(74, 56, 33, 19);
		panel.add(lblLogin);
		lblLogin.setFont(new Font("Tahoma", Font.PLAIN, 15));
		JRootPane rootPane = SwingUtilities.getRootPane(btnSignUp); 
		rootPane.setDefaultButton(btnSignUp);
	}
}
