package SpringBootRestAPI.PhohtoTech.Repo;
import SpringBootRestAPI.PhohtoTech.models.Image;
import jakarta.xml.bind.JAXBException;

import java.util.List;
import java.util.Optional;

public interface IImageRepository {

    List<Image> findAll() throws JAXBException;
    Optional<Image> findById(long id) throws JAXBException;
    Image save(Image image) throws JAXBException;
    Image delete(long id) throws JAXBException;

}