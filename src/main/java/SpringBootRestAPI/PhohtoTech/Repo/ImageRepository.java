package SpringBootRestAPI.PhohtoTech.Repo;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
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
public class ImageRepository implements IImageRepository {

    private static final String FILE_NAME = "src\\main\\resources\\ImageContext.xml";

    public Image saveImage(Image image) {
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder;
        try {

            documentBuilder = documentBuilderFactory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            throw new RuntimeException(e);
        }

        File file = new File(FILE_NAME);
        Document document;
        Element root;
        if (file.exists()) {            // If the file already exists, parse the existing document
            try {
                document = documentBuilder.parse(file);
                root = document.getDocumentElement();
            } catch (SAXException | IOException e) {
                throw new RuntimeException(e);
            }
        } else {            // If the file does not exist, create a new document
            document = documentBuilder.newDocument();
            root = document.createElement("images");
            document.appendChild(root);
        }

        Element element = document.createElement("image");
        root.appendChild(element);

        List<Image> IMAGES = getAllimagess();
        Long idField;
        if (IMAGES.size() == 0) {
            idField = 1L;
        }
        else {
            Image imagE = IMAGES.get(IMAGES.size() - 1);
            idField = imagE.getId() + 1;
        }

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

        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer;
        try {
            transformer = transformerFactory.newTransformer();
            //transformer.setOutputProperty(OutputKeys.INDENT, "yes"); // добавляем отступы
            //transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4"); // задаем количество пробелов для отступа
        } catch (TransformerConfigurationException e) {
            throw new RuntimeException(e);
        }
        DOMSource source = new DOMSource(document);

        FileOutputStream outputStream;
        try {
            outputStream = new FileOutputStream(file);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        StreamResult result = new StreamResult(outputStream);

        try {
            transformer.transform(source, result);
        } catch (TransformerException e) {
            throw new RuntimeException(e);
        }
        return image;
    }

    public void deleteImage(Long id) {
        if (!new File(FILE_NAME).exists()) {
            throw new RuntimeException("File not found: " + FILE_NAME);
        }

        FileInputStream fileInputStream;
        try {
            fileInputStream = new FileInputStream(FILE_NAME);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder;
        try {
            builder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            throw new RuntimeException(e);
        }
        Document document;
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
            Long idField = Long.parseLong(element.getElementsByTagName("id").item(0).getTextContent());

            if (idField == id) {
                node.getParentNode().removeChild(node);
                break;
            }
        }

        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer;
        try {
            transformer = transformerFactory.newTransformer();
        } catch (TransformerConfigurationException e) {
            throw new RuntimeException(e);
        }
        DOMSource source = new DOMSource(document);

        FileOutputStream outputStream;
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

        FileInputStream fileInputStream;
        try {
            fileInputStream = new FileInputStream(new File(FILE_NAME));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder;
        try {
            builder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            throw new RuntimeException(e);
        }
        Document document;
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

            for (int j = 0; j < imageNodes.getLength(); j++) {
                Node imageNode = imageNodes.item(j);

                if (imageNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) imageNode;

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

        FileInputStream fileInputStream;
        try {
            fileInputStream = new FileInputStream(new File(FILE_NAME));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder;
        try {
            builder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            throw new RuntimeException(e);
        }
        Document document;
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

    public Image updateImage(Image image) {
        Long id = image.getId();

        FileInputStream fileInputStream;
        try {
            fileInputStream = new FileInputStream(new File(FILE_NAME));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder;
        try {
            builder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            throw new RuntimeException(e);
        }
        Document document;
        try {
            document = builder.parse(fileInputStream);
        } catch (SAXException | IOException e) {
            throw new RuntimeException(e);
        }

        NodeList nodeList = document.getElementsByTagName("image");

        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            Element element = (Element) node;
            Long idField = Long.parseLong((element.getElementsByTagName("id").item(0).getTextContent()));

            if (idField.equals(id)) {
                element.getElementsByTagName("phototechId").item(0).setTextContent(Long.toString(image.getPhototechId()));
                element.getElementsByTagName("title").item(0).setTextContent(image.getTitle());
                element.getElementsByTagName("path_of_the_samurai").item(0).setTextContent(image.getPath_of_the_samurai());

                try {
                    TransformerFactory transformerFactory = TransformerFactory.newInstance();
                    Transformer transformer = transformerFactory.newTransformer();
                    DOMSource source = new DOMSource(document);
                    StreamResult result = new StreamResult(new File(FILE_NAME));
                    transformer.transform(source, result);
                } catch (TransformerException e) {
                    throw new RuntimeException(e);
                }
                return image;
            }
        }
        return null;
    }
}