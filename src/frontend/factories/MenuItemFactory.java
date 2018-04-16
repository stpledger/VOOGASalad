package frontend.factories;

import java.util.function.Consumer;

import javafx.scene.control.MenuItem;

public class MenuItemFactory implements IMenuItem{

	@Override
	public MenuItem makeMenuItem(String text, Consumer<Void> event) {
		MenuItem mItem = new MenuItem(text);
		mItem.setOnAction(e->event.accept(null));
		return mItem;
	}

}
