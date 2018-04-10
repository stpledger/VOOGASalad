package engine.components;


/**
 * Component superclass. Just a databucket for a parent ID, which all sub components need.
 * Overall, components are just data buckets. They should contain instance variables (ie doubles, ints, etc), 
 * and setters/getters. Systems contain all the game logic.
 * @author fitzj
 */
public class Component {
	
	private int pid;
	private static String key;
	
	/**
	 * Constructs component with entity parent ID
	 * @param pid	ID of parent. Can not be changed externally.
	 */
	public Component(int pid, String key) {
		this.pid = pid;
		this.key = key;
	}
	
	public int getParentID () {
		return pid;
	}

	public static String getKey() {
		return key;
	}
<<<<<<< HEAD

=======
	
>>>>>>> 9d2c61e58bf633d7fda5043bafe04e74d489d2b8
}
