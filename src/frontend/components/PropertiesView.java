package frontend.components;

import java.util.Map;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;


/**
 * 
 * @author Hemanth Yakkali (hy115)
 *
 */
public abstract class PropertiesView {
	
	private final int GRID_SEPARATION = 10;
	private GridPane root;
	
	/**
	 * Initialize the root of this window as a {@code GridPane}.
	 */
	public PropertiesView() {
		root = new GridPane();
		root.setAlignment(Pos.CENTER);
		root.setHgap(GRID_SEPARATION);
		root.setVgap(GRID_SEPARATION);
	}
	
	/**
	 * Opens the Property Editor window.
	 */
	protected void open() {
		Stage stage = new Stage();
		stage.setTitle(this.title());
		stage.setScene(new Scene(root, 450, 450));
		stage.show();
		Label componentLabel = new Label("Component: ");
		root.add(componentLabel, 0, 0);
		NumberField number = new NumberField();
		root.add(number, 1, 0);
	}
	
	/**
	 * Fills the window with the appropriate names and fields.
	 * @param fields a map with component names that map {@code true} if the box should be strictly numeric, and {@code false} if not.
	 */
	protected void fill(Map<String, Boolean> fields) {
		int currentRow = 0;
		for (String componentName : fields.keySet()) {
			Label componentLabel = new Label(componentName);
			root.add(componentLabel, 0, currentRow);
			if (fields.get(componentName)) { 
				// Text field should only accept numeric values
				NumberField number = new NumberField();
				root.add(number, 0, currentRow++);
			} else {
				TextField text = new TextField();
				root.add(text, 0, currentRow++);
			}
		}
	}
	
	/**
	 * Get the title that this window should display.
	 * @return the title of the window.
	 */
	protected abstract String title();
	
	/**
	 * Nested class to created a text field that only accepts numbers.
	 * @param args
	 */
	class NumberField extends TextField {
		@Override
		public void replaceText(int start, int end, String text) {
			if (isNumber(text))
				super.replaceText(start, end, text);
		}
		
		@Override
		public void replaceSelection(String text) {
			if (isNumber(text))
				super.replaceSelection(text);
		}
		
		private boolean isNumber(String input) {
			return input.matches("[0-9]*");
		}
	}
	
	
}
