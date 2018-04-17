package frontend.components;

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
import frontend.entities.Entity;
import frontend.gamestate.*;
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
import engine.components.Component;
import engine.components.Position;
import engine.components.Sprite;
import data.DataRead;
import data.DataWrite;

/**
 * 
 * @author Collin Brown(cdb55)
 *
 */
public class GameEditorView extends BorderPane {
	//private static final String GAME_FILE_EXTENSION = "*.xml";
	private ArrayList<Tab> tabsList;
	private Object[] clipboard;
	private GameState state;
	private TabPane tabPane;
	private Toolbar toolbar;
	private File gameFile;
	private int nextID  = 0; //The next ID to be used
	boolean drag = false; 
	
	private final int BLOCK_DEFAULT_WIDTH = 50;
	/**
	 * Default Constructor
	 */
	public GameEditorView() {
		super();
		//Create a Toolbar
		toolbar = new Toolbar("GameEditor", consumerMap);
		this.setTop(toolbar);
		tabPane = new TabPane();
		tabsList = new ArrayList<Tab>();
		state = new GameState();
		addLevel(); // add the first level
		this.setCenter(tabPane);
	}
	
	//Consumers for the toolbar
	Consumer newGame = (e)->{System.out.println("New Game!");}; 
	Consumer loadGame = (e)->{ loadGameMethod();};
	Consumer newLevel = (e)->{addLevel();};
	Consumer saveGame = (e)-> { saveGameMethod(); };
	Consumer showSettings = (e)->{showSettingsMethod();};
	Consumer hudSettings = (e) -> { hudSettingsMethod();};
	Consumer play = (e)->{playMethod();};
	
	private Map<String, Consumer> consumerMap = new HashMap<String, Consumer>(){{
		this.put("newGame", newGame);
		this.put("loadGame", loadGame);
		this.put("saveGame", saveGame);
		this.put("addLevel", newLevel);
		this.put("showSettings", showSettings);
		this.put("hudSettings", hudSettings);
		this.put("play", play);
	}};
	
	
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
		tabsList.add(new Tab());
		Tab t = tabsList.get(tabsList.size()-1);
		t.setText("Level " + (tabsList.indexOf(t)+1));
		Level level = new Level(tabsList.indexOf(t)+1);
		state.addLevel(level);
		LevelView l = new LevelView(level, tabsList.indexOf(t)+1, addEntity);
		t.setContent(l);
		t.setOnClosed(e -> {
			tabsList.remove(t);
			updateTabs.accept(tabsList);
			
		});
		this.setOnDragDetected((e)->{
			
		});
		((ScrollPane) ((BorderPane) t.getContent()).getCenter()).getContent().setOnMouseReleased((e)->{
				if (e.getButton().equals(MouseButton.PRIMARY) && !drag) {
						addEntity.accept(e);
				}
				drag = false;
		}); // Add a new Entity OnClick
		((ScrollPane) ((BorderPane) t.getContent()).getCenter()).getContent().setOnDragDetected((e)->{
			drag = true;
			});// don't add new element if drag detected
		tabPane.getTabs().add(t);
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
		for(Tab t: tabsList) {
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
		for(Tab t: tabsList) {
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
		System.out.println(gameFile.getPath());
		DataRead dr = new DataRead();
		tabsList = new ArrayList<Tab>();
		Map<Level, Map<Integer, List<Component>>> authorData = dr.loadAuthorFile(gameFile);
		System.out.println(authorData.size());
		for(Entry<Level, Map<Integer, List<Component>>> level : authorData.entrySet()) {
		}
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
		ArrayList<Component> componentArrayList = new ArrayList<Component>();
		Entity entity = null;
		try {
			//Get the class of the entityType
			Class<?> entityType = (Class<?>) clipboardCopy[0]; 
			//Get Constructor for entityType
			Constructor<?> entityConstructor = entityType.getConstructor(int.class);
			//Create a new instance of the entity
			System.out.println("About to create entity with ID " + nextID);
			entity = (Entity) entityConstructor.newInstance(nextID); 
			System.out.println(entity + " created with ID " + entity.getID());
			//Set the X,Y position of the mouseEvent to the X,Y position of the object
			entity.setFitWidth(BLOCK_DEFAULT_WIDTH);
			entity.setFitHeight(BLOCK_DEFAULT_WIDTH);
			entity.setPosition(mouseEvent.getX() - entity.getFitWidth() / 2, mouseEvent.getY() - this.tabPane.getTabMaxHeight() - entity.getFitHeight() / 2);
			entity.add(new Position(nextID, entity.getX(), entity.getY()));
			//Get all of the inputs for components
			Map<Class, Object[]> entityComponents = (Map<Class, Object[]>) clipboardCopy[1]; 
			//iterate through all the properties we have for new components
			for(Class k :entityComponents.keySet()) { 
				 // get the constructor for the type of component
				Constructor<?> componentConstructor = k.getDeclaredConstructors()[0];
				//Create a temporary arraylist
				ArrayList<Object> tempArr = new ArrayList<Object>() {{ 
					 //Add the pId to the temporary arraylist
					this.add(nextID);
					//add all the arguments for the component to the arraylist
					this.addAll(Arrays.asList(entityComponents.get(k))); 
				}};
				Object[] args = tempArr.toArray(); //Convert the temp array to an array of objects
				componentArrayList.add((Component) componentConstructor.newInstance(args)); //Add a new instance to arraylist.
				if(k.equals(Sprite.class)) { //Check if this is the image
					Image image = DataRead.loadImage((String) entityComponents.get(k)[0]);
					entity.setImage(image);
				}
			}
			for(Component c : componentArrayList) { //Add all the components
				entity.add(c);
			}
			((LevelView) tabPane.getSelectionModel().getSelectedItem().getContent()).addEntity(entity); //add the entity to the level
			nextID++; //Increment id's by one
			} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException el) {
			el.printStackTrace();
		}
	};
	
}
