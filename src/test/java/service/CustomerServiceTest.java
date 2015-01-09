package service;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import rentaroom.config.MongoConfig;
import rentaroom.entities.Customer;
import rentaroom.repositories.CustomerRepository;
import rentaroom.services.CustomerService;
import sun.security.x509.AVA;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Christian on 31.12.2014.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {MongoConfig.class, CustomerService.class})
public class CustomerServiceTest {

    @Autowired
    CustomerService customerService;

    @Autowired
    CustomerRepository customerRepository;

    private Iterable<Customer> customersBackup;
    private Customer c;
    private final String FIRSTNAME = "Susi",
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
    public void testAddNewCustomer() {
        Customer saved = customerService.add(FIRSTNAME, LASTNAME, ADDRESS, COMPANYNAME, PHONE, FAX, MAIL, HOMEPAGE, AVATARURL, NOTES);
        assertNotNull(customerRepository.findOne(saved.getId()));
        assertEquals(FIRSTNAME, saved.getFirstName());
        assertEquals(LASTNAME, saved.getLastName());
        assertEquals(ADDRESS, saved.getAddress());
        assertEquals(COMPANYNAME, saved.getCompanyName());
        assertEquals(PHONE, saved.getPhone());
        assertEquals(FAX, saved.getFax());
        assertEquals(MAIL, saved.getMail());
        assertEquals(HOMEPAGE, saved.getHomepage());
        assertEquals(AVATARURL, saved.getAvatarUrl());
        assertEquals(NOTES, saved.getNotes());
    }

    @Test
    public void testFindById() {
        String id = "best-id-ever";
        c.setId(id);
        customerRepository.save(c);
        Customer retrieved = customerService.findById(id);
        assertNotNull(retrieved);
        assertEquals(c.getId(), retrieved.getId());
    }

    @Test
    public void testEditCustomer() {
        Customer saved = customerRepository.save(c);
        customerService.edit(saved.getId(),
                saved.getFirstName(),
                saved.getLastName(),
                ADDRESS + "123",
                saved.getCompanyName(),
                saved.getPhone(),
                saved.getFax(),
                "12345" + MAIL,
                saved.getHomepage(),
                saved.getAvatarUrl(),
                "notes");
        Customer edited = customerRepository.findOne(saved.getId());
        assertNotNull(edited);
        assertEquals(ADDRESS + "123", edited.getAddress());
        assertEquals("12345" + MAIL, edited.getMail());
        assertEquals("notes", edited.getNotes());
    }

}
