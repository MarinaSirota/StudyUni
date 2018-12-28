package ud;

import java.util.LinkedList;
import javax.swing.table.AbstractTableModel;

public class RecordTableModel extends AbstractTableModel {
	private static final long serialVersionUID = 7927259757559420606L;
	private LinkedList<record> RecordT_obj; 
	public RecordTableModel(LinkedList<record> RecordT_obj) {// ����������� ������

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
			return "K��������";
		case 1:
			return "K����";
		case 2:
			return "����������";
		case 3:
			return "���������";
		case 4:
			return "��������";
		case 5:
			return "����";
		case 6:
			return "���������";
		case 7:
			return "��������";
		case 8:
			return "������";
		case 9:
			return "����";
		case 10:
			return "�����";
		case 11:
			return "������";
	
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
	public void addRow(record nr) { // ���������� ������ � �������
		RecordT_obj.add(nr);
		fireTableDataChanged();// �������� ��� ���������� �������
		// �������� ����������� ������ - ����� ����� ���������� ������� ������ ���������
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
	public void deleteAll() { // �������� ������ �� �������
		int size = RecordT_obj.size();
		RecordT_obj.clear();
		    fireTableRowsDeleted(0, size);
	}
}
