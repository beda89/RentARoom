package rentaroom.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import rentaroom.entities.Customer;
import rentaroom.repositories.CustomerRepository;

/**
 * Created by Peter on 11.11.2014.
 */

@Controller
@EnableMongoRepositories
public class LoginController {

    //@Autowired
    private CustomerRepository customerRepo;

    @RequestMapping(value = {"/", "/welcome**"}, method = RequestMethod.GET)
    public ModelAndView welcomePage() {
        customerRepo.save(new Customer("John", "Doe"));
        Customer customer = customerRepo.findByFirstName("John");
        ModelAndView model = new ModelAndView();
        model.addObject("title", customer.getFirstName() + " " + customer.getLastName());
        model.addObject("message", "Welcome and a generic message here.");
        model.setViewName("hello");
        return model;

    }

    @RequestMapping(value = "/login**", method = RequestMethod.GET)
    public ModelAndView login(
            @RequestParam(value = "error", required = false) String error,
            @RequestParam(value = "logout", required = false) String logout) {
        ModelAndView model = new ModelAndView();
        if (error != null) {
            model.addObject("error", "Invalid username and password!");
        }
        if (logout != null) {
            model.addObject("msg", "You've been logged out successfully.");
        }
        return model;
    }

}
