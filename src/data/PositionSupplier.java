package data;
//prompt keycode,
//positions
//entity
//direction
import authoring.entities.Entity;
import authoring.factories.ButtonElement;
import engine.components.Position;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class PositionSupplier {

    private Stage myStage;
    private List<Point> path;
    private boolean anchor= false;
    private Scene myScene;
    private Group root;
    private final int SIZE = 100;
    private double offsetX;
    private double offsetY;
    private final String PROMPT = "Define your path then click ok";
    private final String SELECT = "Submit";
    private final String REDO = "Redo";
    private final String ANCHOR_PROMPT = "Set an anchor";
    private Entity entity;


    public PositionSupplier(Entity e){
        init();
        entity = e;
        path = new ArrayList<>();
    }

    private void init(){
        myStage.setResizable(false);
        myStage.setTitle(PROMPT);
        root = new Group();
        root.setOnMouseClicked(e->logKey(e));
        myScene = new Scene(root,SIZE,SIZE);


    }

    private void setupMenu(){
        VBox menu = new VBox();
        ButtonElement select = new ButtonElement(SELECT);
        select.handleConsumer(e-> myStage.close());
        ButtonElement redo = new ButtonElement(REDO);
        select.handleConsumer(e-> restart());
        Text prompt = new Text(ANCHOR_PROMPT);
        HBox buttons = new HBox(select,redo);
        menu.getChildren().addAll(prompt,buttons);
    }

    private void restart(){
        path.clear();
        anchor=false;
    }

    private void logKey(MouseEvent e){
        if(anchor){
            path.add(new Position(e.getX(),e.getSceneY());
        }
        else
        {
            offsetX = entity.get(X);
        }
    }




}
