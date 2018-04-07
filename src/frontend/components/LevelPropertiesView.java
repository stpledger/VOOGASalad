package frontend.components;

import javafx.scene.Node;

/**
 * @author Hemanth Yakkali (hy115)
 */
public class LevelPropertiesView extends PropertiesView{
	
	private int levelNum;
	
	public LevelPropertiesView(int level) {
		super();
		levelNum = level;
	}

	@Override
	protected String title() {
		return "Level "+levelNum+" Properties";
	}

	@Override
	public Node getNode() {
		// TODO Auto-generated method stub
		return null;
	}

}
