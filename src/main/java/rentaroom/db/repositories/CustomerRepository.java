package rentaroom.db.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import rentaroom.db.entities.*;

import java.util.List;

/**
 * Created by Peter on 07.11.2014.
 */

@Repository
public interface CustomerRepository extends CrudRepository<Customer,Long> {

    @Query("SELECT c " +
            "FROM Customer c " +
            "WHERE c.name = :name" )
    List<Customer> findByName(@Param("name") String name);


}
