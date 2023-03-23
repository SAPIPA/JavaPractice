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

    private IPhototechService iPhototechService;


    @Autowired
    public  PhotoTechController(IPhototechService iPhototechService) {
        this.iPhototechService = iPhototechService;
    }

    @GetMapping
    public Iterable<Phototech> list() {
        return iPhototechService.getAllPhototechs();
    }

    @GetMapping("{id}")
    public Optional<Phototech> getOne(@PathVariable("id") Phototech phototech) {
        return iPhototechService.getPhototechByID(phototech.getId());
    }

    @PostMapping
    public Phototech create(@RequestBody Phototech phototech) {
        return iPhototechService.createPhototech(phototech);
    }

    @PutMapping("{id}")
    public Phototech update(@PathVariable("id") Phototech phototechFromDb, //положим в БД
                            @RequestBody Phototech phototech) { // от пользователя
        BeanUtils.copyProperties(phototech, phototechFromDb, "id");
        return iPhototechService.updatePhototech(phototechFromDb);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") Phototech phototech) {
        iPhototechService.deletePhototech(phototech.getId());
    }
}

/*//fetch('/images', {method: 'POST', headers: {'Content-Type': 'application/json'}, body: JSON.stringify({title: 'Ещё одна картинка'})}).then(console.log)
    //fetch('/images/502', {method: 'PUT', headers: {'Content-Type': 'application/json'}, body: JSON.stringify({title: 'Ещё одна картинка(уже обновленная)', id:502})}).then(console.log)
    //fetch('/images/502', {method: 'DELETE'})*/
