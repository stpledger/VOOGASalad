package authoring.views.properties;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.logging.Logger;

import authoring.factories.ClickElementType;
import authoring.factories.ElementType;
import authoring.factories.NumberField;
import authoring.gamestate.Level;
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
	private static final String NAME = "Global Properties";
	
	private final static Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
	
	private Properties language = new Properties();
	
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
			TextField titleInput = (TextField) this.getElementFactory().buildElement(ElementType.TextField, globalProps.getString("Title").split(",")[1]);
			NumberField livesInput = (NumberField) this.getElementFactory().buildElement(ElementType.NumberField, globalProps.getString("Lives").split(",")[1]);
			TextField pathInput = (TextField) this.getElementFactory().buildElement(ElementType.TextField, globalProps.getString("Filepath").split(",")[1]);
			for(String property:globalProps.keySet()) {
				Label label = (Label) this.getElementFactory().buildElement(ElementType.Label, globalProps.getString(property).split(",")[0]);
				currentRow++;
				getRoot().addRow(currentRow, label);
			}
			getRoot().addColumn(1,livesInput,titleInput,pathInput);
			Button submit = (Button) this.getElementFactory().buildClickElement(ClickElementType.Button, this.getButtonBundle().getString("Submit"), e->{
				for(Level level : levels) {
					level.addGProp(globalProps.getString("Title").split(",")[0], titleInput.getText());
					level.addGProp(globalProps.getString("Lives").split(",")[0], livesInput.getText());
					level.addGProp(globalProps.getString("Filepath").split(",")[0], pathInput.getText());
				}
				this.makeAlert(this.title()+" has been saved!");
				this.close();
			});
			currentRow++;
			getRoot().add(submit, 0, currentRow);
		} catch (Exception e1) {
			 LOGGER.log(java.util.logging.Level.SEVERE, e1.toString(), e1);
		}
	}

	@Override
	public void setLanguage(Properties lang) {
		this.language = lang;
		
		
	}

}
