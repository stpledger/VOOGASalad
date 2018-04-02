package frontend;

import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class MainApplication extends Application {

	@Override
	public void start(Stage  primaryStage) throws Exception {
		IDEView IDE = new IDEView();
		primaryStage.setTitle("SLOGO IDE 9001");
        primaryStage.setScene(IDEView.getScene());
        primaryStage.sizeToScene();
        primaryStage.setResizable(false);
        primaryStage.show();
		
	}

}
