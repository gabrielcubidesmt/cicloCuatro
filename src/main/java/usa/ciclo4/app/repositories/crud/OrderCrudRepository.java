package usa.ciclo4.app.repositories.crud;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import usa.ciclo4.app.entities.Order;

import java.util.List;
import java.util.Optional;

public interface OrderCrudRepository extends MongoRepository<Order, Integer> {

    //Retorna las ordenes de pedido que coincidad con la zona recibida como parametro
    @Query("{'salesMan.zone': ?0}")
    List<Order> findByZone(final String zone);

    //Retorna las ordenes filtradas por  estado
    @Query("{status: ?0}")
    List<Order> findByStatus(final String status);

    //Seleccion max id
    Optional<Order> findTopByOrderByIdDesc();
}
