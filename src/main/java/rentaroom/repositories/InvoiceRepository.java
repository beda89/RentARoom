package rentaroom.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import rentaroom.entities.Invoice;

/**
 * Created by Peter on 28.11.2014.
 */
@Repository
public interface InvoiceRepository extends PagingAndSortingRepository<Invoice, Long> {

}
