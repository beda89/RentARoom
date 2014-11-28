package rentaroom.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import rentaroom.db.repositories.CustomerRepository;

/**
 * Created by Peter on 11.11.2014.
 */

@Controller
public class LoginController {

    @Autowired
    private CustomerRepository customerRepo;

    @RequestMapping(value = {"/", "/welcome**"}, method = RequestMethod.GET)
    public ModelAndView welcomePage() {

        ModelAndView model = new ModelAndView();
        model.addObject("title", "Generic Title");
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

    @RequestMapping(value = "/dba**", method = RequestMethod.GET)
    public ModelAndView dbaPage() {

        ModelAndView model = new ModelAndView();
        model.addObject("title", "Generic Title");
        model.addObject("message", "This is protected page!");
        model.setViewName("admin");

        return model;
    }

}
