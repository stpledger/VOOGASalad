package HUD;

import java.util.List;
import java.util.Map;

import engine.components.Component;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

/**
 * Reflectively creates a Heads up Displays based on a string.
 * @author Ryan
 *
 */

public class HUDFactory {

	private Map<String, Component> PlayerComponentsforLevel;
	private List<String> listOfStates;
	
	public HUDFactory(Map<String, Component> playerComponents, List<String> statesList) {
		PlayerComponentsforLevel = playerComponents;
		listOfStates = statesList;
		
	}
	
	/**
	 * Reflectively creates and returns a heads up display with the correct the correct labels.
	 * @param listOfStates
	 * @return
	 */
	public HBox create(List<String> listOfStates) {
		Label gameStatus = null;
		HBox toolBar = new HBox(250);
		for (String temp: listOfStates) {
			try {
				gameStatus = (Label) Class.forName("labels."+temp).newInstance();
			}catch (InstantiationException e) {
				System.out.println("Error");
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			toolBar.getChildren().add(gameStatus); //repeatedly add Labels to a toolbar
		}
		return toolBar;
	}
}
