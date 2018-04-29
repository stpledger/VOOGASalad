package authoring.actions;

import authoring.entities.Block;
import authoring.entities.Entity;
import authoring.gamestate.GameState;
import authoring.gamestate.Level;
import data.DataRead;
import data.DataWrite;
import engine.components.KeyInput;
import engine.components.Sprite;
import engine.components.XPosition;
import engine.components.YPosition;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class TestSupplier extends Application{


    @Override
    public void start(Stage primaryStage) throws Exception {
        Group root = new Group();
        Scene myScene = new Scene(root, 500,500, Color.AQUA);
        primaryStage.setScene(myScene);
        Entity block = new Block(1,"Block");
        block.add(new XPosition(1,0));
        block.add(new YPosition(1,0));
        block .add(new KeyInput(1));
        block.add(new Sprite(1,"8Bit.png"));
        Level le = new Level(1);
        le.addEntity(block);
        List<Level > levels = new ArrayList<>();
        block.setImage(DataRead.loadImage("8Bit.png"));
        GameState g = new GameState();
        g.addLevel(le);
        primaryStage.show();
        Button b = new Button("Add a file");
        b.setOnAction(e-> {
            try {
                DataWrite.saveFile(g,"ConradActions");
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });
        root.getChildren().addAll(block,b);
        ActionAdderView pSup = new ActionAdderView(block);


    }
}
