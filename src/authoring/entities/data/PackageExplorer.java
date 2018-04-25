package authoring.entities.data;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class PackageExplorer {

	public PackageExplorer() {
		//
	}
	
	/**
	 * Gets all of the class names from a given package. Useful when determining which properties can be changed.
	 * @return a String array of classes from a given package
	 */
	public static String[] getElementsInPackage(String packageName) {
		ClassLoader cld = Thread.currentThread().getContextClassLoader();
		if (cld == null) {
			throw new IllegalStateException("Can't get class loader.");
		}

		URL resource = cld.getResource(packageName.replace('.', '/'));
		if (resource == null) {
			throw new RuntimeException("Package " + packageName + " not found on classpath.");
		}
		File directory = new File(resource.getFile());
		if (!directory.exists()) {
			throw new IllegalArgumentException("Could not get directory resource for package " + packageName + ".");
		}
		List<String> classes = new ArrayList<>();
		for (String filename : directory.list()) {
			if (filename.endsWith(".class") && !filename.startsWith("Entity")) { //Check to make sure its a class file and not the superclass
				String classname = buildClassname(packageName, filename);
				String clazz = classname.replace(".java", "");
				// Strip everything except for the word following the last period (the actual class name)
				classes.add(clazz.substring(clazz.lastIndexOf(".") + 1));
			}
		}
		return classes.toArray(new String[classes.size()]);
	}
	
	/**
	 * Builds the class name to fully represent a given class
	 * @param pckgName the package to look for the class ine
	 * @param fileName the name of the class file
	 * @return a String representing the fully-qualified class name
	 */
	private static String buildClassname(String pckgName, String fileName) {
		return pckgName + '.' + fileName.replace(".class", "");
	}
	
}
