package rentaroom.guest;
 
import javax.servlet.http.HttpServletRequest;

import rentaroom.db.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
 
@Controller
public class GuestController {
 
    @Autowired
    private GuestDao guestDao;

    @Autowired
    private CustomerRepository customerRepo;


    @RequestMapping(value= "/rentaroom/guest")
    public ModelAndView guestbook(HttpServletRequest request) {
        // Handle a new rentaroom.guest (if any):
        String name = request.getParameter("name");


        customerRepo.findByName("dummy");

        if (name != null)
            guestDao.persist(new Guest(name));




        // Prepare the result view (rentaroom.guest.jsp):
        return new ModelAndView("guest.jsp", "guestDao", guestDao);
    }
}