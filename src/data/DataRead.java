package data;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import engine.components.Component;
import frontend.components.Level;
import javafx.scene.control.Alert;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
    @Author Conrad defines methods for reading in various information from 
    the xml files reresenting gameStates
 */
public class DataRead {

    /*loads a game file and return it to the public method if
     * the game file doesnt exist in the right form it returns an empty state
     */
    private static DataGameState buildState(File xml) {
        try {
            XStream xstream = new XStream(new DomDriver()); // does not require XPP3 library
            DataGameState gameState = (DataGameState)xstream.fromXML(xml);
            return gameState;
        }
        catch(Exception e){
            ErrorStatement(e.getMessage());
            return new DataGameState();
        }
    }
    
    /* receives a gamestate and loads it to the player
     * from buildState
     */
    public static DataGameState loadFile(File xml) {
        try {
            return buildState(xml);
        } catch (IllegalStateException e) {
            String error = e.getMessage();
            ErrorStatement(error);
        }
        return new DataGameState();
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
            ErrorStatement("File could not b e read");
            return new HashMap<Level,Map<Integer,List<Component>>>();
        }
    }

    /*prints an error to the screen 
     */
    private static void ErrorStatement(String error) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Error");
        alert.setContentText(error);
        alert.showAndWait();
    }


}
