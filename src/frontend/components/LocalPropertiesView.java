package frontend.components;

import java.util.List;
<<<<<<< HEAD

=======
import java.util.ResourceBundle;
>>>>>>> 7665aabcf6be02d1c7026b4b1149ef1e0c0e79d4
import javafx.scene.control.Label;

/**
 * Opens up the Local Properties window so that an editor can edit certain features of an entity,
 * such as poison, health, velocity, etc. 
 * @author Collin Brown (cdb55)
 * @author Dylan Powers (ddp19)
 *
 */
public class LocalPropertiesView extends PropertiesView {
	
	private final String PROPERTIES_PACKAGE = "resources/components";
	private int entityNumber;
	
	/**
	 * Initialize the object with a given broadcast method
	 * @param broadcast the broadcast to be added
	 */
	public LocalPropertiesView(int entityNumber) {
		super();
		this.entityNumber = entityNumber;
	}
	
	/**
	 * Fills the window with the appropriate text boxes and listeners so that the broadcast can tell the highest level that something has changed.
	 */
	@Override
	protected void fill() {
		int currentRow = 0;
		for (String property : ResourceBundle.getBundle(PROPERTIES_PACKAGE).keySet()) {
			Label componentLabel = new Label(property);
			this.getRoot().add(componentLabel, 0, currentRow);
			NumberField number = new NumberField();
			this.getRoot().add(number, 1, currentRow++);
		}
	}
	
	/**
	 * Gets the title for the window.
	 * @return the title for this window.
	 */
	@Override
	public String title() {
		return String.format("Entity %d Local Properties", this.entityNumber);
	}

	
}
