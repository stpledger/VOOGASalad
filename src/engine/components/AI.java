package engine.components;

import java.util.Map;
import java.util.function.Consumer;

/**
 * Component to be added to enemies that have 'custom' actions (following the player), moving in a circle, etc.,
 * to be implemented by the designer by writing in a method for the entity to perform.
 *
 * @author cndracos
 */
public class AI {

    public static String KEY = "AI";

    private Consumer action;

    public AI (int pid) {
        super(pid);
    }

    public void setAction (Consumer action) {
        this.action = action;
    }

    public void doAction(Double time) {
        action.accept(time);
    }

	public Map<String, String> getParameters() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getKey() {
		return KEY;
	}
}
