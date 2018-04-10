package frontend.components;

import java.util.ResourceBundle;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * @author Hemanth Yakkali (hy115)
 */
public class LevelPropertiesView extends PropertiesView{
	
	private int levelNum;
	private final String RESOURCES = "resources/";
	private ResourceBundle buttonProps;
	private Level level;
	
	public LevelPropertiesView(Level level, int levelNum) {
		super();
		this.levelNum = levelNum;
		this.level = level;
		this.buttonProps = ResourceBundle.getBundle(RESOURCES+"buttons");
		this.fill();
	}

	@Override
	protected String title() {
		return "Level "+levelNum+" Properties";
	}

	@Override
	protected void fill() {
		int currentRow = 0;
		ResourceBundle levelProps = ResourceBundle.getBundle(RESOURCES+"levelProperties");
		TextField infoText = new TextField();
		TextField diffText = new TextField();
		NumberField timeNumber = new NumberField();
		NumberField distNumber = new NumberField();
		for (String property : levelProps.keySet()) {
			Label label = new Label(levelProps.getString(property));
			getRoot().addRow(currentRow++,label);
		}
		getRoot().addColumn(1,diffText,timeNumber,infoText,distNumber);
		getRoot().add(MenuItemBuilder.buildButton(buttonProps.getString("Submit"), e-> {
			level.setLevelInfo(infoText.getText());
			level.setLevelDifficulty(diffText.getText());
			level.setLevelTime(Double.parseDouble(timeNumber.getText()));
			level.setLevelDistance(Double.parseDouble(distNumber.getText()));
		}), 0, currentRow++);
	}

}
