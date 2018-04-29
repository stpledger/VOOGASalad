package authoring.views.popups;

import java.util.Properties;
import java.util.function.Consumer;

/**
 * 
 * @author Collin Brown(cdb55)
 *
 */
public interface PopUp {

	public void show();

	public void onClose(Consumer<?> consumer);

	public void setLanguage(Properties lang);
}
