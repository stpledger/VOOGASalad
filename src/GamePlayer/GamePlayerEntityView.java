package GamePlayer;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

import authoring.gamestate.Level;
import data.DataGameState;
import data.DataWrite;

import engine.components.Component;
import engine.components.Height;
import engine.components.Player;
import engine.components.Sprite;
import engine.components.Width;
import engine.components.XPosition;
import engine.components.YPosition;
import engine.setup.GameInitializer;
import engine.setup.RenderManager;
import engine.setup.SystemManager;
import engine.systems.InputHandler;
import engine.systems.collisions.LevelStatus;

import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;


/**
 * Class that controls how the entity objects are displayed
 * @author Ryan Fu
 *
 */
public class GamePlayerEntityView implements IGamePlayerView{
	//private Group entityRoot;
	private Map<Level,Map<Integer,Map<String,Component>>> Levels;
	private Map<Integer, Map<Integer,Map<String,Component>>> IntLevels;
	private Map<Integer, Map<String, Component>> ActiveEntities;
	private Map<Integer, Pane> LevelDisplays;
	private DataGameState gameState;
	private File gameFile;

	private GameInitializer gameInitializer;

	private InputHandler inputHandler;
	private RenderManager renderManager;
	private SystemManager systemManager;
	private LevelStatus levelStatus;

	private int ActiveLevel;
	private XPosition ActivePlayerPosX;
	private YPosition ActivePlayerPosY;

	private static final double PANE_HEIGHT = 442;
	private static final double PANE_WIDTH = 800;
	private Map<Integer, Map<String, Component>> PlayerKeys;

	/**
	 * Constructor when given the gameState
	 * @param gameState
	 */
	public GamePlayerEntityView(DataGameState gameState) {
		Levels = gameState.getGameState();
		PlayerKeys = new HashMap<>();
		levelToInt();
		LevelDisplays = createEntityGroupMap(Levels);
		setActiveLevel(1);
		initializeGamePlayerEntityView();
	}

	/**
	 * Converts Map of Levels to its Entities to Integers to Entities to make calling a particular level easier
	 */
	private void levelToInt() {
		IntLevels = new HashMap<>();
		for(Level level: Levels.keySet()){
			IntLevels.put(level.getLevelNum(), Levels.get(level));
		}
	}

	/**
	 * returns the levelEntityMap;
	 * @return
	 */
	public Map<Integer, Pane> getlevelEntityMap(){
		return LevelDisplays;
	}

	/**
	 * Method that builds the entire map of level with groups of sprite images
	 * @param map
	 * 
	 */
	private Map<Integer, Pane> createEntityGroupMap(Map<Level, Map<Integer, Map<String, Component>>> map){
		Map<Integer, Pane> levelEntityMap = new HashMap<>();
		for(Level level : map.keySet()) {
			levelEntityMap.put(level.getLevelNum(), createIndividualEntityGroup(map.get(level), level.getLevelNum()));
			//levelEntityMap.put(count+1, createIndividualEntityGroup(Levels.get(level))); //TESTING DELETE
		}
		return levelEntityMap;
	}

	/**
	 * Method that creates all the groups for each level in a Levels.
	 * @param entityMap
	 * @return
	 */

	private Pane createIndividualEntityGroup(Map<Integer, Map<String, Component>> entityMap, int levelNum) {
		Pane entityRoot = new Pane();
		Map<String, Component> entityComponents;
		//Changed enclosed code to only load sprites for 
		for(Integer i : entityMap.keySet()) {
			entityComponents = entityMap.get(i);
			if(entityComponents.containsKey(Sprite.KEY)) {
				Sprite spriteComponent = (Sprite) entityComponents.get(Sprite.KEY);
				ImageView image = spriteComponent.getImage(); //gets the class of the sprite
				
				if (entityComponents.containsKey(XPosition.KEY) && entityComponents.containsKey(YPosition.KEY)) {
					XPosition px = (XPosition) entityComponents.get(XPosition.KEY);
					YPosition py = (YPosition) entityComponents.get(YPosition.KEY);
					image.setX(px.getData());
					image.setY(py.getData());

					// setting up values to track for window scroll
					if(entityComponents.containsKey(Player.KEY)){
                        PlayerKeys.put(levelNum, entityComponents);
                    }
				}
				
				//	JACK ADDED THIS .............
				
				if(entityComponents.containsKey(Width.KEY) && entityComponents.containsKey(Height.KEY)) {
					Width w = (Width) entityComponents.get(Width.KEY);
					Height h = (Height) entityComponents.get(Height.KEY);
					image.setFitHeight(h.getData());
					image.setFitWidth(w.getData());
				}
				
				//	Sizes images correctly	.................
				
				
				//System.exit(0);
				entityRoot.getChildren().add(image);
			}
		}
		//entities that have sprites and setup sprite images
		return entityRoot;
	}

//	/**
//	 * When a level change is invoked, reinitalize the GameInitializer to add functionality.
//	 * @param levelNum
//	 */
//	public void reinitializeGameEngine(int levelNum) {
//		int count = 1;
//		Map<Integer, Map<String, Component>> currentLevel = null;
//		for(Level level : Levels.keySet()) {
//			if (count == levelNum) {
//				
//				currentLevel = Levels.get(level);
//				System.out.println(currentLevel);
//				break;
//			}
//			count++;
//		}
//		try {
//			System.out.println(currentLevel);
//			gameInitializer = new GameInitializer(currentLevel, Math.max(PANE_HEIGHT, PANE_WIDTH),
//					ActivePlayerPos.getXPos(), ActivePlayerPos.getYPos()); //reinitializes the level.
//		} catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			System.out.println("Level Does Not Currently Exist Yet");
//		} 
//	}

	/**
	 * initialize the Game Initializer to create the systemManager and renderManager.
	 * @throws FileNotFoundException
	 */
	public void initializeGamePlayerEntityView() {
		try {
			gameInitializer = new GameInitializer(IntLevels.get(ActiveLevel),
					Math.max(PANE_HEIGHT, PANE_WIDTH), ActivePlayerPosX.getData(), ActivePlayerPosY.getData());
			//gameInitializer = new GameInitializer(IntLevels.get(0)); //gets the first level map.
		} catch (FileNotFoundException e) {
			System.out.println("ActiveEntities not initialized");
		}

		inputHandler = gameInitializer.getInputHandler();
		renderManager = gameInitializer.getRenderManager();
		systemManager = gameInitializer.getSystemManager();

		//added code for listening if level should change, not sure this is the best place to put it, but it works

		levelStatus = new LevelStatus();

		/*levelStatus.getUpdate().addListener((o, oldVal, newVal) -> {
	   //  some action based on the value of newVal like -1 game over, from 1 to 2 change to level two etc. 
	  });*/
	}

	public void setActiveLevel(int i){
		ActiveLevel = i;
		Map<String, Component> player = new HashMap<>(PlayerKeys.get(ActiveLevel));
		ActivePlayerPosX = (XPosition) player.get(XPosition.KEY);
		ActivePlayerPosY = (YPosition) player.get(YPosition.KEY);
	}
	

	public void execute (double time) {
		systemManager.execute(time);
	}

	public void render() {
		double newCenterX = ActivePlayerPosX.getData();
		double newCenterY = ActivePlayerPosY.getData();
		systemManager.setActives(renderManager.render(newCenterX, newCenterY));
	}
    
	public void setInput(KeyCode code){
		inputHandler.addCode(code);
	}

	public void removeInput (KeyCode code) {
		inputHandler.removeCode(code);
	}

	public Map<Integer, Map<String, Component>> getPlayerKeys(){
		return PlayerKeys;
	}
	
	public void saveGame(){
		DataWrite dw = new DataWrite();
		dw.saveGame(gameState, "test");
	}

	// used to update the bounds of the scrollpane so the view shifts with the user's character
	public void updateScroll(Pane gameRoot){
		double minX = gameRoot.getTranslateX() * -1;
		double maxX = gameRoot.getTranslateX() * -1 + PANE_WIDTH;
		double minY = gameRoot.getTranslateY() * -1;
		double maxY = gameRoot.getTranslateY() * -1 + PANE_HEIGHT;

		if(ActivePlayerPosY.getData() - 100 < minY){
			gameRoot.setTranslateY((ActivePlayerPosY.getData() - 100) * -1);
		}

		if(ActivePlayerPosY.getData() + 200 > maxY){
			gameRoot.setTranslateY(((ActivePlayerPosY.getData() + 200) - PANE_HEIGHT) * -1);
		}

		if(ActivePlayerPosX.getData() - 100 < minX){
			gameRoot.setTranslateX((ActivePlayerPosX.getData() - 100) * -1);
		}

		if(ActivePlayerPosX.getData() + 400 > maxX){
			gameRoot.setTranslateX(((ActivePlayerPosX.getData() + 400) - PANE_WIDTH) * -1);
		}
    }

    public LevelStatus getLevelStatus(){
		return levelStatus;
	}

}
