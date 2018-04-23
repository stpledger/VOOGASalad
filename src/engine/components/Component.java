package engine.components;

<<<<<<< HEAD
=======
import engine.setup.EntityManager;

import java.util.Map;
>>>>>>> 14055f464dfac0f710cf0e7759acd85fd1b0c20a

/**
 * Component superclass. Just a databucket for a parent ID, which all sub components need.
 * Overall, components are just data buckets. They should contain instance variables (ie doubles, ints, etc), 
 * and setters/getters. Systems contain all the game logic.
 * @author fitzj
 */
public interface Component {
	
	public String getKey();
	public int getPID();

}
