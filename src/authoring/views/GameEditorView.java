package authoring.views;

import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
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
import authoring.factories.ElementFactory;
import authoring.factories.ElementType;
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
public class GameEditorView extends BorderPane implements AuthoringPane{

	private ArrayList<Tab> levelTabsList;
	private GameState state;
	private TabPane tabPane;
	private Toolbar toolbar;
	private ElementFactory eFactory;
	private int nextEntityID  = 0;
	private final static Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
	private Consumer<List<Tab>> updateTabs = tabList -> { updateTabsMethod(tabList); };
	private static final int BLOCK_DEFAULT_WIDTH = 50;
	private Consumer<String> setMainViewLang;
	
	Properties language = new Properties();

	/**
	 * Default Constructor creates a Borderpane with a toolbar in the top, tabPane in the center, and a gamestate object
	 */
	public GameEditorView(Consumer<String> setLanguage) {
		super();
		setMainViewLang = setLanguage;
		this.toolbar = new Toolbar("GameEditor", buildToolbarFunctionMap());
		this.setTop(toolbar);
		this.tabPane = new TabPane();
		this.levelTabsList = new ArrayList<>();
		this.state = new GameState();
		this.setCenter(tabPane);
		this.eFactory = new ElementFactory();
		addLevel();
	}

	/**
	 * 
	 * @return Map<String, Consumer> names and consumers to be added to the toolbar
	 */
	private Map<String,Consumer> buildToolbarFunctionMap(){

		Map<String, Consumer> consumerMap = new HashMap<>();
		consumerMap.put("newGame", e->{newGameMethod(); addLevel();});
		consumerMap.put("loadGame",  e->{ loadGameMethod();});
		consumerMap.put("saveGame", e-> { saveGameMethod(); });
		consumerMap.put("showSettings", e->{showSettingsMethod();});
		consumerMap.put("hudSettings",e -> { hudSettingsMethod();});
		consumerMap.put("play", e->{playMethod();});
		consumerMap.put("addLevel", e->{addLevel();});
		consumerMap.put("levelSettings", e->{showLevelSettings();});
		consumerMap.put("changeLanguage", e->{setMainViewLanguage();});
		return consumerMap;
	}

	private void setMainViewLanguage() {
		setMainViewLang.accept("cebuano");
		
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
		try {
			Tab t = (Tab) this.eFactory.buildElement(ElementType.Tab, "Level " + (levelTabsList.size()+1));
			levelTabsList.add(t);
			Level level = new Level(levelTabsList.indexOf(t)+1);
			state.addLevel(level);
			LevelView levelView = new LevelView(level, levelTabsList.indexOf(t)+1, e -> {addEntityMethod(e);});
			t.setContent(levelView);
			t.setOnClosed(e -> {
				levelTabsList.remove(t);
				updateTabs.accept(levelTabsList);
			});
			((LevelView) t.getContent()).setLanguage(language);
			tabPane.getTabs().add(t);
		} catch (Exception e) {
			LOGGER.log(java.util.logging.Level.SEVERE, e.getMessage(), e);
		}
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
		LevelView levelView = new LevelView(level, levelTabsList.indexOf(t)+1, e -> {addEntityMethod(e);});

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
					LOGGER.log(java.util.logging.Level.SEVERE, e.getMessage(), e);
				}
			}	
		});	
	}

	/**
	 * Shows the HUD Settings Menu
	 */
	private void hudSettingsMethod() {
		List<Level> levelArray = new ArrayList<>();
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
		List<Level> levelArray = new ArrayList<>();
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
		try {
			DataWrite.saveFile(this.state, "MyFirstGame");
		} catch (Exception ex) {
			LOGGER.log(java.util.logging.Level.SEVERE, ex.getMessage(), ex);
		}
	}

	/**
	 * Opens a file chooser and loads the chosen file to the gameAuthoringEnvironment
	 */
	private void loadGameMethod() {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Open Image File");
		File gameFile = fileChooser.showOpenDialog(new Stage());
		Map<Level, Map<Integer, List<Component>>> authorData = DataRead.loadAuthorFile(gameFile);
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
					LOGGER.log(java.util.logging.Level.SEVERE, "Error creating entity: " + entityID, e);
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
			LOGGER.log(java.util.logging.Level.SEVERE, "Error creating new entity", e);
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


	@Override
	public void setLanguage(Properties lang) {
		language = lang;
		toolbar.setLanguage(language);
		for(Tab t : tabPane.getTabs()) {
			((LevelView) t.getContent()).setLanguage(language);
		}
		
	}

}
