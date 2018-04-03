package frontend.components;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import java.lang.reflect.Method;

import com.sun.prism.paint.Color;

import frontend.IDEView;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Paint;
public class Toolbar extends ViewComponent{
	private HBox toolbar;
	private ArrayList<Node> toolbarNodes = new ArrayList<Node>();
	
	private String[] fileMenuList = {"create", "load", "save"};
	private String[] gameMenuList = {"settings", "play"};

	public Toolbar(IDEView v) {
		super(v);
		toolbar = new HBox();
		addComponents();
		toolbar.getStyleClass().add("toolbar");
	}
	
	public Node getNode() {
		return toolbar;
	}
	
	private void addComponents() {
		List<Node> toAdd = new ArrayList<>();
		toAdd.add(createMenu("File", fileMenuList));
		toAdd.add(createMenu("Game", gameMenuList));
		toolbar.getChildren().addAll(toAdd);
		
	}
	private static void load() {
		System.out.println("file should be loaded");
	}
	
	private static void save() {
		System.out.println("file should be saved");
	}
	
	private static void create() {
		System.out.println("file should have been created");
	}
	
	private static void settings() {
		System.out.println("Settings window should be open");
	}
	private static void play() {
		System.out.println("Game should be playing");
	}
	
	private Node createMenu(String name, String[] items) {
		ComboBox<String> temp = new ComboBox<String>();
		temp.setPromptText(name);
		temp.getItems().addAll(Arrays.asList(items));
		temp.setOnAction(action -> {
			Method method;
			try {
				method = this.getClass().getDeclaredMethod(temp.getValue());
				method.invoke(null, null);
			} catch (Exception error) {
				//TODO: make better error handling
				error.printStackTrace();
			}
		});
		temp.getStyleClass().add("combo-box");
		return temp;
	}
}
	
	