package SpringBootRestAPI.PhohtoTech.services;

import SpringBootRestAPI.PhohtoTech.Repo.IImageRepository;
import SpringBootRestAPI.PhohtoTech.models.Image;
import jakarta.xml.bind.JAXBException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ImageService implements IImageService{

    @Autowired
    private final IImageRepository iImageRepository;

    public ImageService(IImageRepository iImageRepository) {
        this.iImageRepository = iImageRepository;
    }

    @Override
    public List<Image> getAllImages() throws JAXBException {
        return iImageRepository.findAll();
    }

    @Override
    public Optional<Image> getImageByID(long id) throws JAXBException {
        return iImageRepository.findById(id);
    }

    @Override
    public Image createImage(Image image) throws JAXBException {
        return iImageRepository.save(image);
    }

    @Override
    public Image updateImage(Image image) throws JAXBException {
        return iImageRepository.save(image);
    }

    @Override
    public void deleteImage(long id) {

    }
}
