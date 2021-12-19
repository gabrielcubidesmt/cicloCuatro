package usa.ciclo4.app.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import usa.ciclo4.app.entities.Clothes;
import usa.ciclo4.app.repositories.crud.ClothesCrudRpository;

import java.util.List;
import java.util.Optional;

@Repository
public class ClothesRepository {

    @Autowired
    private ClothesCrudRpository repository;

    public List<Clothes> getAll() {
        return repository.findAll();
    }

    public Optional<Clothes> getClothes(String reference) {
        return repository.findById(reference);
    }

    public Clothes create(Clothes clothes) {
        return repository.save(clothes);
    }

    public void update(Clothes clothes) {
        repository.save(clothes);
    }

    public void delete(Clothes clothes) {
        repository.delete(clothes);
    }

    public List<Clothes> productsByPrice(double price){
        return repository.findByPriceLessThanEqual(price);
    }

    public List<Clothes> findByDescriptionLike(String description){
        return repository.findByDescriptionLike(description);
    }
}
