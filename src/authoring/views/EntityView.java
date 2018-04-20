package authoring.views;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

import javax.imageio.ImageIO;

import authoring.factories.Toolbar;

//import com.sun.beans.finder.ClassFinder;

import data.DataRead;
import engine.components.Sprite;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;

public class EntityView extends BorderPane {
	public final static  int ENITITY_VIEW_WIDTH = 300;
	private ArrayList<String> tabsList = new ArrayList<String>();
	private ArrayList<String> entityTypes = new ArrayList<String>();
	private TabPane tabPane = new TabPane();

	public EntityView() {
		super();
		this.setPrefWidth(ENITITY_VIEW_WIDTH);
		this.getStyleClass().add("entity-view");
		this.setTop(new Toolbar("Entities", buildToolbarConsumerMap()));
		this.setCenter(tabPane);
		entityTypes.addAll(Arrays.asList(getEntitiesInEntitiesPackage()));
	}
	
	/**
	 * Builds the consumers for the toolbar
	 * @return Map<String, Consumer> 
	 */
	private Map<String, Consumer> buildToolbarConsumerMap() {
		Map<String, Consumer> consumerMap = new HashMap<String,Consumer>();
		BiConsumer<String, Map<Class, Object[]>> onClose = (entityType,componentAttributes) -> {saveEntity(entityType, componentAttributes);};
		Consumer newEntity = (e) -> {
			EntityBuilderView entityBuilderView = new EntityBuilderView(entityTypes, onClose);
		};
		Consumer saveEntities = (e) -> {System.out.println("Save Entites!");};
		Consumer loadEntities = (e)->{System.out.println("Load Entitites!");};
		consumerMap.put("newEntity", newEntity);
		consumerMap.put("saveEntities", saveEntities);
		consumerMap.put("loadEntities", loadEntities);
		return consumerMap;
	}
	
	/**
	 * Adds a new tab
	 * @param type
	 */
	private void addTab(String type) {
		EntityTab temp = new EntityTab(type, ENITITY_VIEW_WIDTH);
		tabPane.getTabs().add(temp);
	}

	/**
	 * Opens the window to delete an entity
	 */
	public void deleteEntity() {
	}
	/**
	 * Opens the window to edit an entity
	 */
	public void editEntity() {

	}
	/**
	 * Called when a EntityBuilderView is closed
	 * @param entityType String representing the type of entity that this object represents
	 * @param componentAttributes Map<Class, Object[]> representing the class of a component and an object[] representing all the arguments the component takes in
	 */
	public void saveEntity(String entityType, Map<Class, Object[]> componentAttributes) {
		//Turn the imageFile into a usableImage
		Image image = DataRead.loadImage((String) componentAttributes.get(Sprite.class)[0]);

		//Check to see if a tab exists for the type
		if(tabsList.isEmpty() || !tabsList.contains(entityType)) { 
			addTab(entityType);
			tabsList.add(entityType);
		}   
		//Add the entityBox
		for(Tab tab : tabPane.getTabs()) {
			if(tab.getText().equals(entityType)) {
				((EntityTab) tab).addNewEntity(entityType, componentAttributes);
			}
		}
	}

	/**
	 * Gets all of the class names from a given package. Useful when determining which properties can be changed.
	 * @return a String array of classes from a given package
	 */
	protected String[] getEntitiesInEntitiesPackage() {
		String pckgName = "authoring/entities";
		ClassLoader cld = Thread.currentThread().getContextClassLoader();
		if (cld == null) {
			throw new IllegalStateException("Can't get class loader.");
		}

		URL resource = cld.getResource(pckgName.replace('.', '/'));
		if (resource == null) {
			throw new RuntimeException("Package " + pckgName + " not found on classpath.");
		}
		File directory = new File(resource.getFile());
		if (!directory.exists()) {
			throw new IllegalArgumentException("Could not get directory resource for package " + pckgName + ".");
		}
		List<String> classes = new ArrayList<String>();
		for (String filename : directory.list()) {
			if (filename.endsWith(".class") && !filename.startsWith("Entity")) { //Check to make sure its a class file and not the superclass
				String classname = buildClassname(pckgName, filename);
				String clazz = classname.replace(".java", "");
				// Strip everything except for the word following the last period (the actual class name)
				classes.add(clazz.substring(clazz.lastIndexOf(".") + 1));
			}
		}
		return classes.toArray(new String[classes.size()]);
	}

	/**
	 * Builds the class name to fully represent a given class
	 * @param pckgName the package to look for the class ine
	 * @param fileName the name of the class file
	 * @return a String representing the fully-qualified class name
	 */
	private String buildClassname(String pckgName, String fileName) {
		String className = pckgName + '.' + fileName.replace(".class", "");
		return className;
	}

}
