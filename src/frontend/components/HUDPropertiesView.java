package frontend.components;

import java.util.ResourceBundle;

import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;

public class HUDPropertiesView extends PropertiesView{
	
	public HUDPropertiesView() {
		super();
		this.fill();
	}

	@Override
	protected void fill() {
		int currentRow = 0;
		ResourceBundle HUDProps = ResourceBundle.getBundle(this.getResourcesFilePath()+"HUDProperties");
		CheckBox livesBox = new CheckBox();
		CheckBox healthBox = new CheckBox();
		CheckBox timeBox = new CheckBox();
		CheckBox levelBox = new CheckBox();
		for (String property : HUDProps.keySet()) {
			Label label = new Label(HUDProps.getString(property));
			getRoot().addRow(currentRow++,label);
		}		
		getRoot().addColumn(1, healthBox,livesBox,levelBox,timeBox);
		getRoot().add(MenuItemBuilder.buildButton(this.getButtonProps().getString("Submit"), e-> {
//			for(Node n:getRoot().getChildren()) {
//				if(n instanceof CheckBox){
//					final CheckBox boxy = (CheckBox)n;
//					System.out.println(boxy.isSelected());
//				}
//			}
			System.out.println("Lives"+livesBox.isSelected());
			System.out.println("Health"+healthBox.isSelected());
			System.out.println("Time"+timeBox.isSelected());
			System.out.println("Level"+levelBox.isSelected());
		}), 0, currentRow++);
	}

	@Override
	protected String title() {
		return "HUD Properties";
	}

}
