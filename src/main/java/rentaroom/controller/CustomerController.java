package rentaroom.controller;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import rentaroom.entities.Customer;
import rentaroom.repositories.CustomerRepository;
import rentaroom.services.CustomerService;
import rentaroom.services.InvoiceService;
import rentaroom.services.ReservationService;

import java.text.ParseException;

/**
 * Created by Christian on 29.11.2014.
 */

@Controller
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @Autowired
    ReservationService reservationService;

    @Autowired
    InvoiceService invoiceService;

    @RequestMapping(value = {"/customers"}, method = RequestMethod.GET)
    public ModelAndView allCustomers() {
        ModelAndView model = new ModelAndView("customers");
        model.addObject("customers", customerService.findAll());
        return model;
    }

    @RequestMapping(value = {"/customer/{id}"}, method = RequestMethod.GET)
    public ModelAndView customerPage(@PathVariable String id) throws ParseException {
        ModelAndView model = new ModelAndView("customer");
        Customer c = customerService.findById(id);
        model.addObject("customer", c);
        model.addObject("reservations", reservationService.findOutstandingByCustomer(c));
        model.addObject("invoices", invoiceService.findByCustomer(c));
        return model;
    }

    @RequestMapping(value = {"/customer"}, method = RequestMethod.POST)
    public String addCustomer(@RequestParam String firstName,
                              @RequestParam String lastName,
                              @RequestParam String address,
                              @RequestParam String companyName,
                              @RequestParam String phone,
                              @RequestParam String fax,
                              @RequestParam String mail,
                              @RequestParam String homepage,
                              @RequestParam String avatarUrl,
                              @RequestParam String notes) {
        Customer c = customerService.add(firstName, lastName, address, companyName, phone, fax, mail, homepage, avatarUrl, notes);
        return "redirect:/customer/" + c.getId();
    }

    @RequestMapping(value = {"/customer/{id}"}, method = RequestMethod.POST)
    public String editCustomer(@PathVariable String id,
                               @RequestParam String firstName,
                              @RequestParam String lastName,
                              @RequestParam String address,
                              @RequestParam String companyName,
                              @RequestParam String phone,
                              @RequestParam String fax,
                              @RequestParam String mail,
                              @RequestParam String homepage,
                              @RequestParam String avatarUrl,
                              @RequestParam String notes) {
        customerService.edit(id, firstName, lastName, address, companyName, phone, fax, mail, homepage, avatarUrl, notes);
        return "redirect:/customer/" + id;
    }

    @RequestMapping(value="/autocomplete/names",method=RequestMethod.GET)
    @ResponseBody
    public String getCustomerJsonListWithId() {

        return customerService.getAllCustomerJson().toJSONString();

    }

}
