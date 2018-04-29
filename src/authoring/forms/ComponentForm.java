package authoring.forms;

import java.util.Properties;

import javafx.geometry.Pos;

public interface ComponentForm{
	
	public Object buildComponent();

	public String getName();
	
	public void setLanguage(Properties language);

	public void setAlignment(Pos center);

}
