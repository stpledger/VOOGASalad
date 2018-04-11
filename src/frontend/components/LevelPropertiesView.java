package frontend.components;

import java.util.ResourceBundle;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * @author Hemanth Yakkali (hy115)
 */
public class LevelPropertiesView extends PropertiesView{
	
	private int levelNum;
	private Level level;
	
	public LevelPropertiesView(Level level, int levelNum) {
		super();
		this.levelNum = levelNum;
		this.level = level;
		this.fill();
	}

	@Override
	protected String title() {
		return "Level "+levelNum+" Properties";
	}

	@Override
	protected void fill() {
		int currentRow = 0;
		ResourceBundle levelProps = ResourceBundle.getBundle(this.getResourcesFilePath()+"LevelProperties");
		TextField infoText = new TextField();
		TextField diffText = new TextField();
		NumberField timeNumber = new NumberField();
		NumberField distNumber = new NumberField();
		getRoot().addColumn(1,diffText,timeNumber,infoText,distNumber);
		for (String property : levelProps.keySet()) {
			Label label = new Label(levelProps.getString(property));
			this.getRoot().add(label, 0, currentRow++);
		}
		getRoot().add(MenuItemBuilder.buildButton(this.getButtonProps().getString("Submit"), e-> {
			level.setLevelInfo(infoText.getText());
			level.setLevelDifficulty(diffText.getText());
			level.setLevelTime(Double.parseDouble(timeNumber.getText()));
			level.setLevelDistance(Double.parseDouble(distNumber.getText()));
		}), 0, currentRow++);
	}
}
