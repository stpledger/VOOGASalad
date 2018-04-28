package authoring.forms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import javafx.scene.layout.GridPane;

public class AbstractComponentFormCollection extends GridPane {
	private final static String PROPERTIES_PACKAGE = "resources.menus.Entity/";
	private Properties language = new Properties();
	
	private List<ComponentForm> activeForms = new ArrayList<>();
	private List<String> exceptions;

	public AbstractComponentFormCollection(String[] newExceptions) {
		this();
		getExceptions().addAll(Arrays.asList(newExceptions));
	}

	public AbstractComponentFormCollection() {
		this.getStyleClass().add("component-form");
		setExceptions(new ArrayList<>());
	}

	public List<ComponentForm> getActiveForms() {
		return activeForms;
	}

	public void setActiveForms(List<ComponentForm> activeForms) {
		this.activeForms = activeForms;
	}

	public static String getPropertiesPackage() {
		return PROPERTIES_PACKAGE;
	}

	protected List<String> getExceptions() {
		return exceptions;
	}

	public void setExceptions(List<String> exceptions) {
		this.exceptions = exceptions;
	}
	
	public void setLanguage(Properties lang) {
		this.language = lang;
		for(ComponentForm componentForm : activeForms) {
			componentForm.setLanguage(this.language);
		}
	}



}
