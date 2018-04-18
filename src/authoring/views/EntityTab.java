package authoring.views;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import data.DataRead;
import engine.components.Sprite;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.Tab;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;

/**
 * 
 * @author Collin Brown(Cdb55)
 *
 */
public class EntityTab extends Tab{
	public static final double SCROLLBAR_WIDTH = 20;
	private ObjectProperty selectedElement = new SimpleObjectProperty();
	FlowPane pane;
	ScrollPane externalPane;
	double myEntityViewWidth;
	public EntityTab(String name, double entityViewWidth) {
		super(name);
		myEntityViewWidth = entityViewWidth;
		this.setClosable(false);
		this.getStyleClass().add("entity-tab");
		assemble();
	}


	/**
	 * builds the ScrollPane and the FlowPane within it
	 */
	private void assemble() {
		externalPane = new ScrollPane();
		externalPane.setHbarPolicy(ScrollBarPolicy.NEVER);
		externalPane.setVbarPolicy(ScrollBarPolicy.ALWAYS);
		pane = new FlowPane();
		pane.setPrefWidth(myEntityViewWidth);
		externalPane.setContent(pane);
		this.setContent(externalPane);
	}
	public void addNewEntity(String type, Map<Class, Object[]> componentAttributes) {
		pane.getChildren().add(new EntityBox(type, componentAttributes));
	}


	/**
	 * Returns the graphic representation of the ComponentTab
	 */
	public Node getNode() {
		return pane;
	}

	/**
	 * Gets the observable property of the currently selected element
	 * @return
	 */
	public ObjectProperty getSelectedElementProperty() {
		return selectedElement;
	}


	/**
	 *	The ComponentBox holds the properties and images of various gameObjects
	 */
	private class EntityBox extends VBox {
		private String name;
		private String type;
		private Image image;
		private ImageView imageView;
		Map<Class, Object[]> componentAttributes = new HashMap<Class,Object[]>();
		private double boxDimension = (myEntityViewWidth - SCROLLBAR_WIDTH)/3;

		public EntityBox(String t, Map<Class, Object[]> m) {
			//Create the VBox
			this.getStyleClass().add("entity-box");
			this.setWidth(boxDimension);
			this.setHeight(boxDimension);
			//Create the ImageView
			componentAttributes = m;
			image = DataRead.loadImage((String) componentAttributes.get(Sprite.class)[0]);
			type = t;
			componentAttributes = m;
			imageView = new ImageView(image);
			imageView.setFitHeight(boxDimension-20);
			imageView.setFitWidth(boxDimension-20);
			this.getChildren().add(imageView);
			//Create a textbox with the name;

			//Set onClick method to add the item to clipboard
			this.setOnMouseClicked(new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent arg0) {
					try {
						selectedElement.setValue(new Object[] {getEntityFromPackage(type), componentAttributes});
					} catch (ClassNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

				private Class getEntityFromPackage(String type) throws ClassNotFoundException {
					return Class.forName("frontend.entities." + type);
				}
			});
		}

	}
}
