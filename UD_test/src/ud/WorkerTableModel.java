package ud;
import java.util.Iterator;
import java.util.LinkedList;
import javax.swing.table.AbstractTableModel;

public class WorkerTableModel extends AbstractTableModel {

		private static final long serialVersionUID = 7927259757559420606L;
		private LinkedList<worker> WorkerT_obj; 

		public WorkerTableModel(LinkedList<worker> WorkerT_obj) {

			this.WorkerT_obj = WorkerT_obj;
		}
		public Class<?> getColumnClass(int columnIndex) {
			return getValueAt(0, columnIndex).getClass();
		}

		public int getColumnCount() {
			return 7;
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
				return "Процент";
			case 6:
				return "ДтПрнт";
			
			}
			return "";
		}
		public void addRow(worker nr) { // добавление строки в таблицу
			WorkerT_obj.add(nr);
			fireTableDataChanged();// вызываем для обновления таблицы
			// выделяем добавленную строку - иначе после обновления таблицы теряет выделение
			int index = WorkerT_obj.indexOf(nr);
			tablePanelForWorker.table.setRowSelectionInterval(index, index);
		}

		public int getRowCount() {
			return WorkerT_obj.size();
		}

		public Object getValueAt(int rowIndex, int columnIndex) {
			worker p = WorkerT_obj.get(rowIndex);
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
				return p.getProssent();
			case 6:
				return p.getDate();
			}
			return "";
		}

		public boolean isCellEditable(int rowIndex, int columnIndex) {
			return false;
		}

		public void setValueAt(Object value, int rowIndex, int columnIndex) {
		}

		public void updateRow(int index, worker nr) {

			WorkerT_obj.set(index, nr);
			fireTableDataChanged();
			tablePanelForWorker.table.setRowSelectionInterval(index, index);

		}
		public void deleteRow(int id) { 
			Iterator<worker> isph = WorkerT_obj.iterator();
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
			int size = WorkerT_obj.size();
			WorkerT_obj.clear();
			    fireTableRowsDeleted(0, size);
		}
	}
