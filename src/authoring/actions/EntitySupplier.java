package authoring.actions;

import authoring.entities.Entity;
import data.DataRead;
import javafx.scene.control.ComboBox;
import java.io.File;

public class EntitySupplier extends Supplier {
/*@Author Conrad pronpts user for EntityName to help build actions

 */
    private String eName;
    private static final int XSIZE = 350;
    private static final int YSIZE =90; //55
    private static final String PROMPT = "Select an entity type you want associated with this action";
    private static final String COMBO_PROMPT = "Select entity";
    private static final String PERIOD = ".";

    EntitySupplier(Entity e){
        super(PROMPT,XSIZE,YSIZE,e);
        configureMenu();
    }


    @Override
    protected void configureMenu() {
        ComboBox entityList = new ComboBox();
        entityList.setPromptText(COMBO_PROMPT);
        for(File entity : DataRead.getAllEntities()){
            entityList.getItems().add(cutFile(entity.getName()));
        }
        entityList.setOnAction(e->{eName=(String)entityList.getValue(); });
        menu.getChildren().add(0,entityList);
    }

    @Override
    protected Object getData() {
        return eName;
    }

    private String cutFile(String file){
        return file.substring(0,file.indexOf(PERIOD));
    }

}
