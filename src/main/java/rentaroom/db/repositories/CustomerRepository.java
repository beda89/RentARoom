package rentaroom.db.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import rentaroom.db.entities.Customer;

/**
 * Created by Peter on 11.11.2014.
 */
@Repository //-- not working, config is wrong
public interface CustomerRepository extends CrudRepository<Customer,Long> {

}
