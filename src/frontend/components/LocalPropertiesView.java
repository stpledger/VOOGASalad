package frontend.components;

import javafx.scene.control.Label;

/**
 * Opens up the Local Properties window so that an editor can edit certain features of an entity,
 * such as poison, health, velocity, etc. 
 * @author Collin Brown (cdb55)
 * @author Dylan Powers (ddp19)
 *
 */
public class LocalPropertiesView extends PropertiesView {
	
	private final String PROPERTIES_PACKAGE = "engine.components";
	private Broadcast broadcast;
	/**
	 * Initialize the object with a given broadcast method
	 * @param broadcast the broadcast to be added
	 */
	public LocalPropertiesView(Broadcast broadcast) {
		super();
		this.broadcast = broadcast;
	}
	
	/**
	 * Fills the window with the appropriate text boxes and listeners so that the broadcast can tell the highest level that something has changed.
	 */
	@Override
	protected void fill() {
		int currentRow = 0;
		for (String property : super.getClassesInPackage(PROPERTIES_PACKAGE)) {
			Label componentLabel = new Label(property);
			getRoot().add(componentLabel, 0, currentRow);
			// Text field should only accept numeric values
			NumberField number = new NumberField();
			getRoot().add(number, 1, currentRow);
			currentRow++;
		}
	}
	
	/**
	 * Gets the title for the window.
	 * @return the title for this window.
	 */
	@Override
	public String title() {
		return "Local Properties";
	}
	
}
