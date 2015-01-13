package rentaroom.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import rentaroom.entities.Customer;
import rentaroom.entities.ReservationInProgress;

/**
 * Created by Peter on 11.11.2014.
 */
@Repository
public interface ReservationInProgressRepository extends PagingAndSortingRepository<ReservationInProgress, String> {

}
