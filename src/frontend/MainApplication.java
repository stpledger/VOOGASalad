package frontend;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import frontend.components.MainView;
import javafx.application.Application;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;

/**
 * 
 * @author Collin Brown(cdb55)
 *d
 */
public class MainApplication extends Application {

	@Override
	public void start(Stage  primaryStage) throws Exception {
		MainView viewBuilder = new MainView();
		Parent layout = viewBuilder.build();
		Scene s = new Scene(layout, viewBuilder.getIDEWidth(), viewBuilder.getIDEHeight());
		s.getStylesheets().add(MainApplication.class.getResource("styles.css").toExternalForm());
		primaryStage.setTitle("One Class, One Method");
		File imageFile = new File("src/mystery.jpg");
		Image image = SwingFXUtils.toFXImage(ImageIO.read(imageFile), null);
		primaryStage.getIcons().add(image);
        primaryStage.setScene(s);
        primaryStage.sizeToScene();
        primaryStage.setResizable(false);
        primaryStage.show();
	}
	
	public static void main(String[] args) {
        launch(args);
    }

}
