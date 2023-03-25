package SpringBootRestAPI.PhohtoTech.services;

import SpringBootRestAPI.PhohtoTech.Repo.IImageRepository;
import SpringBootRestAPI.PhohtoTech.models.Image;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ImageService implements IImageService{

    private final IImageRepository iImageRepository;

    @Autowired
    public ImageService(IImageRepository iImageRepository) {
        this.iImageRepository = iImageRepository;
    }

    @Override
    public List<Image> getAllImages(){
        return iImageRepository.findAll();
    }

    @Override
    public Optional<Image> getImageByID(long id) {
        return iImageRepository.findById(id);
    }

    @Override
    public Image createImage(Image image) {
        return iImageRepository.save(image);
    }

    @Override
    public Image updateImage(Image image) {
        return iImageRepository.save(image);
    }

    @Override
    public void deleteImage(long id) {

    }
}
