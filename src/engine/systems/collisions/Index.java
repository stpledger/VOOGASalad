package engine.systems.collisions;

/**
 * This class defines sequence of indexes used to store components  for collision system
 * @author Yameng
 */

public class Index {
	public static int TYPE_INDEX;
	public static int DIMENSION_INDEX;
	public static int POSITION_INDEX;
	public static int VELOCITY_INDEX;
	public static int HEALTH_INDEX;
	public static int DAMAMGE_INDEX;
	public static int SPRITE_INDEX;
	
	public Index() {
		TYPE_INDEX = 0;
		DIMENSION_INDEX = 1;
		POSITION_INDEX = 2;
		VELOCITY_INDEX = 3;
		HEALTH_INDEX = 4;
		DAMAMGE_INDEX = 5;
		SPRITE_INDEX = 5;
	}
}
