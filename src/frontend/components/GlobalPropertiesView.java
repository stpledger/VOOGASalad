package frontend.components;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import frontend.factories.ElementType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * 
 * @author Hemanth Yakkali(hy115)
 *
 */
public class GlobalPropertiesView extends PropertiesView {

	private List<Level> levels;
	private final String NAME = "Global Properties";
	
	public GlobalPropertiesView(List<Level> ls){
		super();
		this.levels = ls;
		fill();
	}

	@Override
	protected String title() {
		return NAME;
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
		try {
			Button submit = (Button) this.getElementFactory().buildElement(ElementType.Button, NAME);
			submit.setOnAction(e->{
				for(Level level : levels) {
					level.addGProp(globalProps.getString("Title"), titleInput.getText());
					level.addGProp(globalProps.getString("Lives"), livesInput.getText());
					level.addGProp(globalProps.getString("Filepath"), pathInput.getText());
				}
			});
			getRoot().add(submit, 0, currentRow++);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}

}
