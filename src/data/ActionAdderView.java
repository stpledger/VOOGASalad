package data;

import authoring.entities.Entity;
import authoring.factories.ButtonElement;
import authoring.factories.Element;
import authoring.factories.ElementFactory;
import engine.components.Component;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class ActionAdderView {
    private Stage myStage;
    private Scene myScene;
    private ActionAdder actionAdder;
    private Group root;
    private String action;
    private static final int SIZE = 200;
    private static final double RATIO = 1.7;
    private static final String ADD = "Add an action";

    public ActionAdderView(Entity entity){
        initStage();
        configureMenu();

    }

    private void initStage(){
        root = new Group();
        myScene = new Scene(root,200,SIZE/RATIO);
        myStage.setScene(myScene);
        myStage.setResizable(false);
    }

    private void configureMenu(){

    }

    private Menu getActives(){
        if(action.equals(ADD)) {
            Map<String, Component> actives = actionAdder.getActiveComponents();
        }
        else
        {
            actives = actionAdder.getActiveComponents();
        }
        Consumer<Component> toHappen;
        Menu showComponents = new Menu();
        for(String component :actives.keySet() ){
            MenuItem select = new MenuItem(component);
            select.setOnAction(e->{toHappen.accept()});
            showComponents.getItems().add(select);
        }
        return showComponents;
    }
    private HBox buildButtons(){
        ButtonElement delete = new ButtonElement(ADD);

        delete.handleConsumer(e->{action = ADD;});

        ButtonElement add = new ButtonElement()

    }
}
