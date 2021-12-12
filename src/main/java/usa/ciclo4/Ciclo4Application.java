package usa.ciclo4;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;
import usa.ciclo4.app.repositories.crud.ClothesCrudRpository;
import usa.ciclo4.app.repositories.crud.OrderCrudRepository;
import usa.ciclo4.app.repositories.crud.UserCrudRepository;

@Component
@SpringBootApplication
public class Ciclo4Application implements CommandLineRunner {
	@Autowired
	private ClothesCrudRpository clothesCrudRpository;
	@Autowired
	private OrderCrudRepository orderCrudRepository;
	@Autowired
	private UserCrudRepository userCrudRepository;

		public static void main(String[] args) {
			SpringApplication.run(Ciclo4Application.class, args);
		}
	@Override
	public void run(String... args) throws Exception{
			clothesCrudRpository.deleteAll();
			orderCrudRepository.deleteAll();
			userCrudRepository.deleteAll();
	}
}
