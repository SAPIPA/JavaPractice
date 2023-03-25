package SpringBootRestAPI.PhohtoTech.Repo;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
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
import org.springframework.stereotype.Repository;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

@Repository
public class XmlFileManager implements IXmlFileManager {

    private static final String FILE_NAME = "C:\\Users\\SAPIPA\\Desktop\\Study\\4th _semester\\Introductory_practice\\PhohtoTech\\src\\main\\resources\\ImageContext.xml";

    public Image saveImages(Image imageS) {

        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = null;
        try {
            documentBuilder = documentBuilderFactory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            throw new RuntimeException(e);
        }

        Document document = documentBuilder.newDocument();
        Element root = document.createElement("images");
        document.appendChild(root);

        Image[] images = {imageS};

        for (Image image : images) {
            Element element = document.createElement("image");
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
            Element path_of_the_samuraiElement = document.createElement("path_of_the_samurai");
            path_of_the_samuraiElement.appendChild(document.createTextNode(path_of_the_samuraiField));
            element.appendChild(path_of_the_samuraiElement);
        }

        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = null;
        try {
            transformer = transformerFactory.newTransformer();
        } catch (TransformerConfigurationException e) {
            throw new RuntimeException(e);
        }
        DOMSource source = new DOMSource(document);

        FileOutputStream outputStream = null;
        try {
            outputStream = new FileOutputStream(new File(FILE_NAME));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        StreamResult result = new StreamResult(outputStream);

        try {
            transformer.transform(source, result);
        } catch (TransformerException e) {
            throw new RuntimeException(e);
        }
        return imageS;
    }

    public void deleteimages(Long id) {

        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream(new File(FILE_NAME));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = null;
        try {
            builder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            throw new RuntimeException(e);
        }
        Document document = null;
        try {
            document = builder.parse(fileInputStream);
        } catch (SAXException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

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
        Transformer transformer = null;
        try {
            transformer = transformerFactory.newTransformer();
        } catch (TransformerConfigurationException e) {
            throw new RuntimeException(e);
        }
        DOMSource source = new DOMSource(document);

        FileOutputStream outputStream = null;
        try {
            outputStream = new FileOutputStream(new File(FILE_NAME));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        StreamResult result = new StreamResult(outputStream);

        try {
            transformer.transform(source, result);
        } catch (TransformerException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Image> getAllimagess() {

        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream(new File(FILE_NAME));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = null;
        try {
            builder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            throw new RuntimeException(e);
        }
        Document document = null;
        try {
            document = builder.parse(fileInputStream);
        } catch (SAXException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        NodeList nodeList = document.getElementsByTagName("images");
        List<Image> images = new ArrayList<>();

        for (int i = 0; i < nodeList.getLength(); i++) {
            Node imagesNode = nodeList.item(i);
            NodeList imageNodes = imagesNode.getChildNodes();

            System.out.println("i= " + i);

            for (int j = 0; j < imageNodes.getLength(); j++) {
                Node imageNode = imageNodes.item(j);

                if (imageNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) imageNode;

                    System.out.println("j= " + j);

                    Long idField = Long.parseLong(element.getElementsByTagName("id").item(0).getTextContent());
                    String titleField = element.getElementsByTagName("title").item(0).getTextContent();
                    Long phototechIdField = Long.parseLong(element.getElementsByTagName("phototechId").item(0).getTextContent());
                    String path_of_the_samuraiField = element.getElementsByTagName("path_of_the_samurai").item(0).getTextContent();

                    Image image = new Image(idField, titleField, phototechIdField, path_of_the_samuraiField);
                    images.add(image);
                }
            }
        }
        return images;
    }

    public Image getImageById(Long id) {

        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream(new File(FILE_NAME));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = null;
        try {
            builder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            throw new RuntimeException(e);
        }
        Document document = null;
        try {
            document = builder.parse(fileInputStream);
        } catch (SAXException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        NodeList nodeList = document.getElementsByTagName("image");

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