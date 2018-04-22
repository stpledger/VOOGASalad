package authoring.factories;

public class ElementFactory {

	private final String PACKAGE_NAME = "authoring.factories.";
	private final String ELEMENT = "Element";

	public ElementFactory() {}

	public Element buildElement(ElementType elementName, String text) throws Exception {
		String builderName = PACKAGE_NAME+elementName+ELEMENT;
		Class<?> elementClass = Class.forName(builderName);
		return (Element) elementClass.getConstructor(text.getClass()).newInstance(text);		
	}

}
