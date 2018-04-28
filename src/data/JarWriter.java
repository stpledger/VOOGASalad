package data;

import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class JarWriter {
//do the things that will give you the parent directory
    //get authoring to create a file chooser splash screen to decide between url and file
    private List<File> fileNames;
    private List<File> ignore;
    private List<File> modules;
    private File outputDir;
    private File classSource;
    private File gameSource;
    private File project;
    private File main;
    private String projectName;

    private static final String OUT_INTELLIJ = "out";
    private static final String OUT_ECLIPSE = "bin";
    private static final String USER_DIR = "user.dir";
    private static final String BACKSLASH = "\\";
    private static final String FRONTSLASH = "/";
    private static final String GAME_DIR_PROMPT = "Please navigate to a game directory that you would like to write";
    private static final String IGNORE_PROMPT = "Specify what packages from src not to include";
    private static final String MODULE_PROMPT = "Specify which jar files you would like to include";
    private static final String OUT_PROMPT = "Select the output directory for this jar";
    private static final String MAIN_PROMPT = "Select the class with the main method that you would like to run";

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
        File classContainer = findInDirectory(project,OUT_ECLIPSE);
        classContainer = findInDirectory(project,OUT_INTELLIJ);
        classSource = findInDirectory(classContainer, projectName);
    }

    public void getIgnore(){
        ignore = new ArrayList<>();
        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setInitialDirectory(project);
        directoryChooser.setTitle(IGNORE_PROMPT);
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

    public void getOutput(){
        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setInitialDirectory(project);
        directoryChooser.setTitle(OUT_PROMPT);
        outputDir = directoryChooser.showDialog(primaryStage);
    }

    public void configureModules(){
        FileChooser moduleChooser = new FileChooser();
        moduleChooser.setTitle(MODULE_PROMPT);
        modules = moduleChooser.showOpenMultipleDialog(primaryStage);
        for(File module : modules){
            module.renameTo(new File(classSource.getAbsolutePath() + BACKSLASH + module.getName()));
        }
    }

    public void buildJar(){
        JarZip zipper = new JarZip();
        zipper.zip(classSource,fileNames,ignore,modules,project.getAbsolutePath(), outputDir,main);

    }

    public void selectMain(){
        FileChooser mainChooser = new FileChooser();
        mainChooser.setTitle(MAIN_PROMPT);
        mainChooser.setInitialDirectory(project);
        main = mainChooser.showOpenDialog(primaryStage);
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





    // private FileChooser(String title, boolean multiple, File opening, )







}
