package rentaroom.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import rentaroom.Utils.CommonUtils;
import rentaroom.dtos.RoomDto;
import rentaroom.dtos.RoomOverviewDto;
import rentaroom.entities.Customer;
import rentaroom.entities.Room;
import rentaroom.repositories.CustomerRepository;
import rentaroom.services.ReservationService;
import rentaroom.services.RoomService;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by Christian on 29.11.2014.
 */

@Controller
public class RoomController {

    @Autowired
    private ReservationService resService;

    @Autowired
    private RoomService roomService;

    @RequestMapping(value = {"/rooms"}, method = RequestMethod.GET)
    public ModelAndView roomsPage() {
        ModelAndView model=new ModelAndView("rooms");

        Date selectedDate=new Date();

        try {
            RoomOverviewDto roomOverview = resService.getRoomsWithStateByDate(CommonUtils.getDateWithoutTime(selectedDate).getTime());
            model.addObject("roomOverview", roomOverview);
            model.addObject("selectedDate",CommonUtils.dateFormatter.format(selectedDate));

        }catch(ParseException e){

        }

        return model;
    }

    @RequestMapping(value = {"/getRoomsForDate"}, method = RequestMethod.GET)
    public ModelAndView roomsForDatePage(@RequestParam(value="selectedDate", required=true) String date) {
        ModelAndView model=new ModelAndView("rooms");

        try {
            Date selectedDate=CommonUtils.dateFormatter.parse(date);
            RoomOverviewDto roomOverview = resService.getRoomsWithStateByDate(selectedDate.getTime());
            model.addObject("roomOverview", roomOverview);
            model.addObject("selectedDate", date);

        }catch(ParseException e){
            return null;
        }

        return model;
    }

    @RequestMapping(value = {"/rooms/{id}"}, method = RequestMethod.GET)
    @ResponseBody
    public Room getRoom(@PathVariable String id) {
        return roomService.findById(id);
    }

    @RequestMapping(value = {"/rooms/{id}"}, method = RequestMethod.POST)
    @ResponseBody
    public Room editRoom(@PathVariable String id,
                         @RequestParam String roomNbr,
                         @RequestParam String maxPersons,
                         @RequestParam String price_singleRoom,
                         @RequestParam String price_doubleRoom,
                         @RequestParam String price_threePersons,
                         @RequestParam String price_singleRoomOneChild,
                         @RequestParam String price_singleRoomTwoChildren,
                         @RequestParam String price_doubleRoomOneChild) {
        return roomService.editRoom(id, roomNbr, Integer.parseInt(maxPersons), Long.parseLong(price_singleRoom),
                Long.parseLong(price_doubleRoom), Long.parseLong(price_threePersons), Long.parseLong(price_singleRoomOneChild),
                Long.parseLong(price_singleRoomTwoChildren), Long.parseLong(price_doubleRoomOneChild));
    }

}
