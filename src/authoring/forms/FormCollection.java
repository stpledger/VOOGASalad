package authoring.forms;

import java.util.List;
import java.util.function.Consumer;

public interface FormCollection {
	
	/**
	 * Populates the form
	 */
	public void fill();
	
	/**
	 * Adds a set of Exceptions to the list of possible components for this form collection
	 * @param exceptions
	 */
	public void setExceptions(String[] exceptions);
	
	/**
	 * Add a componentForm to the form collection
	 * @param componentName
	 */
	public void addComponent(Object componentName);
	
	/**
	 * Sets the action when the save button is pressed
	 * @param onSave
	 */
	public void setSaveConsumer(Consumer onSave);
	
	/**
	 * Sets the list of currently active component forms
	 * @param activeForms
	 */
	public void setActiveForms(List<ComponentForm> activeForms);
	
	/**
	 * Gets a list of the currently active component forms
	 * @return
	 */
	public List<ComponentForm> getActiveForms();
	
	/**
	 * Sets the type of component form to be built
	 */
	public void setComponentFormType();
}
