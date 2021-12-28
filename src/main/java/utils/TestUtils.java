package utils;

import org.json.JSONObject;
import org.json.JSONTokener;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

public class TestUtils {

    private DocumentBuilder docBuilder;
    private Document document;
    private InputStream inputStream;

    // Custom way - need improvement to read json file
    public JSONObject readJSONFile(String filePath) {
        try {
            inputStream = getClass().getClassLoader().getResourceAsStream(filePath);
            JSONTokener jsonTokener = new JSONTokener(inputStream);
            if (jsonTokener == null) {
                throw new RuntimeException("JSONObject is null");
            }
            return new JSONObject(jsonTokener);
        } catch (Exception ignored) { } finally {
            if (inputStream != null) {
                try { inputStream.close(); } catch (IOException ex) { ex.printStackTrace(); }
            }
        }
        return null;
    }

    public HashMap<String, String> getExpectedStringMap() {
        // Initialize HashMap expectedStringMap basing on staticStrings.xml file
        String xmlFileName = "static-string/authentication/ExpectedText.xml";
        InputStream isStringMap = getClass().getClassLoader().getResourceAsStream(xmlFileName);
        if (isStringMap == null) { throw new RuntimeException("Expected String Map is empty"); }
        return xmlStringParser(isStringMap);
    }

    private HashMap<String, String> xmlStringParser(InputStream file) {
        HashMap<String, String> stringMap = new HashMap<>();

        /* Get Document Builder */
        try {
            DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
            docBuilder = docBuilderFactory.newDocumentBuilder();
        } catch (ParserConfigurationException ex) { ex.printStackTrace(); }

        /* Build Document */
        try {
            document = docBuilder.parse(file);
        } catch (SAXException | IOException ex) { ex.printStackTrace(); }

        /* Normalize the XML structure */
        document.getDocumentElement().normalize();

        /* Come from the ROOT node */
        Element root = document.getDocumentElement();

        /* Traverse from the ROOT node */
        NodeList nodeList = document.getElementsByTagName("string");

        for (int nodePos = 0 ; nodePos < nodeList.getLength() ; nodePos++) {
            Node node = nodeList.item(nodePos);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element element = ( Element ) node;
                stringMap.put(element.getAttribute("name"), element.getTextContent());
            }
        }

        return stringMap;
    }

    public String getDateTime() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
        Date date = new Date();
        return dateFormat.format(date);
    }
}
