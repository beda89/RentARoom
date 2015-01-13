package rentaroom.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import rentaroom.entities.Invoice;
import rentaroom.entities.Reservation;
import rentaroom.services.InvoiceService;
import rentaroom.services.ReservationService;

import java.security.Principal;

/**
 * Created by Christian on 08.01.2015.
 */
@Controller
public class ReservationController {

    @Autowired
    ReservationService reservationService;

    @RequestMapping(value = {"/reservations/cancel/{id}"}, method = RequestMethod.POST)
    public ModelAndView cancelReservation(@PathVariable String id) {
        Reservation r = reservationService.findById(id);
        reservationService.cancel(r);
        return new ModelAndView("redirect:/customer/" + r.getCustomer().getId());
    }

    @RequestMapping(value = {"/reservations/checkout/{id}"}, method = RequestMethod.POST)
    public ModelAndView checkoutReservation(@PathVariable String id) {
        Reservation r = reservationService.findById(id);
        reservationService.checkout(r);
        return new ModelAndView("redirect:/customer/" + r.getCustomer().getId());
    }

}
