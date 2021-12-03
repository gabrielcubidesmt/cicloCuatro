package usa.ciclo4.app.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import usa.ciclo4.app.entities.User;
import usa.ciclo4.app.repositories.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    // this method will return to us all the existent users
    public List<User>getALL() {
        return repository.getAll();
    }

    // this method will return to us each specified user by his/her id
    public Optional<User> getUser(int userId){
        return repository.getUser(userId);
    }

    // this method will save a new user
    public User save(User user){
        if (user.getId() == null){
            if(getUserByEmail(user.getEmail()) == false){
                return repository.save(user);
            }else {
                return user;
            }
        }else {
            return user;
        }
    }

    // This method will to look if an user exist on the system by its email

    public boolean getUserByEmail(String email){
        return repository.getUserByEmail(email);
    }

    public User authUser(String email, String password){
        Optional<User> user = repository.authUser(email, password);

        if (user.isEmpty()){
            return new User(email, password, "NO DEFINIDO");
        }else {
            return user.get();
        }
    }

}
