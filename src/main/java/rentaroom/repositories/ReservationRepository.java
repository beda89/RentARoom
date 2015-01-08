package rentaroom.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import rentaroom.entities.Customer;
import rentaroom.entities.Reservation;
import rentaroom.entities.Room;

import java.util.List;

/**
 * Created by Peter on 28.11.2014.
 */

@Repository
public interface ReservationRepository extends PagingAndSortingRepository<Reservation, String> {
    public List<Reservation> findByCustomer(Customer c);
    public List<Reservation> findByDateFromGreaterThan(long time);


    @Query("{$or:['dateFrom' : {$gte : ?0, $lt : ?1}, 'dateTo' : {$gte: ?0 , $lt: ?1 }]}")
    public List<Reservation> getReservedRoomsByDates(Long beginDate, Long endDate);

}
