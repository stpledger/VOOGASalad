package data;

import authoring.entities.Block;
import authoring.entities.Entity;
import engine.components.XPosition;
import engine.components.YPosition;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class TestSupplier extends Application{


    @Override
    public void start(Stage primaryStage) throws Exception {
        Scene myScene = new Scene(new Group(), 500,500, Color.AQUA);
        primaryStage.setScene(myScene);
        Entity block = new Block(1,"Block");
        block.add(new XPosition(1,90));
        block.add(new YPosition(1,90));
        primaryStage.show();

        DirectionSupplier pSup = new DirectionSupplier(block);
    }
}
