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
public class PropertiesComponentFormCollection extends AbstractComponentFormCollection {
	
	private int currentRow;
	
	public PropertiesComponentFormCollection(int eID, String[] newExceptions, Consumer onSave) {
		super(newExceptions, onSave, PropertiesComponentForm.class);
		entityID = eID;
	}


	@Override
	protected void save(Consumer onSave) {
		ArrayList<Component> componentsToAdd = new ArrayList<Component>();
		for(ComponentForm cf : this.getActiveForms()) {
			Component c = (Component) cf.buildComponent();
			if(!c.getKey().isEmpty() && c.getClass().isInstance(DataComponent.class)) {
				try {
					((DataComponent) c).getData();
					componentsToAdd.add(c);
				} catch(Exception e) {
					AuthoringException authorException = new AuthoringException(c.getKey() + "is not a valid integer", AuthoringAlert.SHOW);
				}
			} else if (!c.getKey().isEmpty() && c.getClass().isInstance(StringComponent.class)); {
				componentsToAdd.add(c);
			}
		}
		onSave.accept(componentsToAdd);
		
	}


}
