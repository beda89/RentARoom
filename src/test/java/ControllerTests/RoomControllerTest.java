package ControllerTests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.servlet.ModelAndView;
import rentaroom.config.MongoConfig;
import rentaroom.controller.RoomController;
import rentaroom.entities.Room;
import rentaroom.repositories.RoomRepository;
import rentaroom.services.ReservationService;
import rentaroom.services.RoomService;

import static org.junit.Assert.*;

/**
 * Created with IntelliJ IDEA.
 * User: Simerle Christopher
 * Date: 12/01/15
 * Time: 12:21
 * To change this template use File | Settings | File Templates.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {MongoConfig.class, RoomController.class, ReservationService.class, RoomService.class})
public class RoomControllerTest {

    @Autowired
    private RoomController roomController;

    @Autowired
    private RoomRepository roomRepository;

    //DOUBLEROOM PRICES
    private static final Long HIGH_DOUBLEROOM_PRICE = 20000L;
    private static final Long CHEAP_DOUBLEROOM_PRICE = 12500L;

    //SINGLEROOM PRICES
    private static final Long HIGH_SINGLEROOM_PRICE = 17500L;

    //DOUBLEROOM + 1 CHILD PRICES
    private static final Long HIGH_DOUBLEROOM_PLUS1CHILD_PRICE = 21000L;

    //SINGLEROOM + 1 CHILD PRICES
    private static final Long HIGH_SINGLEROOM_PLUS1CHILD_PRICE = 18500L;

    //SINGLEROOM + 2 CHILDREN PRICES
    private static final Long HIGH_SINGLEROOM_PLUS2CHILDREN_PRICE = 19500L;

    //THREE PERSONS PRICE
    private static final Long HIGH_THREEPERSON_PRICE = 26000L;

    private Iterable<Room> roomBackup;
    private Room room1;

    @Before
    public void setUp() {
        roomBackup = roomRepository.findAll();
        room1 = new Room();
        room1.setMaxPersons(3);
        room1.setRoomNbr("001");
        room1.setPrice_doubleRoom(HIGH_DOUBLEROOM_PRICE);
        room1.setPrice_singleRoom(HIGH_SINGLEROOM_PRICE);
        room1.setPrice_singleRoomOneChild(HIGH_SINGLEROOM_PLUS1CHILD_PRICE);
        room1.setPrice_singleRoomTwoChildren(HIGH_SINGLEROOM_PLUS2CHILDREN_PRICE);
        room1.setPrice_doubleRoomOneChild(HIGH_DOUBLEROOM_PLUS1CHILD_PRICE);
        room1.setPrice_threePersons(HIGH_THREEPERSON_PRICE);
    }

    @After
    public void tearDown() {
        roomRepository.deleteAll();
        roomRepository.save(roomBackup);
    }

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

    @Test
     public void testFindById() {
        Room savedRoom = roomRepository.save(room1);
        Room foundRoom = roomController.getRoom(savedRoom.getId());
        assertEquals(savedRoom.getId(), foundRoom.getId());
    }

    @Test
    public void testEditRoom() {
        Room savedRoom = roomRepository.save(room1);
        Room editedRoom = roomController.editRoom(savedRoom.getId(), "101", "1000", "10", "10", "10", "10", "10", "10");
        assertEquals(savedRoom.getId(), editedRoom.getId());
        assertEquals(3L, (long) editedRoom.getMaxPersons());
        assertEquals(17500L, editedRoom.getPrice_singleRoom());

    }
}
