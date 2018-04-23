package authoring.factories;

import java.util.function.Consumer;

/**
 * 
 * @author Hemanth Yakkali
 *
 */
public interface ClickableElement extends Element{
	
	public void handleConsumer(Consumer<Void> event);

}
