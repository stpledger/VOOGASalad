package authoring.views;

import java.util.Map;
import java.util.logging.Logger;

import authoring.entities.Background;
import authoring.entities.Block;
import authoring.entities.Enemy;
import authoring.entities.Player;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.control.Tab;
import javafx.scene.layout.FlowPane;

/**
 * 
 * @author Collin Brown(Cdb55)
 *
 */
public class EntityTab extends Tab{
	public static final double SCROLLBAR_WIDTH = 20;
	public static final double VIEW_WIDTH = 0;
	
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


}
