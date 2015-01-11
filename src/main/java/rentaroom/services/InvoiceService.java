package rentaroom.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rentaroom.entities.Customer;
import rentaroom.entities.Invoice;
import rentaroom.entities.Reservation;
import rentaroom.repositories.InvoiceRepository;
import rentaroom.repositories.ReservationRepository;

import java.util.List;

/**
 * Created by Peter on 03.12.2014.
 */
@Service
public class InvoiceService {

    @Autowired
    private InvoiceRepository invoiceRepo;

    public List<Invoice> findByCustomer(Customer c) {
        return invoiceRepo.findByCustomerOrderByInvoiceDateDesc(c);
    }

    public void add(Invoice i) {
        invoiceRepo.save(i);
    }

}
