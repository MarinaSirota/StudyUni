package ud;

import java.util.LinkedList;
import javax.swing.table.AbstractTableModel;

public class RecordTableModel extends AbstractTableModel {
	private static final long serialVersionUID = 7927259757559420606L;
	private LinkedList<record> RecordT_obj; 
	public RecordTableModel(LinkedList<record> RecordT_obj) {// конструктор модели

		this.RecordT_obj = RecordT_obj;
	}
	public Class<?> getColumnClass(int columnIndex) {
		return getValueAt(0, columnIndex).getClass();
	}

	public int getColumnCount() {
		return 12;
	}
	public String getColumnName(int columnIndex) {
		switch (columnIndex) {
		case 0:
			return "KЛФамилия";
		case 1:
			return "KЛИмя";
		case 2:
			return "КЛОтчество";
		case 3:
			return "КЛТелефон";
		case 4:
			return "СФамилия";
		case 5:
			return "СИмя";
		case 6:
			return "СОтчество";
		case 7:
			return "СТелефон";
		case 8:
			return "Услуга";
		case 9:
			return "Дата";
		case 10:
			return "Время";
		case 11:
			return "Статус";
	
		}
		return "";
	}
	

	public int getRowCount() {
		return RecordT_obj.size();
	}

	public Object getValueAt(int rowIndex, int columnIndex) {
		record p = RecordT_obj.get(rowIndex);
		switch (columnIndex) {
		case 0:
			return p.getSecondNameCustomer();
		case 1:
			return p.getNameCustomer();
		case 2:
			return p.getFatherNameCustomer();
		case 3:
			return p.getPhoneCustomer();
		case 4:
			return p.getSecondNameWorker();
		case 5:
			return p.getNameWorker();
		case 6:
			return p.getFatherNameWorker();
		case 7:
			return p.getPhoneWorker();
		case 8:
			return p.getNameService();
		case 9:
			return p.getDateService();
		case 10:
			return p.getHourService();
		case 11:
			return p.getStatusService();
			
			
		}
		return "";
	}
	public void addRow(record nr) { // добавление строки в таблицу
		RecordT_obj.add(nr);
		fireTableDataChanged();// вызываем для обновления таблицы
		// выделяем добавленную строку - иначе после обновления таблицы теряет выделение
		int index = RecordT_obj.indexOf(nr);
		panelRecord.table.setRowSelectionInterval(index, index);
	}
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return false;
	}

	public void setValueAt(Object value, int rowIndex, int columnIndex) {
	}

	public void updateRow(int index, record nr) {

		RecordT_obj.set(index, nr);
		fireTableDataChanged();
		panelRecord.table.setRowSelectionInterval(index, index);

	}
	public void deleteAll() { // удаление строки из таблицы
		int size = RecordT_obj.size();
		RecordT_obj.clear();
		    fireTableRowsDeleted(0, size);
	}
}
