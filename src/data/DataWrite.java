package data;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import frontend.gamestate.GameState;


import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class DataWrite {

/*  Author @ Conrad methods for saving data from either the authoring environment 
 *  or the player
 */
	private static final String XML_FILETYPE=".xml";
	private static final String GAME_FILEPATH = "games\\";
	private static final String IMAGE_DATAPATH = "dara\\";
    private static final String PERIOD = ".";



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
        File xmlFile = new File(GAME_FILEPATH+name+XML_FILETYPE);
        FileOutputStream fos = new FileOutputStream(xmlFile);
        XStream xstream = new XStream(new DomDriver()); 
        xstream.toXML(dataGameState, fos);
        System.out.print(xmlFile.getAbsolutePath() + "File saved here");
        return xmlFile;
    }

    public static void writeImage(File imageFile) throws IOException {
        BufferedImage image = null;
        try {
            image = ImageIO.read(imageFile);
            ImageIO.write(image, getFileType(imageFile), new File(IMAGE_DATAPATH + imageFile.getName()));
        }
        catch(Exception e)
        {
            throw new IOException();
        }

    }

    public static String getFileType(File file)
    {
        int fIndex = file.getName().indexOf(PERIOD);
        return (fIndex == -1) ? "" : file.getName().substring(fIndex + 1);
    }



}

