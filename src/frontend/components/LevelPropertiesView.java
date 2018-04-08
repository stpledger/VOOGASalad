package frontend.components;

/**
 * @author Hemanth Yakkali (hy115)
 */
public class LevelPropertiesView extends PropertiesView{
	
	private int levelNum;
	private Broadcast broadcast;
	
	public LevelPropertiesView(int level, Broadcast broadcast) {
		super();
		this.broadcast = broadcast;
		levelNum = level;
	}

	@Override
	protected String title() {
		return "Level "+levelNum+" Properties";
	}

	@Override
	protected void fill() {
		// TODO Auto-generated method stub
		
	}

}
