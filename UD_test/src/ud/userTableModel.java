package ud;
import java.util.Iterator;
import java.util.LinkedList;
import javax.swing.table.AbstractTableModel;

public class userTableModel extends AbstractTableModel{
	private static final long serialVersionUID = 7927259757559420606L;
	private LinkedList<user> UserT_obj; 

	public userTableModel(LinkedList<user> UserT_obj) {

		this.UserT_obj = UserT_obj;
	}
	
	public Class<?> getColumnClass(int columnIndex) {
		return getValueAt(0, columnIndex).getClass();
	}

	public int getColumnCount() {
		return 2;
	}
	
	public String getColumnName(int columnIndex) {
		switch (columnIndex) {
		case 0:
			return "Логин";
		case 1:
			return "Пароль";		
		}
		return "";
	}
	public void addRow(user nr) { // добавление строки в таблицу
		UserT_obj.add(nr);
		fireTableDataChanged();// вызываем для обновления таблицы
		// выделяем добавленную строку - иначе после обновления таблицы теряет выделение
		int index = UserT_obj.indexOf(nr);
		tablePanelForUser.table.setRowSelectionInterval(index, index);
	}

	public int getRowCount() {
		return UserT_obj.size();
	}

	public Object getValueAt(int rowIndex, int columnIndex) {
		user p = UserT_obj.get(rowIndex);
		switch (columnIndex) {
		case 0:
			return p.getLogin();
		case 1:
			return p.getPassword();

		}
		return "";
	}

	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return false;
	}

	public void setValueAt(Object value, int rowIndex, int columnIndex) {
	}

	public void updateRow(int index, user nr) {

		UserT_obj.set(index, nr);
		fireTableDataChanged();
		tablePanelForUser.table.setRowSelectionInterval(index, index);

	}
	public void deleteRow(String login) { 
		Iterator<user> isph = UserT_obj.iterator();
		boolean flag = false;
		while (isph.hasNext()) {
			if (isph.next().getLogin().equals(login)) {
				isph.remove();
				flag = true;
				break;
			}
		}
		if (flag) {
			fireTableDataChanged();
		} else
			;
	}
	public void deleteAll() { // удаление строки из таблицы
		int size = UserT_obj.size();
		UserT_obj.clear();
		    fireTableRowsDeleted(0, size);
	}
}
