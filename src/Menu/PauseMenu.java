package Menu;

import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.stage.Popup;

public class PauseMenu extends Popup implements IMenu {

	/**
	 * Constructor for the Pause Menu Popup
	 */
	public PauseMenu() {
		VBox pane = new VBox();
		pane.getChildren().addAll(new Button("Sound"), new Button("Difficulty"), new Button("Settings"));
		this.getContent().add(pane);
		
	}
	
	@Override
	public void toggleMenu() {
		// TODO Auto-generated method stub
		this.show();
		
	}

}
