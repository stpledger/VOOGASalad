package authoring.entities.data;

import java.io.File;
import java.util.Map;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Element;
import authoring.exceptions.AuthoringException;
import org.w3c.dom.Document;

/**
 * This class handles parsing of the XML files that set up the type of simulation.
 * @author dylanpowers
 *
 */
public class EntitySaver {
	private static DocumentBuilder DOCUMENT_BUILDER;
	private static final String DOCUMENT_TITLE = "Entity";
	private static final String XML_EXTENSION = ".xml";
	public static final String ERROR_MESSAGE = "XML file does not properly represent %s";
	private static final String FOLDER_PATH = "data/";
	private static final CharSequence COMPONENT_PREFIX = "engine.components.";
	
	public EntitySaver() {
		DOCUMENT_BUILDER = getDocumentBuilder();
	}
	
	/**
	 * Writes the data given in a map, to a given XML file.
	 * Most code was taken from https://examples.javacodegeeks.com/core-java/xml/parsers/documentbuilderfactory/create-xml-file-in-java-using-dom-parser-example/
	 * @param attributes the attributes of an entity to save
	 * @param fileName the name of the file to write to
	 * @throws TransformerFactoryConfigurationError 
	 * @throws TransformerException 
	 */
	public void writeXML(Map<Class, Object[]> attributes, String fileName) throws TransformerFactoryConfigurationError, TransformerException {
		Document document = DOCUMENT_BUILDER.newDocument();
		Element root = document.createElement(DOCUMENT_TITLE);
		root.setAttribute("name", fileName);
		document.appendChild(root);
		for (Class compClass : attributes.keySet()) {
			String compName = compClass.getName().replace(COMPONENT_PREFIX, "");
			Element comp = document.createElement(compName);
			root.appendChild(comp);
			for (Object o : attributes.get(compClass)) {
        			comp.appendChild(document.createTextNode(String.valueOf(o)));
			}
		}
		
		Transformer t = TransformerFactory.newInstance().newTransformer();
		DOMSource ds = new DOMSource(document);
		StreamResult sr = new StreamResult(new File(FOLDER_PATH + fileName + XML_EXTENSION));
		t.transform(ds, sr);
		System.out.println("XML file created and saved to " + FOLDER_PATH + fileName);
	}
	
	/**
	 * Generate the {@code DocumentBuilder} for finding and parsing the root of the document.
	 * @return a {@code DocumentBuilder} object
	 */
	private DocumentBuilder getDocumentBuilder() {
		try {
			return DocumentBuilderFactory.newInstance().newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			throw new AuthoringException(e);
		}
	}
}
