//package authoring.components;
//
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.Collections;
//import java.util.List;
//import java.util.ResourceBundle;
//
//import authoring.factories.ComboBoxElement;
//import authoring.forms.AbstractComponentFormCollection;
//import javafx.geometry.Pos;
//import javafx.scene.control.Button;
//import javafx.scene.layout.GridPane;
//
//public class EntityComponentFormCollection extends AbstractComponentFormCollection {
//
//	public EntityComponentFormCollection() {
//		super();
//	}
//
//	public EntityComponentFormCollection(String[] newExceptions) {
//		super(newExceptions);
//
//	}
//
//	/**
//	 * Creates the forms and returns them as a GridPane
//	 * @return gridPane a gridpane filled with the necessary forms
//	 */
//	public void fillComponentsForms(String entityType) {
//		int currentRow = 0;
//		this.getChildren().clear();
//		this.setActiveForms(new ArrayList<>());
//		ArrayList<ComponentForm> newActiveForms = new ArrayList<>();
//		for (String property : ResourceBundle.getBundle(getPropertiesPackage() + entityType).keySet()) {
//			if(!getExceptions().contains(property)) {
//				EntityComponentForm cf = new EntityComponentForm(property);
//				cf.setAlignment(Pos.CENTER);
//				newActiveForms.add(cf);
//				currentRow++;
//				this.add(cf, 0, currentRow);
//			}
//		}
//		this.setActiveForms(newActiveForms);
//	}
//}
//
