package rentaroom.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import rentaroom.entities.Customer;
import rentaroom.entities.Reservation;

import java.util.List;

/**
 * Created by Peter on 28.11.2014.
 */

@Repository
public interface ReservationRepository extends PagingAndSortingRepository<Reservation, String> {
    public List<Reservation> findByCustomer(Customer c);
    public List<Reservation> findByDateFromGreaterThan(long time);
}
