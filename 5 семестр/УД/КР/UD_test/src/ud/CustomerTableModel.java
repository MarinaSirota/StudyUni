package ud;
import java.util.Iterator;
import java.util.LinkedList;
import javax.swing.table.AbstractTableModel;

public class CustomerTableModel extends AbstractTableModel {

		private static final long serialVersionUID = 7927259757559420606L;
		private LinkedList<customer> CustomerT_obj; 

		public CustomerTableModel(LinkedList<customer> CustomerT_obj) {

			this.CustomerT_obj = CustomerT_obj;
		}
		public Class<?> getColumnClass(int columnIndex) {
			return getValueAt(0, columnIndex).getClass();
		}

		public int getColumnCount() {
			return 6;
		}
		
		public String getColumnName(int columnIndex) {
			switch (columnIndex) {
			case 0:
				return "ID";
			case 1:
				return "Фамилия";
			case 2:
				return "Имя";
			case 3:
				return "Отчество";
			case 4:
				return "Телефон";
			case 5:
				return "Посещения";
			
			}
			return "";
		}
		

		public int getRowCount() {
			return CustomerT_obj.size();
		}

		public Object getValueAt(int rowIndex, int columnIndex) {
			customer p = CustomerT_obj.get(rowIndex);
			switch (columnIndex) {
			case 0:
				return p.getID();
			case 1:
				return p.getSecondName();
			case 2:
				return p.getName();
			case 3:
				return p.getFatherName();
			case 4:
				return p.getPhone();
			case 5:
				return p.getVisit();
			}
			return "";
		}

		public boolean isCellEditable(int rowIndex, int columnIndex) {
			return false;
		}

		public void setValueAt(Object value, int rowIndex, int columnIndex) {
		}
		public void addRow(customer nr) { // добавление строки в таблицу
			CustomerT_obj.add(nr);
			fireTableDataChanged();// вызываем для обновления таблицы
			// выделяем добавленную строку - иначе после обновления таблицы теряет выделение
			int index = CustomerT_obj.indexOf(nr);
			tablePane.table.setRowSelectionInterval(index, index);
		}

		public void updateRow(int index, customer nr) {// обновление строки в таблице

			CustomerT_obj.set(index, nr);
			fireTableDataChanged();// вызываем для обновления таблицы
			// выделяем измененную строку - иначе после обновления таблицы теряет выделение
			tablePane.table.setRowSelectionInterval(index, index);

		}
		public void deleteRow(int id) { // удаление строки из таблицы
			Iterator<customer> isph = CustomerT_obj.iterator();
			boolean flag = false;
			while (isph.hasNext()) {
				if (isph.next().getID()==id) {
					isph.remove();
					flag = true;
					break;
				}
			}
			if (flag) {
				fireTableDataChanged();// вызываем для обновления таблицы
			} else
	// JOPCT_objionPane.showMessageDialog(myJframe.this,"Запись "+Model+" ненайдена!")
				;
		}
		public void deleteAll() { // удаление строки из таблицы
			int size = CustomerT_obj.size();
			CustomerT_obj.clear();
			    fireTableRowsDeleted(0, size);
		}
	}
