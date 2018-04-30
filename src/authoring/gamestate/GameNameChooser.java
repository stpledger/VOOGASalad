package authoring.gamestate;

import java.util.Properties;
import java.util.function.Consumer;

import authoring.MainApplication;
import authoring.exceptions.AuthoringAlert;
import authoring.exceptions.AuthoringException;
import authoring.factories.ElementFactory;
import authoring.factories.ElementType;
import authoring.views.AuthoringPane;
import authoring.views.popups.PopUp;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Displays a pop-up to select the name of the game. 
 * @author Dylan Powers
 *
 */
public class GameNameChooser implements AuthoringPane {

	private final int SCREEN_WIDTH = 400;
	private final int SCREEN_HEIGHT = 400;
	private final int TEXT_WIDTH = 300;
	private final String BACKGROUND_CSS = "name-selector";
	private final String TEXT_FIELD_CSS = "name-text-box";
	private final String PROMPT = "Please select a name for your game!";
	private VBox root;
	private TextField field;
	private String name;
	private final String ERROR_MESSAGE = "Please enter a non-empty name.";
	
	/**
	 * Initialize with a VBox as the root.
	 */
	public GameNameChooser() {
		root = new VBox();
		root.setAlignment(Pos.CENTER);
		root.setMaxWidth(300);
	}

	/**
	 * Show this pop-up by adding the necessary JavaFX elements.
	 */
	public void showAndWait(Consumer<String> onClose) {
		Stage stage = new Stage();
		Scene scene = new Scene(root, SCREEN_WIDTH, SCREEN_HEIGHT);
		scene.getStylesheets().add(MainApplication.class.getResource("styles.css").toExternalForm());
		root.getStyleClass().add(BACKGROUND_CSS);
		ElementFactory ef = new ElementFactory();
		try {
			field = (TextField) ef.buildElement(ElementType.TextField, PROMPT);
			field.setMaxWidth(TEXT_WIDTH);
			root.getChildren().add(field);
			field.getStyleClass().add(TEXT_FIELD_CSS);
		} catch (Exception e) {
			throw new AuthoringException(e, AuthoringAlert.NO_SHOW);
		}
		stage.setTitle(PROMPT);
		stage.setScene(scene);
		stage.show();
		stage.centerOnScreen();
		scene.addEventHandler(KeyEvent.KEY_PRESSED, key -> {
			if (key.getCode().equals(KeyCode.ENTER)) {
				if (!(field.getText() == null || field.getText().trim().isEmpty())) {
					onClose.accept(field.getText());
					stage.close();
				} else {
					throw new AuthoringException(ERROR_MESSAGE, AuthoringAlert.SHOW);
				}
			}
				
		});
	}

	/**
	 * Gets the name contained in this box. Should only happen after the stage has closed.
	 */
	public String getName() {
		if (this.name != null) return this.name;
		throw new AuthoringException(ERROR_MESSAGE, AuthoringAlert.SHOW);
	}
	/**
	 * Sets the language of this pane
	 */
	@Override
	public void setLanguage(Properties lang) {
	}

}
