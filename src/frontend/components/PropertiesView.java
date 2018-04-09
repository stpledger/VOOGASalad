package frontend.components;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
/**
 * 
 * @author Dylan Powers
 *
 */
public abstract class PropertiesView {
	
	private final int GRID_SEPARATION = 10;
	private final int HEIGHT = 450;
	private final int WIDTH = 450;
	private GridPane root;
	
	/**
	 * Initialize the root of this window as a {@code GridPane}.
	 */
	public PropertiesView() {
		root = new GridPane();
		root.setAlignment(Pos.CENTER);
		root.setHgap(GRID_SEPARATION);
		root.setVgap(GRID_SEPARATION);
	}

	/**
	 * Opens the Property Editor window.
	 */
	protected void open() {
		Stage stage = new Stage();
		stage.setTitle(this.title());
		stage.setScene(new Scene(root, WIDTH, HEIGHT));
		stage.show();
	}
	
	/**
	 * Fills the window with the appropriate names and fields.
	 * @param fields a map with component names that map {@code true} if the box should be strictly numeric, and {@code false} if not.
	 */
	protected abstract void fill();

	/**
	 * Get the title that this window should display.
	 * @return the title of the window.
	 */
	protected abstract String title();
	
	/**
	 * Nested class to created a text field that only accepts numbers.
	 * @param args
	 */
	class NumberField extends TextField {
		@Override
		public void replaceText(int start, int end, String text) {
			if (isNumber(text))
				super.replaceText(start, end, text);
		}
		
		@Override
		public void replaceSelection(String text) {
			if (isNumber(text))
				super.replaceSelection(text);
		}
		
		private boolean isNumber(String input) {
			return input.matches("[0-9]*");
		}
	}
	
	/**
	 * Gets all of the class names from a given package. Useful when determining which properties can be changed.
	 * @param pckgname name of the package in which to look for these properties
	 * @return a String array of classes from a given package
	 */
	protected String[] getClassesInPackage(String pckgName) {
        ClassLoader cld = Thread.currentThread().getContextClassLoader();
        if (cld == null) {
            throw new IllegalStateException("Can't get class loader.");
        }
 
        URL resource = cld.getResource(pckgName.replace('.', '/'));
        if (resource == null) {
            throw new RuntimeException("Package " + pckgName + " not found on classpath.");
        }
        File directory = new File(resource.getFile());
        if (!directory.exists()) {
            throw new IllegalArgumentException("Could not get directory resource for package " + pckgName + ".");
        }
        List<String> classes = new ArrayList<String>();
        for (String filename : directory.list()) {
            if (filename.endsWith(".class")) {
                String classname = buildClassname(pckgName, filename);
                try {
                		String clazz = Class.forName(classname).toString();
                		// Strip everything except for the word following the last period (the actual class name)
                    classes.add(clazz.substring(clazz.lastIndexOf(".") + 1));
                } catch (ClassNotFoundException e) {
                    System.err.println("Error creating class " + classname);
                }
            }
        }
        return classes.toArray(new String[classes.size()]);
	}
 
	/**
	 * Builds the class name to fully represent a given class
	 * @param pckgname the package to look for the class ine
	 * @param filename the name of the class file
	 * @return a String representing the fully-qualified class name
	 */
    private String buildClassname(String pckgName, String fileName) {
    		String className = pckgName + '.' + fileName.replace(".class", "");
        System.out.println(className);
        return className;
    }
    
    /**
     * Gets and returns the JavaFX-related root of the {@code PropertiesView}.
     * @return the {@code GridPane} root.
     */
	protected GridPane getRoot() {
		return this.root;
	}
}
