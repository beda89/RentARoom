package ServiceTests;

import org.json.simple.JSONObject;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import rentaroom.entities.Customer;
import rentaroom.repositories.CustomerRepository;
import rentaroom.services.CustomerService;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: Simerle Christopher
 * Date: 08/01/15
 * Time: 18:46
 * To change this template use File | Settings | File Templates.
 */

@RunWith(MockitoJUnitRunner.class)
public class CustomerServiceTest {

    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private CustomerService customerService = new CustomerService();

    private Customer customer1;
    private ArrayList<Customer> customerList;

    @Before
    public void setUp() {
        customer1 = new Customer("Hans", "Huber");
        customer1.setId("54aec4e360b263045a3db672");
        customer1.setAddress("Weimarer Straße 3/3 1180 Wien");
        customer1.setDiscount(10);
        customer1.setMail("huber@hans.com");
        customer1.setNotes("Stammgast, kommt mehrmals im Jahr");
        customer1.setPhone("+43 7744 1443");

        customerList = new ArrayList<Customer>();
        customerList.add(customer1);
        customerList.add(customer1);
        customerList.add(customer1);
        customerList.add(customer1);
    }

    @Test
    public void testGetAllCustomerJson() {
        Mockito.when(customerRepository.findAll()).thenReturn(customerList);
        JSONObject getAllCustomerJsonResult = customerService.getAllCustomerJson();
        Assert.assertTrue(!getAllCustomerJsonResult.isEmpty());
        Assert.assertEquals(getAllCustomerJsonResult.toJSONString(), "{\"suggestions\":[{\"data\":\"54aec4e360b263045a3db672\",\"value\":\"Huber Hans\"},{\"data\":\"54aec4e360b263045a3db672\",\"value\":\"Huber Hans\"},{\"data\":\"54aec4e360b263045a3db672\",\"value\":\"Huber Hans\"},{\"data\":\"54aec4e360b263045a3db672\",\"value\":\"Huber Hans\"}]}");
    }

    @Test
    public void testFindById() {
        Mockito.when(customerRepository.findOne("1")).thenReturn(customer1);
        Customer CustomerResult = customerService.findById("1");
        Assert.assertEquals(customer1, CustomerResult);
    }

    @Test
    public void testFindById_NotFound() {
        Mockito.when(customerRepository.findOne("1")).thenReturn(null);
        Customer CustomerResult = customerService.findById("20");
        Assert.assertEquals(null, CustomerResult);
    }

    @Test
    public void testAdd() {
        Mockito.when(customerRepository.save(any(Customer.class))).thenReturn(customer1);
        Customer CustomerResult = customerService.add("Hans", "Huber", "Weimarer Straße 3/3 1180 Wien", "", "+43 7744 1443", "", "huber@hans.com", "", "", "Stammgast, kommt mehrmals im Jahr");
        Assert.assertEquals(customer1, CustomerResult);
    }
}
