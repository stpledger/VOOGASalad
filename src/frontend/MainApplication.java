package frontend;

import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class MainApplication extends Application {

	@Override
	public void start(Stage  primaryStage) throws Exception {
		IDEView IDE = new IDEView();
		primaryStage.setTitle("One Class, One Method");
        primaryStage.setScene(IDEView.getScene());
        primaryStage.sizeToScene();
        primaryStage.setResizable(false);
        primaryStage.show();
		
	}
	
	public static void main(String[] args) {
        launch(args);
    }

}
