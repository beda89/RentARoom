package rentaroom.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import rentaroom.entities.Customer;
import rentaroom.repositories.CustomerRepository;

/**
 * Created by Peter on 11.11.2014.
 */

@Controller
@EnableMongoRepositories
public class LoginController {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    MongoTemplate mongoTemplate;

    @RequestMapping(value = {"/", "/welcome**"}, method = RequestMethod.GET)
    public ModelAndView welcomePage() {

        customerRepository.save(new Customer("John", "Doe"));

        Customer customer = customerRepository.findByFirstName("John");

        ModelAndView model = new ModelAndView();
        model.addObject("title", customer.getFirstName() + " " + customer.getLastName());
        model.addObject("message", "Welcome and a generic message here.");
        model.setViewName("hello");
        return model;

    }

    @RequestMapping(value = "/admin**", method = RequestMethod.GET)
    public ModelAndView adminPage() {

        ModelAndView model = new ModelAndView();
        model.addObject("title", "Generic Title");
        model.addObject("message", "This is protected page!");
        model.setViewName("admin");

        return model;

    }

}
