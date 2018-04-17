package frontend.factories;

public class ElementFactory {
	
	private final String PACKAGE_NAME = "frontend.factories.";
	private final String ELEMENT = "Element";
	
	public ElementFactory() {}
	
	public Element buildElement(ElementType elementName, String text) throws Exception {
		String builderName = PACKAGE_NAME+elementName+ELEMENT;
		Class<?> elementClass = Class.forName(builderName);
		//TODO extend element factory to make toolbars
		return (Element) elementClass.getConstructor(text.getClass()).newInstance(text);		
	}

}
