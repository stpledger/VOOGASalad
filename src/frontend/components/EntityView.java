package frontend.components;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.imageio.ImageIO;
import javax.swing.event.ChangeEvent;

import com.sun.beans.finder.ClassFinder;
import com.sun.prism.paint.Color;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Pair;
import sun.reflect.Reflection;

public class EntityView extends TabPane {
	private double entityViewWidth = 300;
	private ArrayList<String> tabsList = new ArrayList<String>();
	private Object clipboard;
	private ArrayList<String> entityTypes = new ArrayList<String>();
	
	public EntityView() {
		super();
		this.setPrefWidth(entityViewWidth);
		this.getStyleClass().add("entity-view");
	}
	
	private void addTab(String type) {
			ClipboardListener c = new ClipboardListener();
			EntityTab temp = new EntityTab(type, entityViewWidth);
			temp.getSelectedElementProperty().addListener(c);
			this.getTabs().add(temp);
	}
	
	/**
	 * Opens the window to create a new entity
	 */
	public void createEntity() {
		entityTypes.addAll(Arrays.asList(getEntitiesInEntitiesPackage()));
		EntityBuilderView entityBuilderView = new EntityBuilderView(entityTypes, broadcast);
			
	}
	/**
	 * Opens the window to delete an entity
	 */
	public void deleteEntity() {
	}
	/**
	 * Opens the window to edit an entity
	 */
	public void editEntity() {
		
	}
	/**
	 * Called when a EntityBuilderView is closed
	 * @param entityType String representing the type of entity that this object represents
	 * @param imgFile Image file corresponding to the Sprite Image for this object.
	 */
	public void saveEntity(String entityType, File imgFile) {
	//Turn the imageFile into a usableImage
		BufferedImage bufferedImage = null;
		try {
			bufferedImage = ImageIO.read(imgFile);
		} catch (IOException e) {
			//TODO: Show ImageNotFoundException
		}
        Image image = SwingFXUtils.toFXImage(bufferedImage, null);
    //Check to see if a tab exists for the type
        if(tabsList.isEmpty() || !tabsList.contains(entityType)) { 
        	addTab(entityType);
        	tabsList.add(entityType);
        }   
    //Add the entityBox
        for(Tab tab : this.getTabs()) {
        	if(tab.getText().equals(entityType)) {
        		((EntityTab) tab).addNewEntity("object", image);
        	}
        }
    }
	
	/**
	 * Gets all of the class names from a given package. Useful when determining which properties can be changed.
	 * @param pckgname name of the package in which to look for these properties
	 * @return a String array of classes from a given package
	 */
	protected String[] getEntitiesInEntitiesPackage() {
		String pckgName = "entities";
        ClassLoader cld = Thread.currentThread().getContextClassLoader();
        if (cld == null) {
            throw new IllegalStateException("Can't get class loader.");
        }
 
        URL resource = cld.getResource(pckgName.replace('.', '/'));
        if (resource == null) {
            throw new RuntimeException("Package " + pckgName + " not found on classpath.");
        }
        File directory = new File(resource.getFile());
        if (!directory.exists()) {
            throw new IllegalArgumentException("Could not get directory resource for package " + pckgName + ".");
        }
        List<String> classes = new ArrayList<String>();
        for (String filename : directory.list()) {
            if (filename.endsWith(".class") && !filename.startsWith("Entity")) { //Check to make sure its a class file and not the superclass
                String classname = buildClassname(pckgName, filename);
                try {
                		String clazz = Class.forName(classname).toString();
                		// Strip everything except for the word following the last period (the actual class name)
                    classes.add(clazz.substring(clazz.lastIndexOf(".") + 1));
                } catch (ClassNotFoundException e) {
                    System.err.println("Error creating class " + classname);
                }
            }
        }
        return classes.toArray(new String[classes.size()]);
	}
	
	/**
	 * Builds the class name to fully represent a given class
	 * @param pckgname the package to look for the class ine
	 * @param filename the name of the class file
	 * @return a String representing the fully-qualified class name
	 */
    private String buildClassname(String pckgName, String fileName) {
    		String className = pckgName + '.' + fileName.replace(".class", "");
        System.out.println(className);
        return className;
    }
	
	@Override
	public Node getNode() {
		return pane;
	}
	
	private class ClipboardListener implements ChangeListener{

		@Override
		public void changed(ObservableValue clipboardObject, Object oldValue, Object newValue) {
			broadcast.setMessage("setClipboard", new Object[] {newValue});	
			broadcast.setMessage("setTool", new Object[] {"addTool"});
		}
	}

}
