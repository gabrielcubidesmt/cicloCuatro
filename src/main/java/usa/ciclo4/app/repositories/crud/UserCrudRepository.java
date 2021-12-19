package usa.ciclo4.app.repositories.crud;

import org.springframework.data.mongodb.repository.MongoRepository;
import usa.ciclo4.app.entities.User;

import java.util.List;
import java.util.Optional;
// Nombre de entidad y propiedad de la Pk
public interface UserCrudRepository extends MongoRepository<User, Integer> {

    Optional<User> findByEmail(String email);

    Optional<User> findByEmailAndPassword(String email, String password);

    Optional<User> findTopByOrderByIdDesc();

    List<User> findByMonthBirthtDay(String month);
}

