package ServiceTests;

import junit.framework.Assert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import rentaroom.Utils.CommonUtils;
import rentaroom.config.MongoConfig;
import rentaroom.entities.Customer;
import rentaroom.entities.Invoice;
import rentaroom.entities.Reservation;
import rentaroom.repositories.InvoiceRepository;
import rentaroom.services.InvoiceService;

import java.text.ParseException;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created with IntelliJ IDEA.
 * User: Simerle Christopher
 * Date: 09/01/15
 * Time: 15:40
 * To change this template use File | Settings | File Templates.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {MongoConfig.class, InvoiceService.class})
public class InvoiceServiceTest {

    @Autowired
    InvoiceService invoiceService;

    @Autowired
    InvoiceRepository invoiceRepository;

    private Iterable<Invoice> invoiceBackup;
    private Customer customer2;
    private Invoice i1;
    private Invoice i2;
    private Invoice i3;

    @Before
    public void setUp() {
        invoiceBackup = invoiceRepository.findAll();
        customer2 = new Customer("Sepp", "Mayerhofer");
        customer2.setId("sepps_id");
        customer2.setAddress("Munderfing 19");
        customer2.setDiscount(10);
        customer2.setMail("sepp@hofer.com");
        customer2.setNotes("Stammgast");
        customer2.setPhone("+43 680 1231443234");
        customer2.setAvatarUrl("some_avatar");

        try {
            i1 = new Invoice();
            i1.setCustomer(customer2);
            i1.setPrice(300L);
            i1.setInvoiceDate(CommonUtils.dateFormatter.parse("01.08.2018").getTime());

            i2 = new Invoice();
            i2.setCustomer(customer2);
            i2.setPrice(350L);
            i2.setInvoiceDate(CommonUtils.dateFormatter.parse("15.06.2018").getTime());

            i3 = new Invoice();
            i3.setCustomer(customer2);
            i3.setPrice(350L);
            i3.setInvoiceDate(CommonUtils.dateFormatter.parse("23.12.2018").getTime());

            invoiceRepository.save(i1);
            invoiceRepository.save(i2);
            invoiceRepository.save(i3);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @After
    public void tearDown() {
        invoiceRepository.deleteAll();
        invoiceRepository.save(invoiceBackup);
    }

    @Test
    public void testFindByCustomer() {
        List<Invoice> foundInvoiceList = invoiceService.findByCustomer(customer2);
        assertTrue(!foundInvoiceList.isEmpty());
        assertEquals(customer2.getFirstName(), foundInvoiceList.get(0).getCustomer().getFirstName());
    }
}
