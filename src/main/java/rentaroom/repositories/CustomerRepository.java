package rentaroom.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import rentaroom.entities.Customer;

/**
 * Created by Peter on 11.11.2014.
 */
@Repository
public interface CustomerRepository extends PagingAndSortingRepository<Customer, String> {
    public Customer findByFirstName(String firstName);
}
