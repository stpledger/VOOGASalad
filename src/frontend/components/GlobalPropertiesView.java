package frontend.components;

import java.util.ResourceBundle;

import javafx.scene.control.Label;

/**
 * 
 * @author Hemanth Yakkali(hy115)
 *
 */
public class GlobalPropertiesView extends PropertiesView {

	public GlobalPropertiesView(){
		super();
	}

	@Override
	protected String title() {
		return "Global Properties";
	}

	@Override
	protected void fill() {
		int currentRow = 0;
		ResourceBundle globalProps = ResourceBundle.getBundle(this.getResourcesFilePath()+"globalProperties");
		NumberField timeNumber = new NumberField();
		NumberField livesNumber = new NumberField();
		NumberField levelNumber = new NumberField();
		for(String property:globalProps.keySet()) {
			Label label = new Label(globalProps.getString(property));
			getRoot().addRow(currentRow++, label);
		}
		getRoot().addColumn(1,timeNumber,livesNumber,levelNumber);
		getRoot().add(MenuItemBuilder.buildButton(this.getButtonProps().getString("Submit"), e-> {
			level.setLevelInfo(timeNumber.getText());
			level.setLevelDifficulty(livesNumber.getText());
			level.setLevelTime(Double.parseDouble(levelNumber.getText()));
		}), 0, currentRow++);

	}

}
