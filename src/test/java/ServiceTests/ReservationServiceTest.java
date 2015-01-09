package ServiceTests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import rentaroom.config.MongoConfig;
import rentaroom.entities.Customer;
import rentaroom.entities.Reservation;
import rentaroom.entities.Room;
import rentaroom.repositories.ReservationRepository;
import rentaroom.services.ReservationService;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created with IntelliJ IDEA.
 * User: Simerle Christopher
 * Date: 09/01/15
 * Time: 15:59
 * To change this template use File | Settings | File Templates.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {MongoConfig.class, ReservationService.class})
public class ReservationServiceTest {

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

    @Autowired
    ReservationService reservationService;

    @Autowired
    ReservationRepository reservationRepository;

    private Iterable<Reservation> reservationBackup;
    private Reservation r1;
    private Reservation r2;
    private List<Reservation> reservationList;

    private Customer customer2;

    private Room room1;

    @Before
    public void setUp() {
        reservationBackup = reservationRepository.findAll();
        customer2 = new Customer("Wuzli", "Duzli");
        customer2.setId("wuzli_id");
        customer2.setAddress("Wuzlitown");
        customer2.setDiscount(8);
        customer2.setMail("wuzli@duzli.com");
        customer2.setNotes("Stammgast");
        customer2.setPhone("+434634646");
        customer2.setAvatarUrl("wuzlavatar.com");

        room1 = new Room();
        room1.setMaxPersons(3);
        room1.setRoomNbr("001");
        room1.setPrice_doubleRoom(HIGH_DOUBLEROOM_PRICE);
        room1.setPrice_singleRoom(HIGH_SINGLEROOM_PRICE);
        room1.setPrice_singleRoomOneChild(HIGH_SINGLEROOM_PLUS1CHILD_PRICE);
        room1.setPrice_singleRoomTwoChildren(HIGH_SINGLEROOM_PLUS2CHILDREN_PRICE);
        room1.setPrice_doubleRoomOneChild(HIGH_DOUBLEROOM_PLUS1CHILD_PRICE);
        room1.setPrice_threePersons(HIGH_THREEPERSON_PRICE);

        try {
            r1 = new Reservation();
            r1.setCustomer(customer2);
            r1.setDateFrom(Reservation.dateFormatter.parse("20.02.2018").getTime());
            r1.setDateTo(Reservation.dateFormatter.parse("25.02.2018").getTime());
            r1.setRoomPrice(CHEAP_DOUBLEROOM_PRICE);
            r1.setDiscount(0);
            List<Room> roomList = new ArrayList<Room>();
            roomList.add(room1);
            r1.setRoomList(roomList);

            r2 = new Reservation();
            r2.setCustomer(customer2);
            r2.setDateFrom(Reservation.dateFormatter.parse("20.01.2018").getTime());
            r2.setDateTo(Reservation.dateFormatter.parse("01.03.2018").getTime());
            r2.setRoomPrice(HIGH_SINGLEROOM_PRICE);
            r2.setDiscount(10);
            roomList = new ArrayList<Room>();
            roomList.add(room1);
            roomList.add(room1);
            roomList.add(room1);
            r2.setRoomList(roomList);

            reservationRepository.save(r1);
            reservationRepository.save(r2);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @After
    public void tearDown() {
        reservationRepository.deleteAll();
        reservationRepository.save(reservationBackup);
    }

    @Test
    public void testFindOutstandingByCustomer(){
        List<Reservation> foundReservationList = reservationService.findOutstandingByCustomer(customer2);
        assertTrue(!foundReservationList.isEmpty());
        assertEquals(customer2.getFirstName(), foundReservationList.get(0).getCustomer().getFirstName());
    }
}



















