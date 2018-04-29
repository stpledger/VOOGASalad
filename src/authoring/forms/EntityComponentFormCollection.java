package authoring.forms;

import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.function.Consumer;

import javafx.geometry.Pos;

/**
 * 
 * @author Collin Brown(cdb55)
 *
 */
public class EntityComponentFormCollection extends AbstractComponentFormCollection {
	
	public EntityComponentFormCollection() {
		super(EntityComponentForm.class);
	}
	
	public EntityComponentFormCollection(String[] newExceptions, Consumer onSave) {
		super(newExceptions, onSave, EntityComponentForm.class);	
	}
	
	
}

