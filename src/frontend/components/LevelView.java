package frontend.components;

import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.GridPane;
/**
 *  
 * @author Hemanth Yakkali((hy115)
 *
 */
public class LevelView extends ScrollPane {
	
	private GridPane pane;
	private Level level;
	
	public LevelView(Level level, int levelNum) {
		this.getStyleClass().add("level-view");
		this.setLevel(level);
		this.setContent(pane);
		this.getStyleClass().add("level-view-wrapper");
		this.setOnMouseClicked(e-> {		
			if(e.getButton().equals(MouseButton.SECONDARY)) {
				LevelPropertiesView lView = new LevelPropertiesView(level, levelNum);
				lView.open();
			} else if (e.getButton().equals(MouseButton.PRIMARY)) {
				if (e.getClickCount() == 2) {
					LocalPropertiesView LPV = new LocalPropertiesView(1);
					LPV.open();
				}
				if (e.getClickCount() == 3) {
					HUDPropertiesView HPV = new HUDPropertiesView(level);
					HPV.open();
				}
			}
		});
	}

	public Level getLevel() {
		return level;
	}

	public void setLevel(Level level) {
		this.level = level;
	}

}
