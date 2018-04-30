package buttons;

import gameplayer.Controller;
import data.DataGameState;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class GameSelectButton extends Button implements IGamePlayerButton{

	private DataGameState gameState;
	private Stage myStage;
	private BooleanProperty gameSelectBoolean;
	private Controller gameController;
	private DataGameState myGameState;
	
	/**
	 * 
	 * @param name of the Game
	 * @param currentGameState of the GameData to be imported
	 * @param image of the Game to used for the button.
	 */
	public GameSelectButton(Stage stage, String name, DataGameState currentGameState, Image image) {
		gameSelectBoolean = new SimpleBooleanProperty(false);
		myGameState = currentGameState;
		myStage = stage;
		this.setText(name);
		ImageView gameImage = new ImageView(image);
		gameImage.setFitHeight(100);
		gameImage.setFitWidth(100);
		this.setGraphic(gameImage);
		gameState = currentGameState;
		this.setEvent();
	}
	
	public void setEvent() {

		this.setOnAction(e -> {
			gameController = new Controller(myStage, myGameState);
			myStage.setScene(gameController.getControllerScene());
		});
	}
//	
//	public void setGameSelectBoolean() {
//		gameSelectBoolean.setValue(!gameSelectBoolean.getValue());
//	}
//	
//	public BooleanProperty getGameSelectBooleanProperty() {
//		return gameSelectBoolean;
//	}
//	
//	public DataGameState getGameState() {
//		return gameState;
//	}
}
