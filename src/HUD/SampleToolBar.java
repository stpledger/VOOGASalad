package HUD;

import buttons.FileUploadButton;
import javafx.scene.control.Label;
import javafx.scene.control.ToolBar;

public class SampleToolBar extends ToolBar implements IHUD{

	/**
	 * Builds a Sample Tool Bar that acts as the HUD for the game
	 */
	public SampleToolBar() {
		//constructor to create a Sample Tool Bar
		Label label1 = new Label();
		label1.setText("Lives");
		Label label2 = new Label();
		label2.setText("Score");
		Label label3 = new Label();
		label3.setText("Time");
		this.getItems().addAll(label1, label2, label3);
	}
	
	
	@Override
	public void updateHUD() {
		// TODO Auto-generated method stub
		
	}

}
