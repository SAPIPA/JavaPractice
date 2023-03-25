package SpringBootRestAPI.PhohtoTech.Repo;

import SpringBootRestAPI.PhohtoTech.models.Image;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IXmlFileManager {
    Image saveImages(Image imageS);
    void deleteimages(Long id);
    List<Image> getAllimagess();
    Image getImageById(Long id);
}
