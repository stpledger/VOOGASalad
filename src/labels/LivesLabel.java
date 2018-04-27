package labels;
import java.text.MessageFormat;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.StringBinding;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.control.Label;

public class LivesLabel extends Label{

	private int currentLives;
	private final String LIVES_LABEL_NAME = "Lives: ";
	private SimpleIntegerProperty livesProperty;
	
	
	public LivesLabel(int lives) {
		currentLives = lives;
		livesProperty = new SimpleIntegerProperty(currentLives);
		StringBinding binding = Bindings.createStringBinding(
	            () -> MessageFormat.format(LIVES_LABEL_NAME+"{0}", livesProperty.getValue()));
		this.textProperty().bind(binding);
	}
	
	
}
