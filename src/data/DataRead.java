package data;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import engine.components.Component;
import frontend.components.Level;
import frontend.entities.Entity;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.*;

import static engine.components.Sprite.IMAGE_PATH;

/*
    @Author Conrad defines methods for reading in various information from 
    the xml files reresenting gameStates
 */


public class DataRead  {
    private static final String EMPTY_GAME = "Empty";
    private static final String FAIL_MESSAGE ="File could not be loaded";
    private static final String IMAGE_PATH = "data/images/";
    private static final String SOUND_PATH = "data/sounds/";
    private static final String GAME_PATH = "games/";
    private static final String EMPTY_IMAGE ="File:data/images/picture-placeholder.png";
    private static final String ERROR ="Error";
    private static final String SLASH = "/";
    private static final String FILE ="File:";
    private static final String PERIOD = ".";
    private static final String SPACE ="";
    private static final String ENTITY_PATH = "entity";
    private static String path = "";
    private static final Class CLASS = "Entity".getClass();
    private static final String SAVE_PATH = "saves/";
    private static final String PLAYER_TARGET = "Player.xml";

    /* receives a gamestate and loads it to the player
     * from buildState
     */
    public static DataGameState loadPlayerFile(File xml) {
        try {
            return buildState(xml);
        } catch (IllegalStateException e) {
            ErrorStatement(FAIL_MESSAGE);
            return new DataGameState(EMPTY_GAME);
        }
    }

    /*return a map for the gamestate to be sent to authoring that can be built into their 
     *version of game state
     */
    public static Map<Level, Map<Integer, List<Component>>> loadAuthorFile(File xml) {
        try {
            DataGameState tempState = loadPlayerFile(xml);
            return tempState.getGameStateAuthoring();
        } 
        catch (IllegalStateException e) {
            ErrorStatement(FAIL_MESSAGE + e.getMessage());
            return new HashMap<Level,Map<Integer,List<Component>>>();
        }
    }

    public static Image loadImage(String name)throws RuntimeException {
        try {
            return  new Image(FILE+path+IMAGE_PATH+name);
        }
        catch (Exception e) {
            ErrorStatement(FAIL_MESSAGE);
            return new Image(EMPTY_IMAGE);
        }
    }

   // public static List<DataGameState> getGames

    public static Image loadImage(File file) {
        try {
            BufferedImage image = ImageIO.read(file);
            DataWrite.writeImage(file);
            return SwingFXUtils.toFXImage(image, null);
        } catch (IOException e) {
            ErrorStatement(FAIL_MESSAGE);
            return new Image(EMPTY_IMAGE);
        }
    }

    public static Map<Image, DataGameState> getAllGames() {
        Map<Image, DataGameState> games =new HashMap<>();
        File file = new File(GAME_PATH);
        for(File game : file.listFiles()){
          game = new File(game.getAbsolutePath() + SLASH + PLAYER_TARGET);
          DataGameState playable = loadPlayerFile(game);
          Image icon = getIcons().get(0);
          games.put(icon, playable);
        }
        return games;
    }

    public static List<DataGameState> getSaves(){
        List<DataGameState> saves = new ArrayList<DataGameState>();
        File file = new File(path + SLASH + SAVE_PATH);
        for(File save : file.listFiles()){
            saves.add((DataGameState)deserialize(save));
        }
        return saves;
    }

    public static List<Entity> getEntityList(){
        List<Entity> entitySelect = new ArrayList<Entity>();
        File f = new File(ENTITY_PATH);
        for(File entityFile : f.listFiles()){
            entitySelect.add((Entity)deserialize(entityFile));
        }
        return entitySelect;
    }

    // util file for finding filetype

    private static String getFileType(File file) {
        int fIndex = file.getName().indexOf(PERIOD);
        return (fIndex == -1) ? SPACE : file.getName().substring(fIndex + 1);
    }

    /*prints an error to the screen
     */
    private static void ErrorStatement(String error)  {
        try {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle(ERROR);
            alert.setContentText(error);
            alert.showAndWait();
        }
        catch (Error el )
        {
            System.out.print("Application not defined");
        }
    }

    /*loads a game file and return it to the public method if
     * the game file doesnt exist in the right form it returns an empty state
     */
    private static DataGameState buildState(File xml) {
        try {
            DataGameState gameState = (DataGameState)deserialize(xml);
            path=GAME_PATH+gameState.getGameName()+SLASH;
            return gameState;
        }
        catch(Exception e){
            ErrorStatement(FAIL_MESSAGE);
            return new DataGameState(EMPTY_GAME);
        }
    }

    private static Object deserialize(File xml) {
        XStream xstream = new XStream(new DomDriver()); // does not require XPP3 library
        return xstream.fromXML(xml);
    }

    public static List<Image> getIcons(){
        List<Image> icons = new ArrayList<>();
        File imageRepo = new File(FILE+path+IMAGE_PATH);
        for(File image : imageRepo.listFiles())
            icons.add(loadImage(image));
        return icons;
    }
}
