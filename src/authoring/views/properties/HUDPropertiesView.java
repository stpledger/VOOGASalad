package authoring.views.properties;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.logging.Logger;

import authoring.factories.ClickElementType;
import authoring.factories.Element;
import authoring.factories.ElementType;
import authoring.gamestate.Level;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;

/**
 * 
 * @author Hemanth Yakkali(hy115)
 *
 */
public class HUDPropertiesView extends PropertiesView{
	
	private List<Level> levels;
	private static final String NAME = "HUD Properties";
	
	private final static Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
	
	
	public HUDPropertiesView(List<Level> levels) {
		super();
		this.levels = new ArrayList<>(levels);
	}

	@Override
	protected void fill() {
		ResourceBundle HUDProps = this.getResourcesBundle(this.title().replace(" ", ""));
		try {
			CheckBox livesBox = (CheckBox) this.getElementFactory().buildElement(ElementType.CheckBox, HUDProps.getString("Lives"));
			CheckBox healthBox = (CheckBox) this.getElementFactory().buildElement(ElementType.CheckBox, HUDProps.getString("Health"));
			CheckBox timeBox = (CheckBox) this.getElementFactory().buildElement(ElementType.CheckBox, HUDProps.getString("Time"));
			CheckBox levelBox = (CheckBox) this.getElementFactory().buildElement(ElementType.CheckBox, HUDProps.getString("Levels"));
			Button submit = (Button) this.getElementFactory().buildClickElement(ClickElementType.Button,this.getButtonBundle().getString("Submit"), e->{
				for(Level level : levels) {
					level.addHUDProp(HUDProps.getString("Lives"), livesBox.isSelected());
					level.addHUDProp(HUDProps.getString("Health"), healthBox.isSelected());
					level.addHUDProp(HUDProps.getString("Time"), timeBox.isSelected());
					level.addHUDProp(HUDProps.getString("Levels"), levelBox.isSelected());
				}
				this.makeAlert(this.title()+" has been updated!");
				this.close();
			});
			elements.addAll(Arrays.asList(new Element[] {(Element) livesBox, (Element) healthBox, (Element) timeBox, (Element) levelBox}));
			getRoot().addColumn(0, healthBox,livesBox,levelBox,timeBox);
			getRoot().add(submit, 0, getRoot().getChildren().size());
		} catch (Exception e2) {
			 LOGGER.log(java.util.logging.Level.SEVERE, e2.toString(), e2);
		}	
	}

	@Override
	protected String title() {
		return NAME;
	}


}
