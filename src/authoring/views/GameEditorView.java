package authoring.views;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Consumer;
import javax.imageio.ImageIO;

import data.DataRead;
import javafx.application.Platform;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.image.Image;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.FileChooser.ExtensionFilter;
import GamePlayer.Main;
import authoring.entities.Entity;
import authoring.factories.Toolbar;
import authoring.gamestate.*;
import authoring.views.properties.GlobalPropertiesView;
import authoring.views.properties.HUDPropertiesView;
import engine.components.Component;
import engine.components.EntityType;
import engine.components.Position;
import engine.components.Sprite;
import data.DataWrite;

/**
 * 
 * @author Collin Brown(cdb55)
 *
 */
public class GameEditorView extends BorderPane {
	//private static final String GAME_FILE_EXTENSION = "*.xml";
	private ArrayList<Tab> levelTabsList;
	private Object[] clipboard;
	private GameState state;
	private TabPane tabPane;
	private Toolbar toolbar;
	private File gameFile;
	private int nextEntityID  = 0;

	private final int BLOCK_DEFAULT_WIDTH = 50;
	
	/**
	 * Default Constructor creates a Borderpane with a toolbar in the top, tabPane in the center, and a gamestate object
	 */
	public GameEditorView() {
		super();
		this.toolbar = new Toolbar("GameEditor", buildToolbarFunctionMap());
		this.setTop(toolbar);
		this.tabPane = new TabPane();
		this.levelTabsList = new ArrayList<Tab>();
		this.state = new GameState();
		this.setCenter(tabPane);
		addLevel();
	}

	/**
	 * 
	 * @return Map<String, Consumer> names and consumers to be added to the toolbar
	 */
	private Map<String,Consumer> buildToolbarFunctionMap(){
		//Consumers for the toolbar
		Consumer<?> newGame = (e)->{newGameMethod(); addLevel();}; 
		Consumer<?> loadGame = (e)->{ loadGameMethod();};
		Consumer<?> newLevel = (e)->{addLevel();};
		Consumer<?> saveGame = (e)-> { saveGameMethod(); };
		Consumer<?> showSettings = (e)->{showSettingsMethod();};
		Consumer<?> hudSettings = (e) -> { hudSettingsMethod();};
		Consumer<?> play = (e)->{playMethod();};
		
		
		Map<String, Consumer> consumerMap = new HashMap<String, Consumer>();
		consumerMap.put("newGame", newGame);
		consumerMap.put("loadGame", loadGame);
		consumerMap.put("saveGame", saveGame);
		consumerMap.put("addLevel", newLevel);
		consumerMap.put("showSettings", showSettings);
		consumerMap.put("hudSettings", hudSettings);
		consumerMap.put("play", play);
		return consumerMap;
	}



	/**
	 * called whenever there is any change to the tabslist
	 * TODO: This might not even need to be a lambda but gotta flex for Duval.
	 */
	Consumer<List<Tab>> updateTabs = (l) -> {
		for(Tab t : l) {
			t.setText("Level " + (l.indexOf(t)+1));
		}
	};

	/**
	 * Creates a new LevelView
	 */
	public void addLevel(){
		levelTabsList.add(new Tab());
		Tab t = levelTabsList.get(levelTabsList.size()-1);
		t.setText("Level " + (levelTabsList.indexOf(t)+1));
		
		Level level = new Level(levelTabsList.indexOf(t)+1);
		state.addLevel(level);
		LevelView levelView = new LevelView(level, levelTabsList.indexOf(t)+1, addEntity);
		
		t.setContent(levelView);
		t.setOnClosed(e -> {
			levelTabsList.remove(t);
			updateTabs.accept(levelTabsList);
		});
		tabPane.getTabs().add(t);
	}

	/**
	 * Creates a new LevelView based on an existing LevelView
	 * @param l Level to be added to current view
	 * @return LevelView object just instantiated
	 */
	public LevelView loadLevel(Level l){
		levelTabsList.add(new Tab());
		Tab t = levelTabsList.get(levelTabsList.size()-1);
		t.setText("Level " + (levelTabsList.indexOf(t)+1));
		
		Level level = l;
		state.addLevel(level);
		LevelView levelView = new LevelView(level, levelTabsList.indexOf(t)+1, addEntity);
		
		t.setContent(levelView);
		t.setOnClosed(e -> {
			levelTabsList.remove(t);
			updateTabs.accept(levelTabsList);
		});
		
		tabPane.getTabs().add(t);
		return levelView;
	}

	private void newGameMethod() {
		state = new GameState();
		levelTabsList = new ArrayList<Tab>();
		tabPane.getTabs().clear();
	}

	/**
	 * Opens a new thread to play the game on
	 */
	private void playMethod() {
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				try { 
					new Main().start(new Stage());
				} catch (Exception e) { 
					System.out.println("Error Running Game");
				}
			}	
		});	
	}
	
	/**
	 * Shows the HUD Settings Menu
	 */
	private void hudSettingsMethod() {
		ArrayList<Level> levelArray = new ArrayList<Level>();
		for(Tab t: levelTabsList) {
			levelArray.add(((LevelView) t.getContent()).getLevel());
		}
		HUDPropertiesView HPV = new HUDPropertiesView(levelArray);
		HPV.open();	
	}

	/**
	 * Shows the GlobalPropertiesView Panel
	 */
	private void showSettingsMethod() {
		ArrayList<Level> levelArray = new ArrayList<Level>();
		for(Tab t: levelTabsList) {
			levelArray.add(((LevelView) t.getContent()).getLevel());
		}
		GlobalPropertiesView GPV = new GlobalPropertiesView(levelArray);
		GPV.open();
	}

	/**
	 * Saves the Current instance of the game
	 */
	private void saveGameMethod() {
		DataWrite dr = new DataWrite();
		try {
			dr.saveFile(this.state, "MyFirstGame");
		} catch (Exception ex) {
			// TODO better exception
			ex.printStackTrace();
		}
	}

	/**
	 * Opens a file chooser and loads the chosen file to the gameAuthoringEnvironment
	 */
	private void loadGameMethod() {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Open Image File");
		gameFile = fileChooser.showOpenDialog(new Stage());
		DataRead dr = new DataRead();
		Map<Level, Map<Integer, List<Component>>> authorData = dr.loadAuthorFile(gameFile);
		newGameMethod();
		for(Level level : authorData.keySet()) {
			LevelView levelView = loadLevel(level);
			Map<Integer, List<Component>> entities = authorData.get(level);
			for(Integer entityID : entities.keySet()) {
				List<Component> componentList = entities.get(entityID);
				try {
					Entity entity = createEntityFromComponentList(entityID, componentList);
					levelView.addEntity(entity);
				} catch (Exception e) {
					System.out.println("Error creating entity: " + entityID);
				}
			}

		}
	}

	/**
	 * Creates a component with the ID and a List of components
	 * @return entity 
	 */
	private Entity createEntityFromComponentList(Integer entityID, List<Component> componentList) throws Exception{
		Entity entity = null;
		for(Component c : componentList) {
			if(c.getKey().equals("EntityType")) {
				String entityType = ((EntityType)c).getType();
				Class<?> entityTypeClass = Class.forName("authoring.entities." + entityType);
				entity = createEntityFromType(entityTypeClass, entityID);	
			}
		}
		for(Component c : componentList) {
			entity.add(c);
			if(c.getKey().equals("Sprite")) {
				Image image = DataRead.loadImage(((Sprite) c).getName());	
				entity.setImage(image);
			} else if(c.getKey().equals("Position")) {
				Position p = (Position) c;
				entity.setX(p.getXPos());
				entity.setY(p.getYPos());
			}
		}
		return entity;
	}

	/**
	 * Set the element in the clipboard
	 * @param o
	 */
	public void setClipboard(Object o) {
		clipboard = (Object[]) o;
	}

	/**
	 * Consumer to handle adding a new entity to the current level
	 */
	private Consumer<MouseEvent> addEntity = (mouseEvent) -> {
		Object[] clipboardCopy =  clipboard; //Create a copy of the clipboard
		Entity entity = null;
		try {
			//Get the class of the entityType
			Class<?> entityType = (Class<?>) clipboardCopy[0]; 
			entity = createEntityFromType(entityType, nextEntityID);
			//Set the position
			entity.setPosition(mouseEvent.getX() - entity.getFitWidth() / 2, mouseEvent.getY() - this.tabPane.getTabMaxHeight() - entity.getFitHeight() / 2);

			//Add all the components
			Map<Class, Object[]> entityComponents = (Map<Class, Object[]>) clipboardCopy[1]; 
			entity = addEntityComponentsFromMap(entity, entityComponents);
			((LevelView) tabPane.getSelectionModel().getSelectedItem().getContent()).addEntity(entity);
			nextEntityID++; //Increment id's by one
		} catch (Exception e) {
			System.out.println("Error adding entity");
		}
	};

	/**
	 * Creates an Entity object from the Class representing its type and an ID
	 * @param entityType class representing the type of entity to make
	 * @param ID int representing the ID of an entity
	 * @return
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 */
	private Entity createEntityFromType(Class<?> entityType, int ID) throws NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Constructor<?> entityConstructor = entityType.getConstructor(int.class);
		Entity entity = (Entity) entityConstructor.newInstance(nextEntityID); 
		entity.setFitWidth(BLOCK_DEFAULT_WIDTH);
		entity.setFitHeight(BLOCK_DEFAULT_WIDTH);
		return entity;
	}
	/**
	 * Takes in a Map<Class, Object[]> and creates components in an entity
	 * @param e Entity object to recieve the new components
	 * @param entityComponents Map<Class, Object[]> which represents the components to be built
	 * @return Entity with components added by this class
	 * @throws Exception
	 */
	private Entity addEntityComponentsFromMap(Entity e, Map<Class, Object[]> entityComponents) throws Exception {
		ArrayList<Component> componentArrayList = new ArrayList<Component>();
		Entity entity = e;
		
		for(Class<?> componentClass :entityComponents.keySet()) { 
			Constructor<?> componentConstructor = componentClass.getDeclaredConstructors()[0];
			ArrayList<Object> tempComponentAttributeArray = new ArrayList<Object>() {{ 
				this.add(nextEntityID);
				this.addAll(Arrays.asList(entityComponents.get(componentClass))); 
			}};

			Object[] componentArguments = tempComponentAttributeArray.toArray();
			componentArrayList.add((Component) componentConstructor.newInstance(componentArguments));
			
			if(componentClass.equals(Sprite.class)) {
				Image image = DataRead.loadImage((String) entityComponents.get(componentClass)[0]);
				entity.setImage(image);
			}
		}
		entity.addAll(componentArrayList);
		nextEntityID++;
		return entity;
	}

}
