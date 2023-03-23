package SpringBootRestAPI.PhohtoTech.Repo;

import SpringBootRestAPI.PhohtoTech.models.Image;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class ImageRepository implements IImageRepository {

    private final String XML_FILE_PATH = "/resources/ImageContext.xml";
    private final JAXBContext context;

    public ImageRepository() throws JAXBException {
        context = JAXBContext.newInstance(Image.class);
    }


    @Override
    public List<Image> findAll() throws JAXBException {
        Unmarshaller unmarshaller = context.createUnmarshaller();
        String images = unmarshaller.unmarshal(new File(XML_FILE_PATH)).toString();
        return null;
        //return images != null ? images : new ArrayList<>();
    }

    @Override
    public Optional<Image> findById(long id) throws JAXBException {
        List<Image> images = findAll();
        return images.stream().filter(i -> i.getId() == id).findFirst();
    }

    @Override
    public Image save(Image image) throws JAXBException {
        List<Image> images = findAll();
        long maxId = images.stream().mapToLong(Image::getId).max().orElse(0);
        image.setId(maxId + 1);
        images.add(image);
        saveAll(images);
        return image;
    }

    @Override
    public Image delete(long id) throws JAXBException {
        List<Image> images = findAll();
        Optional<Image> imageToDelete = images.stream().filter(i -> i.getId() == id).findFirst();
        if (imageToDelete.isPresent()) {
            Image deletedImage = imageToDelete.get();
            images.remove(deletedImage);
            saveAll(images);
            return deletedImage;
        } else {
            return null;
        }
    }

    private void saveAll(List<Image> images) throws JAXBException {
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(images, new File(XML_FILE_PATH));
    }
}