package gameplayer.levelunlock;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;

//TODO: SHOULD THIS BE REFACTORED TO EXTEND SCENE INSTEAD?
public class SelectLevel extends ScrollPane {
    private int LevelProgress;
    private int NumOfLevels;
    private ArrayList<LevelItem> Levels;
    private VBox LevelColumn;
    private Scene MyScene;

    private static final int PANE_WIDTH = 400;
    private static final int PANE_HEIGHT = 400;

    public SelectLevel(int levelProgress, int numOfLevels, Stage stage){
        super();
        LevelProgress = levelProgress;
        NumOfLevels = numOfLevels;
        Levels = new ArrayList<>();
        LevelColumn = new VBox();

        stage.setWidth(PANE_WIDTH);
        stage.setHeight(PANE_HEIGHT);

        LevelColumn.prefWidthProperty().bind(stage.widthProperty());
        LevelColumn.prefHeightProperty().bind(stage.heightProperty());
        LevelColumn.setSpacing(40);
        LevelColumn.setPadding(new Insets(40, 0, 40, 0));
        LevelColumn.getStyleClass().add("vbox");

        createLevels();

        this.setContent(LevelColumn);
        this.setHbarPolicy(ScrollBarPolicy.NEVER);
        this.setVbarPolicy(ScrollBarPolicy.AS_NEEDED);
        //this.getStyleClass().add("pane");

        MyScene = new Scene(this);
        MyScene.getStylesheets().add(getClass().getResource("LevelStyle.css").toExternalForm());

        Button button = new Button("Completed Level 1");
        button.setOnAction(e -> updateLevelProgress(2));
        LevelColumn.getChildren().add(button);
    }

    private void createLevels(){
        for(int i = 1; i < NumOfLevels + 1; i++){
            LevelItem temp = new LevelItem(i, i > LevelProgress);
            //LevelItem temp = new LevelItem(i, true);
            temp.prefWidthProperty().bind(LevelColumn.widthProperty());
            Levels.add(temp);
        }

        LevelColumn.getChildren().addAll(Levels);
    }

    public void updateLevelProgress(int levelProgress){
        LevelProgress = levelProgress;
        for(LevelItem l : Levels){
            // if level is less than level progress, set level to unlocked
            l.setLocked(!(l.getLevel() <= levelProgress));
        }
    }

    public Scene getMyScene(){
        return MyScene;
    }
}
