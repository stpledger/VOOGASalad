package frontend.components;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.Tab;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;

/**
 * 
 * @author Collin Brown(Cdb55)
 *
 */
public class ComponentTab extends Tab{
	public static final double SCROLLBAR_WIDTH = 20;
	private ObjectProperty selectedElement = new SimpleObjectProperty();
	FlowPane pane;
	ScrollPane externalPane;
	double myComponentViewWidth;
	public ComponentTab(String name, double componentViewWidth) {
		super(name);
		myComponentViewWidth = componentViewWidth;
		this.setClosable(false);
		this.getStyleClass().add("component-tab");
		assemble();
	}
	
	public interface DragAndDropDynamicYoutility{
		public Node execute(Node n);
	}
	
	private void assemble() {
		externalPane = new ScrollPane();
		externalPane.setHbarPolicy(ScrollBarPolicy.NEVER);
		externalPane.setVbarPolicy(ScrollBarPolicy.ALWAYS);
		pane = new FlowPane();
		pane.setPrefWidth(myComponentViewWidth);
		//TODO: Make this dynamic to handle each individual component
		for(int i = 0; i < 8; i++) {
		pane.getChildren().add(new ComponentBox("bleh", "temp"));
		}
		externalPane.setContent(pane);
		this.setContent(externalPane);
	}
	/**
	 * 
	 * @return
	 */
	public Node getNode() {
		return pane;
	}
	
	public ObjectProperty getSelectedElementProperty() {
		return selectedElement;
	}
	
	
	
	private class ComponentBox extends Rectangle{
		
		public ComponentBox(String name, String imagePath) {
			this.setWidth((myComponentViewWidth - SCROLLBAR_WIDTH)/3);
			this.setHeight((myComponentViewWidth - SCROLLBAR_WIDTH)/3);
			this.getStyleClass().add("component-box");
			this.setOnMouseClicked(new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent arg0) {
					selectedElement.setValue(this);
				}
			});
		}
	}
	
}
