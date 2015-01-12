package ControllerTests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.servlet.ModelAndView;
import rentaroom.Utils.CommonUtils;
import rentaroom.config.MongoConfig;
import rentaroom.controller.RoomController;
import rentaroom.services.ReservationService;

import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created with IntelliJ IDEA.
 * User: Simerle Christopher
 * Date: 12/01/15
 * Time: 12:21
 * To change this template use File | Settings | File Templates.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {MongoConfig.class, RoomController.class, ReservationService.class})
public class RoomControllerTest {

    @Autowired
    private RoomController roomController;

    @Test
    public void testRoomsPage() {
        ModelAndView model = roomController.roomsPage();
        assertEquals("rooms", model.getViewName());
        assertTrue(model.getModel().containsKey("roomOverview"));
        assertTrue(model.getModel().containsKey("selectedDate"));
    }

    @Test
    public void testRoomsForDatePage() {
        ModelAndView model = roomController.roomsForDatePage("01.08.2015");
        assertEquals("rooms", model.getViewName());
        assertTrue(model.getModel().containsKey("roomOverview"));
        assertTrue(model.getModel().containsKey("selectedDate"));
        assertEquals("01.08.2015", model.getModel().get("selectedDate"));
    }
}
