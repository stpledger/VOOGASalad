package GamePlayer;

import javafx.application.Application;
import javafx.stage.Stage;


public class Main extends Application {

	private static final String TITLE = "VoogaSalad";

	private static Stage mainStage;

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception{
		GamePlayerController gamePlayer = new GamePlayerController();
		mainStage = primaryStage;
		primaryStage.setTitle(TITLE);
		primaryStage.setScene(gamePlayer.intializeStartScene());
		primaryStage.show();
		
	}


	public Stage getMainStage() {
		return mainStage;
	}
	
}
