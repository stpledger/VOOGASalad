package authoring.factories;

import java.lang.reflect.InvocationTargetException;
import java.util.function.Consumer;

public class ElementFactory {

	private final static String PACKAGE_NAME = "authoring.factories.";
	private final static String ELEMENT = "Element";

	/**
	 * Empty constructor, makes it easier to be instantiated anywhere in the project
	 */
	public ElementFactory() {}

	/**
	 * 
	 * @param elementName Type of Element to be created
	 * @param text {@code String} text to be placed in the new Element
	 * @return Desired element
	 * @throws Exception
	 */
	public Element buildElement(ElementType elementName, String text) throws Exception {
		String builderName = PACKAGE_NAME+elementName+ELEMENT;
		Class<?> elementClass = Class.forName(builderName);
		return (Element) elementClass.getConstructor(text.getClass()).newInstance(text);		
	}
	
	public Element buildClickElement(ElementType elementName, String text, Consumer<Void> event) throws Exception {
		String builderName = PACKAGE_NAME+elementName+ELEMENT;
		Class<?> elementClass = Class.forName(builderName);
		ClickableElement element = (ClickableElement) elementClass.getConstructor(text.getClass()).newInstance(text);		
		element.handleConsumer(event);
		return element;
	}

}
