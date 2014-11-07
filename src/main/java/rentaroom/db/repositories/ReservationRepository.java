package rentaroom.db.repositories;

import rentaroom.db.entities.Reservation;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Peter on 07.11.2014.
 */
@Repository
public interface ReservationRepository extends CrudRepository<Reservation,Long> {
}
