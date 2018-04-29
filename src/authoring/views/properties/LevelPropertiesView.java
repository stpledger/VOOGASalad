package authoring.views.properties;

import java.util.logging.Logger;

import authoring.factories.ClickElementType;
import authoring.factories.Element;
import authoring.factories.ElementType;
import authoring.factories.NumberField;
import authoring.gamestate.Level;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 * @author Hemanth Yakkali (hy115)
 */
public class LevelPropertiesView extends PropertiesView{

	private int levelNum;
	private Level level;

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
		try {
			this.createLabels(this.title().replaceAll("[0-9]", "").replaceAll(" ", ""));
			//text argument is empty, no text needed for these fields
			TextField infoText = (TextField) this.getElementFactory().buildElement(ElementType.TextField,"");
			TextField diffText = (TextField) this.getElementFactory().buildElement(ElementType.TextField,"");
			NumberField timeNumber = (NumberField) this.getElementFactory().buildElement(ElementType.NumberField,"");
			this.getRoot().addColumn(1,diffText,timeNumber,infoText);
			Button submit = (Button) this.getElementFactory().buildClickElement(ClickElementType.Button,this.getButtonBundle().getString("Submit"), e->{
				level.setLevelInfo(infoText.getText());
				level.setLevelDifficulty(diffText.getText());
				level.setLevelTime(Double.parseDouble(timeNumber.getText()));
				this.makeAlert(this.title()+" has been updated!");
				this.close();
			});
			this.getElementList().add((Element) submit);
			this.getRoot().addColumn(0, submit);
		} catch (Exception e) {
			LOGGER.log(java.util.logging.Level.SEVERE, e.toString(), e);
		}
	}

}
