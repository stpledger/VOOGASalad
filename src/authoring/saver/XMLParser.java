package authoring.saver;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.stream.Stream;
import java.util.HashMap;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
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
public class XMLParser {
	private static DocumentBuilder DOCUMENT_BUILDER;
	// message that we want to display when the user picks a file in the incorrect format
	public static final String ERROR_MESSAGE = "XML file does not properly represent %s";
	// name of the root attribute that denotes the type of file we want to parse
	private final String FOLDER_PATH = "data/";
	
	public XMLParser() {
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
		Element root = document.createElement("Entity");
		document.appendChild(root);
		Element components = document.createElement("Components");
		root.appendChild(components);
		
		for (String compName : attributes.keySet()) {
			Element component = document.createElement(compName);
			for (Object o : attributes.get(compName)) {
        			component.appendChild(document.createTextNode(String.valueOf(o)));
        			components.appendChild(component);
			}
		}
		
		Transformer t = TransformerFactory.newInstance().newTransformer();
		DOMSource ds = new DOMSource(document);
		StreamResult sr = new StreamResult(new File(FOLDER_PATH + fileName + ".xml"));
		t.transform(ds, sr);
		System.out.println("XML file created and saved to " + FOLDER_PATH + fileName);
	}
	
	/**
	 * Get the grid properties for this simulation. This does not include initial probabilities.
	 * @param XMLFile the XMLFile to be read in
	 * @return a Map object containing information about this simulation
	 */
//	public Map<String, String> getGridProperties(File XMLFile) {
//		Element root = getRootElement(XMLFile);
//		Map<String, String> gridProperties = new HashMap<>();
//		for (String field : SimulationBuilder.DATA_FIELDS) {
//			// this is handled elsewhere since it generates a map of its own
//			String value = getTextValue(root, field);
//			gridProperties.put(field,  value);
//		}
//		return gridProperties;
//	}
	
	/**
	 * Get the initial states probabilities for all of the cells in the grid.
	 * @param XMLFile the XMLFile to be read in
	 * @return a Map object containing information about the initial states of cells for this simulation
	 */
	public Map<String, double[]> getInitialStates(File XMLFile) {
		Element root = getRootElement(XMLFile);
		// read only the data from the "initialStates" field
		NodeList nList = root.getElementsByTagName("keke");
		Map<String, double[]> initialStatesMap = new HashMap<>();
		for (int i = 0; i < nList.getLength(); i++) {
			initialStatesMap.put(nList.item(i).getAttributes().getNamedItem("type").getNodeValue(), 
			Stream.of(nList.item(i).getTextContent().split("\\s+")).mapToDouble(Double::parseDouble).toArray());
		}
		return initialStatesMap;
		
	}
	
	private Element getRootElement(File XMLFile) {
		try {
			DOCUMENT_BUILDER.reset();
			Document XMLDoc = DOCUMENT_BUILDER.parse(XMLFile);
			return XMLDoc.getDocumentElement();
		} catch (SAXException | IOException e) {
			// throw XML exception with same cause
			throw new XMLException(e);
		}
	}
	
	/**
	 * Get the text value contained within an XML element.
	 * @param e the element to be queried
	 * @param tag the tag to be searched
	 * @return the text value of this certain element
	 */
//	private String getTextValue(Element e, String tag) {
//		NodeList nList = e.getElementsByTagName(tag);
//		if (nList != null && nList.getLength() > 0) {
//            return nList.item(0).getTextContent();
//        } else {
//        		throw new XMLException(ERROR_MESSAGE, CellSociety.DATA_TYPE);
//        }
//	}
	
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
