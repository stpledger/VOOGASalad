package frontend.components;

import java.util.List;

import javafx.scene.control.Label;

/**
 * @author Hemanth Yakkali (hy115)
 */
public class LevelPropertiesView extends PropertiesView{
	
	private int levelNum;
	private Broadcast broadcast;
	private final String LEVELS_PACKAGE = "engine.components";
	
	public LevelPropertiesView(int level, Broadcast broadcast, List<String> props) {
		super();
		this.broadcast = broadcast;
		levelNum = level;
		this.fill(props);
	}

	@Override
	protected String title() {
		return "Level "+levelNum+" Properties";
	}

	@Override
	protected void fill() {
		int currentRow = 0;
		for (String property : props) {
			System.out.println(property);
			Label componentLabel = new Label(property);
			getRoot().add(componentLabel, 0, currentRow);
			// Text field should only accept numeric values
			NumberField number = new NumberField();
			componentLabel.setLabelFor(number);
			getRoot().add(number, 1, currentRow);
			currentRow++;
		}
//		getRoot().add(MenuItemBuilder.buildButton("Submit Changes", e->fieldUpdate()), 0, currentRow++);
	}

}
