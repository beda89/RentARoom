package rentaroom.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import rentaroom.Utils.CommonUtils;
import rentaroom.dtos.RoomDto;
import rentaroom.entities.Customer;
import rentaroom.repositories.CustomerRepository;
import rentaroom.services.ReservationService;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

/**
 * Created by Christian on 29.11.2014.
 */

@Controller
public class RoomController {

    @Autowired
    private ReservationService resService;


    @RequestMapping(value = {"/rooms"}, method = RequestMethod.GET)
    public ModelAndView roomsPage() {
        ModelAndView model=new ModelAndView("rooms");

        Date date= new Date();

        try {
            List<RoomDto> roomDtoList = resService.getRoomsWithStateByDate(CommonUtils.dateFormatter.parse("21.01.2015").getTime(), date.getTime());
            model.addObject("roomList", roomDtoList);
        }catch(ParseException e){

        }

        return model;


    }

}
