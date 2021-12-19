package usa.ciclo4.app.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;
import usa.ciclo4.app.entities.Order;
import usa.ciclo4.app.repositories.crud.OrderCrudRepository;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Repository
public class OrderRepository {
    @Autowired
    private OrderCrudRepository repository;

    @Autowired
    private MongoTemplate mongoTemplate;


    public List<Order> getAll() {
        return (List<Order>) repository.findAll();
    }

    public Optional<Order> getOrder(int id) {
        return repository.findById(id);
    }

    public Order create(Order order) {
        return repository.save(order);
    }

    public void update(Order order) {
        repository.save(order);
    }

    public void delete(Order order) {
        repository.delete(order);
    }

    public Optional<Order> lastUserId(){
        return repository.findTopByOrderByIdDesc();
    }

    public List<Order> findByZone(String zona) {
        return repository.findByZone(zona);
    }

    // Ordenes Asesor
    public List<Order> orderSalesManByID(Integer id){
        Query query = new Query();
        Criteria dateCriteria = Criteria.where("salesMan.id").is(id);

        query.addCriteria(dateCriteria);
        List<Order> orders = mongoTemplate.find(query, Order.class);

        return orders;
    }

    //Ordenes Asesor Estado
    public List<Order> orderSalesManByState(String state, Integer id){
        Query query = new Query();
        Criteria dateCriteria = Criteria.where("salesMan.id").is(id).and("status").is(state);
        query.addCriteria(dateCriteria);
        List<Order> orders = mongoTemplate.find(query, Order.class);

        return orders;
    }

    //Ordenes Asesor Fecha
    public List<Order> orderSalesManByDate(String dateStr, Integer id){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        Query query = new Query();
        Criteria dateCriteria = Criteria.where("registerDay")
                .gte(LocalDate.parse(dateStr,dtf).minusDays(1).atStartOfDay())
                .lt(LocalDate.parse(dateStr, dtf).plusDays(2).atStartOfDay())
                .and("salesMan.id").is(id);
        query.addCriteria(dateCriteria);
        List<Order> orders = mongoTemplate.find(query, Order.class);
        return orders;
    }
}
