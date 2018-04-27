package data_management;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

import static javafx.application.Application.launch;

public class JarBuilderUI extends Application{


    public static void main(String[] args){

        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Group root = new Group();
        Scene sceen = new Scene(root, 10,10);
        primaryStage.setScene(new LinearMenuScreen(400,primaryStage));
        primaryStage.show();
    }
}
