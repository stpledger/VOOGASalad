package frontend.components;

import java.util.ResourceBundle;

import javafx.scene.control.Label;

/**
 * @author Hemanth Yakkali (hy115)
 */
public class LevelPropertiesView extends PropertiesView{
	
	private int levelNum;
	private final String RESOURCES = "resources/";
	
	public LevelPropertiesView(Level level, int levelNum) {
		super();
		this.levelNum = levelNum;
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
		for (String property : levelProps.keySet()) {
			Label componentLabel = new Label(levelProps.getString(property));
			NumberField number = new NumberField();
			componentLabel.setLabelFor(number);
			getRoot().addRow(currentRow++, componentLabel,number);
		}
//		getRoot().add(MenuItemBuilder.buildButton("Submit Changes", e->fieldUpdate()), 0, currentRow++);
	}

}
