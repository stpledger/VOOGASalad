package engine.components;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

public interface BehaviorComponent {
	public void addBehavior(Object identifier, Consumer con);
	public void addBehavior(Object identifier, BiConsumer bic);
}
