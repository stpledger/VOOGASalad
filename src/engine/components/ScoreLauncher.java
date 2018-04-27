package engine.components;
/**
 * This component is added to entities that when collided with player
 * either give or subtract certain amount of score
 * @sv116
 */


public class ScoreLauncher extends SingleDataComponent{
    public static String KEY ="ScoreLauncher";

	public ScoreLauncher(int pid, double data) {
		super(pid,data);
		
	}
	
	@Override
	public String getKey() {
		return KEY;
	}

}
