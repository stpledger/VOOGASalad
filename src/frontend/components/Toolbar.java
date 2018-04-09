package frontend.components;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;
import java.util.TreeMap;

import javafx.scene.Node;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.HBox;
import javafx.util.Pair;
/**
 * 
 * @author Collin Brown(cdb55)
 *
 */
public class Toolbar extends MenuBar{
	private ArrayList<Node> toolbarNodes = new ArrayList<Node>();
	
	public Toolbar() {
		super();
		this.getStyleClass().add("toolbar");
		addMenus(getMenuProperties());
	}
	
	
	private void addMenus(ArrayList<Pair<String,Properties>> menuProperties) {
		ArrayList<Pair<String,Properties>> tempMenuProperties = menuProperties;
		for(Pair<String, Properties> p: tempMenuProperties) {
			Menu m = new Menu();
			m.setText(p.getKey());
			Set<Object> items = p.getValue().keySet();
			
		}
	}

	/**
	 * Dynamically create a menu
	 * @param name the name of the menu
	 * @param items the items in a given menu
	 * @return a Node with the menu
	 */
	private Menu createMenu(Properties p) {
		
		return null;
	}
	
	/**
	 * Get all of the properties files located in the resources.menus folder
	 * @return An ArrayList of Pairs that has the name of the menu and the properties of that menu
	 */
	private ArrayList<Pair<String,Properties>> getMenuProperties(){
		ArrayList<Pair<String,Properties>> menuProperties = new ArrayList<Pair<String,Properties>>();
		File menuFolder = new File("resources/menus");
		File[] menuFiles = menuFolder.listFiles();
		for(File f : Arrays.asList(menuFiles)) {
			Properties p = new Properties();
			InputStream in = this.getClass().getResourceAsStream(f.getPath());
			try {
				p.load(in);
				in.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			menuProperties.add(new Pair(f.getName(),p));
		}
		return menuProperties;	
	}
}
	
	