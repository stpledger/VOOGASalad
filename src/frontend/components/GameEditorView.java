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
import java.util.function.Consumer;

import javax.imageio.ImageIO;

import frontend.entities.Entity;
import frontend.gamestate.*;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.image.Image;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.FileChooser.ExtensionFilter;
import GamePlayer.Main;
import engine.components.Component;
import engine.components.EntityType;
import engine.components.Sprite;

/**
 * 
 * @author Collin Brown(cdb55)
 *
 */
public class GameEditorView extends BorderPane {
	private static final String GAME_FILE_EXTENSION = ".xml";
	private ArrayList<Tab> tabsList;
	private Object[] clipboard;
	private String activeTool;
	private IGameState state;
	private TabPane tabPane;
	private Toolbar toolbar;
	private File gameFile;
	private int nextID  = 0;
	
	/**
	 * Default Constructor
	 */
	public GameEditorView() {
		super();
		//Create a Toolbar
		toolbar = new Toolbar("GameEditor", consumerMap);
		this.setTop(toolbar);
		tabPane = new TabPane();
		activeTool = "move";
		tabsList = new ArrayList<Tab>();
		state = new GameState();
		addLevel(); // add the first level
		this.setCenter(tabPane);
		tabPane.setOnMouseClicked((e)->addEntity.accept(e)); //Add a new Entity OnClick
		
	}
	
	/**
	 * Below are all of the consumers to be passed to the toolbar
	 */
	//Handles new game call from toolbar
	Consumer newGame = (e)->{System.out.println("New Game!");}; 
	//Handles load game call from toolbar
	Consumer loadGame = (e)->{
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Open Image File");
		fileChooser.setSelectedExtensionFilter(new ExtensionFilter("Image Filter", GAME_FILE_EXTENSION));
		gameFile = fileChooser.showOpenDialog(new Stage());};
	//Handles save game call from toolbar
	Consumer saveGame = (e)->{System.out.println("Save Game!");};
	//Handles the add new level call from toolbar
	Consumer newLevel = (e)->{addLevel();};
	//Handles the show settings call from toolbar
	Consumer showSettings = (e)->{System.out.println("Show Settings!");};
	//Handles the play game call from toolbar
	Consumer play = (e)->{
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
		
		};
	//All of the consumers are added to the consumerMap below
	private Map<String, Consumer> consumerMap = new HashMap<String, Consumer>(){{
		this.put("newGame", newGame);
		this.put("loadGame", loadGame);
		this.put("saveGame", saveGame);
		this.put("addLevel", newLevel);
		this.put("showSettings", showSettings);
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
		t.setContent(new LevelView(level,tabsList.indexOf(t)+1));
		t.setOnClosed(new EventHandler<Event>() { //Handles tab closed events
			@Override
			public void handle(Event e) {
				tabsList.remove(t);
				updateTabs.accept(tabsList);
			}
		});
		tabPane.getTabs().add(t);
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
			Class entityType = (Class) clipboardCopy[0]; 
			//Get Constructor for entityType
			Constructor<?> entityConstructor = entityType.getConstructor(int.class);
			//Create a new instance of the entity
			entity = (Entity) entityConstructor.newInstance(nextID);
			 //Set the X,Y position of the mouseEvent to the X,Y position of the object
			entity.setPosition(mouseEvent.getX(), mouseEvent.getY() - this.tabPane.getTabMaxHeight());
			//Get all of the inputs for components
			Map<Class, Object[]> entityComponents = (Map<Class, Object[]>) clipboardCopy[1]; 
			//iterate through all the properties we have for new components
			for(Class k :entityComponents.keySet()) { 
				 // get the constructor for the type of component
				Constructor<?> componentConstructor = k.getConstructors()[0];
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
					File imageFile = new File((String) entityComponents.get(k)[0]);
					Image image = null;
					image = SwingFXUtils.toFXImage(ImageIO.read(imageFile), null);
					entity.setImage(image);
				}
			}
			for(Component c : componentArrayList) { //Add all the components
				entity.add(c);
			}
			((LevelView) tabPane.getSelectionModel().getSelectedItem().getContent()).addEntity(entity); //add the entity to the level
			nextID ++; //Increment id's by one
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException | IOException e1) {
			System.out.println("Error creating entity");
		} 
	};
	
}
