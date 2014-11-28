package TestData;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import rentaroom.db.repositories.CustomerRepository;
import rentaroom.db.repositories.InvoiceRepository;
import rentaroom.db.repositories.ReservationRepository;
import rentaroom.db.repositories.RoomRepository;

/**
 * Created by Peter on 28.11.2014.
 */
@RunWith(SpringJUnit4ClassRunner.class)
public class TestData {

    @Autowired
    private CustomerRepository customerRepo;

    @Autowired
    private RoomRepository roomRepo;

    @Autowired
    private ReservationRepository reservationRepo;

    @Autowired
    private InvoiceRepository invoiceRepo;

    @Test
    public void initDBWithTestData(){
        //setBackDatabase
        customerRepo.deleteAll();
        roomRepo.deleteAll();
        reservationRepo.deleteAll();
        invoiceRepo.deleteAll();


        //TODO: generate new testdata


    }

}
