package authoring.forms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.function.Consumer;
import java.util.logging.Logger;

import authoring.entities.data.PackageExplorer;
import authoring.factories.ClickElementType;
import authoring.factories.ElementFactory;
import authoring.views.popups.SelectionBox;
import javafx.geometry.Pos;
import javafx.scene.Node;
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
	
	protected int currentRow;
	protected int entityID;
	
	protected Button addComponentButton;
	protected Button saveButton;
	
	private Class<?> componentFormType;
	
	private Consumer onSave;

	public AbstractComponentFormCollection(String[] newExceptions, Consumer c, Class cType) {
		this(cType);
		getExceptions().addAll(Arrays.asList(newExceptions));
		onSave = c;
		
	}

	public AbstractComponentFormCollection(Class cType) {
		componentFormType = cType;
		this.getStyleClass().add("component-form");
		setExceptions(new ArrayList<>());
	}
	
	protected void createAddComponentButton(int row) {
		try {
			addComponentButton = (Button) eFactory.buildClickElement(ClickElementType.Button,"AddComponent", onClick->{
				String[] options = PackageExplorer.getElementsInPackage(ENTITIES_PACKAGE, ".class","Component");
				String[] exceptions = new String[] {"Component","Conditional","DataComponent", "ReadDataComponent", "ReadStringComponent","StringComponent", "SingleDataComponent", "SingleStringComponent"};
				SelectionBox  selectionBox = new SelectionBox(options, exceptions, us -> {addComponent(us);});
				selectionBox.setLanguage(language);
			});
			this.add(addComponentButton, 0, row);
			this.setAlignment(Pos.CENTER);
			this.getParent().getScene().getWindow().sizeToScene();
		} catch (Exception e) {
			 LOGGER.log(java.util.logging.Level.SEVERE, e.toString(), e);
		}
	}
	
	protected void createSaveButton(int row) {
		try {
			saveButton = (Button) eFactory.buildClickElement(ClickElementType.Button, "Save", onClick->{
				onSave.accept(activeForms);
			});
			this.add(saveButton, 0, row);
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
	
	public void setSaveConsumer(Consumer onSave) {
		saveButton.setOnMouseClicked(e->{
			onSave.accept(this.getActiveForms());
		});
	}
	
	public void fill(String entityType) {
		try {
		currentRow = 0;
		this.getChildren().clear();
		ArrayList<ComponentForm> newActiveForms = new ArrayList<>();
		for (String property : ResourceBundle.getBundle(getPropertiesPackage() + entityType).keySet()) {
			if(!getExceptions().contains(property)) {
				ComponentForm cf = null;
				if(componentFormType.equals(PropertiesComponentForm.class)) {
					cf = (ComponentForm) componentFormType.getConstructors()[0].newInstance(entityID, ((String) property));
				} else {
					cf = (ComponentForm) componentFormType.getConstructors()[0].newInstance(entityID, ((String) property));
				}
				cf.setAlignment(Pos.CENTER);
				newActiveForms.add(cf);
				this.add((Node) cf, 0, currentRow);
				currentRow++;
		
			}
		}
		this.setActiveForms(newActiveForms);
		this.createAddComponentButton(currentRow);
		currentRow++;
		this.createSaveButton(currentRow);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void addComponent(Object componentName) {
		try {
			ArrayList<ComponentForm> newActiveForms = (ArrayList<ComponentForm>) this.getActiveForms();
			this.getChildren().remove(addComponentButton);
			this.getChildren().remove(saveButton);
			ComponentForm cf = null;
			if(componentFormType.equals(PropertiesComponentForm.class)) {
				cf = (ComponentForm) componentFormType.getConstructors()[0].newInstance(entityID, ((String) componentName));
			} else {
				cf = (ComponentForm) componentFormType.getConstructors()[0].newInstance(((String) componentName));
			}
			cf.setAlignment(Pos.CENTER);
			this.add((Node) cf, 0, currentRow);
			cf.setLanguage(language);
			newActiveForms.add(cf);
			this.setActiveForms(newActiveForms);
			currentRow++;
			this.createAddComponentButton(currentRow);
			currentRow++;
			this.createSaveButton(currentRow);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}



}
