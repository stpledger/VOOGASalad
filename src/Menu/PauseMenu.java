package Menu;

import buttons.FileUploadButton;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.stage.Popup;

public class PauseMenu extends Popup {

	/**
	 * Constructor for the Pause Menu Popup
	 */
	public PauseMenu() {
		VBox pane = new VBox();
		pane.getChildren().addAll(new Button("Sound"), new Button("Difficulty"), new Button("Settings"), new FileUploadButton());
		this.getContent().add(pane);
		
	}
}
