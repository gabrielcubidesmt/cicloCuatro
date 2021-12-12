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
    /*
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
    }*/

    // This method will to look if an user exist on the system by its email

    public boolean getUserByEmail(String email){
        return repository.getUserByEmail(email);
    }

    public User authUser(String email, String password){
        Optional<User> user = repository.authUser(email, password);

        if (user.isEmpty()){
            return new User();
        }else {
            return user.get();
        }
    }

    public User update(User user){
        if (user.getId() != null){
            Optional<User> userDb = repository.getUser(user.getId());
            if (!userDb.isEmpty()){
                if (user.getIdentification() != null){
                    userDb.get().setIdentification(user.getIdentification());
                }
                if (user.getName() != null){
                    userDb.get().setName(user.getName());
                }
                if (user.getAddress() != null){
                    userDb.get().setAddress(user.getAddress());
                }
                if (user.getCellPhone() != null){
                    userDb.get().setCellPhone(user.getCellPhone());
                }
                if (user.getEmail() != null){
                    userDb.get().setEmail(user.getEmail());
                }
                if (user.getPassword() != null){
                    userDb.get().setPassword(user.getPassword());
                }
                if (user.getZone() != null){
                    userDb.get().setZone(user.getZone());
                }

                repository.update(userDb.get());
                return userDb.get();
            } else {
                return user;
            }
        }else {
            return user;
        }
    }

    public boolean delete(int id){
        Optional<User> user = getUser(id);

        if (user.isEmpty()){
            return false;
        }else {
            repository.delete(user.get());
            return true;
        }
    }


    public User save(User user) {
        Optional<User> userMaxId = repository.lastUserId();
        if (user.getId() == null) {
            if (userMaxId.isEmpty()) {
                user.setId(1);
            } else {
                user.setId(userMaxId.get().getId() + 1);
            }
        }
        Optional<User> e = repository.getUser(user.getId());
        if (e.isEmpty()) {
            if (getUserByEmail(user.getEmail()) == false) {
                return repository.save(user);
            } else {
                return user;
            }
        } else {
            return user;
        }

    }

}
