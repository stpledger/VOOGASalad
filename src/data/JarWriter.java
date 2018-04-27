package data;

import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.jar.JarOutputStream;
import java.util.jar.Manifest;

public class JarWriter {
//do the things that will give you the parent directory
    //get authoring to create a file chooser splash screen to decide between url and file
    private List<File> fileNames;
    private Manifest manifest;
    private JarOutputStream jos;
    private FileOutputStream fos;
    private List<File> ignore;
    private List<File> modules;
    private File outPut;
    private File gameSource;
    private File dataSource;
    private File classContainer;
    private File project;
    private String projectName;

    private static final String ROOT = ".classpath";
    private static final String OUT_INTELLIJ = "out";
    private static final String OUT_ECLIPSE = "bin";
    private static final String USER_DIR = "user.dir";
    private static final String BACKSLASH = "\\";
    private static final String FRONTSLASH = "/";
    private static final String GAME_DIR_PROMPT = "Please navigate to a game directory that you would like to write";
    private static final String BIN_ERROR = "The out/bin file could not be found.";
    private static final String DATA_DIR_PROMPT = "Please navigate to a data directory that you would like to write";
    private static final String GAME_DIR = "Directory";
    private static final String GAME_FILE = "File";
    private static final String FILE_DIR_PROMPT = "Is your game in a "+GAME_DIR+" or a "+GAME_FILE+ "?";
    private static final int SIZE = 300;
    private Stage primaryStage;


    public JarWriter(Stage primaryStage){
        this.primaryStage =  primaryStage;
        fileNames = new ArrayList<>();
        getProjectName();
        getClassFiles();
    }

    private void getProjectName(){
        String superDir = System.getProperty(USER_DIR).replace(BACKSLASH,FRONTSLASH);
        projectName = superDir.substring(superDir.lastIndexOf(FRONTSLASH)+1);
        project = new File(superDir);
    }

    private void getClassFiles(){
        classContainer = findInDirectory(project,OUT_ECLIPSE);
        classContainer = findInDirectory(project,OUT_INTELLIJ);
        outPut = findInDirectory(classContainer, projectName);
    }

    public void getIgnore(){
        ignore = new ArrayList<>();
        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setInitialDirectory(project);
        directoryChooser.setTitle("Specify what packages from src not to include");
        while(true) {
            File ignoreFile = directoryChooser.showDialog((new Stage()));
            if(ignoreFile==null)
                break;
            else
                ignore.add(ignoreFile);
        }
    }

    public void getGameDir(){
        DirectoryChooser gameChooser = new DirectoryChooser();
        gameChooser.setTitle(GAME_DIR_PROMPT);
        gameChooser.setInitialDirectory(project);
        gameSource = gameChooser.showDialog(primaryStage);
        fileNames.add(gameSource);
    }

    public void getGameFile(){
            FileChooser gameChooser = new FileChooser();
            gameChooser.setTitle(GAME_DIR_PROMPT);
            gameChooser.setInitialDirectory(project);
            gameSource = gameChooser.showOpenDialog(primaryStage);
            fileNames.add(gameSource);
        }

    public void getData(){
        DirectoryChooser gameChooser = new DirectoryChooser();
        gameChooser.setTitle(DATA_DIR_PROMPT);
        gameChooser.setInitialDirectory(project);
        gameSource = gameChooser.showDialog(primaryStage);
        fileNames.add(gameSource);
    }

    public void configureModules(){
        FileChooser moduleChooser = new FileChooser();
        moduleChooser.setTitle("Select modules such as XML that this project needs");
        modules = moduleChooser.showOpenMultipleDialog(primaryStage);
        for(File module : modules){
            module.renameTo(new File(outPut.getAbsolutePath() + "\\" + module.getName()));
                module.delete();
        }
    }

    public void buildJar() {
        AppZip zipper = new AppZip();
        zipper.zip(outPut,fileNames,ignore,modules,project.getAbsolutePath());

    }

    private File findInDirectory(File directory, String target){
        if(directory.isDirectory()){
            for(File subDir : directory.listFiles()){
                File found = findInDirectory(subDir, target);
                if(found!=null){
                    return found;
                }
            }
        }
        if(directory.getName().equals(target)){
            return directory;
        }
        else return null;
    }

    private File findParentDirectory(File directory, String target) throws NullPointerException{
        for(File subFile : directory.listFiles()){
            System.out.println("    "+subFile.getName());
            if(subFile.getName().equals(target))
                return directory;
        }
        return findParentDirectory(directory.getParentFile(),target);
    }




    // private FileChooser(String title, boolean multiple, File opening, )







}
