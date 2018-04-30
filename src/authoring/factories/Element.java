package authoring.factories;

import authoring.languages.AuthoringLanguage;

/**
 * 
 * @author Hemanth Yakkali(hy115)
 *
 */
public interface Element extends AuthoringLanguage{

	public void handleText(String text);
	
}
