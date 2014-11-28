package rentaroom.db.daos;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import rentaroom.guest.Guest;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Created by Peter on 28.11.2014.
 */
@Component
public class CustomerDao {

    // Injected database connection:
    @PersistenceContext
    private EntityManager em;

    // Stores a new rentaroom.guest:
    @Transactional
    public void persist(Guest guest) {
        em.persist(guest);
    }

    // Retrieves all the guests:
    public List<Guest> getAllGuests() {
        TypedQuery<Guest> query = em.createQuery(
                "SELECT g FROM Guest g ORDER BY g.id", Guest.class);
        return query.getResultList();
    }

}