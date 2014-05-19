package ua.com.sourceit.xml.dom;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.annotation.Nullable;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;

/**
 * Created by yuriy on 19.05.14.
 */
public class DomParser {

    public static final String STAFF_ID = "id";
    public static final String FIRSTNAME = "firstname";
    public static final String LASTNAME = "lastname";
    public static final String NICKNAME = "nickname";
    public static final String SALARY = "salary";
    public static final String STAFF_ELEMENT = "staff";

    private final String pathname;
    private final File xmlFile;

    public DomParser() {
        this("staff.xml");
    }

    public DomParser(String pathname) {
        this.pathname = pathname;
        this.xmlFile = new File(pathname);
    }

    public void parse(){
            Document document = getDocument();

            System.out.println("Root element :" + document.getDocumentElement().getNodeName());

            NodeList nList = document.getElementsByTagName(STAFF_ELEMENT);

            System.out.println("----------------------------");

            for (int temp = 0; temp < nList.getLength(); temp++) {

                Node nNode = nList.item(temp);

                System.out.println("\nCurrent Element :" + nNode.getNodeName());

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                    Element eElement = (Element) nNode;

                    System.out.println("Staff id : " + eElement.getAttribute(STAFF_ID));
                    System.out.println("First Name : " + eElement.getElementsByTagName(FIRSTNAME).item(0).getTextContent());
                    System.out.println("Last Name : " + eElement.getElementsByTagName(LASTNAME).item(0).getTextContent());
                    System.out.println("Nick Name : " + eElement.getElementsByTagName(NICKNAME).item(0).getTextContent());
                    System.out.println("Salary : " + eElement.getElementsByTagName(SALARY).item(0).getTextContent());
                }
            }

    }

    private @Nullable Document getDocument() {
        try {
            final DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            final DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            final Document document = dBuilder.parse(getXmlFile());

            //optional, but recommended
            //read this - http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
            document.getDocumentElement().normalize();
            return document;
        } catch (ParserConfigurationException | SAXException | IOException e) {
            System.err.println("Something went wrong");
            e.printStackTrace();
        }
        return null;
    }

    public void addElement(){
        final Document document = getDocument();
        final Element newStaffMember = document.createElement(STAFF_ELEMENT);
        newStaffMember.setAttribute(STAFF_ID,"3001");

        appendTextElement(newStaffMember, FIRSTNAME, "Nicola");
        appendTextElement(newStaffMember, LASTNAME, "Tesla");
        appendTextElement(newStaffMember, NICKNAME, "scientist");
        appendTextElement(newStaffMember, SALARY, "15000");

        NodeList list = document.getElementsByTagName("company");
        Element section = (Element)list.item(0);
        section.appendChild(newStaffMember);

        writeDocument(document);

    }

    private void writeDocument(Document document) {
        try {
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.transform(new DOMSource(document), new StreamResult(getXmlFile()));
        } catch (TransformerException e) {
            e.printStackTrace();
        }
    }

    private void appendTextElement(Element parentElement, String childElementName, String childElementValue) {
        final Document document = parentElement.getOwnerDocument();
        final Element name = document.createElement(childElementName);
        name.appendChild(document.createTextNode(childElementValue));
        parentElement.appendChild(name);
    }

    public File getXmlFile() {
        return xmlFile;
    }

    public static void main(String[] args) {
        final DomParser test = new DomParser();
        test.parse();
        test.addElement();
    }
}
