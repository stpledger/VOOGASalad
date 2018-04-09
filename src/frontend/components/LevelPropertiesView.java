package frontend.components;

import java.util.List;

import javafx.scene.control.Label;

/**
 * @author Hemanth Yakkali (hy115)
 */
public class LevelPropertiesView extends PropertiesView{
	
	private int levelNum;
	private final String LEVELS_PACKAGE = "engine.components";
	
	public LevelPropertiesView(int level, List<String> props) {
		super();
		levelNum = level;
		this.fill(props);
	}

	@Override
	protected String title() {
		return "Level "+levelNum+" Properties";
	}

	@Override
	protected void fill(List<String> props) {
		int currentRow = 0;
		for (String property : props) {
			Label componentLabel = new Label(property);
			NumberField number = new NumberField();
			componentLabel.setLabelFor(number);
			getRoot().addRow(currentRow, componentLabel,number);
			currentRow++;
		}
//		getRoot().add(MenuItemBuilder.buildButton("Submit Changes", e->fieldUpdate()), 0, currentRow++);
	}

}
