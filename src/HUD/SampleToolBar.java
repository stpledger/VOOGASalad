package HUD;

import Menu.LevelSelector;
import javafx.scene.control.Label;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.HBox;
import labelComponents.timeLabel;

public class SampleToolBar extends ToolBar implements IHUD{

	/**
	 * Builds a Sample Tool Bar that acts as the HUD for the game
	 */
	public SampleToolBar() {
		//constructor to create a Sample Tool Bar
		HBox toolbarLayout = new HBox(250); //adding spacing by 40 units
		Label label1 = new Label();
		label1.setText("Lives");
		Label label2 = new Label();
		label2.setText("Score");
		timeLabel label3 = new timeLabel(0);
		toolbarLayout.getChildren().addAll(label1, label2, label3);
		//label4.textProperty().bind(observable); //bind to the time of the game
		this.getItems().add(toolbarLayout);
	}
	
	
	@Override
	public void updateHUD() {
		// TODO Auto-generated method stub
		
	}

}
