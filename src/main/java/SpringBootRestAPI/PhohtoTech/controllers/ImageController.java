package SpringBootRestAPI.PhohtoTech.controllers;


import SpringBootRestAPI.PhohtoTech.models.Image;
import SpringBootRestAPI.PhohtoTech.services.IImageService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("images")
public class ImageController {

    private final IImageService ImageService;

    @Autowired
    public ImageController (IImageService ImageService) {
        this.ImageService = ImageService;
    }

    @GetMapping
    public List<Image> list()  {
        return ImageService.getAllImages();
    }

    @GetMapping("{id}")
    public Image getOne(@PathVariable("id") Long id)  {
        return ImageService.getImageByID(id);
    }

    @PostMapping
    public Image create(@RequestBody Image image) {
        return ImageService.createImage(image);
    }

    @PutMapping("{id}")
    public Image update(@PathVariable("id") Image imageFromFile, //положим в БД
                            @RequestBody Image image)  { // от пользователя
        BeanUtils.copyProperties(image, imageFromFile, "id");
        return ImageService.updateImage(imageFromFile);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") Image image) {
        ImageService.deleteImage(image.getId());
    }
}