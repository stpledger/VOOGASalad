package GamePlayer;

import javafx.application.Application;
import javafx.stage.Stage;


public class Main extends Application {

	private static final String TITLE = "VoogaSalad";

	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception{
		GamePlayerController gamePlayer = new GamePlayerController(primaryStage);
		primaryStage.setTitle(TITLE);
		primaryStage.setScene(gamePlayer.intializeStartScene());
		primaryStage.show();
	}


}
