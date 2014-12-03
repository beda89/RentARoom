package rentaroom.controller;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import rentaroom.entities.Customer;
import rentaroom.repositories.CustomerRepository;
import rentaroom.services.CustomerService;

/**
 * Created by Christian on 29.11.2014.
 */

@Controller
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @RequestMapping(value = {"/customer/{id}"}, method = RequestMethod.GET)
    public ModelAndView customerPage(@PathVariable String id) throws Exception {
        ModelAndView model = new ModelAndView("customer");
        model.addObject("customer", customerService.findById(id));
        return model;
    }

    @RequestMapping(value="/autocomplete/names",method=RequestMethod.GET)
    @ResponseBody
    public String getCustomerJsonListWithId() {

        return customerService.getAllCustomerJson().toJSONString();

    }

}
