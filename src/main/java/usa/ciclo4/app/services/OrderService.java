package usa.ciclo4.app.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import usa.ciclo4.app.entities.Order;
import usa.ciclo4.app.repositories.OrderRepository;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    private OrderRepository repository;

    public List<Order> getAll() {
        return repository.getAll();
    }

    public Optional<Order> getOrder(int id) {
        return repository.getOrder(id);
    }

    public Order create(Order order) {

        //obtiene el maximo id existente en la coleccion
        Optional<Order> orderIdMaxima = repository.lastUserId();

        //si el id de la orden que se recibe como parametro es nulo, entonces valida el maximo id existente en base de datos
        if (order.getId() == null) {
            //valida el maximo id generado, si no hay ninguno aun el primer id sera 1
            if (orderIdMaxima.isEmpty())
                order.setId(1);
                //si retorna informacion suma 1 al maximo id existente y lo asigna como el codigo de la orden
            else
                order.setId(orderIdMaxima.get().getId() + 1);
        }

        Optional<Order> e = repository.getOrder(order.getId());
        if (e.isEmpty()) {
            return repository.create(order);
        }else{
            return order;
        }
    }

    public Order update(Order order) {

        if (order.getId() != null) {
            Optional<Order> orderDb = repository.getOrder(order.getId());
            if (!orderDb.isEmpty()) {
                if (order.getStatus() != null) {
                    orderDb.get().setStatus(order.getStatus());
                }
                repository.update(orderDb.get());
                return orderDb.get();
            } else {
                return order;
            }
        } else {
            return order;
        }
    }

    public boolean delete(int id) {
        Boolean aBoolean = getOrder(id).map(order -> {
            repository.delete(order);
            return true;
        }).orElse(false);
        return aBoolean;
    }

    //Ordenes de pedido asociadas a los asesores de una zona
    public List<Order> findByZone(String zona) {
        return repository.findByZone(zona);
    }

    //Reto 4 Listar ordenes de pedido de un asesor
    public List<Order> ordersSalesManByID(Integer id) {
        return repository.orderSalesManById(id);
    }

    //Reto 4 Listar ordenes de pedido por x estado y asesor
    public List<Order> ordersSalesManByState(String state, Integer id) {
        return repository.orderSalesManByState(state,id);
    }

    //Reto 4: Ordenes de un asesor x Fecha
    public List<Order> ordersSalesManByDate(String dateStr, Integer id){
        return repository.orderSalesManByDate(dateStr, id);
    }
//
//    public List<Order> ordersSalesManByDate(String dateStr, int id) {
//        return orderRepository.ordersSalesManByDate(dateStr, id);
//    }
//
//    public List<Order> ordersSalesManByState(String state, Integer id) {
//        return orderRepository.ordersSalesManByState(state, id);
//    }
}
