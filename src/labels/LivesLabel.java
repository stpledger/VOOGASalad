package labels;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.control.Label;

public class LivesLabel extends Label{

	private int currentLives;
	private final String Lives_LABEL_NAME = "Lives: ";
	
	
	public LivesLabel(int lives) {
		this.setText(Lives_LABEL_NAME);
		currentLives = lives;
		this.textProperty().bind(new SimpleIntegerProperty(currentLives).asString());
	}
	
	
}
