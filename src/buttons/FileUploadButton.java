package buttons;


import java.io.File;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;

public class FileUploadButton extends Button {
	
		private Stage myStage;
		private final String BUTTON_NAME = "Upload"; //change to a resource file
		private File uploadedFile;
		private BooleanProperty fileBoolean;
		
		public FileUploadButton() {
			this.setText(BUTTON_NAME);
			this.setFileEvent();
			fileBoolean = new SimpleBooleanProperty(false);
		}
		/*
		 * private method to set the action event to speed
		 */
		private void setFileEvent() {
			this.setOnAction(new EventHandler<ActionEvent>() {
				@Override public void handle(ActionEvent e) {
					fileUpload(); //activate the file upload method
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
	        		System.out.println("File Uploaded");
	        		fileBoolean.setValue(true); //game file has been uploaded
	        }
	       
		}
		
		public File getFile() {
			return uploadedFile;
		}
		
		public Boolean isFile() {
			return (uploadedFile!=null);
		}
		
		public BooleanProperty getFileBooleanProperty() {
			return fileBoolean;
		}
		
		public void setFileBoolean() {
			fileBoolean.setValue(!fileBoolean.getValue());
		}
		
}	
