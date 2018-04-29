package authoring.forms;

import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.function.Consumer;

import javafx.geometry.Pos;

public class PropertiesComponentFormCollection extends AbstractComponentFormCollection {
	
	private int currentRow;
	private int entityID;
	
	public PropertiesComponentFormCollection(int eID, String[] newExceptions) {
		super(newExceptions);
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
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void addComponent(Object componentName) {
		try {
			ArrayList<ComponentForm> newActiveForms = (ArrayList<ComponentForm>) this.getActiveForms();
			this.getChildren().remove(addComponentButton);
			System.out.println(componentName);
			PropertiesComponentForm cf = new PropertiesComponentForm(entityID, (String) componentName);
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

	@Override
	public void setSaveConsumer(Consumer onSave) {
		// TODO Auto-generated method stub
		
	}

}
