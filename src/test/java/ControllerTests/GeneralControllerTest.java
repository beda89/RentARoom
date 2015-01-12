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
import rentaroom.controller.GeneralController;
import rentaroom.entities.Customer;
import rentaroom.services.CustomerService;
import rentaroom.services.InvoiceService;
import rentaroom.services.ReservationService;

import java.security.Principal;

import static org.junit.Assert.assertEquals;

/**
 * Created with IntelliJ IDEA.
 * User: Simerle Christopher
 * Date: 12/01/15
 * Time: 11:53
 * To change this template use File | Settings | File Templates.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {MongoConfig.class, GeneralController.class})
public class GeneralControllerTest {

    @Autowired
    private GeneralController generalController;

    @Test
    public void testAboutPage(){
        ModelAndView model = generalController.aboutPage();
        assertEquals("about", model.getViewName());
    }

    @Test
    public void testIndexPageNoPrincipal() throws Exception{
        String response = generalController.indexPage(null, null);
        assertEquals("redirect:/login", response);
    }

    @Test
    public void testIndexPageWithPrincipal() throws Exception{
        String response = generalController.indexPage(null, new Principal() {
            public String getName() {
                return "admin";
            }
        });
        assertEquals("redirect:/rooms", response);
    }
}
