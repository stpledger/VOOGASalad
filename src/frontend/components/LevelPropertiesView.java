package frontend.components;

/**
 * 
 * @author Hemanth Yakkali (hy115)
 *
 */
public class LevelPropertiesView extends PropertiesView{
	
	private int levelNum;
	
	public LevelPropertiesView(int level) {
		setLevelNum(level);
	}
	
	private void setLevelNum(int num) {
		levelNum = num;
	}

	@Override
	protected String title() {
		return "Level "+levelNum+" Properties";
	}

}
