package engine.components;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

/**
 * Interface for adding behavior in the form of consumers to a component. 
 * Done to help authoring environment.
 * @author fitzj
 *
 */
public interface BehaviorComponent {
	public void addBehavior(Object identifier, Consumer con);
	public void addBehavior(Object identifier, BiConsumer bic);
}
