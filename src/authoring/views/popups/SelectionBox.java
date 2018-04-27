package authoring.views.popups;

import java.util.Properties;
import java.util.function.Consumer;
import java.util.logging.Logger;

import authoring.MainApplication;
import authoring.factories.ElementFactory;
import authoring.factories.ElementType;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
/**
 * 
 * @author Collin Brown(Cdb55)
 *
 */
public class SelectionBox extends VBox implements PopUp {
	
	Properties language = new Properties();
	String selection = "";
	Stage stage;
	
	ElementFactory eFactory = new ElementFactory();
	private final static Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
	
	public SelectionBox(String[] options, Consumer consumer){
		this.fill(options, consumer);
		this.show();
	}
	/**
	 * Builds the list of options
	 * @param options
	 */
	private void fill(String[] options, Consumer consumer) {
		for(String o: options) {
			try {
			Label label = (Label) eFactory.buildElement(ElementType.Label, o);
			label.setOnMouseClicked(e->{
				selection = label.getId();
				onClose(consumer);
				stage.close();
			});
			this.getChildren().add(label);
			} catch (Exception e) {
				LOGGER.log(java.util.logging.Level.SEVERE, e.toString(), e);
			}
		}
	}

	@Override
	public void show() {
		stage = new Stage();
		this.getStyleClass().add("selection-box");
		stage.setScene(new Scene(this));
		stage.getScene().getStylesheets().add(MainApplication.class.getResource("styles.css").toExternalForm());
		stage.show();
		stage.sizeToScene();
		
		
	}

	@Override
	public void onClose(Consumer consumer) {
		consumer.accept(selection);
		
	}

	public void setLanguage(Properties lang) {
		this.language = lang;
		
	}
}
