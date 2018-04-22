package authoring.views.properties;

import java.util.List;
import java.util.ResourceBundle;

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
	private final String NAME = "HUD Properties";
	
	public HUDPropertiesView(List<Level> levelArray) {
		super();
		this.levels = levelArray;
	}

	@Override
	protected void fill() {
		ResourceBundle HUDProps = this.getResourcesBundle(this.NAME.replace(" ", ""));
		try {
			CheckBox livesBox = (CheckBox) this.getElementFactory().buildElement(ElementType.CheckBox, HUDProps.getString("Lives"));
			CheckBox healthBox = (CheckBox) this.getElementFactory().buildElement(ElementType.CheckBox, HUDProps.getString("Health"));
			CheckBox timeBox = (CheckBox) this.getElementFactory().buildElement(ElementType.CheckBox, HUDProps.getString("Time"));
			CheckBox levelBox = (CheckBox) this.getElementFactory().buildElement(ElementType.CheckBox, HUDProps.getString("Levels"));
			Button submit = (Button) this.getElementFactory().buildElement(ElementType.Button,this.getButtonBundle().getString("Submit") );
			submit.setOnAction(e->{
				for(Level level : levels) {
					level.addHUDProp(HUDProps.getString("Lives"), livesBox.isSelected());
					level.addHUDProp(HUDProps.getString("Health"), healthBox.isSelected());
					level.addHUDProp(HUDProps.getString("Time"), timeBox.isSelected());
					level.addHUDProp(HUDProps.getString("Levels"), levelBox.isSelected());
				}
				this.makeAlert(this.title()+" has been updated!");
				this.close();
			});
			getRoot().addColumn(0, healthBox,livesBox,levelBox,timeBox);
			getRoot().add(submit, 0, getRoot().getChildren().size());
		} catch (Exception e2) {
			e2.printStackTrace();
		}	
	}

	@Override
	protected String title() {
		return NAME;
	}

}
