package authoring.views;

import java.util.Map;
import java.util.logging.Logger;

import authoring.entities.Background;
import authoring.entities.Block;
import authoring.entities.Enemy;
import authoring.entities.Player;
import authoring.factories.ElementFactory;
import authoring.factories.ElementType;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.Node;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.input.MouseButton;
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
	
	private final static Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
	
	private ElementFactory eFactory;
	
	
	FlowPane pane;
	ScrollPane externalPane;
	
	double myEntityViewWidth;
	
	public EntityTab(String name, double entityViewWidth) {
		super(name);
		myEntityViewWidth = entityViewWidth;
		
		eFactory = new ElementFactory();
		
		Block block = new Block(0);
		Enemy enemy = new Enemy(0);
		Player player = new Player(0);
		Background bgrnd =  new Background(0);
		
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
	
	/**
	 * 
	 * @param type
	 * @param componentAttributes
	 */
	public void addNewEntity(String type, Map<Class, Object[]> componentAttributes) {
		EntityBox eb = new EntityBox(type, componentAttributes);
		eb.setOnMouseClicked(mouseEvent -> {
			if(mouseEvent.getButton().equals(MouseButton.SECONDARY)) {
				ContextMenu cMenu = new ContextMenu();
				try {
					MenuItem delete = (MenuItem) eFactory.buildElement(ElementType.MenuItem, "Delete");
					delete.setOnAction(e -> {
						removeEntity(eb);
					});
					cMenu.getItems().add(delete);
					
					cMenu.setAutoHide(true);
				} catch (Exception e1) {
					LOGGER.log(java.util.logging.Level.SEVERE, e1.toString(), e1);
				}
				cMenu.show(pane, mouseEvent.getScreenX(), mouseEvent.getScreenY());
			}
		});
		pane.getChildren().add(eb);
	}


	/**
	 * Returns the graphic representation of the ComponentTab
	 */
	public Node getNode() {
		return pane;
	}
	
	/**
	 * Removes and entity from the entityView
	 * @param eb the entitBox to be removed
	 */
	private void removeEntity(EntityBox eb) {
		pane.getChildren().remove(eb);
	}


}
