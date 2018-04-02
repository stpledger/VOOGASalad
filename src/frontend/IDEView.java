package frontend;

import frontend.components.ViewBuilder;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
/**
 * 
 * @author Collin Brown(cdb55)
 *
 */
public class IDEView {
	private static double ideHeight = 600;
	private static double ideWidth = 1200;
	private double toolbarHeight = 25;
	private double componentViewWidth = 300;
	
	private ViewBuilder viewBuilder;
	private static Parent layout;
	
	public IDEView() {
		viewBuilder = new ViewBuilder(this);
		layout = viewBuilder.build();
	}
	
	public static Scene getScene() {
		Scene s = new Scene(layout, ideWidth, ideHeight);
		// s.getStylesheets().add("styles.css"); TODO: Add a styles.css
		return s;
	}
	public double getIdeHeight() {
		return ideHeight;
	}
	public double getIdeWidth() {
		return ideWidth;
	}
	public void update() {
		layout = viewBuilder.build();
	}

}
