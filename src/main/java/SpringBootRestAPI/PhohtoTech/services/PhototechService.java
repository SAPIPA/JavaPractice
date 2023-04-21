package SpringBootRestAPI.PhohtoTech.services;

import SpringBootRestAPI.PhohtoTech.Repo.IPhototechRepository;
import SpringBootRestAPI.PhohtoTech.models.Image;
import SpringBootRestAPI.PhohtoTech.models.Phototech;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class PhototechService implements IPhototechService {

    @Qualifier("IPhototechRepository")
    private final IPhototechRepository iPhototechRepository;

    private final IImageService iImageService;

    @Autowired
    public PhototechService(@Qualifier("IPhototechRepository") IPhototechRepository iPhototechRepository, IImageService iImageService) {
        this.iPhototechRepository = iPhototechRepository;
        this.iImageService = iImageService;
    }

    @Override
    public List<Phototech> getAllPhototechs() {
        return (List<Phototech>) iPhototechRepository.findAll();
    }

    @Override
    public Optional<Phototech> getPhototechByID(long id) {
        return iPhototechRepository.findById(id);
    }

    @Override
    public Phototech createPhototech(Phototech phototech) {
        return iPhototechRepository.save(phototech);
    }

    @Override
    public Phototech updatePhototech(Phototech phototech) {
        return iPhototechRepository.save(phototech);
    }

    @Override
    public void deletePhototech(long id) {
        iPhototechRepository.deleteById(id);
        List<Image> images = iImageService.getAllImages();
       for(int i = 0; i < images.size(); ++i) {
            Image image = images.get(i);
            if (image.getPhototechId() == id) {
                iImageService.deleteImage(image.getId());
            }
        }
    }
}
