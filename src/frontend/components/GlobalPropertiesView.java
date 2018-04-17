package frontend.components;

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
	private final String SUBMIT = "Submit";
	
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
		ResourceBundle globalProps = ResourceBundle.getBundle(this.getResourcesFilePath()+NAME.replace(" ", ""));
		try {
			int currentRow = 0;
			//TODO update text to be something meaningful from properties files
			TextField titleInput = (TextField) this.getElementFactory().buildElement(ElementType.TextField, NAME);
			NumberField livesInput = (NumberField) this.getElementFactory().buildElement(ElementType.NumberField, NAME);
			TextField pathInput = (TextField) this.getElementFactory().buildElement(ElementType.TextField, NAME);
			for(String property:globalProps.keySet()) {
				Label label = (Label) this.getElementFactory().buildElement(ElementType.Label, globalProps.getString(property));
				getRoot().addRow(currentRow++, label);
			}
			getRoot().addColumn(1,titleInput,livesInput,pathInput);
			Button submit = (Button) this.getElementFactory().buildElement(ElementType.Button, SUBMIT);
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
