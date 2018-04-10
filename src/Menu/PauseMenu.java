package Menu;

import buttons.FileUploadButton;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.stage.Popup;

public class PauseMenu extends Popup {

	public FileUploadButton fileBtn;
	/**
	 * Constructor for the Pause Menu Popup
	 */
	public PauseMenu() {
		VBox pane = new VBox();
		fileBtn = new FileUploadButton();
		pane.getChildren().addAll(new Button("Sound"), new Button("Difficulty"), new Button("Settings"), fileBtn);
		this.getContent().add(pane);
		
	}
}
