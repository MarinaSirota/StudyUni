import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import javax.swing.table.AbstractTableModel;

public class PCTableModel extends AbstractTableModel {
//������������� ��� ������������
	private static final long serialVersionUID = 7927259757559420606L;
	private LinkedList<PC> PCT_obj; // ��������� ������

	public PCTableModel(LinkedList<PC> PCT_obj) {// ����������� ������

		this.PCT_obj = PCT_obj;
	}

//������������ ����� - ���������� ��� �������� �������
	public Class<?> getColumnClass(int columnIndex) {
		return getValueAt(0, columnIndex).getClass();
	}

	public int getColumnCount() {
		return 4;
	}

//������������ ����� - ���������� ��� �������
	public String getColumnName(int columnIndex) {
		switch (columnIndex) {
		case 0:
			return "ID";
		case 1:
			return "������ ����������";
		case 2:
			return "�������� �������";
		case 3:
			return "����� ���";
		}
		return "";
	}

	public int getRowCount() {// ������������ ����� - ���������� ���������� �����
		return PCT_obj.size();
	}

//������������ ����� - ���������� �������� ���� �������
	public Object getValueAt(int rowIndex, int columnIndex) {
		PC p = PCT_obj.get(rowIndex);
		switch (columnIndex) {
		case 0:
			return p.getID();
		case 1:
			return p.getMakerCPU();
		case 2:
			return p.getFrequency();
		case 3:
			return p.getRAM();
		}
		return "";
	}

// ���������� �������� �� �������������� �������
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return false;
	}

//��������� ��������� ������ � ������
	public void setValueAt(Object value, int rowIndex, int columnIndex) {
	}

	public void addRow(PC nr) { // ���������� ������ � �������
		PCT_obj.add(nr);
		fireTableDataChanged();// �������� ��� ���������� �������
		// �������� ����������� ������ - ����� ����� ���������� ������� ������ ���������
		int index = PCT_obj.indexOf(nr);
		myJframe.table.setRowSelectionInterval(index, index);
	}

	public void deleteRow(String makerCPU) { // �������� ������ �� �������
		Iterator<PC> isph = PCT_obj.iterator();
		boolean flag = false;
		while (isph.hasNext()) {
			if (isph.next().getMakerCPU().equals(makerCPU)) {
				isph.remove();
				flag = true;
				break;
			}
		}
		if (flag) {
			fireTableDataChanged();// �������� ��� ���������� �������
		} else
// JOPCT_objionPane.showMessageDialog(myJframe.this,"������ "+Model+" ���������!")
			;
	}

	public void updateRow(int index, PC nr) {// ���������� ������ � �������

		PCT_obj.set(index, nr);
		fireTableDataChanged();// �������� ��� ���������� �������
		// �������� ���������� ������ - ����� ����� ���������� ������� ������ ���������
		myJframe.table.setRowSelectionInterval(index, index);

	}

	public void Sort() { // ����������
		Collections.sort(PCT_obj);
		fireTableDataChanged(); // �������� ��� ���������� �������
	}
}
