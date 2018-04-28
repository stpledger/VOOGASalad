package data;

import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class JarWriterUI extends Scene{
    private VBox options;
    private Group root;
    private static final int SPACING = 20;
    private static final String OUTPUT = "Select Output";
    private static final String MODULE_PROMPT = "Do you have external modules?";
    private static final String IGNORE_PROMPT = "Do you have files you don't want to add to the jar?";
    private static final String DIR_FILE_PROMPT = "Is your game a file or a directory";
    private static final String CREATE = "Create Jar";
    private static final String MAIN = "Select Main";
    private JarWriter jWrite;

    public JarWriterUI(int size, Stage stage){
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

        options.getChildren().add(buildCombo(IGNORE_PROMPT, ignoreoptions));

        Map<String, Consumer<Void>> gameOptions = new HashMap<String, Consumer<Void>>(){{
            put("Directory",e->jWrite.getGameDir());
            put("File",e->jWrite.getGameFile());
        }};

        options.getChildren().add(buildCombo(DIR_FILE_PROMPT, gameOptions));

        Map<String, Consumer<Void>> moduleOptions = new HashMap<String, Consumer<Void>>(){{
            put("Yes",e->jWrite.configureModules());
            put("No",null);
        }};

        options.getChildren().add(buildCombo(MODULE_PROMPT, moduleOptions));
        options.getChildren().add(getButtons());
        return options;
    }

    private HBox getButtons(){
        HBox buttons = new HBox();
        Button create = new Button(CREATE);
        create.setOnAction(e->jWrite.buildJar());
        Button outPath = new Button(OUTPUT);
        outPath.setOnAction(e->jWrite.getOutput());
        Button main = new Button(MAIN);
        main.setOnAction(e->jWrite.selectMain());
        buttons.getChildren().addAll(outPath,main,create);
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
