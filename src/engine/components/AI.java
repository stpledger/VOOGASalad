package engine.components;

import java.util.Map;
import java.util.function.BiConsumer;

import java.util.function.Consumer;

/**
 * Component to be added to enemies that have 'custom' actions (following the player), moving in a circle, etc.,
 * to be implemented by the designer by writing in a method for the entity to perform.
 *
 * @author cndraco
 */
public class AI implements Component, BehaviorComponent {


    public static final String KEY = "AI";

    private Consumer<Map<String, Component>> action;

    private int pid;
    
    public AI (int pid) {
        this.pid = pid;
    }

    public void setAction (Consumer<Map<String, Component>> action) {
        this.action = action;
    }

    public void doAction(Map<String, Component> components) {
        action.accept(components);
    }

	public String getKey() {
		return KEY;
	}

	public int getPID() {
		return pid;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void addBehavior(Object identifier, Consumer con) {
		setAction(con);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void addBehavior(Object identifier, BiConsumer bic) {
		setAction((entity) -> {
			bic.accept((Map<String, Component>) entity, null);
		});
	}
}
