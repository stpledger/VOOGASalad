package data;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import engine.components.Component;
import javafx.scene.control.Alert;
import javafx.stage.FileChooser;


import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;

/*
    Auhot @Conrad defines the method of reading in xml files and outputting this information to the UI to be initialized
 */
public class DataRead {

    /*
        parses the xml file and sends state info to UI
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

    public static DataGameState loadFile(File xml) {
        try {
            return buildState(xml);
        } catch (IllegalStateException e) {
            String error = e.getMessage();
            ErrorStatement(error);
        }
        return new DataGameState();
    }

    private static void ErrorStatement(String error) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Error");
        alert.setContentText(error);
        alert.showAndWait();
    }


}
