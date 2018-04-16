package data;

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import engine.components.Component;
import engine.components.Sprite;
import frontend.components.Level;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.*;

/*
    @Author Conrad defines methods for reading in various information from 
    the xml files reresenting gameStates
 */


public class DataRead {
    private static final String EMPTY_GAME = "Empty";
    private static final String FAIL_MESSAGE ="File could not be loaded";
    private static final String IMAGE_PATH = "data\\images\\";
    private static final String SOUND_PATH = "data\\sounds\\";
    private static final String GAME_PATH = "games\\";
    private static final String EMPTY_IMAGE ="File:data\\images\\picture-placeholder.png";
    private static final String ERROR ="Error";
    private static final String SLASH = "\\";
    private static final String FILE ="File:";
    private static final String PERIOD = ".";
    private static final Set<String> ACCEPTED_IMAGE_FILES = new HashSet<>(Arrays.asList(new String []{"jpg","png","gif"}));

    private static String path = "";


    /*loads a game file and return it to the public method if
     * the game file doesnt exist in the right form it returns an empty state
     */
    private static DataGameState buildState(File xml) {
        try {
            XStream xstream = new XStream(new DomDriver()); // does not require XPP3 library
            DataGameState gameState = (DataGameState)xstream.fromXML(xml);
            path=GAME_PATH+gameState.getGameName()+SLASH;
            return gameState;
        }
        catch(Exception e){
            ErrorStatement(FAIL_MESSAGE);
            return new DataGameState(EMPTY_GAME);
        }
    }
    
    /* receives a gamestate and loads it to the player
     * from buildState
     */
    public static DataGameState loadFile(File xml) {
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
            DataGameState tempState = loadFile(xml);
            return tempState.getGameStateAuthoring();
        } 
        catch (IllegalStateException e) {
            ErrorStatement(FAIL_MESSAGE);
            return new HashMap<Level,Map<Integer,List<Component>>>();
        }
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
            System.out.print("Applicationb not defined");
        }
    }

    /* removing all autonomy of file reading and writing from all other files by doing myself
     */
    public static Image loadImage(String name)throws RuntimeException
    {
        try {
            System.out.println(FILE+path+IMAGE_PATH+name);
            return new Image(FILE+path+IMAGE_PATH+name);
        }
        catch (Exception e) {
            ErrorStatement(FAIL_MESSAGE);
            return new Image(EMPTY_IMAGE);
        }
    }

    public static Image importImage(File file) {
        try {
            BufferedImage image = ImageIO.read(file);
            File fileDest = new File(IMAGE_PATH + file.getName());
            ImageIO.write(image, getFileType(file), fileDest);
            return SwingFXUtils.toFXImage(image, null);
        } catch (IOException e) {
            ErrorStatement(FAIL_MESSAGE);
            return new Image(EMPTY_IMAGE);
        }
    }

    public static void importFromWeb()
    {
        ResourceGetter imageGetter = new ResourceGetter();
        imageGetter.selectImage();
    }


    // util file for finding filetype
    private static String getFileType(File file) {
        int fIndex = file.getName().indexOf(PERIOD);
        return (fIndex == -1) ? "" : file.getName().substring(fIndex + 1);
    }


    //TODO undo duplicate dode
    public static Image importFromURL(URL imageURL, String name) throws IOException{
        BufferedImage image = ImageIO.read(imageURL);
        File fileDest = new File(IMAGE_PATH + name);
        if(!ACCEPTED_IMAGE_FILES.contains(getFileType(fileDest).toLowerCase())) {
            throw new IOException();
        }
        ImageIO.write(image, getFileType(fileDest), fileDest);
        return SwingFXUtils.toFXImage(image, null);
    }


//    public Image loadImage(File imageFile)
//    {
//
//    }
}
