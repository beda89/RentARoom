package rentaroom.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import rentaroom.db.repositories.CustomerRepository;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Peter on 11.11.2014.
 */

@Controller
public class LoginController {

    @Autowired
    private CustomerRepository customerRepo;

    @RequestMapping(value= "/guest")
    public ModelAndView guestbook(HttpServletRequest request) {
        // Handle a new rentaroom.guest (if any):
       /* String name = request.getParameter("name");

        if (name != null)
            guestDao.persist(new Guest(name));

        // Prepare the result view (rentaroom.guest.jsp):
        return new ModelAndView("guest.jsp", "guestDao", guestDao); */

        return null;
    }

}
