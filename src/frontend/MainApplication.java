package frontend;

import frontend.components.ViewBuilder;
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;

/**
 * 
 * @author Collin Brown(cdb55)
 *
 */
public class MainApplication extends Application {

	@Override
	public void start(Stage  primaryStage) throws Exception {
		ViewBuilder viewBuilder = new ViewBuilder();
		Parent layout = viewBuilder.build();
		Scene s = new Scene(layout, viewBuilder.getIDEWidth(), viewBuilder.getIDEHeight());
		s.getStylesheets().add(MainApplication.class.getResource("styles.css").toExternalForm());
		primaryStage.setTitle("One Class, One Method");
        primaryStage.setScene(s);
        primaryStage.sizeToScene();
        primaryStage.setResizable(false);
        primaryStage.show();
		
	}
	
	public static void main(String[] args) {
        launch(args);
    }

}
