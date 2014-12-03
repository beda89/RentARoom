package rentaroom.controller;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import rentaroom.entities.Customer;
import rentaroom.repositories.CustomerRepository;
import rentaroom.services.CustomerService;

/**
 * Created by Christian on 29.11.2014.
 */

@Controller
public class CustomerController {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    CustomerService customerService;

    @RequestMapping(value = {"/customer"}, method = RequestMethod.GET)
    public ModelAndView customerPage() {
        ModelAndView model = new ModelAndView("customer");
        Customer c1 = new Customer("Susi", "Sorglos1234");
        c1.setAddress("Liechtensteinstraße 200/26, 1090 Wien");
        c1.setCompanyName("Smart Assistand");
        c1.setHomepage("http://www.google.com");
        c1.setPhone("+436763649593");

        customerRepository.save(c1);
        Customer c2 = customerRepository.findByFirstName("Susi");
        model.addObject("customer", c2);
        return model;
    }

    @RequestMapping(value="/autocomplete/names",method=RequestMethod.GET)
    @ResponseBody
    public String getCustomerJsonListWithId(){

        return customerService.getAllCustomerJson().toJSONString();

    }

}
