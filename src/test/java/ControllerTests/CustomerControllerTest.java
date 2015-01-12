package ControllerTests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.servlet.ModelAndView;
import rentaroom.config.MongoConfig;
import rentaroom.controller.CustomerController;
import rentaroom.entities.Customer;
import rentaroom.repositories.CustomerRepository;
import rentaroom.services.CustomerService;
import rentaroom.services.InvoiceService;
import rentaroom.services.ReservationService;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created with IntelliJ IDEA.
 * User: Simerle Christopher
 * Date: 12/01/15
 * Time: 11:14
 * To change this template use File | Settings | File Templates.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {MongoConfig.class, CustomerController.class, CustomerService.class, ReservationService.class, InvoiceService.class})
public class CustomerControllerTest {

    @Autowired
    private CustomerController customerController;

    @Autowired
    CustomerRepository customerRepository;

    private Iterable<Customer> customersBackup;
    private Customer c;
    private final String FIRSTNAME = "CustomerControllerTest",
            LASTNAME = "Sorglos",
            ADDRESS = "Testweg 12",
            COMPANYNAME = "Apple",
            PHONE = "1244363463",
            FAX = "16565143",
            MAIL = "test@mail.com",
            HOMEPAGE = "https://bitbucket.org/csim/rentaroom/overview",
            AVATARURL = "",
            NOTES = "very reliable customer";

    @Before
    public void setUp() {
        customersBackup = customerRepository.findAll();
        c = new Customer(FIRSTNAME, LASTNAME);
        c.setAddress(ADDRESS);
        c.setCompanyName(COMPANYNAME);
        c.setPhone(PHONE);
        c.setFax(FAX);
        c.setMail(MAIL);
        c.setHomepage(HOMEPAGE);
        c.setAvatarUrl(AVATARURL);
        c.setNotes(NOTES);
    }

    @After
    public void tearDown() {
        customerRepository.deleteAll();
        customerRepository.save(customersBackup);
        c = null;
    }

    @Test
    public void testCustomerPage() throws Exception {
        customerRepository.save(c);
        Customer savedCustomer = customerRepository.findByFirstName("CustomerControllerTest");
        ModelAndView model = customerController.customerPage(savedCustomer.getId());
        assertEquals("customer", model.getViewName());
        assertTrue(model.getModel().containsKey("customer"));
        assertTrue(model.getModel().containsKey("reservations"));
        assertTrue(model.getModel().containsKey("invoices"));
        assertEquals(savedCustomer.getId(), ((Customer) model.getModel().get("customer")).getId());
    }

    @Test
    public void testAddCustomer() throws Exception {
        String response = customerController.addCustomer(FIRSTNAME, LASTNAME, ADDRESS, COMPANYNAME, PHONE, FAX, MAIL, HOMEPAGE, AVATARURL, NOTES);
        Customer savedCustomer = customerRepository.findByFirstName("CustomerControllerTest");
        assertEquals("redirect:/customer/" + savedCustomer.getId(), response);
    }


    @Test
    public void testEditCustomer() throws Exception {
        customerController.addCustomer(FIRSTNAME, LASTNAME, ADDRESS, COMPANYNAME, PHONE, FAX, MAIL, HOMEPAGE, AVATARURL, NOTES);
        Customer customer = customerRepository.findByFirstName("CustomerControllerTest");
        String response = customerController.editCustomer(customer.getId(), "CustomerControllerTestEDITED", LASTNAME, ADDRESS, COMPANYNAME, PHONE, FAX, MAIL, HOMEPAGE, AVATARURL, NOTES);
        Customer editedCustomer = customerRepository.findByFirstName("CustomerControllerTestEDITED");
        assertEquals("redirect:/customer/" + editedCustomer.getId(), response);
    }
}























