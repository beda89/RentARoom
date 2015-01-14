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
    public List<Reservation> findByDateToGreaterThanEqualOrderByDateFromAsc(long time);

    @Query("{$or: [" +
            " {$and: " +
            "[ {'dateFrom':{$gte :?0}}," +
            "  {'dateFrom':{$lte: ?1}}" +
            "]" +
            " }," +
            " {$and: " +
            "[ {'dateTo': {$gte : ?0}}," +
            "  {'dateTo': {$lte: ?1}}" +
            "]" +
            " }," +
            " {$and: " +
            "[ {'dateFrom':{$lte: ?1}}," +
            "  {'dateTo': {$gte : ?0}}" +
            "]" +
            " }" +
            "]} ")
    public List<Reservation> getReservationsByDate(Long beginDate, Long endDate);


}
