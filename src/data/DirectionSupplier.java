package data;

import authoring.entities.Entity;
import engine.systems.collisions.Collision;
import engine.systems.collisions.CollisionDirection;
import javafx.scene.control.ComboBox;

import java.io.File;
public class DirectionSupplier extends Supplier{

    private CollisionDirection dir;
    private static final int XSIZE = 150;
    private static final int YSIZE =55;
    private static final String PROMPT = "Select a direction you want associated with this action";
    private static final String COMBO_PROMPT = "Select Direction";


    public DirectionSupplier(Entity e){
        super(PROMPT,XSIZE,YSIZE,e);
    }

    @Override
    protected void configureMenu() {
        ComboBox entityList = new ComboBox();
        entityList.setPromptText(COMBO_PROMPT);
        for(CollisionDirection direction : CollisionDirection.values()){
            entityList.getItems().add(direction.name());
        }
        entityList.setOnAction(e->{dir= CollisionDirection.valueOf((String)entityList.getValue());});
        menu.getChildren().add(0,entityList);
    }
}
