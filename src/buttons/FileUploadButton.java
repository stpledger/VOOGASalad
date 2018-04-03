package buttons;


import java.io.File;

import GamePlayer.GamePlayerView;
import GamePlayer.Main;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;

public class FileUploadButton extends Button {
	
		private final int MAX_WIDTH = 250;
		private final String BUTTON_NAME = "Upload"; //change to a resource file
	
		public FileUploadButton() {
			this.setText(BUTTON_NAME);
			this.setFileEvent();
		}
		/*
		 * private method to set the action event to speed
		 */
		private void setFileEvent() {
			this.setOnAction(new EventHandler<ActionEvent>() {
				@Override public void handle(ActionEvent e) {
					fileUpload(); //activate the play method
				}
			});
		}
		private void fileUpload() {
			
			FileChooser fileChooser = new FileChooser();
			fileChooser.setTitle("Open Resource File");
			fileChooser.getExtensionFilters().add(
					new FileChooser.ExtensionFilter("XML", "*.xml"));
			Main currentMain = new Main();
			File file = fileChooser.showOpenDialog(currentMain.getMainStage());
//	        if (file != null) {
//	        		GamePlayerView.getFileSelector().getItems().add(file);
//	        		GamePlayerView.getFileSelector().setMaxWidth(MAX_WIDTH);
//	        }
		}
}
