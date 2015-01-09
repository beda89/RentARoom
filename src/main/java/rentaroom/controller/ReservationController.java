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

    @Autowired
    InvoiceService invoiceService;

    @RequestMapping(value = {"/reservations/cancel/{id}"}, method = RequestMethod.POST)
    public ModelAndView cancelReservation(@PathVariable String id) {
        String customerId = reservationService.findById(id).getCustomer().getId();
        reservationService.delete(id);
        return new ModelAndView("redirect:/customer/" + customerId);
    }

    @RequestMapping(value = {"/reservations/checkout/{id}"}, method = RequestMethod.POST)
    public ModelAndView checkoutReservation(@PathVariable String id) {
        Reservation r = reservationService.findById(id);
        Invoice i = new Invoice();
        i.setCustomer(r.getCustomer());
        i.setPrice((long)(r.getRoomPrice() * (100 - r.getDiscount()) / 100.0));
        i.setInvoiceDate(System.currentTimeMillis());
        String customerId = r.getCustomer().getId();
        reservationService.delete(r.getId());
        invoiceService.add(i);
        return new ModelAndView("redirect:/customer/" + customerId);
    }

}
