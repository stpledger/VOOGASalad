package authoring.factories;

import java.util.Properties;

/**
 * 
 * @author Hemanth Yakkali(hy115)
 * @author Collin Brown(cdb55)
 *
 */
public interface Element {

	public void handleText(String text);
	
	public void setLanguage(Properties language);

}
