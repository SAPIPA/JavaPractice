package SpringBootRestAPI.PhohtoTech.services;

import SpringBootRestAPI.PhohtoTech.models.Image;

import java.util.List;
import java.util.Optional;

public interface IImageService {

    List<Image> getAllImages();
    Image getImageByID(long id);
    Image createImage(Image image);
    Image updateImage(Image image);
    void deleteImage(long id);

}