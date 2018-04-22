package authoring.views;

import java.util.HashMap;
import java.util.Map;

import data.DataRead;
import engine.components.Sprite;
import javafx.event.EventHandler;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.VBox;

public class EntityBox extends VBox {
	private String name;
	private String type;
	private Image image;
	private ImageView imageView;
	Map<Class, Object[]> componentAttributes = new HashMap<Class,Object[]>();
	private double boxDimension = (EntityView.ENITITY_VIEW_WIDTH - EntityTab.SCROLLBAR_WIDTH)/3;

	public EntityBox(String t, Map<Class, Object[]> m) {
		this.getStyleClass().add("entity-box");
		this.setWidth(boxDimension);
		this.setHeight(boxDimension);
		componentAttributes = m;
		image = DataRead.loadImage((String) componentAttributes.get(Sprite.class)[0]);
		type = t;
		imageView = new ImageView(image);
		imageView.setFitHeight(boxDimension-20);
		imageView.setFitWidth(boxDimension-20);
		this.getChildren().add(imageView);
		this.setOnDragDetected(e -> {
			Dragboard db = this.startDragAndDrop(TransferMode.COPY);
			ClipboardContent cc = new ClipboardContent();
			cc.putImage(image);
			cc.putString(t);	
			db.setContent(cc);
			e.consume();
		});

		this.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent arg0) {
			}
			
			private Class getEntityFromPackage(String type) throws ClassNotFoundException {
				return Class.forName("authoring.entities." + type);
			}
		});
	}

}
