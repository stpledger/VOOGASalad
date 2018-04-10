package data;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import engine.components.Acceleration;
import engine.components.Component;
import engine.components.DamageLauncher;
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

    public static File saveFile(DataGameState dataGameState, String fileName) throws Exception{
       return createFile(dataGameState, fileName);
    }

    //specifies a format and file location to save the information
    private static File createFile(DataGameState dataGameState, String name) throws Exception {
        File xmlFile = new File(System.getProperty("user.dir")+"\\"+name+".xml");
        FileOutputStream fos = new FileOutputStream(xmlFile);
        XStream xstream = new XStream(new DomDriver()); // does not require XPP3 library
        xstream.toXML(dataGameState, fos);
        return xmlFile;
    }


}

