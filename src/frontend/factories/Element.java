package frontend.factories;

import java.util.function.Consumer;

public interface Element {
	
	public void addEvent(Consumer<Void> event);

}
