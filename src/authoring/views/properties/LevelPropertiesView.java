package authoring.views.properties;

import java.util.ResourceBundle;

import authoring.factories.ElementFactory;
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
	ElementFactory eFactory;
	
	public LevelPropertiesView(Level level, int levelNum) {
		super();
		this.levelNum = levelNum;
		this.level = level;
		this.eFactory = new ElementFactory();
		this.fill();
	}

	@Override
	protected String title() {
		//TODO make final variable for this
		return "Level "+levelNum+" Properties";
	}

	@Override
	protected void fill() {
		ResourceBundle levelProps = ResourceBundle.getBundle(this.getResourcesFilePath()+"LevelProperties");
		try {
			int currentRow = 0;
			for (String property : levelProps.keySet()) {
				Label label = (Label) eFactory.buildElement(ElementType.Label,levelProps.getString(property));
				this.getRoot().add(label, 0, currentRow++);
			}
			//TODO update text to be something meaningful from properties files
			TextField infoText = (TextField) eFactory.buildElement(ElementType.TextField,text);
			TextField diffText = (TextField) eFactory.buildElement(ElementType.TextField,text);
			NumberField timeNumber = (NumberField) eFactory.buildElement(ElementType.NumberField,text);
			NumberField distNumber = (NumberField) eFactory.buildElement(ElementType.NumberField,text);
			Button button = (Button) eFactory.buildElement(ElementType.Button,text);
			button.setOnAction(e->{
				level.setLevelInfo(infoText.getText());
				level.setLevelDifficulty(diffText.getText());
				level.setLevelTime(Double.parseDouble(timeNumber.getText()));
				level.setLevelDistance(Double.parseDouble(distNumber.getText()));
			});
			getRoot().addColumn(1,diffText,timeNumber,infoText,distNumber);
			getRoot().add(button, 0, currentRow++);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
