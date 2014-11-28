package rentaroom.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import rentaroom.db.repositories.CustomerRepository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Peter on 11.11.2014.
 */

@Controller
public class LoginController {

    @Autowired
    private CustomerRepository customerRepo;

    @RequestMapping(method = RequestMethod.GET)
    public String hello(ModelMap model) {

        model.addAttribute("name", "JCG Hello World!");
        return "test";

    }

}
