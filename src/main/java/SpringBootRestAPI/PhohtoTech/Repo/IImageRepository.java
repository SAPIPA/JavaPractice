package SpringBootRestAPI.PhohtoTech.Repo;

import SpringBootRestAPI.PhohtoTech.models.Image;
import java.util.List;
import java.util.Optional;

public interface IImageRepository {

    List<Image> findAll();
    Optional<Image> findById(long id) ;
    Image save(Image image);
    Image delete(long id);

}