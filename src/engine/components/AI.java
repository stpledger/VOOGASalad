package engine.components;

import java.util.function.Consumer;

/**
 * Component to be added to enemies that have 'custom' actions (following the player), moving in a circle, etc.,
 * to be implemented by the designer by writing in a method for the entity to perform.
 *
 * @author cndraco
 */
public class AI implements Component {


    public static String KEY = "AI";

    private Consumer action;

    private int pid;
    
    public AI (int pid) {
        this.pid = pid;
    }

    public void setAction (Consumer action) {
        this.action = action;
    }

    public void doAction(Double time) {
        action.accept(time);
    }

	public String getKey() {
		return KEY;
	}

	public int getPID() {
		return pid;
	}
}
