package data;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import frontend.gamestate.GameState;


import java.io.File;
import java.io.FileOutputStream;

public class DataWrite {

/*  Author @ Conrad methods for saving data from either the authoring environment 
 *  or the player
 */
	private static final String XML_FILETYPE=".xml";
	private static final String GAME_FILEPATH = "\\games\\";
	
    
	/*creates an xml file from an authoiring environment 
	 * this method converts authoring gamestate to player gamestate 
	 * then writes to xml
	 */
    public static void saveFile(GameState gameState, String fileName) throws Exception{
        DataGameState dataGameState = new DataGameState(gameState);
        createFile(dataGameState, fileName);
    }

    /*creates an xml file from an authoiring environment 
	 */
    public static File saveFile(DataGameState dataGameState, String fileName) throws Exception{
       return createFile(dataGameState, fileName);
    }
    
    /*does the backend work to create new files in the game directory
     */
    private static File createFile(DataGameState dataGameState, String name) throws Exception {
        File xmlFile = new File(System.getProperty("user.dir")+GAME_FILEPATH+name+XML_FILETYPE);
        FileOutputStream fos = new FileOutputStream(xmlFile);
        XStream xstream = new XStream(new DomDriver()); 
        xstream.toXML(dataGameState, fos);
        return xmlFile;
    }


}

