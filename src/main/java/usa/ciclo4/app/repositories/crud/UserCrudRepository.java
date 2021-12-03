package usa.ciclo4.app.repositories.crud;

import org.springframework.data.repository.CrudRepository;
import usa.ciclo4.app.entities.User;

import java.util.Optional;
// Nombre de entidad y propiedad de la Pk
public interface UserCrudRepository extends CrudRepository<User, Integer>{

    Optional<User> findByEmail(String email);

    Optional<User> findByEmailAndPassword(String email, String password);
}

