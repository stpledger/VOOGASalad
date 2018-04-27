package authoring.components;

import java.util.Properties;

public interface ComponentForm {
	
	public Object buildComponent();

	public String getName();
	
	public void setLanguage(Properties language);

}
