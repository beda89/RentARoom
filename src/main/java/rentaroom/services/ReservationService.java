package rentaroom.services;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import rentaroom.entities.Customer;
import rentaroom.entities.Reservation;
import rentaroom.repositories.CustomerRepository;
import rentaroom.repositories.ReservationRepository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Peter on 03.12.2014.
 */
@Service
public class ReservationService {

    @Autowired
    private ReservationRepository reservationRepo;

    public List<Reservation> findOutstandingByCustomer(Customer c) {
        List<Reservation> reservations = new ArrayList<Reservation>();
        if (c != null) {
            for (Reservation r : reservationRepo.findByDateFromGreaterThan(new Date().getTime())) {
                if (r.getCustomer().getId().equals(c.getId())) {
                    reservations.add(r);
                }
            }
        }
        return reservations;
    }

}
