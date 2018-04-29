package levelunlock;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class LevelItem extends Button {

    private BooleanProperty Locked;
    private int Level;

    private static final String LOCKED_STRING = "images/locked.png";
    private static final String UNLOCKED_STRING = "images/unlocked.png";
    private static final int IMAGE_HEIGHT = 20;
    private static final int IMAGE_WIDTH = 20;

    public LevelItem(int level, boolean locked){
        super();
        this.setText("Level " + level);
        Level = level;
        Locked = new SimpleBooleanProperty();
        Locked.addListener((o, oldVal, newVal) -> {
            if(newVal){
                ImageView temp = new ImageView(new Image(getClass().getClassLoader().getResourceAsStream(LOCKED_STRING)));
                temp.setFitHeight(IMAGE_HEIGHT);
                temp.setFitWidth(IMAGE_WIDTH);
                this.setGraphic(temp);
                this.getStyleClass().add("lockedButton");
            }
            else{
               /* ImageView temp = new ImageView(new Image(getClass().getClassLoader().getResourceAsStream(UNLOCKED_STRING)));
                temp.setFitHeight(IMAGE_HEIGHT);
                temp.setFitWidth(IMAGE_WIDTH);*/
                this.setGraphic(null);
                this.getStyleClass().add("unlockedButton");
            }
        });
        setLocked(true);
        setLocked(locked);
    }

    public void setLocked(boolean locked){
        Locked.setValue(locked);
    }

    public int getLevel(){
        return Level;
    }
}
