package usa.ciclo4.app.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import usa.ciclo4.app.entities.User;
import usa.ciclo4.app.repositories.crud.UserCrudRepository;

import java.util.List;
import java.util.Optional;

@Repository
public class UserRepository {

    @Autowired
    private UserCrudRepository repository;

    public List<User> getAll(){
        return (List<User>) repository.findAll();
    }

    public Optional<User> getUser(int id){
        return repository.findById(id);
    }

    public User save(User user){
        return repository.save(user);
    }

    public void delete(User user){
        repository.delete(user);
    }

    public boolean getUserByEmail(String email){
        Optional<User> user = repository.findByEmail(email);
        return !user.isEmpty();
    }

    public Optional<User> authUser(String email, String password){
        return repository.findByEmailAndPassword(email, password);
    }

    public User update(User user){
        return repository.save(user);
    }

    public Optional<User> lastUserId() {
        return repository.findTopByOrderByIdDesc();
    }

}