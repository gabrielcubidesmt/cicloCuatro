package usa.ciclo4.app.repositories.crud;

import org.springframework.data.mongodb.repository.MongoRepository;
import usa.ciclo4.app.entities.Clothes;

public interface ClothesCrudRpository extends MongoRepository<Clothes, String> {

}
