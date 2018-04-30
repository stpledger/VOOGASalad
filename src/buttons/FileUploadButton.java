package buttons;


import java.io.File;

import data.DataGameState;
import data.DataRead;
import gameplayer.buttons.IGamePlayerButton;
import gameplayer.controller.Controller;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;

public class FileUploadButton extends Button implements IGamePlayerButton {
	
		private Stage myStage;
		private final String BUTTON_NAME = "Upload"; //change to a resource file
		private File uploadedFile;
		private DataGameState gameState;
		private BooleanProperty fileBoolean;
		private Controller gameController;
		
		public FileUploadButton(Stage stage) {
			myStage = stage;
			this.setText(BUTTON_NAME);
			this.setEvent();
			fileBoolean = new SimpleBooleanProperty(false);
		}
		/*
		 * private method to set the action event to speed
		 */
		public void setEvent() {
			this.setOnAction(new EventHandler<ActionEvent>() {
				@Override public void handle(ActionEvent e) {
					fileUpload(); //activate the file upload method
					gameController = new Controller(myStage, gameState);
					myStage.setScene(gameController.getControllerScene());
				}
			});
		}

		//test
		private void fileUpload() {
			FileChooser fileChooser = new FileChooser();
			fileChooser.setTitle("Open Resource File");
			fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("XML", "*.xml"));
//			Stage mainStage = (Stage) this.getScene().getWindow();
//			File file = fileChooser.showOpenDialog(mainStage);
//			
			Window mainStage = this.getScene().getWindow();
			File file = fileChooser.showOpenDialog(mainStage);
			//change into a new Scene
	        if (file != null) {
	        		uploadedFile = file;
	        		gameState = DataRead.loadPlayerFile(file);
	        		System.out.println("File Uploaded");
	        		//setFileBoolean(); //game file has been uploaded
	        }
	       
		}
//		
//		public File getFile() {
//			return uploadedFile;
//		}
//		
//		public DataGameState getGameState() {
//			return gameState;
//		}
//		
//		public Boolean isFile() {
//			return (uploadedFile!=null);
//		}
//		
//		public BooleanProperty getFileBooleanProperty() {
//			return fileBoolean;
//		}
//		
//		public void setFileBoolean() {
//			fileBoolean.setValue(!fileBoolean.getValue());
//		}
		
}	
