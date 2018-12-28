package lab6;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

import javax.swing.JFileChooser;

import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.chart.*;

public class TableViewExample extends Application {
	private TableView<Phone> table = new TableView<Phone>();
	private final ObservableList<Phone> data = FXCollections.observableArrayList();

	final HBox hb = new HBox();
	ObservableList<PieChart.Data> pcData = FXCollections.observableArrayList();

	PieChart chart = new PieChart();

	public void UpdateChart() {
		int i;
		pcData.clear();
		for (i = 0; i < data.size(); i++) {
			pcData.add(new PieChart.Data(data.get(i).getModel(), Double.parseDouble(data.get(i).getSize())));
		}
		chart.setData(pcData);
	}

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) {
		Scene scene = new Scene(new Group());
		stage.setTitle("Table");
		stage.setWidth(1050);
		stage.setHeight(650);
		final Label label = new Label("Модели телефонов");
		label.setFont(new Font("Arial", 20));
		label.setMaxWidth(501);
		table.setEditable(true);

		data.addListener(new ListChangeListener<Phone>() {
			@Override
			public void onChanged(javafx.collections.ListChangeListener.Change<? extends Phone> arg0) {
// TODO Auto-generated method stub
				UpdateChart();
			}
		});
		TableColumn ModelCol = new TableColumn("Модель");
		ModelCol.setMinWidth(100);
		ModelCol.setCellValueFactory(new PropertyValueFactory<Phone, String>("model"));
		ModelCol.setCellFactory(TextFieldTableCell.forTableColumn());
		ModelCol.setOnEditCommit(new EventHandler<CellEditEvent<Phone, String>>() {
			@Override
			public void handle(CellEditEvent<Phone, String> t) {
				((Phone) t.getTableView().getItems().get(t.getTablePosition().getRow())).setModel(t.getNewValue());
			}
		});

		TableColumn SizeCol = new TableColumn("Размер экрана");
		SizeCol.setMinWidth(100);
		SizeCol.setCellValueFactory(new PropertyValueFactory<Phone, String>("size"));
		SizeCol.setCellFactory(TextFieldTableCell.forTableColumn());
		SizeCol.setOnEditCommit(new EventHandler<CellEditEvent<Phone, String>>() {
			@Override
			public void handle(CellEditEvent<Phone, String> t) {
				((Phone) t.getTableView().getItems().get(t.getTablePosition().getRow())).setSize(t.getNewValue());
			}
		});

		TableColumn TypeCol = new TableColumn("Тип экрана");
		TypeCol.setMinWidth(100);
		TypeCol.setCellValueFactory(new PropertyValueFactory<Phone, String>("type"));
		TypeCol.setCellFactory(TextFieldTableCell.forTableColumn());
		TypeCol.setOnEditCommit(new EventHandler<CellEditEvent<Phone, String>>() {
			@Override
			public void handle(CellEditEvent<Phone, String> t) {
				((Phone) t.getTableView().getItems().get(t.getTablePosition().getRow())).setSize(t.getNewValue());
			}
		});

		TableColumn RamCol = new TableColumn("Кол-во ОЗУ");
		RamCol.setMinWidth(100);
		RamCol.setCellValueFactory(new PropertyValueFactory<Phone, String>("ram"));
		RamCol.setCellFactory(TextFieldTableCell.forTableColumn());
		RamCol.setOnEditCommit(new EventHandler<CellEditEvent<Phone, String>>() {
			@Override
			public void handle(CellEditEvent<Phone, String> t) {
				((Phone) t.getTableView().getItems().get(t.getTablePosition().getRow())).setSize(t.getNewValue());
			}
		});

		table.setItems(data);
		table.getColumns().addAll(ModelCol, SizeCol, TypeCol, RamCol);
		table.setMaxWidth(501);
		final TextField addModel = new TextField();
		addModel.setPromptText("Модель");
		addModel.setMaxWidth(ModelCol.getPrefWidth());

		final TextField addSize = new TextField();
		addSize.setMaxWidth(SizeCol.getPrefWidth());
		addSize.setPromptText("Диагональ экрана");

		final TextField addType = new TextField();
		addType.setMaxWidth(TypeCol.getPrefWidth());
		addType.setPromptText("Тип экрана");

		final TextField addRam = new TextField();
		addRam.setMaxWidth(RamCol.getPrefWidth());
		addRam.setPromptText("Кол-во ОЗУ");

		final Button addButton = new Button("Добавить");
		addButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				data.add(new Phone(addModel.getText(), addSize.getText(), addType.getText(), addRam.getText()));
				addModel.clear();
				addSize.clear();

			}
		});
		hb.getChildren().addAll(addModel, addSize, addType, addRam, addButton);
		hb.setSpacing(3);
		hb.setMaxWidth(500);
		final VBox vbox = new VBox();
		vbox.setSpacing(5);
		vbox.setPadding(new Insets(10, 0, 0, 10));
		vbox.getChildren().addAll(label, table, hb);
		vbox.setMaxWidth(500);
		chart.setTitle("База моделей телефонов");
		chart.setPadding(new Insets(10, 0, 0, 10));
		chart.setMaxWidth(400);
		UpdateChart();

		TilePane tilePane = new TilePane();
		tilePane.getChildren().addAll(vbox, chart);

		((Group) scene.getRoot()).getChildren().add(tilePane);
		stage.setScene(scene);
		stage.show();
	
		final Button LoadButton = new Button("Загрузить");
		LoadButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				FileChooser fileChooser = new FileChooser();
				fileChooser.setTitle("Open Resource File");
				 File file = fileChooser.showOpenDialog(stage);
				System.out.println("Открытие файла - : " + file.getAbsolutePath());
				Scanner in = null;
				try {
					
					in = new Scanner(file);
					in.useDelimiter(";");
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				while (in.hasNext()) {
					Phone tyty = new Phone(in.next(), in.next(), in.next(), in.next());
					data.add(tyty);
				}
			}
		});
		
		
		final Button SaveButton = new Button("Сохранить");
		SaveButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				FileChooser fileChooser = new FileChooser();
				fileChooser.setTitle("Файл для сохранения");
				 File file = fileChooser.showOpenDialog(stage);
				 
				 
				System.out.println("Сохранение в файл - : " + file.getAbsolutePath());
					PrintWriter out = null;
					try {
						out = new PrintWriter(file);
					} catch (FileNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					Phone[] myArray = {};
					myArray = data.toArray(new Phone[data.size()]);
					for (int i = 0; i < myArray.length; i++) {
						out.print(myArray[i].model.get() + ";" + myArray[i].size.get() + ";" + myArray[i].type.get() + ";"
								+ myArray[i].ram.get() + ";");
					}
					out.close();
			}
		});
		
		
		
		
		vbox.getChildren().addAll(LoadButton,SaveButton);
	
	
	
	
	}

	public static class Phone {
		private final SimpleStringProperty model;
		private final SimpleStringProperty size;
		private final SimpleStringProperty type;
		private final SimpleStringProperty ram;

		public Phone(String m, String s, String t, String r) {
			model = new SimpleStringProperty(m);
			size = new SimpleStringProperty(s);
			type = new SimpleStringProperty(t);
			ram = new SimpleStringProperty(r);

		}

		public String getModel() {
			return model.get();
		}

		public String getType() {
			return type.get();
		}

		public String getSize() {
			return size.get();
		}

		public String getRam() {
			return ram.get();
		}

		public void setRam(String R) {
			ram.set(R);
		}

		public void setSize(String S) {
			size.set(S);
		}

		public void setModel(String M) {
			model.set(M);
		}

		public void setType(String T) {
			type.set(T);
		}
	}
}