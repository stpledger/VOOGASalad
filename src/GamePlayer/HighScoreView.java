package GamePlayer;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class HighScoreView {
	
	private Scene highScoreScene;
	private TableView highScoreTable;
	private TableColumn firstCol;
	private TableColumn secondCol;
	private BorderPane highScorePane;	
	private final int WIDTH_SIZE = 800;
	private final int HEIGHT_SIZE = 500;
	private final ObservableList<Person> data = FXCollections.observableArrayList(
		    new Person("Jacob Smith", 250.0),
		    new Person("Isabella Johnson", 230.0),
		    new Person("Ethan Williams", 150.0),
		    new Person("Emma Jones", 78.0),
		    new Person("Michael Brown", 48.0)
		);
	
	
	public HighScoreView(Stage stage) {
		setupHighScoreTable();
		displayHighScores();
		highScoreScene = initializeHighScoreScreen();
	}
	
	public Scene initializeHighScoreScreen() {
		highScorePane = new BorderPane();
		highScorePane.setCenter(highScoreTable);
		Scene currentScene = new Scene(highScorePane,WIDTH_SIZE,HEIGHT_SIZE);
		return currentScene;
	}
	
	public Scene getHighScoreScene() {
		return highScoreScene;
	}
	
	public void setupHighScoreTable() {
		highScoreTable = new TableView();
		firstCol = new TableColumn("Name");
		secondCol = new TableColumn("Score");
		secondCol.setSortType(TableColumn.SortType.DESCENDING);
		firstCol.prefWidthProperty().bind(highScoreTable.widthProperty().divide(2));
		secondCol.prefWidthProperty().bind(highScoreTable.widthProperty().divide(2));
		highScoreTable.getColumns().addAll(firstCol,secondCol);
		highScoreTable.setMaxHeight(500);
		highScoreTable.setMaxWidth(300);
	}
	
	public void displayHighScores() {
        firstCol.setCellValueFactory(
                new PropertyValueFactory<Person, String>("name"));
        secondCol.setCellValueFactory(
                new PropertyValueFactory<Person, Double>("score"));
        highScoreTable.setItems(data);
	}
	
	public void addHighScore() {
		
	}
	
}
