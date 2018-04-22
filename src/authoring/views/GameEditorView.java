package authoring.views;

import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.logging.Logger;

import data.DataRead;
import javafx.application.Platform;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import GamePlayer.Main;
import authoring.entities.Entity;
import authoring.factories.Toolbar;
import authoring.gamestate.GameState;
import authoring.gamestate.Level;
import authoring.views.properties.GlobalPropertiesView;
import authoring.views.properties.HUDPropertiesView;
import authoring.views.properties.LevelPropertiesView;
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
	private GameState state;
	private TabPane tabPane;
	private Toolbar toolbar;
	private int nextEntityID  = 0;
	
	private final static Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
	
	private Consumer<MouseEvent> addEntity = mouseEvent -> { addEntityMethod(mouseEvent);};
	private Consumer<List<Tab>> updateTabs = tabList -> { updateTabsMethod(tabList); };
	private static final int BLOCK_DEFAULT_WIDTH = 50;
	
	/**
	 * Default Constructor creates a Borderpane with a toolbar in the top, tabPane in the center, and a gamestate object
	 */
	public GameEditorView() {
		super();
		this.toolbar = new Toolbar("GameEditor", buildToolbarFunctionMap());
		this.setTop(toolbar);
		this.tabPane = new TabPane();
		this.levelTabsList = new ArrayList<>();
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
		Consumer<?> newGame = e->{newGameMethod(); addLevel();}; 
		Consumer<?> loadGame = e->{ loadGameMethod();};
		Consumer<?> saveGame = e-> { saveGameMethod(); };
		Consumer<?> showSettings = e->{showSettingsMethod();};
		Consumer<?> hudSettings = e -> { hudSettingsMethod();};
		Consumer<?> play = e->{playMethod();};
		Consumer<?> newLevel = e->{addLevel();};
		Consumer<?> levelSettings = e->{showLevelSettings();};
		
		
		Map<String, Consumer> consumerMap = new HashMap<>();
		consumerMap.put("newGame", newGame);
		consumerMap.put("loadGame", loadGame);
		consumerMap.put("saveGame", saveGame);
		consumerMap.put("showSettings", showSettings);
		consumerMap.put("hudSettings", hudSettings);
		consumerMap.put("play", play);
		consumerMap.put("addLevel", newLevel);
		consumerMap.put("levelSettings", levelSettings);
		return consumerMap;
	}
	
	/**
	 * Update the numbers of the level tabs
	 * @param tabList
	 */
	private void updateTabsMethod(List<Tab> tabList) {
		for(Tab t : tabList) {
			t.setText("Level " + (tabList.indexOf(t)+1));
		}
	}

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
		levelTabsList = new ArrayList<>();
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
					LOGGER.severe(e.getMessage());
				}
			}	
		});	
	}
	
	/**
	 * Shows the HUD Settings Menu
	 */
	private void hudSettingsMethod() {
		ArrayList<Level> levelArray = new ArrayList<>();
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
			LOGGER.severe(ex.getMessage());
		}
	}

	/**
	 * Opens a file chooser and loads the chosen file to the gameAuthoringEnvironment
	 */
	private void loadGameMethod() {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Open Image File");
		File gameFile = fileChooser.showOpenDialog(new Stage());
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
					LOGGER.severe(e.getMessage());
				}
			}
		}
	}
	/**
	 * Opens the levelPropertiesView
	 */
	private void showLevelSettings() {
		Level level = ((LevelView) this.tabPane.getSelectionModel().getSelectedItem().getContent()).getLevel();
		LevelPropertiesView lView = new LevelPropertiesView(level, level.getLevelNum());
		lView.open();
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
	 * Adds an entity to the LevelView
	 * @param mouseEvent
	 */
	private void addEntityMethod(MouseEvent mouseEvent) {
		Entity entity = null;
		try {
			entity.setPosition(mouseEvent.getX() - entity.getFitWidth() / 2, mouseEvent.getY() - this.tabPane.getTabMaxHeight() - entity.getFitHeight() / 2);
			((LevelView) tabPane.getSelectionModel().getSelectedItem().getContent()).addEntity(entity);
			nextEntityID++;
		} catch (Exception e) {
			System.out.println("Error creating new entity");
			LOGGER.severe(e.getMessage());
		}
	}

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
		ArrayList<Component> componentArrayList = new ArrayList<>();
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
