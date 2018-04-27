package authoring.views;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

import authoring.entities.Entity;
import authoring.entities.data.EntityLoader;
import authoring.entities.data.PackageExplorer;
import authoring.factories.Toolbar;
import data.DataRead;
import engine.components.Sprite;

import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

public class EntityView extends BorderPane implements AuthoringPane {
	public final static String ENTITIES_PACKAGE_NAME = "authoring/entities";
	public final static  int ENITITY_VIEW_WIDTH = 300;
	private ArrayList<String> tabsList = new ArrayList<>();
	private ArrayList<String> entityTypes = new ArrayList<>();
	private TabPane tabPane = new TabPane();
	

	public EntityView() {
		super();
		this.setPrefWidth(ENITITY_VIEW_WIDTH);
		this.getStyleClass().add("entity-view");
		this.setTop(new Toolbar("Entities", buildToolbarConsumerMap()));
		this.setCenter(tabPane);
		entityTypes.addAll(Arrays.asList(PackageExplorer.getElementsInPackage(ENTITIES_PACKAGE_NAME)));
	}

	/**
	 * Builds the consumers for the toolbar
	 * @return Map<String, Consumer> 
	 */
	private Map<String, Consumer> buildToolbarConsumerMap() {
		Map<String, Consumer> consumerMap = new HashMap<>();
		BiConsumer<String, Map<Class, Object[]>> onClose = (entityType,componentAttributes) -> {saveEntity(entityType, componentAttributes);};
		Consumer<?> newEntity = e -> {
			EntityBuilderView entityBuilderView = new EntityBuilderView(entityTypes, onClose);
		};
		Consumer<?> loadEntity = e -> {loadEntity();};
		consumerMap.put("newEntity", newEntity);
		consumerMap.put("loadEntity", loadEntity);
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
	 * Called when a EntityBuilderView is closed
	 * @param entityType String representing the type of entity that this object represents
	 * @param componentAttributes Map<Class, Object[]> representing the class of a component and an object[] representing all the arguments the component takes in
	 */
	public void saveEntity(String entityType, Map<Class, Object[]> componentAttributes) {

		Image image = DataRead.loadImage((String) componentAttributes.get(Sprite.class)[0]);

		if(tabsList.isEmpty() || !tabsList.contains(entityType)) { 
			addTab(entityType);
			tabsList.add(entityType);
		}   

		for(Tab tab : tabPane.getTabs()) {
			if(tab.getText().equals(entityType)) {
				((EntityTab) tab).addNewEntity(entityType, componentAttributes);
			}
		}
	}
	
	public void loadEntity() {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Open Entity File");
		File entityFile = fileChooser.showOpenDialog(this.getScene().getWindow());
		EntityLoader entityLoader = new EntityLoader();
		//TODO: Make this load an entity
		
	}

	@Override
	public void setLanguage(Properties language) {
		((Toolbar) this.getTop()).setLanguage(language);
		for(Tab t : tabPane.getTabs()) {
			t.setText(language.getProperty(t.getId(),t.getId()));
		}
	}

}
