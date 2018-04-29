package authoring.views.properties;

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
 * @author Hemanth Yakkali (hy115)
 */
public class LevelPropertiesView extends PropertiesView{
	
	private int levelNum;
	private Level level;
	private String text = "text";
	
	private final static Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
		
	public LevelPropertiesView(Level level, int levelNum) {
		super();
		this.levelNum = levelNum;
		this.level = level;
	}

	@Override
	protected String title() {
		return "Level "+levelNum+" Properties";
	}

	@Override
	protected void fill() {
		ResourceBundle levelProps = this.getResourcesBundle(this.title().replaceAll("[0-9]", "").replaceAll(" ", ""));
		try {
			int currentRow = 0;
			for (String property : levelProps.keySet()) {
				Label label = (Label) this.getElementFactory().buildElement(ElementType.Label,levelProps.getString(property));
				currentRow++;
				this.getRoot().add(label, 0, currentRow);
				elements.add((Element) label);
			}
			//TODO update text to be something meaningful from properties files
			TextField infoText = (TextField) this.getElementFactory().buildElement(ElementType.TextField,text);
			TextField diffText = (TextField) this.getElementFactory().buildElement(ElementType.TextField,text);
			NumberField timeNumber = (NumberField) this.getElementFactory().buildElement(ElementType.NumberField,text);
			NumberField distNumber = (NumberField) this.getElementFactory().buildElement(ElementType.NumberField,text);
			Button button = (Button) this.getElementFactory().buildClickElement(ClickElementType.Button,this.getButtonBundle().getString("Submit"), e->{
				level.setLevelInfo(infoText.getText());
				level.setLevelDifficulty(diffText.getText());
				level.setLevelTime(Double.parseDouble(timeNumber.getText()));
				level.setLevelDistance(Double.parseDouble(distNumber.getText()));
				this.makeAlert(this.title()+" has been updated!");
				this.close();
			});
			getRoot().addColumn(1,diffText,timeNumber,infoText,distNumber);
			currentRow++;
			getRoot().add(button, 0, currentRow);
		} catch (Exception e) {
			 LOGGER.log(java.util.logging.Level.SEVERE, e.toString(), e);
		}
	}
}
