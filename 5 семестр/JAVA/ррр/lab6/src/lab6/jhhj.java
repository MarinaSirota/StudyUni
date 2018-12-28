package lab6;

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
import javafx.stage.Stage;
import javafx.scene.chart.*;

public class jhhj extends Application {
	private TableView<Language> table = new TableView<Language>();
	private final ObservableList<Language> data = FXCollections.observableArrayList(new Language("С", "60"),
			new Language("Java", "40"));

	final HBox hb = new HBox();
	ObservableList<PieChart.Data> pcData = FXCollections.observableArrayList();

	PieChart chart = new PieChart();

	public void UpdateChart() {
		int i;
		pcData.clear();
		for (i = 0; i < data.size(); i++) {
			pcData.add(new PieChart.Data(data.get(i).langName.getValue().toString(),
					Double.parseDouble(data.get(i).langPercent.getValue().toString())));
		}
		chart.setData(pcData);
	}

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) {
		Scene scene = new Scene(new Group());
		stage.setTitle("Table Sample");
		stage.setWidth(850);
		stage.setHeight(550);
		final Label label = new Label("Языки программирования");
		label.setFont(new Font("Arial", 20));
		label.setMaxWidth(300);
		table.setEditable(true);

		data.addListener(new ListChangeListener<Language>() {
			@Override
			public void onChanged(javafx.collections.ListChangeListener.Change<? extends Language> arg0) {
// TODO Auto-generated method stub
				UpdateChart();
			}
		});
		TableColumn langNameCol = new TableColumn("Название");
		langNameCol.setMinWidth(100);
		langNameCol.setCellValueFactory(new PropertyValueFactory<Language, String>("langName"));
		langNameCol.setCellFactory(TextFieldTableCell.forTableColumn());
		langNameCol.setOnEditCommit(new EventHandler<CellEditEvent<Language, String>>() {
			@Override
			public void handle(CellEditEvent<Language, String> t) {
				((Language) t.getTableView().getItems().get(t.getTablePosition().getRow()))
						.setLangName(t.getNewValue());
			}
		});

		TableColumn langPercentCol = new TableColumn("Использование");
		langPercentCol.setMinWidth(100);
		langPercentCol.setCellValueFactory(new PropertyValueFactory<Language, String>("langPercent"));
		langPercentCol.setCellFactory(TextFieldTableCell.forTableColumn());
		langPercentCol.setOnEditCommit(new EventHandler<CellEditEvent<Language, String>>() {
			@Override
			public void handle(CellEditEvent<Language, String> t) {
				((Language) t.getTableView().getItems().get(t.getTablePosition().getRow()))
						.setLangPercent(t.getNewValue());
			}
		});

		table.setItems(data);
		table.getColumns().addAll(langNameCol, langPercentCol);
		table.setMaxWidth(201);
		final TextField addName = new TextField();
		addName.setPromptText("Название");
		addName.setMaxWidth(langNameCol.getPrefWidth());

		final TextField addLang = new TextField();
		addLang.setMaxWidth(langPercentCol.getPrefWidth());
		addLang.setPromptText("Использование");
		final Button addButton = new Button("Add");
		addButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				data.add(new Language(addName.getText(), addLang.getText()));
				addName.clear();
				addLang.clear();

			}
		});
		hb.getChildren().addAll(addName, addLang, addButton);
		hb.setSpacing(3);
		hb.setMaxWidth(300);

		final VBox vbox = new VBox();
		vbox.setSpacing(5);
		vbox.setPadding(new Insets(10, 0, 0, 10));
		vbox.getChildren().addAll(label, table, hb);
		vbox.setMaxWidth(300);

		chart.setTitle("Использование языков программbрования");
		chart.setPadding(new Insets(10, 0, 0, 10));
		chart.setMaxWidth(400);
		UpdateChart();

		TilePane tilePane = new TilePane();
		tilePane.getChildren().addAll(vbox, chart);

		((Group) scene.getRoot()).getChildren().add(tilePane);
		stage.setScene(scene);
		stage.show();
	}

	public static class Language {
		private final SimpleStringProperty langName;
		private final SimpleStringProperty langPercent;

		private Language(String fName, String lName) {
			this.langName = new SimpleStringProperty(fName);
			this.langPercent = new SimpleStringProperty(lName);
		}

		public String getLangName() {
			return langName.get();
		}

		public void setLangName(String fName) {
			langName.set(fName);
		}

		public String getLangPercent() {
			return langPercent.get();
		}

		public void setLangPercent(String fName) {
			langPercent.set(fName);
		}
	}
}