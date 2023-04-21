package SpringBootRestAPI.PhohtoTech.controllers;

import SpringBootRestAPI.PhohtoTech.models.Phototech;
import SpringBootRestAPI.PhohtoTech.services.IPhototechService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("phototech")

public class PhotoTechController {

    private final IPhototechService PhototechService;


    @Autowired
    public  PhotoTechController(IPhototechService PhototechService) {
        this.PhototechService = PhototechService;
    }

    @GetMapping
    public Iterable<Phototech> list() {
        return PhototechService.getAllPhototechs();
    }

    @GetMapping("{id}")
    public Optional<Phototech> getOne(@PathVariable("id") Phototech phototech) {
        return PhototechService.getPhototechByID(phototech.getId());
    }

    @PostMapping
    public Phototech create(@RequestBody Phototech phototech) {
        return PhototechService.createPhototech(phototech);
    }

    @PutMapping("{id}")
    public Phototech update(@PathVariable("id") Phototech phototechFromDb, //положим в БД
                            @RequestBody Phototech phototech) { // от пользователя
        BeanUtils.copyProperties(phototech, phototechFromDb, "id");
        return PhototechService.updatePhototech(phototechFromDb);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") Phototech phototech) {
        PhototechService.deletePhototech(phototech.getId());
    }
}
