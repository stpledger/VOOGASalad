package levelunlock;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;

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
        LevelColumn.setSpacing(40);
        LevelColumn.setPadding(new Insets(40, 0, 40, 0));
        LevelColumn.getStylesheets().add("LevelStyle.css");

        createLevels();

        this.setContent(LevelColumn);
        this.setHbarPolicy(ScrollBarPolicy.NEVER);
        this.setVbarPolicy(ScrollBarPolicy.AS_NEEDED);

        MyScene = new Scene(this);
        //MyScene.getStylesheets().add(getClass().getResource("LevelStyle.css").toExternalForm());
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
    }

    public Scene getMyScene(){
        return MyScene;
    }
}
