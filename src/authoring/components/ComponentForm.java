package authoring.components;

import java.util.Properties;

public interface ComponentForm {
	
	Object buildComponent();

	String getName();
	
	void setLanguage(Properties language);

}
