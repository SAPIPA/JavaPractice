package SpringBootRestAPI.PhohtoTech.services;

import SpringBootRestAPI.PhohtoTech.Repo.IXmlFileManager;
import SpringBootRestAPI.PhohtoTech.models.Image;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImageService implements IImageService{


    private final IXmlFileManager iXmlFileManager;
    @Autowired
    public ImageService(@Qualifier("xmlFileManager") IXmlFileManager iXmlFileManager) {
        this.iXmlFileManager = iXmlFileManager;
    }

    @Override
    public List<Image> getAllImages(){
        return iXmlFileManager.getAllimagess();
    }

    @Override
    public Image getImageByID(long id) {
        return iXmlFileManager.getImageById(id);
    }

    @Override
    public Image createImage(Image image) {
        return iXmlFileManager.saveImages(image);
    }

    @Override
    public Image updateImage(Image image) {
        return iXmlFileManager.saveImages(image);
    }

    @Override
    public void deleteImage(long id) {
        iXmlFileManager.deleteimages(id);
    }
}
