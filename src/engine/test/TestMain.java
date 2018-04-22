package engine.test;

import javafx.application.Application;
import javafx.stage.Stage;


public class TestMain extends Application {
		
	public static void main(String[] args) {
		launch(args);
	}
	
	public void start(Stage primaryStage) {
		primaryStage.setTitle("Test");
		TestPlayer tp = new TestPlayer();
		primaryStage.setScene(tp.getScene());
		primaryStage.show();
		
	}
	
	
}



