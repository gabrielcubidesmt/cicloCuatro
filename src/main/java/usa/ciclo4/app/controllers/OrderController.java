package usa.ciclo4.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import usa.ciclo4.app.entities.Order;
import usa.ciclo4.app.services.OrderService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("order")
@CrossOrigin("*")
public class OrderController {
    @Autowired
    private OrderService service;

    @GetMapping("/all")
    public List<Order> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public Optional<Order> getOrder(@PathVariable("id") int id) {
        return service.getOrder(id);
    }

    @PostMapping("/new")
    @ResponseStatus(HttpStatus.CREATED)
    public Order create(@RequestBody Order gadget) {
        return service.create(gadget);
    }

    @PutMapping("/update")
    @ResponseStatus(HttpStatus.CREATED)
    public Order update(@RequestBody Order gadget) {
        return service.update(gadget);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public boolean delete(@PathVariable("id") int id) {
        return service.delete(id);
    }

    //Reto 3:Ordenes de pedido asociadas a los asesores de una zona
    @GetMapping("/zona/{zona}")
    public List<Order> findByZone(@PathVariable("zona") String zona) {
        return service.findByZone(zona);
    }
}
