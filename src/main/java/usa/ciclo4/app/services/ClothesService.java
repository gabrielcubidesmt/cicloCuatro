package usa.ciclo4.app.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import usa.ciclo4.app.entities.Clothes;
import usa.ciclo4.app.repositories.ClothesRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ClothesService {

    @Autowired
    private ClothesRepository repository;

    public List<Clothes> getAll() {
        return repository.getAll();
    }

    public Optional<Clothes> getClothes(String reference) {
        return repository.getClothes(reference);
    }

    public Clothes create(Clothes clothes) {
        if (clothes.getReference() == null) {
            return clothes;
        } else {
            return repository.create(clothes);
        }
    }

    public Clothes update(Clothes clothes) {

        if (clothes.getReference() != null) {
            Optional<Clothes> clothesDb = repository.getClothes(clothes.getReference());
            if (!clothesDb.isEmpty()) {
                if (clothes.getReference() != null) {
                    clothesDb.get().setReference(clothes.getReference());
                }
                if (clothes.getCategory() != null) {
                    clothesDb.get().setCategory(clothes.getCategory());
                }

                if (clothes.getDescription() != null) {
                    clothesDb.get().setDescription(clothes.getDescription());
                }
                if (clothes.getPrice() != 0.0) {
                    clothesDb.get().setPrice(clothes.getPrice());
                }
                if (clothes.getQuantity() != 0) {
                    clothesDb.get().setQuantity(clothes.getQuantity());
                }
                if (clothes.getPhotography() != null) {
                    clothesDb.get().setPhotography(clothes.getPhotography());
                }
                clothesDb.get().setAvailability(clothes.isAvailability());
                repository.update(clothesDb.get());
                return clothesDb.get();
            } else {
                return clothes;
            }
        } else {
            return clothes;
        }
    }

    public boolean delete(String reference) {
        Boolean aBoolean = getClothes(reference).map(supplement -> {
            repository.delete(supplement);
            return true;
        }).orElse(false);
        return aBoolean;
    }

    public List<Clothes> productsByPrice(double price){
        return repository.productsByPrice(price);
    }

    public List<Clothes> findByDescriptionLike(String description){
        return repository.findByDescriptionLike(description);
    }

}
