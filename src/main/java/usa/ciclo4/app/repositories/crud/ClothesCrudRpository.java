package usa.ciclo4.app.repositories.crud;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import usa.ciclo4.app.entities.Clothes;

import java.util.List;

public interface ClothesCrudRpository extends MongoRepository<Clothes, String> {
    public List<Clothes> findByPriceLessThanEqual(double price);

    //@Query("{'description':{'$regex':'?0', '$options':'1'}}")
    public List<Clothes> findByDescriptionLike(String description);
}
