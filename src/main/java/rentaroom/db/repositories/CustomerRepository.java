package rentaroom.db.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import rentaroom.db.entities.Customer;

/**
 * Created by Peter on 11.11.2014.
 */
@Repository
public interface CustomerRepository extends MongoRepository<Customer,Long> {

}
