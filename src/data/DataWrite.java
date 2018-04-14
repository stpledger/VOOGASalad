package data;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import engine.components.Component;
import engine.components.Sprite;
import frontend.components.Level;
import frontend.gamestate.GameState;
import javafx.scene.control.Alert;


import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

public class DataWrite {

/*  Author @ Conrad methods for saving data from either the authoring environment 
 *  or the player
 */
	private static final String XML_FILETYPE=".xml";
	private static final String GAME_FILEPATH = "games\\";
	private static final String IMAGE_DATAPATH = "images\\";
	private static final String DATA_DATAPTH = "data";
    private static final String PERIOD = ".";
    private static final String PLAYER = "\\Player";
    private static final String ERROR ="Error";
    private static final String WRITE_ERROR = "Could not write file";
    private static final String IMAGE_GETTER="getImageFile";
    private static final String SOUND_GETTER="getSoundFile";
    private static final String SOUND_DATAPATH = "sounds\\";
    private static final String SLASH = "\\";
    private static final String DEFAULT_IMAGEPATH = DATA_DATAPTH + SLASH +IMAGE_DATAPATH;
    private static final Set<Object> DATA_COMPONENTS = new HashSet<>(Arrays.asList(new Object[]{Sprite.class}));


    //creates an xml file from an authoiring environment this method converts authoring gamestate to player
    // gamestate then writes to xml
    public static void saveFile(GameState gameState, String fileName) throws Exception{
        DataGameState dataGameState = new DataGameState(gameState, fileName);
        createFile(dataGameState, fileName);
    }

    //creates an xml file from an authoiring environment
    public static void saveFile(DataGameState dataGameState, String fileName) throws Exception{
        createFile(dataGameState, fileName);
    }


    // does the backend work to create new files in the game directory
    private static void createFile(DataGameState dataGameState, String name) throws Exception {
        makeFolders(name);
        writeGame(dataGameState, name);
        writeResources(dataGameState, name);
    }

    // Utility for adding imaged into a specified directory instead of a random directory in the user's computer
    private static String writeImage(String gameName, String imageName) throws IOException {
        File imageFile = new File(DEFAULT_IMAGEPATH+imageName);
        try {
            BufferedImage image = ImageIO.read(imageFile);
            String filePath = GAME_FILEPATH+gameName+ SLASH +DEFAULT_IMAGEPATH+ imageName;
            File fileDest = new File(filePath);
            ImageIO.write(image, getFileType(imageFile), fileDest);
            return filePath;
        }
        catch(Exception e)
        {
            throw new IOException();
        }

    }



    //makes a folder system or cleans one out for data to be written
    private static void makeFolders(String name) {
        String gameDir = GAME_FILEPATH+name;
        String dataDir = gameDir + SLASH + DATA_DATAPTH;
        String imageDir = dataDir + SLASH + IMAGE_DATAPATH;
        String soundDir = dataDir +SLASH + SOUND_DATAPATH;

        File gameFolder = new File(gameDir);
        if (!gameFolder.exists()) {
            gameFolder.mkdir();
            File dataFolder = new File(dataDir);
            dataFolder.mkdir();
            File imageFolder = new File(imageDir);
            imageFolder.mkdir();
            File soundFolder = new File(soundDir);
            soundFolder.mkdir();
        }
        else {
            deleteDir(gameFolder);
            makeFolders(name);
        }
    }


    //writes the xml to the folder created above
    private static void writeGame(DataGameState dataGameState, String name) {
        File game = new File(GAME_FILEPATH+name+ PLAYER + XML_FILETYPE);
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(game);
        } catch (FileNotFoundException e) {
            ErrorStatement(WRITE_ERROR);
        }
        XStream xstream = new XStream(new DomDriver());
        xstream.toXML(dataGameState, fos);
    }


    //TODO make this method more efficient possibly??
    private static void writeResources(DataGameState dataGameState, String name) {
        List<Component> componentList = dataGameState.getComponents();
            for(Component component : componentList) {
              if(DATA_COMPONENTS.contains(component.getClass())) {
                  for (Method method : component.getClass().getMethods())
                      if (method.getName().equals(IMAGE_GETTER))
                          try {
                              writeImage(name, (String) method.invoke(component, null));
                          } catch (IOException | IllegalAccessException | InvocationTargetException e) {
                              e.printStackTrace();
                          }
//                   if (method.getName().equals(SOUND_GETTER))
//                       try {
//                           writeSound((String) method.invoke(component, null));
//                       } catch (IOException | IllegalAccessException | InvocationTargetException e) {
//                           e.printStackTrace();
//                       }
              }
            }
    }




    // util file for finding filetype
    private static String getFileType(File file) {
        int fIndex = file.getName().indexOf(PERIOD);
        return (fIndex == -1) ? "" : file.getName().substring(fIndex + 1);
    }


    //destroy a directory TO BE KEPT PRIVATE!!!!!!!!!
    //DO NOT FUCK WITH THIS METHOD IT WILL FUCK YOUR SHIT UP
    //SERIOUSLY IT WILL DELETE YOUR COMPUTER...
    private static void deleteDir(File dir) {
        if (!dir.exists())
            return;
        if (dir.isDirectory()) {
            File[] children = dir.listFiles();
            for (File file : children) {
                deleteDir (file);
            }
        }
        dir.delete();
    }

    /*prints an error to the screen
     */
    private static void ErrorStatement(String error) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(ERROR);
        alert.setContentText(error);
        alert.showAndWait();
    }





}

