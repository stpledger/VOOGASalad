package frontend.components;

import frontend.IDEView;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.Tab;
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
	IDEView ideView;
	GridPane pane;
	ScrollPane externalPane;
	public ComponentTab(String name, IDEView v) {
		super(name);
		ideView = v;
		this.setClosable(false);
		this.getStyleClass().add("component-tab");
		assemble();
	}
	
	public interface DragAndDropDynamicYoutility{
		public Group execute(Node n);
	}
	
	private static final class DragContext {
		public double mouseAnchorX;
		public double mouseAnchorY;
		public double initialTranslateX;
		public double initialTranslateY;
	}
	
	DragAndDropDynamicYoutility daddy = (n) -> {
		final DragContext dragContext = new DragContext();
		final Group wrapper = new Group(n);
		
		n.setOnMouseClicked(new EventHandler<MouseEvent>() {
			            public void handle(final MouseEvent mouseEvent) {
			                    dragContext.mouseAnchorX = mouseEvent.getX();
			                    dragContext.mouseAnchorY = mouseEvent.getY();
			                    dragContext.initialTranslateX =
			                        n.getTranslateX();
			                    dragContext.initialTranslateY =
			                        n.getTranslateY();
			                    System.out.println("oh");
			            }   
			            });
		 n.setOnMouseDragged( new EventHandler<MouseEvent>() {
		                public void handle(final MouseEvent mouseEvent) {
		                        n.setTranslateX(
		                            dragContext.initialTranslateX
		                                + mouseEvent.getX()
		                                - dragContext.mouseAnchorX);
		                        n.setTranslateY(
		                            dragContext.initialTranslateY
		                                + mouseEvent.getY()
		                                - dragContext.mouseAnchorY);
		                }
		            });
		    System.out.println(wrapper.toString());
		    return wrapper;
	};
	
	private void assemble() {
		externalPane = new ScrollPane();
		externalPane.setHbarPolicy(ScrollBarPolicy.NEVER);
		pane = new GridPane();
		pane.setPrefWidth(ideView.getComponentViewWidth());
		//TODO: Make this dynamic to handle each individual component
		for(int i = 0; i < 9; i++) {
		pane.add(new ComponentBox("bleh", "temp"), 0, i);
		pane.add(new ComponentBox("bleh", "temp"), 1, i);
		pane.add(new ComponentBox("bleh", "temp"), 2, i);
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
	
	
	
	private class ComponentBox extends Rectangle{
		
		public ComponentBox(String name, String imagePath) {
			this.setWidth((ideView.getComponentViewWidth() - SCROLLBAR_WIDTH)/3);
			this.setHeight((ideView.getComponentViewWidth() - SCROLLBAR_WIDTH)/3);
			this.getStyleClass().add("component-box");
			daddy.execute(this);
		}
	}
	
}
