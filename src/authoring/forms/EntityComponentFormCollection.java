package authoring.forms;

import java.util.ArrayList;
import java.util.Properties;
import java.util.ResourceBundle;

import javafx.geometry.Pos;

/**
 * 
 * @author Collin Brown(cdb55)
 *
 */
public class EntityComponentFormCollection extends AbstractComponentFormCollection {
	
	int currentRow;
	
	public EntityComponentFormCollection() {
		super();
	}
	
	public EntityComponentFormCollection(String[] newExceptions) {
		super(newExceptions);
		
	}
	
	/**
	 * Creates the forms and returns them as a GridPane
	 * @return gridPane a gridpane filled with the necessary forms
	 */
	public void fillComponentsForms(String entityType) {
		try {
		currentRow = 0;
		this.getChildren().clear();
		ArrayList<ComponentForm> newActiveForms = new ArrayList<>();
		for (String property : ResourceBundle.getBundle(getPropertiesPackage() + entityType).keySet()) {
			if(!getExceptions().contains(property)) {
				EntityComponentForm cf;
				
					cf = new EntityComponentForm(property);
				
				cf.setAlignment(Pos.CENTER);
				newActiveForms.add(cf);
				this.add(cf, 0, currentRow);
				currentRow++;
		
			}
		}
		this.setActiveForms(newActiveForms);
		this.createAddComponentButton(currentRow);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Adds a componentForm to the current list of active forms
	 */
	@Override
	public void addComponent(Object componentName) {
		try {
			ArrayList<ComponentForm> newActiveForms = (ArrayList<ComponentForm>) this.getActiveForms();
			this.getChildren().remove(addComponentButton);
			System.out.println(componentName);
			EntityComponentForm cf = new EntityComponentForm((String) componentName);
			cf.setAlignment(Pos.CENTER);
			this.add(cf, 0, currentRow);
			cf.setLanguage(language);
			newActiveForms.add(cf);
			this.setActiveForms(newActiveForms);
			currentRow++;
			this.createAddComponentButton(currentRow);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
}

