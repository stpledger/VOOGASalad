package frontend.components;

import java.util.ResourceBundle;

import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;

/**
 * 
 * @author Hemanth Yakkali(hy115)
 *
 */
public class HUDPropertiesView extends PropertiesView{
	
	private Level level;
	
	public HUDPropertiesView(Level level) {
		super();
		this.level = level;
		this.fill();
	}

	@Override
	protected void fill() {
		int currentRow = 0;
		ResourceBundle HUDProps = ResourceBundle.getBundle(this.getResourcesFilePath()+"HUDProperties");
		CheckBox livesBox = new CheckBox();
		CheckBox healthBox = new CheckBox();
		CheckBox timeBox = new CheckBox();
		CheckBox levelBox = new CheckBox();
		for (String property : HUDProps.keySet()) {
			Label label = new Label(HUDProps.getString(property));
			getRoot().addRow(currentRow++,label);
		}		
		getRoot().addColumn(1, healthBox,livesBox,levelBox,timeBox);
		getRoot().add(MenuItemBuilder.buildButton(this.getButtonProps().getString("Submit"), e-> {
			level.addHUDProp(HUDProps.getString("Lives"), livesBox.isSelected());
			level.addHUDProp(HUDProps.getString("Health"), healthBox.isSelected());
			level.addHUDProp(HUDProps.getString("Time"), timeBox.isSelected());
			level.addHUDProp(HUDProps.getString("Levels"), levelBox.isSelected());
		}), 0, currentRow++);
	}

	@Override
	protected String title() {
		return "HUD Properties";
	}

}
