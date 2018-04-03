
import javafx.application.Application;
import javafx.stage.Stage;


public class Main extends Application {
	
	prviate static final String TITLE = "VoogaSalad";
	private static Stage mainStage;
	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception{
		MainView simulation = new MainView;
		mainStage = primaryStage;
		primaryStage.setTitle(TITLE);
		//primaryStage.setScene(simulation.initializeStartScene();
		primaryStage.show();
	}
	
}
