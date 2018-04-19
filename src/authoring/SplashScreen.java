package authoring;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Properties;
import java.util.function.Consumer;

import javax.imageio.ImageIO;

import authoring.views.MainView;
import javafx.beans.property.Property;
import javafx.embed.swing.SwingFXUtils;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

/**
 * 
 * @author Collin Brown(cdb55)
 *
 */
public class SplashScreen extends VBox {
	Properties properties;
	Consumer changeScene;
	
	public SplashScreen(Consumer<Scene> cs) {
		//Load the menu properties
		 properties = new Properties();
		 changeScene = cs;
		try {
			properties.load(new FileInputStream("src/resources/menus/SplashScreen/SplashScreen.properties"));
		} catch (IOException e) {
			System.out.println("Error Loading SplashScreen");
		}
		
		//Set the basics
		this.setWidth(400);
		this.setHeight(300);
		this.setAlignment(Pos.CENTER);
		this.getStyleClass().add("root");
		this.getStyleClass().add("splash-screen-wrapper");
		
		//Create the buttons
		for(String key: properties.stringPropertyNames()) {
			try {
				this.getChildren().add(buildButton(key, this.getClass().getMethod(key)));
			} catch (NoSuchMethodException | SecurityException e) {
				System.out.println("Error creating button " + key);
				e.printStackTrace();
			}
		}
		
	}

	public void playGame() {
		
	}
	/**
	 * Creates a new gameAuthoringEnviornment view based on the 
	 */
	public void loadAuthor() {
		FileChooser fileChooser = new FileChooser();
		File selectedFile = fileChooser.showOpenDialog(null);
		MainView mainView  = new MainView(selectedFile);
		Parent layout = mainView.build();
		Scene s = new Scene(layout, mainView.getIDEWidth(), mainView.getIDEHeight());
		s.getStylesheets().add(MainApplication.class.getResource("styles.css").toExternalForm());
        changeScene.accept(s);
	}
	public void newAuthor() {
		MainView mainView  = new MainView();
		Parent layout = mainView.build();
		Scene s = new Scene(layout, mainView.getIDEWidth(), mainView.getIDEHeight());
		s.getStylesheets().add(MainApplication.class.getResource("styles.css").toExternalForm());
        changeScene.accept(s);
	}

	/**
	 * Builds a button with the text equivalent to 
	 * @param name the name of the type of button to build
	 * @param method the consumer to be invoked on click
	 * @return the final button
	 */
	public Button buildButton(String name, Method method) {
		Button b = new Button();
		b.setText(properties.getProperty(name));
		b.setMinWidth(this.getSplashWidth());
		b.getStyleClass().add("splash-screen-button");
		b.setOnMouseClicked((e)->{
		try {
			method.invoke(this, null);
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e1) {
			e1.printStackTrace();
		}});
		return b;	
	}
	
	/**
	 * Returns a double that is the size of the splashScreen window
	 * @return double SplashScreenHeight
	 */
	public double getSplashHeight() {
		return this.getHeight();
	}
	
	/**
	 * Returns a double that is the size of the splashScreen window
	 * @return double SplashScreenWidth
	 */
	public double getSplashWidth() {
		return this.getWidth();
	}
}
