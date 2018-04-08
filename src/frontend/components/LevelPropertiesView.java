package frontend.components;

import java.util.HashMap;
import java.util.Map;

import frontend.components.PropertiesView.NumberField;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * @author Hemanth Yakkali (hy115)
 */
public class LevelPropertiesView extends PropertiesView{
	
	private int levelNum;
	private Broadcast broadcast;
	private final String LEVELS_PACKAGE = "engine.components";
	
	public LevelPropertiesView(int level, Broadcast broadcast) {
		super();
		this.broadcast = broadcast;
		levelNum = level;
		this.fill();
	}

	@Override
	protected String title() {
		return "Level "+levelNum+" Properties";
	}

	@Override
	protected void fill() {
		int currentRow = 0;
		for (String property : getClassesInPackage(LEVELS_PACKAGE)) {
			System.out.println(property);
			Label componentLabel = new Label(property);
			getRoot().add(componentLabel, 0, currentRow);
			// Text field should only accept numeric values
			NumberField number = new NumberField();
			getRoot().add(number, 1, currentRow);
			currentRow++;
		}
		getRoot().add(MenuItemBuilder.buildButton("Submit Changes", e->fieldUpdate()), 0, currentRow++);
	}
	
	private void fieldUpdate() {
		
	}

}
