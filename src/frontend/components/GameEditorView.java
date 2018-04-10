package frontend.components;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import frontend.gamestate.*;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.FileChooser.ExtensionFilter;
import GamePlayer.Main;

/**
 * 
 * @author Collin Brown(cdb55)
 *
 */
public class GameEditorView extends BorderPane {
	private static final String GAME_FILE_EXTENSION = ".xml";
	private ArrayList<Tab> tabsList;
	private Object clipboard;
	private String activeTool;
	private IGameState state;
	private TabPane tabPane;
	private Toolbar toolbar;
	private File gameFile;
	
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
	//Handles the call for the addTool in toolbar
	Consumer addTool = (e)->{System.out.println("Add Tool!");};
	//Handles the call for the deleteTool in toolbar
	Consumer deleteTool = (e)->{System.out.println("Delete Tool!");};
	//Handles thecall for the editTool in Toolbar
	Consumer editTool = (e)->{System.out.println("edit Tool!");};
	//All of the consumers are added to the consumerMap below
	private Map<String, Consumer> consumerMap = new HashMap<String, Consumer>(){{
		this.put("newGame", newGame);
		this.put("loadGame", loadGame);
		this.put("saveGame", saveGame);
		this.put("addLevel", newLevel);
		this.put("showSettings", showSettings);
		this.put("play", play);
		this.put("addTool", addTool);
		this.put("deleteTool", deleteTool);
		this.put("editTool", editTool);
		
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
		//TODO: add argument check because this is being called from the controller
		clipboard = o;
		System.out.println(o.toString());
	}
	
	//TODO: change these class names
	public void addTool() {setTool("add");}
	public void deleteTool() {setTool("delete");}
	public void editTool() {setTool("edit");}
	
	public void setTool(Object o) {
		activeTool = o.toString();
		switch(o.toString()) {
		case "edit":
			this.setCursor(javafx.scene.Cursor.OPEN_HAND);
			break;
		case "add":
		case "delete":
			this.setCursor(javafx.scene.Cursor.HAND);
			break;
		}
	}
}
