package data_management;

import authoring.factories.ButtonElement;
import authoring.factories.ElementFactory;
import authoring.factories.ElementType;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class LinearMenuScreen extends Scene{
    private VBox options;
    private Group root;
    private LinearMenuScreen next;
    private static final int SPACING = 50;
    private static final double RATIO = 1.7;
    private JarWriter jWrite;

    public LinearMenuScreen(int size, Stage stage){
        super(new Group(), size,size);
        jWrite = new JarWriter(stage);
        configureMenu();
    }

    private void configureMenu(){
        options = getMenuOptions();
        BorderPane pane = new BorderPane();
        pane.setCenter(options);
        pane.setPadding(new Insets(40));
        root = new Group(pane);
        this.setRoot(root);
    }

    private VBox getMenuOptions(){
        VBox options = new VBox();
        options.setSpacing(SPACING);
        Map<String, Consumer<Void>> ignoreoptions = new HashMap<String, Consumer<Void>>(){{
          put("Yes",e->jWrite.getIgnore());
          put("No",null);
        }};

        options.getChildren().add(buildCombo("Do you have files you don't want to add to the jar?", ignoreoptions));

        Map<String, Consumer<Void>> gameOptions = new HashMap<String, Consumer<Void>>(){{
            put("Directory",e->jWrite.getGameDir());
            put("File",e->jWrite.getGameFile());
        }};

        options.getChildren().add(buildCombo("Is your game a file or a directory", gameOptions));

        Map<String, Consumer<Void>> moduleOptions = new HashMap<String, Consumer<Void>>(){{
            put("Yes",e->jWrite.configureModules());
            put("No",null);
        }};

        options.getChildren().add(getButtons());
        options.getChildren().add(buildCombo("Do you have external modules?", moduleOptions));
        return options;
    }

    private VBox getButtons(){
        VBox buttons = new VBox();
        ButtonElement create = new ButtonElement("Create Jar");
        create.handleConsumer(e->jWrite.buildJar());
        buttons.getChildren().add(create);
        return buttons;
    }

    public  ComboBox buildCombo(String prompt,  Map<String,Consumer<Void>> event)
    {
        ComboBox<String> combobox = new ComboBox<>();
        for (String option : event.keySet()) {
            combobox.getItems().add(option);
            combobox.setOnAction(e -> event.get(option).accept(null));
        }
        combobox.setPromptText(prompt);
        return combobox;
    }

}
