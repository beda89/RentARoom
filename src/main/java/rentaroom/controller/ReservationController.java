package rentaroom.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import rentaroom.Utils.CommonUtils;
import rentaroom.entities.Invoice;
import rentaroom.entities.Reservation;
import rentaroom.entities.ReservationInProgress;
import rentaroom.services.InvoiceService;
import rentaroom.services.ReservationService;

import java.security.Principal;
import java.util.Date;
import java.util.List;

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


    @RequestMapping(value = {"/reservations/reserve"}, method =RequestMethod.POST)
    public ModelAndView reserveRoom(@RequestParam List<String> roomCheckbox){

        ModelAndView model = new ModelAndView("reservation_step2");

        ReservationInProgress reservationInProgress=reservationService.preReserveSelectedDays(roomCheckbox);


        model.addObject("progressId",reservationInProgress.getId());
        model.addObject("reservationInProgress",reservationInProgress);

        return model;
    }


    @RequestMapping(value = {"/reservations/reserve/step2/{id}"}, method =RequestMethod.POST)
    public ModelAndView reservationStep2(@PathVariable String id){

        ModelAndView model = new ModelAndView("reservation_step3");
        model.addObject("progressId",id);

        return model;
    }

    //handling when existing customer is choosen
    @RequestMapping(value = {"/reservations/reserve/step3_1/{id}"}, method =RequestMethod.POST)
    public ModelAndView reservationStep3_1(@PathVariable String id,@RequestParam String selectedCustomerId){

        ReservationInProgress reservationInProgress=reservationService.addCustomerToReservationInProgess(id, selectedCustomerId);

        ModelAndView model = new ModelAndView("reservation_confirm");
        model.addObject("progressId",id);
        model.addObject("reservationInProgress",reservationInProgress);

        Date fromDate=new Date(reservationInProgress.getDateFrom());
        Date toDate=new Date(reservationInProgress.getDateTo());

        model.addObject("formattedDateFrom", CommonUtils.getGermanWeekday(fromDate)+" "+CommonUtils.dateFormatter.format(fromDate));
        model.addObject("formattedDateTo",CommonUtils.getGermanWeekday(toDate)+" "+CommonUtils.dateFormatter.format(toDate));

        return model;
    }

    //handling when new customer is created for reservation
    @RequestMapping(value = {"/reservations/reserve/step3_2/{id}"}, method =RequestMethod.POST)
    public ModelAndView reservationStep3_2(@PathVariable String id){

        ModelAndView model = new ModelAndView("reservation_confirm");
        model.addObject("progressId",id);

        return model;
    }


    @RequestMapping(value = {"/reservations/reserve/confirm/{id}"}, method =RequestMethod.POST)
    public String reservationConfirm(@PathVariable String id){

        reservationService.confirmReservation(id);

        return "redirect:/rooms";
    }

}
