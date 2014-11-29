package rentaroom.controller;

import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import rentaroom.entities.Customer;
import rentaroom.repositories.CustomerRepository;

/**
 * Created by Christian on 29.11.2014.
 */

@Controller
public class RoomController {

    @RequestMapping(value = {"/rooms"}, method = RequestMethod.GET)
    public ModelAndView roomsPage() {
        return new ModelAndView("rooms");
    }

}
