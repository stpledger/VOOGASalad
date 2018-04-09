package data;

import com.sun.org.apache.xml.internal.serialize.OutputFormat;
import com.sun.org.apache.xml.internal.serialize.XML11Serializer;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import engine.components.Acceleration;
import engine.components.Component;
import engine.components.Damage;
import javafx.scene.shape.Line;
import org.w3c.dom.Document;
import org.w3c.dom.Element;


import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.nio.file.FileAlreadyExistsException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.IllegalFormatException;
import java.util.List;
import java.util.Map;

public class DataWrite {

/*
      Author @ Conrad defines how and where files are saveed by the user
 */
    //calls save turtles lines and preferences and writes output
    public static void saveFile(GameState gameState) throws Exception{
       // buildDoc();
        createFile(gameState, "Game1");

    }

    //specifies a format and file location to save the information
    private static void createFile(GameState gameState, String name) throws Exception {
        File xmlFile = new File(System.getProperty("user.dir")+"\\"+name+".xml");
        FileOutputStream fos = new FileOutputStream(xmlFile);

        XStream xstream = new XStream(new DomDriver()); // does not require XPP3 library
        xstream.toXML(gameState, fos);
    }

    //Testing

//    public static void main(String[] args)
//    {
//        Map<Integer, Map<String, Component>> testState = new HashMap<Integer, Map<String, Component>>();
//        Map<String, Component> input1 = new HashMap<String, Component>();
//        input1.put("Acceleration", new Acceleration(1349134, 7, 1));
//        input1.put("Damage",new Damage(23423409,2,1));
//        testState.put(143, input1);
//        try {
//            createFile(new GameState(testState),"Baby's_First_Serialized");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }


}

