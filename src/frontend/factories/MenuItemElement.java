package frontend.factories;

import java.util.function.Consumer;

import javafx.scene.control.MenuItem;

public class MenuItemElement extends MenuItem implements Element{
	
	public MenuItemElement(String text) {
		this.setText(text);
	}

	@Override
	public void addEvent(Consumer<Void> event) {
		// TODO Auto-generated method stub
		
	}

}
