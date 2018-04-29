package data;

import GamePlayer.Person;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import authoring.gamestate.Level;
import engine.components.Component;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;

/*
    @Author Conrad defines methods for reading in various information from 
    the xml files reresenting gameStates
 */


public class DataRead  {
    private static final String EMPTY_GAME = "Empty";
    private static final String FAIL_MESSAGE ="File could not be loaded";
    private static final String IMAGE_PATH = "data/images/";
    private static final String SOUND_PATH = "data/sounds/";
    private static final String HIGHSCORE_PATH = "highscores.xml";
    private static final String GAME_PATH = "games/";
    private static final String EMPTY_IMAGE ="File:data/images/picture-placeholder.png";
    private static final String ERROR ="Error";
    private static final String FRONTSLASH = "/";
    private static final String BACKSLASH = "\\";
    private static final String FILE ="File:";
    private static final String PERIOD = ".";
    private static final String SPACE ="";
    private static final String ENTITY_PATH = "entity";
    private static String path = "";
    private static final Class CLASS = "Entity".getClass();
    private static final String SAVE_PATH = "saves/";
    private static final String PLAYER_TARGET = "Player.xml";
    private static final String USER_DIR = "user.dir";

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
            return new HashMap<>();
        }
    }

    public static Image loadImage(String name)throws RuntimeException {
        File imageFile = loadFile(path + FRONTSLASH + IMAGE_PATH +name);
        return loadImage(imageFile);
    }

   // public static List<DataGameState> getGames

    public static Image addImage(File file) {

        DataWrite.writeImage(file);
        return loadImage(file);
    }

    public static Image loadImage(File file) {
        try {
            BufferedImage image = ImageIO.read(file);
            return SwingFXUtils.toFXImage(image, null);
        } catch (IOException e) {
            ErrorStatement(FAIL_MESSAGE);
            return new Image(EMPTY_IMAGE);
        }
    }

    public static Map<String,List<Person>> loadHighscore(){
        File hs = loadFile(HIGHSCORE_PATH);
        System.out.print(hs.getAbsolutePath());
        return ((HighScore)deserialize(hs)).getHighscores();

    }


    public static Map<Image, DataGameState> getAllGames() {
        Map<Image, DataGameState> games =new HashMap<>();
        File file = loadFile(GAME_PATH);
        for(File game : file.listFiles()){
          game = findInDirectory(game,PLAYER_TARGET);
          DataGameState playable = loadPlayerFile(game);
          //Image icon = getIcons().get(0);
          //games.put(icon, playable);
        }
        return games;
    }

    public static List<DataGameState> getSaves(){
        List<DataGameState> saves = new ArrayList<DataGameState>();
        File file = loadFile(path + FRONTSLASH + SAVE_PATH);
        for(File save : file.listFiles()){
            saves.add((DataGameState)deserialize(save));
        }
        return saves;
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
            path=GAME_PATH + gameState.getGameName()+ FRONTSLASH;
            return gameState;
        }
        catch(Exception e){
            ErrorStatement(FAIL_MESSAGE);
            return new DataGameState(EMPTY_GAME);
        }
    }
    private static Object deserialize(File xml) {
        XStream xstream = new XStream(new DomDriver());
        return xstream.fromXML(xml);
    }
    public static List<Image> getIcons(){
        List<Image> icons = new ArrayList<>();
        File imageRepo = loadFile(path+IMAGE_PATH);
        for(File image : imageRepo.listFiles()) {
            icons.add(loadImage(image));
        }
        return icons;
    }
    private static File findInDirectory(File directory, String target){
        if(directory.isDirectory()){
            for(File subDir : directory.listFiles()){
                File found = findInDirectory(subDir, target);
                if(found!=null){
                    return found;
                }
            }
        }
        if(directory.getName().equals(target)){
            System.out.println("Found Directory " + directory.getName());
            return directory;
        }
        else return null;
    }
    private static File loadFile(String path) {
        String filePath = path.replace(BACKSLASH,FRONTSLASH);
        File file = new File(filePath);
        if(!file.exists())
            file = new File(FILE+filePath);
        if (!file.exists()) {
            try {
                file = new File(DataRead.class.getClassLoader().getResource(filePath).getFile());
                if (!file.exists())
                    throw new NullPointerException();
            } catch (NullPointerException e) {
                try {
                    file = new File(DataRead.class.getClassLoader().getResource(FRONTSLASH + filePath).getFile());
                    if (!file.exists())
                        throw new NullPointerException();
                } catch (NullPointerException f) {
                    try {
                        System.out.print(filePath);
                        String fileName = filePath.substring(filePath.lastIndexOf(FRONTSLASH )+1);
                        System.out.println("");
                        System.out.println("Finding " + fileName + "   in  " + getProjectDir().getAbsolutePath());
                        file = findInDirectory(getProjectDir(), fileName);
                    }
                    catch(NullPointerException g){
                        System.out.print("Error reading file DataRead line 240");
                        ErrorStatement(FAIL_MESSAGE);
                    }
                }
            }
        }
        return file;
    }
    private static File getProjectDir(){
        String superDir = System.getProperty(USER_DIR).replace(BACKSLASH,FRONTSLASH);
        return new File(superDir);
    }


}
