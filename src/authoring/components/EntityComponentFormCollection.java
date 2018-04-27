package authoring.components;

import java.util.ArrayList;

import java.util.ResourceBundle;

import javafx.geometry.Pos;

/**
 * 
 * @author Collin Brown(cdb55)
 *
 */
public class EntityComponentFormCollection extends AbstractComponentFormCollection {
	
	
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
		int currentRow = 0;
		this.getChildren().clear();
		ArrayList<ComponentForm> newActiveForms = new ArrayList<>();
		for (String property : ResourceBundle.getBundle(getPropertiesPackage() + entityType).keySet()) {
			if(!getExceptions().contains(property)) {
				EntityComponentForm cf = new EntityComponentForm(property);
				cf.setAlignment(Pos.CENTER);
				newActiveForms.add(cf);
				this.add(cf, 0, currentRow);
				currentRow++;
			}
		}
		this.setActiveForms(newActiveForms);
		this.createAddComponentButton(0, currentRow);
	}

	@Override
	public void addComponent(String componentName) {
		// TODO Auto-generated method stub
		
	}
	
}

