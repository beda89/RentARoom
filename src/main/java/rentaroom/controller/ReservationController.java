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
        Reservation r = reservationService.findById(id);

        Invoice i = new Invoice();
        i.setCustomer(r.getCustomer());
        i.setPrice(1000L);
        i.setInvoiceDate(System.currentTimeMillis());
        i.setNotes("storniert");
        i.setReservation(r);
        String customerId = r.getCustomer().getId();
        invoiceService.add(i);

        reservationService.delete(id);
        return new ModelAndView("redirect:/customer/" + customerId);
    }

    @RequestMapping(value = {"/reservations/checkout/{id}"}, method = RequestMethod.POST)
    public ModelAndView checkoutReservation(@PathVariable String id) {
        Reservation r = reservationService.findById(id);

        Invoice i = new Invoice();
        i.setCustomer(r.getCustomer());
        i.setInvoiceDate(System.currentTimeMillis());
        double numDays = ((r.getDateTo() - r.getDateFrom()) / 86400000); // gesamtdauer der reservierung
        i.setPrice((long)(r.getRoomPrice() * numDays * (100 - r.getDiscount()) / 100.0));
        i.setReservation(r);
        if (System.currentTimeMillis() < r.getDateTo() ) {
            // frühzeitige Abreise -> prozentuelle abrechnung + 15% aufschlag
            i.setNotes("frühzeitige Abreise");
            double spentDays = ((System.currentTimeMillis() - r.getDateFrom()) / 86400000); // anzahl der gebliebenen tage
            i.setPrice((long)((double)(i.getPrice()) * Math.min(1.0, (spentDays / numDays) + 0.15)));
        }
        String customerId = r.getCustomer().getId();
        invoiceService.add(i);

        reservationService.delete(r.getId());
        return new ModelAndView("redirect:/customer/" + customerId);
    }

}
