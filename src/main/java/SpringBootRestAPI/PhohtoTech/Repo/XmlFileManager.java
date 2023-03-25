package SpringBootRestAPI.PhohtoTech.Repo;

import java.io.*;
import java.util.ArrayList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import SpringBootRestAPI.PhohtoTech.models.Image;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class XmlFileManager {

    private static final String FILE_NAME = "C:\\Users\\SAPIPA\\Desktop\\Study\\4th _semester\\Introductory_practice\\PhohtoTech\\src\\main\\resources\\ImageContext.xml";

    public static void saveImages(Image[] images) throws ParserConfigurationException, TransformerException, FileNotFoundException {

        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();

        Document document = documentBuilder.newDocument();
        Element root = document.createElement("images");
        document.appendChild(root);

        for (Image image : images) {
            Element element = document.createElement("images");
            root.appendChild(element);

            Long idField = image.getId();
            Element idElement = document.createElement("id");
            idElement.appendChild(document.createTextNode(String.valueOf(idField)));
            element.appendChild(idElement);

            String titleField = image.getTitle();
            Element titleElement = document.createElement("title");
            titleElement.appendChild(document.createTextNode(titleField));
            element.appendChild(titleElement);

            Long phototechIdField = image.getPhototechId();
            Element phototechIdElement = document.createElement("phototechId");
            phototechIdElement.appendChild(document.createTextNode(String.valueOf(phototechIdField)));
            element.appendChild(phototechIdElement);

            String path_of_the_samuraiField = image.getPath_of_the_samurai();
            Element path_of_the_samuraiElement = document.createElement("phototechId");
            path_of_the_samuraiElement.appendChild(document.createTextNode(path_of_the_samuraiField));
            element.appendChild(path_of_the_samuraiElement);
        }

        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(document);

        FileOutputStream outputStream = new FileOutputStream(new File(FILE_NAME));
        StreamResult result = new StreamResult(outputStream);

        transformer.transform(source, result);
    }

    public static void deleteimages(Long id) throws SAXException, IOException, ParserConfigurationException, TransformerConfigurationException, TransformerException {

        FileInputStream fileInputStream = new FileInputStream(new File(FILE_NAME));
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(fileInputStream);

        NodeList nodeList = document.getElementsByTagName("images");

        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            Element element = (Element) node;
            Long idField = Long.parseLong(element.getElementsByTagName("id").item(0).getTextContent());

            if (idField == id) {
                element.getParentNode().removeChild(node);
                break;
            }
        }

        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(document);

        FileOutputStream outputStream = new FileOutputStream(new File(FILE_NAME));
        StreamResult result = new StreamResult(outputStream);

        transformer.transform(source, result);
    }

    public static Image[] getAllimagess() throws SAXException, IOException, ParserConfigurationException {

        FileInputStream fileInputStream = new FileInputStream(new File(FILE_NAME));
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(fileInputStream);

        NodeList nodeList = document.getElementsByTagName("images");
        ArrayList<Image> images = new ArrayList<>();

        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            Element element = (Element) node;

            Long idField = Long.parseLong((element.getElementsByTagName("id").item(0).getTextContent()));
            Long phototechIdField = Long.parseLong((element.getElementsByTagName("phototechId").item(0).getTextContent()));
            String titleField = element.getElementsByTagName("title").item(0).getTextContent();
            String path_of_the_samuraiField = element.getElementsByTagName("path_of_the_samurai").item(0).getTextContent();

            Image image = new Image(idField, titleField, phototechIdField, path_of_the_samuraiField);
            images.add(image);
        }

        return images.toArray(new Image[images.size()]);
    }

    public static Image getImageById(int id) throws SAXException, IOException, ParserConfigurationException {

        FileInputStream fileInputStream = new FileInputStream(new File(FILE_NAME));
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(fileInputStream);

        NodeList nodeList = document.getElementsByTagName("images");

        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            Element element = (Element) node;
            Long idField = Long.parseLong((element.getElementsByTagName("id").item(0).getTextContent()));

            if (idField == id) {
                Long phototechIdField = Long.parseLong((element.getElementsByTagName("phototechId").item(0).getTextContent()));
                String titleField = element.getElementsByTagName("title").item(0).getTextContent();
                String path_of_the_samuraiField = element.getElementsByTagName("path_of_the_samurai").item(0).getTextContent();
                return new Image(idField, titleField, phototechIdField, path_of_the_samuraiField);
            }
        }
        return null;
    }
}