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


    @RequestMapping(value = {"/reservations/reserve"}, method =RequestMethod.POST)
    public ModelAndView reserveRoom(@RequestParam List<String> roomCheckbox){

        ModelAndView model = new ModelAndView("reservation_step2");

        ReservationInProgress reservationInProgress=reservationService.preReserveSelectedDays(roomCheckbox);


        model.addObject("progressId",reservationInProgress.getId());
        model.addObject("reservationInProgress",reservationInProgress);

        return model;
    }


    @RequestMapping(value = {"/reservations/reserve/step2/{id}"}, method =RequestMethod.POST)
    public ModelAndView reservationStep2(@PathVariable String id, @RequestParam List<String> roomSelection ){


        reservationService.setSelectedRooms(id,roomSelection);

        ModelAndView model = new ModelAndView("reservation_step3");
        model.addObject("progressId",id);

        return model;
    }


    @RequestMapping(value = {"/reservations/reserve/step2/{id}"}, method =RequestMethod.GET)
         public ModelAndView reservationBackToStep2(@PathVariable String id){

        ModelAndView model = new ModelAndView("reservation_step2");

        ReservationInProgress reservationInProgress=reservationService.findInProgressById(id);

        model.addObject("progressId",reservationInProgress.getId());
        model.addObject("reservationInProgress",reservationInProgress);


        return model;
    }


    @RequestMapping(value = {"/reservations/reserve/step3/{id}"}, method =RequestMethod.GET)
    public ModelAndView reservationBackToStep3(@PathVariable String id){

        ModelAndView model = new ModelAndView("reservation_step3");

        ReservationInProgress reservationInProgress=reservationService.findInProgressById(id);

        model.addObject("progressId",reservationInProgress.getId());
        model.addObject("reservationInProgress",reservationInProgress);
        model.addObject("chosenName",reservationInProgress.getCustomer().getFirstName()+" "+reservationInProgress.getCustomer().getLastName());
        model.addObject("userId",reservationInProgress.getCustomer().getId());

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
    public ModelAndView reservationStep3_2(@PathVariable String id,
                                           @RequestParam String firstName,
                                           @RequestParam String lastName,
                                           @RequestParam String address,
                                           @RequestParam String companyName,
                                           @RequestParam String phone,
                                           @RequestParam String fax,
                                           @RequestParam String mail,
                                           @RequestParam String homepage,
                                           @RequestParam String avatarUrl,
                                           @RequestParam String notes){
        ReservationInProgress reservationInProgress=reservationService.addNewCustomerToReservationInProgess(id, firstName, lastName, address, companyName, phone, fax, mail, homepage, avatarUrl, notes);

        ModelAndView model = new ModelAndView("reservation_confirm");
        model.addObject("progressId",id);
        model.addObject("reservationInProgress",reservationInProgress);

        Date fromDate=new Date(reservationInProgress.getDateFrom());
        Date toDate=new Date(reservationInProgress.getDateTo()+CommonUtils.DAY_IN_MS);

        model.addObject("formattedDateFrom", CommonUtils.getGermanWeekday(fromDate)+" "+CommonUtils.dateFormatter.format(fromDate));
        model.addObject("formattedDateTo",CommonUtils.getGermanWeekday(toDate)+" "+CommonUtils.dateFormatter.format(toDate));

        return model;
    }


    @RequestMapping(value = {"/reservations/reserve/confirm/{id}"}, method =RequestMethod.POST)
    public String reservationConfirm(@PathVariable String id){
        reservationService.confirmReservation(id);

        return "redirect:/rooms";
    }

}
