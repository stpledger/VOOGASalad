package frontend.components;

import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * 
 * @author Hemanth Yakkali(hy115)
 *
 */
public class GlobalPropertiesView extends PropertiesView {

	private ArrayList<Level> levels;
	
	public GlobalPropertiesView(ArrayList<Level> ls){
		super();
		this.levels = ls;
		fill();
	}

	@Override
	protected String title() {
		return "Global Properties";
	}

	@Override
	protected void fill() {
		int currentRow = 0;
		ResourceBundle globalProps = ResourceBundle.getBundle(this.getResourcesFilePath()+"GlobalProperties");

		TextField titleInput = new TextField();
		NumberField livesInput = new NumberField();
		TextField pathInput = new TextField();
		for(String property:globalProps.keySet()) {
			Label label = new Label(globalProps.getString(property));
			getRoot().addRow(currentRow++, label);
		}
		getRoot().addColumn(1,titleInput,livesInput,pathInput);
		getRoot().add(this.getButtonFactory().makeButton(this.getButtonProps().getString("Submit"), e-> {
			for(Level level : levels) {
				level.addGProp(globalProps.getString("Title"), titleInput.getText());
				level.addGProp(globalProps.getString("Lives"), livesInput.getText());
				level.addGProp(globalProps.getString("Filepath"), pathInput.getText());
			}
		}), 0, currentRow++);
	}

}
