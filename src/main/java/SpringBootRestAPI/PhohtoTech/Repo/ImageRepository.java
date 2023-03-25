package SpringBootRestAPI.PhohtoTech.Repo;

import SpringBootRestAPI.PhohtoTech.models.Image;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;
import lombok.SneakyThrows;
import org.springframework.stereotype.Repository;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class ImageRepository implements IImageRepository {

    private final String XML_FILE_PATH = "C:\\Users\\SAPIPA\\Desktop\\Study\\4th _semester\\Introductory_practice\\PhohtoTech\\src\\main\\resources\\ImageContext.xml";
    private final JAXBContext context;

    public ImageRepository() {
        try {
            context = JAXBContext.newInstance(Image.class);
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
    }

    public static class ImageList {
     private List<Image> images = new ArrayList<>();

     public List<Image> getImages() {
     return images;
     }

     public void setImages(List<Image> images) {
     this.images = images;
     }
     }

    @SneakyThrows
    @Override
    public List<Image> findAll()  {
        Unmarshaller unmarshaller = context.createUnmarshaller();
        ImageList images = (ImageList) unmarshaller.unmarshal(new File(XML_FILE_PATH));
        return images.getImages();
    }

    @Override
    public Optional<Image> findById(long id)  {
        List<Image> images = findAll();
        return images.stream().filter(i -> i.getId() == id).findFirst();
    }

    @SneakyThrows
    @Override
    public Image save(Image image) {
        List<Image> images = findAll();
        long maxId = images.stream().mapToLong(Image::getId).max().orElse(0);
        image.setId(maxId + 1);
        images.add(image);
        saveAll(images);
        return image;
    }

    @SneakyThrows
    @Override
    public Image delete(long id) {
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