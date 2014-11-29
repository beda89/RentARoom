package rentaroom.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import rentaroom.entities.Customer;

/**
 * Created by Peter on 28.11.2014.
 */
@Controller
public class GeneralController {

    @RequestMapping(value = {"/about"}, method = RequestMethod.GET)
    public ModelAndView aboutPage() {
        return new ModelAndView("about");
    }

}
