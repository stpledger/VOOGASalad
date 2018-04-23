package authoring.factories;

import java.util.function.Consumer;

public interface ClickableElement extends Element{
	
	public void handleConsumer(Consumer<Void> event);

}
