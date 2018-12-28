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
				return "�������";
			case 2:
				return "���";
			case 3:
				return "��������";
			case 4:
				return "�������";
			case 5:
				return "���������";
			
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
		public void addRow(customer nr) { // ���������� ������ � �������
			CustomerT_obj.add(nr);
			fireTableDataChanged();// �������� ��� ���������� �������
			// �������� ����������� ������ - ����� ����� ���������� ������� ������ ���������
			int index = CustomerT_obj.indexOf(nr);
			tablePane.table.setRowSelectionInterval(index, index);
		}

		public void updateRow(int index, customer nr) {// ���������� ������ � �������

			CustomerT_obj.set(index, nr);
			fireTableDataChanged();// �������� ��� ���������� �������
			// �������� ���������� ������ - ����� ����� ���������� ������� ������ ���������
			tablePane.table.setRowSelectionInterval(index, index);

		}
		public void deleteRow(int id) { // �������� ������ �� �������
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
				fireTableDataChanged();// �������� ��� ���������� �������
			} else
	// JOPCT_objionPane.showMessageDialog(myJframe.this,"������ "+Model+" ���������!")
				;
		}
		public void deleteAll() { // �������� ������ �� �������
			int size = CustomerT_obj.size();
			CustomerT_obj.clear();
			    fireTableRowsDeleted(0, size);
		}
	}
