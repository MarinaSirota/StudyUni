import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.Scanner;
import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class myJframe extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	static JTable table;
	private JTextField textID;
	private JTextField textMakerCPU;
	private JTextField textFrequency;
	private JTextField textRAM;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					myJframe frame = new myJframe();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public myJframe() {
		setTitle("ПК");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 512);
		setBackground(new Color(232, 202, 202));

		JMenuBar menuBar = new JMenuBar();
		menuBar.setToolTipText("");
		setJMenuBar(menuBar);
		//menuBar.setBackground(new Color(232, 202, 202));

		JMenu menu = new JMenu("Файл");
		menuBar.add(menu);
		JMenuItem mntmNewMenuItem = new JMenuItem("Открыть файл");

		menu.add(mntmNewMenuItem);

		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Сохранить в файл");

		menu.add(mntmNewMenuItem_1);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.setBackground(new Color(232, 202, 202));

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 400, 250);
		contentPane.add(scrollPane);
		scrollPane.setBackground(new Color(232, 202, 202));

		final LinkedList<PC> tbl = new LinkedList<PC>();
		final PCTableModel PCT_obj = new PCTableModel(tbl);

		table = new JTable(PCT_obj);
		table.getColumnModel().getColumn(1).setPreferredWidth(98);
		scrollPane.setViewportView(table);

		
		JLabel label = new JLabel("ID");
		label.setBounds(10, 282, 130, 23);
		contentPane.add(label);

		JLabel label_1 = new JLabel("Произв процессора");
		label_1.setBounds(10, 313, 130, 23);
		contentPane.add(label_1);

		JLabel label_2 = new JLabel("Тактовая частота");
		label_2.setBounds(10, 344, 130, 23);
		contentPane.add(label_2);

		JLabel label_3 = new JLabel("Объем ОЗУ");
		label_3.setBounds(10, 375, 130, 23);
		contentPane.add(label_3);
		
		textID = new JTextField();
		textID.setBounds(160, 282, 140, 23);
		contentPane.add(textID);
		textID.setColumns(10);

		textMakerCPU = new JTextField();
		textMakerCPU.setColumns(10);
		textMakerCPU.setBounds(160, 313, 140, 23);
		contentPane.add(textMakerCPU);

		textFrequency = new JTextField();
		textFrequency.setColumns(10);
		textFrequency.setBounds(160, 344, 140, 23);
		contentPane.add(textFrequency);

		textRAM = new JTextField();
		textRAM.setColumns(10);
		textRAM.setBounds(160, 375, 140, 23);
		contentPane.add(textRAM);

		textRAM.getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void changedUpdate(DocumentEvent arg0) {

			}

			@Override
			public void insertUpdate(DocumentEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void removeUpdate(DocumentEvent arg0) {
				// TODO Auto-generated method stub

			}

		});

		JButton addbtn = new JButton("Добавить");
		addbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				PCT_obj.addRow(new PC(Integer.parseInt(textID.getText()), textMakerCPU.getText(),
						Integer.parseInt(textFrequency.getText()), Integer.parseInt(textRAM.getText())));
			}
		});
		addbtn.setBounds(310, 282, 95, 23);
		contentPane.add(addbtn);

		JButton delbtn = new JButton("Удалить");
		delbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PCT_obj.deleteRow(textMakerCPU.getText());

			}
		});
		delbtn.setBounds(310, 344, 95, 23);
		contentPane.add(delbtn);

		JButton sortbtn = new JButton("Сортировать");
		sortbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PCT_obj.Sort();

			}
		});
		sortbtn.setBounds(310, 375, 95, 23);
		contentPane.add(sortbtn);

		
		JButton btnUpdt = new JButton("Изменить");
		//btnUpdt.setForeground(new Color(186, 108, 156).brighter());
		//btnUpdt.setBackground(new Color(232, 202, 202));
		
		btnUpdt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (table.getSelectedRow() >= 0)
					PCT_obj.updateRow(table.getSelectedRow(),
							new PC(Integer.parseInt(textID.getText()), textMakerCPU.getText(),
									Integer.parseInt(textFrequency.getText()), Integer.parseInt(textRAM.getText())));
				else
					JOptionPane.showMessageDialog(myJframe.this, "Не выбрана ни одна строка таблицы");

			}
		});
		//btnUpdt.setForeground(new Color(186, 108, 156));
		btnUpdt.setBounds(310, 313, 95, 23);
		contentPane.add(btnUpdt);
		table.addMouseListener(new MouseListener() {

			public void mouseClicked(MouseEvent e) {
				int row = table.getSelectedRow();
				textID.setText(table.getValueAt(row, 0).toString());
				textMakerCPU.setText(table.getValueAt(row, 1).toString());
				textFrequency.setText(table.getValueAt(row, 2).toString());
				textRAM.setText(table.getValueAt(row, 3).toString());
				// сделать кнопку обновления недоступной(пока нет изменений)
				btnUpdt.setEnabled(false);
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

		textRAM.getDocument().addDocumentListener(new DocumentListener() {

			public void changedUpdate(DocumentEvent arg0) {
				btnUpdt.setEnabled(true);
				System.out.println("Was changed...");
			}

			public void insertUpdate(DocumentEvent e) {
				btnUpdt.setEnabled(true);
			}

			public void removeUpdate(DocumentEvent e) {
				btnUpdt.setEnabled(true);
			}
		});
		textID.getDocument().addDocumentListener(new DocumentListener() {

			public void changedUpdate(DocumentEvent arg0) {
				btnUpdt.setEnabled(true);
				System.out.println("Was changed...");
			}

			public void insertUpdate(DocumentEvent e) {
				btnUpdt.setEnabled(true);
			}

			public void removeUpdate(DocumentEvent e) {
				btnUpdt.setEnabled(true);
			}
		});
		textFrequency.getDocument().addDocumentListener(new DocumentListener() {

			public void changedUpdate(DocumentEvent arg0) {
				btnUpdt.setEnabled(true);
				System.out.println("Was changed...");
			}

			public void insertUpdate(DocumentEvent e) {
				btnUpdt.setEnabled(true);
			}

			public void removeUpdate(DocumentEvent e) {
				btnUpdt.setEnabled(true);
			}
		});
		textMakerCPU.getDocument().addDocumentListener(new DocumentListener() {

			public void changedUpdate(DocumentEvent arg0) {
				btnUpdt.setEnabled(true);
				System.out.println("Was changed...");
			}

			public void insertUpdate(DocumentEvent e) {
				btnUpdt.setEnabled(true);
			}

			public void removeUpdate(DocumentEvent e) {
				btnUpdt.setEnabled(true);
			}
		});

		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooser = new JFileChooser();
				int returnVal = chooser.showOpenDialog(getParent());
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					System.out.println("Открытие файла - : " + chooser.getSelectedFile().getName());
					Scanner in = null;
					try {
						in = new Scanner(new File(chooser.getSelectedFile().getAbsolutePath()));
						in.useDelimiter(";");
					} catch (FileNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

					while (in.hasNext()) {
						PC t = new PC(in.nextInt(),in.next(), in.nextInt(), in.nextInt());
						PCT_obj.addRow(t);
					}
				}
			}
		});

		mntmNewMenuItem_1.addActionListener(new ActionListener() {
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
					PC[] myArray = {};
					myArray = tbl.toArray(new PC[tbl.size()]);
					for (int i = 0; i < myArray.length; i++) {
						out.print(myArray[i].id + ";" + myArray[i].makerCPU + ";" + myArray[i].frequency + ";"
								+ myArray[i].RAM + ";");
					}
					out.close();

				}
			}
		});

	}
}
