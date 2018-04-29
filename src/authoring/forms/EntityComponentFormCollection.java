package authoring.forms;

import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.function.Consumer;

import authoring.exceptions.AuthoringAlert;
import authoring.exceptions.AuthoringException;
import engine.components.Component;
import engine.components.DataComponent;
import engine.components.StringComponent;
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

	@Override
	protected void save(Consumer c) {
		ArrayList<Object[]> componentsToAdd = new ArrayList<Object[]>();
		for(ComponentForm cf : this.getActiveForms()) {
			Object[] component = ((EntityComponentForm) cf).buildComponent();
			componentsToAdd.add(component);	
		}
		onSave.accept(componentsToAdd);
		
	}
}

