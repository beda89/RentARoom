package ServiceTests;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import rentaroom.entities.Customer;
import rentaroom.entities.Reservation;
import rentaroom.entities.Room;
import rentaroom.repositories.ReservationRepository;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Simerle Christopher
 * Date: 09/01/15
 * Time: 13:53
 * To change this template use File | Settings | File Templates.
 */

@RunWith(MockitoJUnitRunner.class)
public class ReservationService {

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

    @Mock
    private ReservationRepository reservationRepository;

    @InjectMocks
    private rentaroom.services.ReservationService reservationService = new rentaroom.services.ReservationService();

    private Reservation r1;
    private Reservation r2;
    private List<Reservation> reservationList;

    private Customer customer1;

    private Room room1;

    @Before
    public void setUp() {
        customer1 = new Customer("Hans", "Huber");
        customer1.setId("54aec4e360b263045a3db672");
        customer1.setAddress("Weimarer Straße 3/3 1180 Wien");
        customer1.setDiscount(10);
        customer1.setMail("huber@hans.com");
        customer1.setNotes("Stammgast, kommt mehrmals im Jahr");
        customer1.setPhone("+43 7744 1443");

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
            r1.setCustomer(customer1);
            r1.setDateFrom(Reservation.dateFormatter.parse("20.02.2015").getTime());
            r1.setDateTo(Reservation.dateFormatter.parse("25.02.2015").getTime());
            r1.setRoomPrice(CHEAP_DOUBLEROOM_PRICE);
            r1.setDiscount(0);
            List<Room> roomList = new ArrayList<Room>();
            roomList.add(room1);
            r1.setRoomList(roomList);

            r2 = new Reservation();
            r2.setCustomer(customer1);
            r2.setDateFrom(Reservation.dateFormatter.parse("20.01.2015").getTime());
            r2.setDateTo(Reservation.dateFormatter.parse("01.03.2015").getTime());
            r2.setRoomPrice(HIGH_SINGLEROOM_PRICE);
            r2.setDiscount(10);
            roomList = new ArrayList<Room>();
            roomList.add(room1);
            roomList.add(room1);
            roomList.add(room1);
            r2.setRoomList(roomList);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        reservationList = new ArrayList<Reservation>();
        reservationList.add(r1);
        reservationList.add(r2);
    }

    @Test
    public void testFindOutstandingByCustomer() {
        Mockito.when(reservationRepository.findByDateFromGreaterThan(Mockito.anyLong())).thenReturn(reservationList);
        List<Reservation> findByCustomerResult = reservationService.findOutstandingByCustomer(customer1);
        Assert.assertTrue(!findByCustomerResult.isEmpty());
        Assert.assertEquals(findByCustomerResult, reservationList);
    }

}
