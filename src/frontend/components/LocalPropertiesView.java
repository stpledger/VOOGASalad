package frontend.components;
/**
 * Opens up the Local Properties window so that an editor can edit certain features of an entity,
 * such as poison, health, velocity, etc. 
 * @author Collin Brown (cdb55)
 * @author Dylan Powers (ddp19)
 *
 */
public class LocalPropertiesView extends PropertiesView {
	
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
