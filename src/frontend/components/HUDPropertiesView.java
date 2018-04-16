package frontend.components;

import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;

/**
 * 
 * @author Hemanth Yakkali(hy115)
 *
 */
public class HUDPropertiesView extends PropertiesView{
	
	private ArrayList<Level> levels;
	private final String NAME = "HUD Properties";
	
	public HUDPropertiesView(ArrayList<Level> levelArray) {
		super();
		this.levels = levelArray;
		this.fill();
	}

	@Override
	protected void fill() {
		int currentRow = 0;
		ResourceBundle HUDProps = ResourceBundle.getBundle(this.getResourcesFilePath()+NAME);
		CheckBox livesBox = new CheckBox();
		CheckBox healthBox = new CheckBox();
		CheckBox timeBox = new CheckBox();
		CheckBox levelBox = new CheckBox();
		for (String property : HUDProps.keySet()) {
			Label label = new Label(HUDProps.getString(property));
			getRoot().addRow(currentRow++,label);
		}		
		getRoot().addColumn(1, healthBox,livesBox,levelBox,timeBox);
		getRoot().add(this.getButtonFactory().makeButton(this.getButtonProps().getString("Submit"), e-> {
			for(Level level : levels) {
				level.addHUDProp(HUDProps.getString("Lives"), livesBox.isSelected());
				level.addHUDProp(HUDProps.getString("Health"), healthBox.isSelected());
				level.addHUDProp(HUDProps.getString("Time"), timeBox.isSelected());
				level.addHUDProp(HUDProps.getString("Levels"), levelBox.isSelected());
			}
		}), 0, currentRow++);
	}

	@Override
	protected String title() {
		return NAME;
	}

}
