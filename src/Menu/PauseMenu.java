package Menu;

import buttons.FileUploadButton;
import buttons.SaveGameButton;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.stage.Popup;

public class PauseMenu extends Popup {

	public FileUploadButton fileBtn;
	private SaveGameButton saveBtn;
	/**
	 * Constructor for the Pause Menu Popup
	 */
	public PauseMenu() {
		VBox pane = new VBox();
		fileBtn = new FileUploadButton();
		saveBtn = new SaveGameButton();
		pane.getChildren().addAll(new Button("Sound"), new Button("Difficulty"), new Button("Settings"), fileBtn, saveBtn);
		this.getContent().add(pane);
		
	}
}
