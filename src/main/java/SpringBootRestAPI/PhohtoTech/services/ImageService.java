package SpringBootRestAPI.PhohtoTech.services;

import SpringBootRestAPI.PhohtoTech.Repo.IImageRepository;
import SpringBootRestAPI.PhohtoTech.models.Image;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImageService implements IImageService{


    private final IImageRepository iImageRepository;
    @Autowired
    public ImageService(@Qualifier("imageRepository") IImageRepository iImageRepository) {
        this.iImageRepository = iImageRepository;
    }

    @Override
    public List<Image> getAllImages(){
        return iImageRepository.getAllimagess();
    }

    @Override
    public Image getImageByID(long id) {
        return iImageRepository.getImageById(id);
    }

    @Override
    public Image createImage(Image image) {
        return iImageRepository.saveImage(image);
    }

    @Override
    public Image updateImage(Image image) {
        return iImageRepository.updateImage(image);
    }

    @Override
    public void deleteImage(long id) {
        iImageRepository.deleteImage(id);
    }
}
