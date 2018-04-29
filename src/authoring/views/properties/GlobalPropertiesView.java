package authoring.views.properties;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Logger;

import authoring.factories.ClickElementType;
import authoring.factories.Element;
import authoring.factories.ElementType;
import authoring.factories.NumberField;
import authoring.gamestate.Level;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * Creates properties view form that allows user to update the global properties of the game. 
 * @author Hemanth Yakkali(hy115)
 */
public class GlobalPropertiesView extends PropertiesView {

	private List<Level> levels;
	private static final String NAME = "Global Properties";
	private static final Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

	public GlobalPropertiesView(List<Level> levels){
		super();
		this.levels = new ArrayList<>(levels);
	}

	@Override
	protected String title() {
		return NAME;
	}

	@Override
	protected void fill() {
		ResourceBundle globalProps = this.getResourcesBundle(this.title().replace(" ", ""));
		try {
			int currentRow = 0;
			//text argument is empty, no text needed for these fields
			TextField titleInput = (TextField) this.getElementFactory().buildElement(ElementType.TextField,""); 
			NumberField livesInput = (NumberField) this.getElementFactory().buildElement(ElementType.NumberField,"");
			TextField pathInput = (TextField) this.getElementFactory().buildElement(ElementType.TextField,"");
			this.getRoot().addColumn(1,livesInput,titleInput,pathInput);
			for(String property:globalProps.keySet()) {
				Label label = (Label) this.getElementFactory().buildElement(ElementType.Label, globalProps.getString(property));
				this.getRoot().add(label, 0, currentRow);
				this.getElementList().add((Element) label);
				currentRow++;
			}
			Button submit = (Button) this.getElementFactory().buildClickElement(ClickElementType.Button, this.getButtonBundle().getString("Submit"), e->{
				for(Level level : levels) {
					level.addGProp(globalProps.getString("Title"), titleInput.getText());
					level.addGProp(globalProps.getString("Lives"), livesInput.getText());
					level.addGProp(globalProps.getString("Filepath"), pathInput.getText());
				}
				this.makeAlert(this.title()+" has been saved!"); //TODO put in properties file
				this.close();
			});
			currentRow++;
			this.getElementList().add((Element) submit);	
			this.getRoot().add(submit, 0, currentRow);
		} catch (Exception e1) {
			LOGGER.log(java.util.logging.Level.SEVERE, e1.toString(), e1);
		}
	}

}
