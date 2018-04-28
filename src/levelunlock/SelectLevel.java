package levelunlock;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

public class SelectLevel extends ScrollPane {

    private int LevelProgress;
    private int NumOfLevels;
    private ArrayList<LevelItem> Levels;
    private VBox LevelColumn;

    public SelectLevel(int levelProgress, int numOfLevels){
        super();
        LevelProgress = levelProgress;
        NumOfLevels = numOfLevels;
        Levels = new ArrayList<>();
        LevelColumn = new VBox();

        for(int i = 1; i < numOfLevels + 1; i++){
            LevelItem temp = new LevelItem(i, i > levelProgress);
            //LevelItem temp = new LevelItem(i, true);
            Levels.add(temp);
        }

        LevelColumn.getChildren().addAll(Levels);

        LevelColumn.setSpacing(40);
        LevelColumn.setPadding(new Insets(40, 40, 40, 40));

        this.setContent(LevelColumn);
    }

    public void updateLevelProgress(int levelProgress){
        LevelProgress = levelProgress;
    }
}
