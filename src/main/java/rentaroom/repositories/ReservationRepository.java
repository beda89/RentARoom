package rentaroom.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import rentaroom.entities.Reservation;

/**
 * Created by Peter on 28.11.2014.
 */
public interface ReservationRepository extends MongoRepository<Reservation,Long> {
}
