package ud;
import java.util.Iterator;
import java.util.LinkedList;
import javax.swing.table.AbstractTableModel;

public class ServiceTableModel extends AbstractTableModel {

		private static final long serialVersionUID = 7927259757559420606L;
		private LinkedList<service> ServiceT_obj; 

		public ServiceTableModel(LinkedList<service> ServiceT_obj) {

			this.ServiceT_obj = ServiceT_obj;
		}
		
		public Class<?> getColumnClass(int columnIndex) {
			return getValueAt(0, columnIndex).getClass();
		}

		public int getColumnCount() {
			return 4;
		}
		
		public String getColumnName(int columnIndex) {
			switch (columnIndex) {
			case 0:
				return "ID";
			case 1:
				return "Название";
			case 2:
				return "Cтоимость";
			case 3:
				return "Время (минуты)";
			
			}
			return "";
		}
		public void addRow(service nr) { // добавление строки в таблицу
			ServiceT_obj.add(nr);
			fireTableDataChanged();// вызываем для обновления таблицы
			// выделяем добавленную строку - иначе после обновления таблицы теряет выделение
			int index = ServiceT_obj.indexOf(nr);
			tablePanelForService.table.setRowSelectionInterval(index, index);
		}

		public int getRowCount() {
			return ServiceT_obj.size();
		}

		public Object getValueAt(int rowIndex, int columnIndex) {
			service p = ServiceT_obj.get(rowIndex);
			switch (columnIndex) {
			case 0:
				return p.getID();
			case 1:
				return p.getName();
			case 2:
				return p.getCost();
			case 3:
				return p.getTime();
				
			}
			return "";
		}

		public boolean isCellEditable(int rowIndex, int columnIndex) {
			return false;
		}

		public void setValueAt(Object value, int rowIndex, int columnIndex) {
		}

		public void updateRow(int index, service nr) {

			ServiceT_obj.set(index, nr);
			fireTableDataChanged();
			tablePanelForService.table.setRowSelectionInterval(index, index);

		}
		public void deleteRow(int id) { 
			Iterator<service> isph = ServiceT_obj.iterator();
			boolean flag = false;
			while (isph.hasNext()) {
				if (isph.next().getID()==id) {
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
			int size = ServiceT_obj.size();
			ServiceT_obj.clear();
			    fireTableRowsDeleted(0, size);
		}
	}
