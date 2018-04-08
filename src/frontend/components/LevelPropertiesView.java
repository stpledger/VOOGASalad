package frontend.components;

import java.util.HashMap;
import java.util.Map;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * @author Hemanth Yakkali (hy115)
 */
public class LevelPropertiesView extends PropertiesView{
	
	private int levelNum;
	private Broadcast broadcast;
	
	public LevelPropertiesView(int level, Broadcast broadcast) {
		super();
		this.broadcast = broadcast;
		levelNum = level;
		this.fill(this.getFields());
	}

	@Override
	protected String title() {
		return "Level "+levelNum+" Properties";
	}

	@Override
	protected void fill(Map<String, Boolean> fields) {
		int currentRow = 0;
		for (String componentName : fields.keySet()) {
			Label componentLabel = new Label(componentName);
			getRoot().add(componentLabel, 0, currentRow);
			if (fields.get(componentName)) { 
				// Text field should only accept numeric values
				NumberField number = new NumberField();
				getRoot().add(number, 1, currentRow);
			} else {
				TextField text = new TextField();
				getRoot().add(text, 1, currentRow);
			}
			currentRow++;
		}
		getRoot().add(MenuItemBuilder.buildButton("Submit Changes", null), 0, currentRow++);
	}
	
	private void fieldUpdate() {
		
	}
	
	private Map<String, Boolean> getFields() {
		Map<String,Boolean> fieldMap = new HashMap<String,Boolean>();
		fieldMap.put("Level Number: ", true);
		fieldMap.put("Time: ", true);
		fieldMap.put("Distance: ", true);
		return fieldMap;
	}

}
