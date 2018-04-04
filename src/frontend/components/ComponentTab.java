package frontend.components;

import frontend.IDEView;
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
	IDEView ideView;
	FlowPane pane;
	ScrollPane externalPane;
	public ComponentTab(String name, IDEView v) {
		super(name);
		ideView = v;
		this.setClosable(false);
		this.getStyleClass().add("component-tab");
		assemble();
	}
	
	public interface DragAndDropDynamicYoutility {
		public Node execute(Node n);
	}
	
	private static final class DragContext {
		public double mouseAnchorX;
		public double mouseAnchorY;
		public double initialLayoutX;
		public double initialLayoutY;
	}
	
	DragAndDropDynamicYoutility daddy = (n) -> {
		final DragContext dragContext = new DragContext();
		
		n.addEventFilter(
                MouseEvent.MOUSE_PRESSED,
                new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(final MouseEvent mouseEvent) {
                    		n.setCursor(Cursor.CLOSED_HAND);
                            dragContext.mouseAnchorX = mouseEvent.getX();
                            dragContext.mouseAnchorY = mouseEvent.getY();
                            dragContext.initialLayoutX =
                                    n.getTranslateX();
                            dragContext.initialLayoutY =
                                    n.getTranslateY();
                    }
                });
		
		n.addEventFilter(MouseEvent.MOUSE_RELEASED,
				new EventHandler<MouseEvent>() {
					@Override
					public void handle(final MouseEvent mouseEvent) {
						n.setCursor(Cursor.DEFAULT);
					}
		});
 
        n.addEventFilter(
                MouseEvent.MOUSE_DRAGGED,
                new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(final MouseEvent mouseEvent) {
                            n.setTranslateX(
                                    dragContext.initialLayoutX
                                        + mouseEvent.getX()
                                        - dragContext.mouseAnchorX);
                            n.setTranslateY(
                                    dragContext.initialLayoutY
                                        + mouseEvent.getY()
                                        - dragContext.mouseAnchorY);
                    }
                });
        n.setOnDragExited(new EventHandler<DragEvent>() {

			@Override
			public void handle(DragEvent event) {
				System.out.println("m");
				
			}
        	
        });
		    return n;
	};
	
	private void assemble() {
		externalPane = new ScrollPane();
		externalPane.setHbarPolicy(ScrollBarPolicy.NEVER);
		externalPane.setVbarPolicy(ScrollBarPolicy.ALWAYS);
		pane = new FlowPane();
		pane.setPrefWidth(ideView.getComponentViewWidth());
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
	
	
	
	private class ComponentBox extends Rectangle{
		
		public ComponentBox(String name, String imagePath) {
			this.setWidth((ideView.getComponentViewWidth() - SCROLLBAR_WIDTH)/3);
			this.setHeight((ideView.getComponentViewWidth() - SCROLLBAR_WIDTH)/3);
			this.getStyleClass().add("component-box");
			daddy.execute(this);
		}
	}
	
}
