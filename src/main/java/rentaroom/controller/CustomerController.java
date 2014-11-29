package rentaroom.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import rentaroom.entities.Customer;

/**
 * Created by Christian on 29.11.2014.
 */

@Controller
public class CustomerController {

    @RequestMapping(value = {"/customer"}, method = RequestMethod.GET)
    public ModelAndView customerPage() {
        ModelAndView model = new ModelAndView("customer");
        Customer c1 = new Customer("Susi", "Sorglos");
        c1.setAddress("Liechtensteinstraße 200/26, 1090 Wien");
        c1.setCompanyName("Smart Assistand");
        c1.setHomepage("http://www.google.com");
        c1.setPhone("+436763649593");
        model.addObject("customer", c1);
        return model;
    }

}
