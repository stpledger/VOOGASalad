package levelunlock;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class DynamicMain extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        SelectLevel menu = new SelectLevel(1, 3);
        primaryStage.setTitle("Level Selector");
        primaryStage.setScene(new Scene(menu));
        primaryStage.show();
    }
}
