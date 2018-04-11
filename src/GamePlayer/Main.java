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
<<<<<<< HEAD
		//GamePlayerController gamePlayer = new GamePlayerController();
		mainStage = primaryStage;
		primaryStage.setTitle(TITLE);
		//primaryStage.setScene(gamePlayer.intializeStartScene());
		primaryStage.show();
		
=======
		GamePlayerController gamePlayer = new GamePlayerController(primaryStage);
		mainStage = primaryStage;
		mainStage.setTitle(TITLE);
		mainStage.setScene(gamePlayer.intializeStartScene());
		mainStage.show();
>>>>>>> 5a8cb25e4995b2deaf23b47c9c4cc00e0f9bc0a4
	}


	public Stage getMainStage() {
		return mainStage;
	}

}
