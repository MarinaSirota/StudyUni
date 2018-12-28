package ud;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import java.awt.BorderLayout;
import javax.swing.JPasswordField;

public class Java_login3 extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField LoginTextField;
	private JLabel lblLogin;
	private JLabel lblPassword;
	private JPasswordField passwordTextField;
	static JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Java_login3 frame = new Java_login3();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Java_login3() {
		setBounds(100, 100, 450, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(new BorderLayout());

		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 434, 261);
		getContentPane().add(panel);
		panel.setLayout(null);

		JPanel panelMenu = new JPanel();
		panelMenu.setBounds(0, 0, 434, 261);
		JButton btnCustomerButton = new JButton("\u041A\u043B\u0438\u0435\u043D\u0442\u044B");
		btnCustomerButton.setBounds(20, 30, 113, 23);
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
		btnWorkerButton.setBounds(20, 50, 113, 23);
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
		btnServiceButton.setBounds(20, 80, 113, 23);
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

		JButton btnRecondButton = new JButton("\u0417\u0430\u043F\u0438\u0441\u044C");
		btnRecondButton.setBounds(20, 110, 113, 23);
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

		JButton btnExitButton = new JButton("Выход");
		btnExitButton.setBounds(20, 140, 113, 23);
		btnExitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				getContentPane().remove(panelMenu);
				setContentPane(panel);
				panel.setLayout(null);
				update(getGraphics());
			}
		});
		panelMenu.add(btnExitButton);

		LoginTextField = new JTextField();
		LoginTextField.setBounds(187, 57, 86, 20);
		panel.add(LoginTextField);
		LoginTextField.setColumns(10);
		JButton btnSignUp = new JButton("Login");
		btnSignUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try (Connection connection = DriverManager.getConnection(
						"jdbc:mysql://127.0.0.1:3306/salon?serverTimezone=Europe/Moscow&useSSL=false", "root", "")) {
					// JOptionPane.showMessageDialog(null, "Connection to Store DB succesfull!");
					String uName = LoginTextField.getText();
					if(LoginTextField.getText()=="admin") {
						System.out.println("rtredfgdx");
					}
					@SuppressWarnings("deprecation")
					String pass = passwordTextField.getText();
					String query = "SELECT * FROM `user` WHERE `user_Name`=? and `password`=?";
					PreparedStatement statement = connection.prepareStatement(query);
					statement.setString(1, uName);
					statement.setString(2, pass);

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

		/*
		 * JButton btnNewButton = new JButton("New button");
		 * btnNewButton.addActionListener(new ActionListener() { public void
		 * actionPerformed(ActionEvent arg0) { getContentPane().remove(panel);
		 * getContentPane().add(panelMenu); panelMenu.doLayout(); update(getGraphics());
		 * } }); btnNewButton.setBounds(172, 175, 89, 23); panel.add(btnNewButton);
		 */
	}
}
