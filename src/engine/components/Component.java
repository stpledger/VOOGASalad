package engine.components;

import java.io.Serializable;

/**
 * Component interface. Components must provided both a parent ID (PID) and a unique identifying key.
 * 
 * @author fitzj
 */
public interface Component extends Serializable {
	public int getPID();
	public String getKey();
}
