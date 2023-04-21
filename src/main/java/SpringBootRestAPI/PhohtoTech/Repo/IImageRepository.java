package SpringBootRestAPI.PhohtoTech.Repo;

import SpringBootRestAPI.PhohtoTech.models.Image;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IImageRepository {
    Image saveImage(Image imageS);
    void deleteImage(Long id);
    List<Image> getAllimagess();
    Image getImageById(Long id);
    Image updateImage(Image image);
}
