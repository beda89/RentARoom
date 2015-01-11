package ServiceMockTests;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import rentaroom.Utils.CommonUtils;
import rentaroom.entities.Customer;
import rentaroom.entities.Invoice;
import rentaroom.entities.Reservation;
import rentaroom.repositories.InvoiceRepository;
import rentaroom.services.InvoiceService;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Simerle Christopher
 * Date: 09/01/15
 * Time: 13:44
 * To change this template use File | Settings | File Templates.
 */

@RunWith(MockitoJUnitRunner.class)
public class InvoiceServiceMockTest {

    @Mock
    private InvoiceRepository invoiceRepository;

    @InjectMocks
    private InvoiceService invoiceService = new InvoiceService();

    private Customer customer1;

    private Invoice invoice1;
    private Invoice invoice2;
    private Invoice invoice3;
    private List<Invoice> invoiceList;

    @Before
    public void setUp() {
        customer1 = new Customer("Hans", "Huber");
        customer1.setId("54aec4e360b263045a3db672");
        customer1.setAddress("Weimarer Straße 3/3 1180 Wien");
        customer1.setDiscount(10);
        customer1.setMail("huber@hans.com");
        customer1.setNotes("Stammgast, kommt mehrmals im Jahr");
        customer1.setPhone("+43 7744 1443");

        try {
            invoice1 = new Invoice();
            invoice1.setCustomer(customer1);
            invoice1.setPrice(300L);
            invoice1.setInvoiceDate(CommonUtils.dateFormatter.parse("01.08.2014").getTime());


            invoice2 = new Invoice();
            invoice2.setCustomer(customer1);
            invoice2.setPrice(350L);
            invoice2.setInvoiceDate(CommonUtils.dateFormatter.parse("15.06.2014").getTime());

            invoice3 = new Invoice();
            invoice3.setCustomer(customer1);
            invoice3.setPrice(350L);
            invoice3.setInvoiceDate(CommonUtils.dateFormatter.parse("23.12.2014").getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        invoiceList = new ArrayList<Invoice>();
        invoiceList.add(invoice1);
        invoiceList.add(invoice2);
        invoiceList.add(invoice3);
    }

    @Test
    public void testFindByCustomer(){
        Mockito.when(invoiceRepository.findByCustomerOrderByInvoiceDateDesc(Mockito.any(Customer.class))).thenReturn(invoiceList);
        List<Invoice> returnedInvoiceList = invoiceService.findByCustomer(customer1);
        Assert.assertTrue(!returnedInvoiceList.isEmpty());
        Assert.assertEquals(invoiceList, returnedInvoiceList);
    }
}





























