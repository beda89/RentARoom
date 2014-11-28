package rentaroom.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import rentaroom.entities.Customer;

import java.util.List;

/**
 * Created by Peter on 11.11.2014.
 */
@Repository
public interface CustomerRepository extends MongoRepository<Customer, String> {
    public Customer findByFirstName(String firstName);
}
