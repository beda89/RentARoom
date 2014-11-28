package rentaroom.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import rentaroom.db.entities.Customer;
import rentaroom.db.repositories.CustomerRepository;

import java.util.List;

/**
 * Created by Peter on 11.11.2014.
 */

@Controller
public class LoginController {

    @Autowired
    private CustomerRepository customerRepo;

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String hello(ModelMap model) {

        List<Customer> allCustomers=customerRepo.findAll();

        model.addAttribute("message", "Hello World!");
        return "hello";

    }

}
