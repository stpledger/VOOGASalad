package GamePlayer;

import java.util.List;

import buttons.IGamePlayerButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class HighScoreView extends BranchScreenView {
	
	private Scene highScoreScene;
	private TableView highScoreTable;
	private TableColumn firstCol;
	private TableColumn secondCol;
	private BorderPane highScorePane;	
	
	private final ObservableList<Person> data = FXCollections.observableArrayList(
		    new Person("Jacob Smith", 250.0),
		    new Person("Isabella Johnson", 230.0),
		    new Person("Ethan Williams", 150.0),
		    new Person("Emma Jones", 78.0),
		    new Person("Michael Brown", 48.0)
		);
	
	
	public HighScoreView() {
		setupHighScoreTable();
		displayHighScores();
		highScoreScene = initializeScreen();
	}
	
	@Override
	public Scene initializeScreen() {
		highScorePane = new BorderPane();
		highScorePane.setCenter(highScoreTable);
		Scene currentScene = new Scene(highScorePane,WIDTH_SIZE,HEIGHT_SIZE);
		return currentScene;
	}

	
	@Override
	public Scene getScene() {
		// TODO Auto-generated method stub
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



	@Override
	public List<IGamePlayerButton> getButtons() {
		// TODO Auto-generated method stub
		return null;
	}


	
}
