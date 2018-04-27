package data;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import authoring.entities.Entity;
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
        Image image;
            image = new Image("File:data/images/" + name);
            if (image.getHeight() == 0) {
                try {
                    image = new Image(DataRead.class.getClassLoader().getResourceAsStream("/" + IMAGE_PATH + name));
                    System.out.println("Path try /" + IMAGE_PATH + name);
                } catch (Exception e) {
                    try {
                        image = new Image(DataRead.class.getClassLoader().getResourceAsStream("/" + path + IMAGE_PATH + name));
                        System.out.println("Path try/" + IMAGE_PATH + name);
                    } catch (Exception y) {
                        try {
                            image = SwingFXUtils.toFXImage(ImageIO.read(loadFile(path + IMAGE_PATH + name)), null);
                        } catch (IOException e1) {
                            e1.printStackTrace();
                        }
                    }
                }
                return image;
            }
            return image;
    }

   // public static List<DataGameState> getGames

    public static Image addImage(File file) {
        DataWrite.writeImage(file);
        return loadImage(file);
    }
    public static Image loadImage(File file) {
       System.out.print(file.getAbsolutePath());
        try {
            BufferedImage image = ImageIO.read(file);
            return SwingFXUtils.toFXImage(image, null);
        } catch (IOException e) {
            ErrorStatement(FAIL_MESSAGE);
            return new Image(EMPTY_IMAGE);
        }
    }


    public static Map<Image, DataGameState> getAllGames() {
        Map<Image, DataGameState> games =new HashMap<>();
        File file = loadFile(GAME_PATH);
        for(File game : file.listFiles()){
          game = findInDirectory(game,PLAYER_TARGET);
          System.out.println(game.getAbsolutePath());
          DataGameState playable = loadPlayerFile(game);
          Image icon = getIcons().get(0);
          games.put(icon, playable);
        }
        return games;
    }

    public static List<DataGameState> getSaves(){
        List<DataGameState> saves = new ArrayList<DataGameState>();
        File file = loadFile(path + SLASH + SAVE_PATH);
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
        File imageRepo = loadFile(path+IMAGE_PATH);
        for(File image : imageRepo.listFiles()) {
            icons.add(loadImage(image));
            System.out.println(image.getAbsolutePath());
        }
        return icons;
    }

    private static File loadFile(String path){
        File file = new File(FILE+path);
        if(!file.exists()) {
            try {
                file = new File(DataRead.class.getClassLoader().getResource("/" + path).getFile());
            } catch (Exception e) {
                if (!file.exists())
                    file = new File(path);
            }
        }
        return file;
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
            return directory;
        }
        else return null;
    }

}
