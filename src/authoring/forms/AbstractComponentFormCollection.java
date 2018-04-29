package authoring.forms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.logging.Logger;

import authoring.entities.data.PackageExplorer;
import authoring.factories.ClickElementType;
import authoring.factories.ElementFactory;
import authoring.views.popups.SelectionBox;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

public abstract class AbstractComponentFormCollection extends GridPane {
	private final static String PROPERTIES_PACKAGE = "resources.menus.Entity/";
	private final static String ENTITIES_PACKAGE = "engine.components.";
	protected Properties language = new Properties();

	ElementFactory eFactory = new ElementFactory();

	private final static Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

	private List<ComponentForm> activeForms = new ArrayList<>();
	private List<String> exceptions;

	protected Button addComponentButton;

	public AbstractComponentFormCollection(String[] newExceptions) {
		this();
		getExceptions().addAll(Arrays.asList(newExceptions));
	}

	public AbstractComponentFormCollection() {
		this.getStyleClass().add("component-form");
		setExceptions(new ArrayList<>());
	}

	protected void createAddComponentButton(int row) {
		try {
			Button addComponent = (Button) eFactory.buildClickElement(ClickElementType.Button,"AddComponent", onClick->{
				String[] options = PackageExplorer.getElementsInPackage(ENTITIES_PACKAGE, ".class","Component");
				String[] exceptions = new String[] {"Component","Conditional","DataComponent", "ReadDataComponent", "ReadStringComponent","StringComponent", "SingleDataComponent", "SingleStringComponent"};
				SelectionBox  selectionBox = new SelectionBox(options, exceptions, us -> {addComponent(us);});
				selectionBox.setLanguage(language);
			});
			addComponentButton = addComponent;
			this.add(addComponent, 0, row);
			this.setAlignment(Pos.CENTER);
			this.getParent().getScene().getWindow().sizeToScene();
		} catch (Exception e) {
			LOGGER.log(java.util.logging.Level.SEVERE, e.toString(), e);
		}
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

	public abstract void addComponent(Object componentName);



}
