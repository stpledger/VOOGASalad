package data;

import com.sun.org.apache.xml.internal.serialize.OutputFormat;
import com.sun.org.apache.xml.internal.serialize.XML11Serializer;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import engine.components.Component;
import javafx.scene.control.Alert;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Line;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.w3c.dom.html.HTMLLabelElement;


import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/*
    Auhot @Conrad defines the method of reading in xml files and outputting this information to the UI to be initialized
 */
public class DataRead {

    /*
        parses the xml file and sends state info to UI
     */
    private static  GameState buildState(File xml) {
        try {
            XStream xstream = new XStream(new DomDriver()); // does not require XPP3 library
            Map<Integer, Map<String,Component>> gameState = (Map<Integer,Map<String, Component>>)xstream.fromXML(xml);
            return new GameState(gameState);
        }
        catch(Exception e){throw new IllegalStateException();}
    }

    public static  GameState loadFile(Stage activeScreen) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        fileChooser.setInitialDirectory(new File(System.getProperty("user.dir")));
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("XML Files", "*.xml"));
        try {
            File xml = fileChooser.showOpenDialog(activeScreen);
            return buildState(xml);
        } catch (IllegalStateException e) {
            String error = e.getMessage();
            ErrorStatement(error);
        }
        return new GameState();
    }

    private static void ErrorStatement(String error)
    {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Error");
        alert.setContentText(error);
        alert.showAndWait();
    }


//    public static void main(String args[])
//    {
//        File read = new File("C:\\Users\\Conrad\\IdeaProjects\\voogasalad_oneclassonemethod\\src\\data\\Baby's_First_Serialized.xml");
//        Map<Integer,Map<String,Component>> poop = buildState(read).getGameState();
//        System.out.print(poop);
//    }

}
