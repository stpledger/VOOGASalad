package frontend.components;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Observable;
import java.util.Observer;

import javax.imageio.ImageIO;
import javax.swing.event.ChangeEvent;

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

public class EntityView extends ViewComponent {
	private double entityViewWidth = 300;
	private TabPane pane;
	private ArrayList<String> tabsList = new ArrayList<String>();
	private Object clipboard;
	private ArrayList<String> entityTypes = new ArrayList<String>();
	
	public EntityView() {
		super();
		pane = new TabPane();
		pane.setPrefWidth(entityViewWidth);
		pane.getStyleClass().add("entity-view");
	}
	
	private void addTab(String type) {
		//TODO: Convert this to be reflection of all the components
			ClipboardListener c = new ClipboardListener();
			EntityTab temp = new EntityTab(type, entityViewWidth);
			temp.getSelectedElementProperty().addListener(c);
			pane.getTabs().add(temp);
	}
	
	/**
	 * Opens the window to create a new entity
	 */
	public void createEntity() {
		//TODO: Replace this with the real types of entities
		ArrayList<String> entityTypes = new ArrayList<String>();
		entityTypes.addAll(Arrays.asList(new String[] {"Block", "Character", "Game Object", "NPC", "Power Up"}));
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
        for(Tab tab : pane.getTabs()) {
        	if(tab.getText().equals(entityType)) {
        		((EntityTab) tab).addNewEntity("object", image);
        	}
        }
    }
	
	@Override
	public Node getNode() {
		return pane;
	}

	
	protected Broadcast buildBroadcast() {
		Broadcast b = new Broadcast();
		return b;
	}
	private class ClipboardListener implements ChangeListener{

		@Override
		public void changed(ObservableValue clipboardObject, Object oldValue, Object newValue) {
			broadcast.setMessage("setClipboard", new Object[] {newValue});	
			broadcast.setMessage("setTool", new Object[] {"addTool"});
		}
	}

}
