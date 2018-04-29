package authoring.forms;

import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.function.Consumer;

import javafx.geometry.Pos;

public class PropertiesComponentFormCollection extends AbstractComponentFormCollection {
	
	private int currentRow;
	
	public PropertiesComponentFormCollection(int eID, String[] newExceptions, Consumer onSave) {
		super(newExceptions, onSave, PropertiesComponentForm.class);
		entityID = eID;
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
				PropertiesComponentForm cf;
				cf = new PropertiesComponentForm(entityID, property);
				cf.setAlignment(Pos.CENTER);
				newActiveForms.add(cf);
				this.add(cf, 0, currentRow);
				currentRow++;
			}
		}
		this.setActiveForms(newActiveForms);
		this.createAddComponentButton(currentRow);
		currentRow++;
		this.createSaveButton(currentRow);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void setSaveConsumer(Consumer c) {
		// TODO Auto-generated method stub
		
	}

}
