package data;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import engine.components.Acceleration;
import engine.components.Component;
import engine.components.Damage;
import frontend.gamestate.GameState;


import java.io.File;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Map;

public class DataWrite {

/*
      Author @ Conrad defines how and where files are saveed by the user
 */
    //calls save turtles lines and preferences and writes output
    public static void saveFile(GameState gameState, String fileName) throws Exception{
        DataGameState dataGameState = new DataGameState(gameState);
        createFile(dataGameState, fileName);
    }

    public static void saveFile(DataGameState dataGameState, String fileName) throws Exception{
        createFile(dataGameState, fileName);
    }

    //specifies a format and file location to save the information
    private static void createFile(DataGameState dataGameState, String name) throws Exception {
        File xmlFile = new File(System.getProperty("user.dir")+"\\"+name+".xml");
        FileOutputStream fos = new FileOutputStream(xmlFile);
        XStream xstream = new XStream(new DomDriver()); // does not require XPP3 library
        xstream.toXML(dataGameState, fos);
    }

    //Testing

//    public static void main(String[] args)
//    {
//        Map<Integer, Map<String, Component>> testState = new HashMap<Integer, Map<String, Component>>();
//        Map<String, Component> input1 = new HashMap<String, Component>();
//        input1.put("Acceleration", new Acceleration(234, "3", "4"));
//        input1.put("Damage",new Damage(13423 ));
//        testState.put(143, input1);
//        try {
//            createFile(new DataGameState(testState),"Baby's_First_Serialized");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }


}

