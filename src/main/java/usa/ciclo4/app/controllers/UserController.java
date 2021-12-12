package usa.ciclo4.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import usa.ciclo4.app.entities.User;
import usa.ciclo4.app.services.UserService;

import java.util.List;

@RestController
@RequestMapping("user")
@CrossOrigin(origins = "*")
public class UserController {

    @Autowired
    private UserService service;

    // Esta parte es para que al ADMIN obtenga lista de los usuarios actuales
    @GetMapping("/all")
    public List<User> getUsers(){
        return service.getALL();
    }

    // Esta parte se usa cuando el ADMIN va a crear un nuevo usuario
    @PostMapping("/new")
    @ResponseStatus(HttpStatus.CREATED)
    public void save(@RequestBody User user){
        service.save(user);
    }


    // Esta parte permitira verificar si un usuario existe por el atributo 'email'
    @GetMapping("/emailexist/{email}")
    public boolean verification(@PathVariable("email")String email){
        return service.getUserByEmail(email);
    }

    @GetMapping("/{email}/{password}")
    public User authUser(@PathVariable("email")String email, @PathVariable("password")String password){
        return service.authUser(email, password);
    }

    // Actualizacion de Usuario
    @PostMapping("/update")
    @ResponseStatus(HttpStatus.CREATED)
    public User update(@RequestBody User user){
        return service.update(user);
    }

    //Borrar Usuario
    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public boolean delete(@PathVariable("id") int id){
        return service.delete(id);
    }

}