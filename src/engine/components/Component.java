package engine.components;

import java.util.Map;

/**
 * Component superclass. Just a databucket for a parent ID, which all sub components need.
 * Overall, components are just data buckets. They should contain instance variables (ie doubles, ints, etc), 
 * and setters/getters. Systems contain all the game logic.
 * @author fitzj
 */
public abstract class Component {
	
	private int pid;
	private String key;
	
	/**
	 * Constructs component with entity parent ID
	 * @param pid	ID of parent. Can not be changed externally.
	 */
	
	
	public Component(int pid, String key) {
		this.key = key;
		this.pid = pid;
	}
	
	public int getParentID () {
		return pid;
	}

	public String getKey() { return key; }

	public abstract Map<String,String> getParameters();

}
