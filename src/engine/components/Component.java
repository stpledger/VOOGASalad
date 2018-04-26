package engine.components;
import java.util.Map;

/**
 * Component superclass. Just a databucket for a parent ID, which all sub components need.
 * Overall, components are just data buckets. They should contain instance variables (ie doubles, ints, etc), 
 * and setters/getters. Systems contain all the game logic.
 * @author fitzj
 */
public interface Component {
	public int getPID();
}
