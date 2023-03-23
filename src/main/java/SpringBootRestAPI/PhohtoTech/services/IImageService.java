package SpringBootRestAPI.PhohtoTech.services;

import SpringBootRestAPI.PhohtoTech.models.Image;
import javax.xml.bind.JAXBException;

import java.util.List;
import java.util.Optional;

public interface IImageService {

    List<Image> getAllImages() throws JAXBException;
    Optional<Image> getImageByID(long id) throws JAXBException;
    Image createImage(Image image) throws JAXBException;
    Image updateImage(Image image) throws JAXBException;
    void deleteImage(long id);
}
