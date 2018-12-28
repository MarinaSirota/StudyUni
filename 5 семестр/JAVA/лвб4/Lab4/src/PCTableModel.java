import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import javax.swing.table.AbstractTableModel;

public class PCTableModel extends AbstractTableModel {
//идентификатор для сериализации
	private static final long serialVersionUID = 7927259757559420606L;
	private LinkedList<PC> PCT_obj; // хранилище данных

	public PCTableModel(LinkedList<PC> PCT_obj) {// конструктор модели

		this.PCT_obj = PCT_obj;
	}

//обязательный метод - возвращает тип значения столбца
	public Class<?> getColumnClass(int columnIndex) {
		return getValueAt(0, columnIndex).getClass();
	}

	public int getColumnCount() {
		return 4;
	}

//обязательный метод - возвращает имя столбца
	public String getColumnName(int columnIndex) {
		switch (columnIndex) {
		case 0:
			return "ID";
		case 1:
			return "Произв процессора";
		case 2:
			return "Тактовая частота";
		case 3:
			return "Обьем ОЗУ";
		}
		return "";
	}

	public int getRowCount() {// обязательный метод - возвращает количество строк
		return PCT_obj.size();
	}

//обязательный метод - возвращает значение поля таблицы
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

// определяет возможно ли редактирование таблицы
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return false;
	}

//выполняет изменение данных в ячейке
	public void setValueAt(Object value, int rowIndex, int columnIndex) {
	}

	public void addRow(PC nr) { // добавление строки в таблицу
		PCT_obj.add(nr);
		fireTableDataChanged();// вызываем для обновления таблицы
		// выделяем добавленную строку - иначе после обновления таблицы теряет выделение
		int index = PCT_obj.indexOf(nr);
		myJframe.table.setRowSelectionInterval(index, index);
	}

	public void deleteRow(String makerCPU) { // удаление строки из таблицы
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
			fireTableDataChanged();// вызываем для обновления таблицы
		} else
// JOPCT_objionPane.showMessageDialog(myJframe.this,"Запись "+Model+" ненайдена!")
			;
	}

	public void updateRow(int index, PC nr) {// обновление строки в таблице

		PCT_obj.set(index, nr);
		fireTableDataChanged();// вызываем для обновления таблицы
		// выделяем измененную строку - иначе после обновления таблицы теряет выделение
		myJframe.table.setRowSelectionInterval(index, index);

	}

	public void Sort() { // сортировка
		Collections.sort(PCT_obj);
		fireTableDataChanged(); // вызываем для обновления таблицы
	}
}
