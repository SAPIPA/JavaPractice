package SpringBootRestAPI.PhohtoTech.controllers;


import SpringBootRestAPI.PhohtoTech.models.Image;
import SpringBootRestAPI.PhohtoTech.services.IImageService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("images")
public class ImageController {

    private final IImageService iImageService;

    @Autowired
    public ImageController (IImageService iImageService) {
        this.iImageService = iImageService;
    }

    @GetMapping
    public List<Image> list()  {
        return iImageService.getAllImages();
    }

    @GetMapping("{id}")
    public Optional<Image> getOne(@PathVariable("id") Image image)  {
        return iImageService.getImageByID(image.getId());
    }

    @PostMapping
    public Image create(@RequestBody Image image) {
        return iImageService.createImage(image);
    }

    @PutMapping("{id}")
    public Image update(@PathVariable("id") Image imageFromFile, //положим в БД
                            @RequestBody Image image)  { // от пользователя
        BeanUtils.copyProperties(image, imageFromFile, "id");
        return iImageService.updateImage(imageFromFile);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") Image image) {
        iImageService.deleteImage(image.getId());
    }
}