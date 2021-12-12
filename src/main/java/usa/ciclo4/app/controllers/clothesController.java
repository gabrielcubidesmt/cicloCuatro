package usa.ciclo4.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import usa.ciclo4.app.entities.Clothes;
import usa.ciclo4.app.services.ClothesService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/clothes")
@CrossOrigin("*")
public class clothesController {

    @Autowired
    private ClothesService service;

    @GetMapping("/all")
    public List<Clothes> getAll() {
        return service.getAll();
    }

    @GetMapping("/{reference}")
    public Optional<Clothes> getSupplement(@PathVariable("reference") String reference) {
        return service.getClothes(reference);
    }

    @PostMapping("/new")
    @ResponseStatus(HttpStatus.CREATED)
    public Clothes create(@RequestBody Clothes clothes) {
        return service.create(clothes);
    }

    @PutMapping("/update")
    @ResponseStatus(HttpStatus.CREATED)
    public Clothes update(@RequestBody Clothes clothes) {
        return service.update(clothes);
    }

    @DeleteMapping("/{reference}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public boolean delete(@PathVariable("reference") String reference) {
        return service.delete(reference);
    }
}
