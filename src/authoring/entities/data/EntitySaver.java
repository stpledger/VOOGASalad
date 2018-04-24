package authoring.entities.data;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.stream.Stream;
import java.util.HashMap;
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
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.w3c.dom.Document;

/**
 * This class handles parsing of the XML files that set up the type of simulation.
 * @author dylanpowers
 *
 */
public class EntitySaver {
	private static DocumentBuilder DOCUMENT_BUILDER;
	private final String COMPONENT_WRAPPER = "Component";
	private final String DOCUMENT_TITLE = "Entity";
	private final String XML_EXTENSION = ".xml";
	public final static String ERROR_MESSAGE = "XML file does not properly represent %s";
	private final static String FOLDER_PATH = "data/";
	
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
	public void writeXML(Map<String, Object[]> attributes, String fileName) throws TransformerFactoryConfigurationError, TransformerException {
		Document document = DOCUMENT_BUILDER.newDocument();
		Element root = document.createElement(DOCUMENT_TITLE);
		root.setAttribute("name", fileName);
		document.appendChild(root);
		
		for (String compName : attributes.keySet()) {
			Element componentWrapper = document.createElement(COMPONENT_WRAPPER);
			root.appendChild(componentWrapper);
			componentWrapper.setAttribute("type", compName);
			for (Object o : attributes.get(compName)) {
        			componentWrapper.appendChild(document.createTextNode(String.valueOf(o) + " "));
			}
		}
		
		Transformer t = TransformerFactory.newInstance().newTransformer();
		DOMSource ds = new DOMSource(document);
		StreamResult sr = new StreamResult(new File(FOLDER_PATH + fileName + XML_EXTENSION));
		t.transform(ds, sr);
		System.out.println("XML file created and saved to " + FOLDER_PATH + fileName);
	}
	
	// generate the document builder for parsing the document
	private DocumentBuilder getDocumentBuilder() {
		try {
			return DocumentBuilderFactory.newInstance().newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			// throw XMLException with same cause
			throw new XMLException(e);
		}
	}
}
