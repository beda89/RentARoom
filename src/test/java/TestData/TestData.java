package TestData;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import rentaroom.Utils.CommonUtils;
import rentaroom.config.MongoConfig;
import rentaroom.config.ServletConfig;
import rentaroom.entities.Customer;
import rentaroom.entities.Invoice;
import rentaroom.entities.Reservation;
import rentaroom.entities.Room;
import rentaroom.repositories.CustomerRepository;
import rentaroom.repositories.InvoiceRepository;
import rentaroom.repositories.ReservationRepository;
import rentaroom.repositories.RoomRepository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Peter on 28.11.2014.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ServletConfig.class, MongoConfig.class})
@WebAppConfiguration
public class TestData {

    //ALL PRICES ARE IN CENT

    //DOUBLEROOM PRICES
    private static final Long HIGH_DOUBLEROOM_PRICE = 20000L;
    private static final Long MIDDLE_DOUBLEROOM_PRICE = 15000L;
    private static final Long CHEAP_DOUBLEROOM_PRICE = 12500L;

    //SINGLEROOM PRICES
    private static final Long HIGH_SINGLEROOM_PRICE = 17500L;
    private static final Long MIDDLE_SINGLEROOM_PRICE = 12500L;
    private static final Long CHEAP_SINGLEROOM_PRICE = 10000L;

    //DOUBLEROOM + 1 CHILD PRICES
    private static final Long HIGH_DOUBLEROOM_PLUS1CHILD_PRICE = 21000L;
    private static final Long MIDDLE_DOUBLEROOM_PLUS1CHILD_PRICE = 16000L;
    private static final Long CHEAP_DOUBLEROOM_PLUS1CHILD_PRICE = 13500L;

    //SINGLEROOM + 1 CHILD PRICES
    private static final Long HIGH_SINGLEROOM_PLUS1CHILD_PRICE = 18500L;
    private static final Long MIDDLE_SINGLEROOM_PLUS1CHILD_PRICE = 13500L;
    private static final Long CHEAP_SINGLEROOM_PLUS1CHILD_PRICE = 11000L;

    //SINGLEROOM + 2 CHILDREN PRICES
    private static final Long HIGH_SINGLEROOM_PLUS2CHILDREN_PRICE = 19500L;
    private static final Long MIDDLE_SINGLEROOM_PLUS2CHILDREN_PRICE = 14500L;
    private static final Long CHEAP_SINGLEROOM_PLUS2CHILDREN_PRICE = 12000L;


    //THREE PERSONS PRICE
    private static final Long HIGH_THREEPERSON_PRICE = 26000L;
    private static final Long MIDDLE_THREEPERSON_PRICE = 21500L;
    private static final Long CHEAP_THREEPERSON_PRICE = 185000L;

    @Autowired
    private CustomerRepository customerRepo;

    @Autowired
    private RoomRepository roomRepo;

    @Autowired
    private ReservationRepository reservationRepo;

    @Autowired
    private InvoiceRepository invoiceRepo;

    @Test
    public void initDBWithTestData() throws ParseException {
        //setBackDatabase
        customerRepo.deleteAll();
        roomRepo.deleteAll();
        reservationRepo.deleteAll();
        invoiceRepo.deleteAll();

        //----------------------------------------------create rooms for groundfloor----------------------------------------------------------//
        Room room1 = new Room();
        room1.setMaxPersons(3);
        room1.setRoomNbr("001");
        room1.setPrice_doubleRoom(HIGH_DOUBLEROOM_PRICE);
        room1.setPrice_singleRoom(HIGH_SINGLEROOM_PRICE);
        room1.setPrice_singleRoomOneChild(HIGH_SINGLEROOM_PLUS1CHILD_PRICE);
        room1.setPrice_singleRoomTwoChildren(HIGH_SINGLEROOM_PLUS2CHILDREN_PRICE);
        room1.setPrice_doubleRoomOneChild(HIGH_DOUBLEROOM_PLUS1CHILD_PRICE);
        room1.setPrice_threePersons(HIGH_THREEPERSON_PRICE);
        roomRepo.save(room1);


        Room room2 = new Room();
        room2.setMaxPersons(3);
        room2.setRoomNbr("002");
        room2.setPrice_doubleRoom(HIGH_DOUBLEROOM_PRICE);
        room2.setPrice_singleRoom(HIGH_SINGLEROOM_PRICE);
        room2.setPrice_singleRoomOneChild(HIGH_SINGLEROOM_PLUS1CHILD_PRICE);
        room2.setPrice_singleRoomTwoChildren(HIGH_SINGLEROOM_PLUS2CHILDREN_PRICE);
        room2.setPrice_doubleRoomOneChild(HIGH_DOUBLEROOM_PLUS1CHILD_PRICE);
        room2.setPrice_threePersons(HIGH_THREEPERSON_PRICE);
        roomRepo.save(room2);


        Room room3 = new Room();
        room3.setMaxPersons(3);
        room3.setRoomNbr("003");
        room3.setPrice_doubleRoom(HIGH_DOUBLEROOM_PRICE);
        room3.setPrice_singleRoom(HIGH_SINGLEROOM_PRICE);
        room3.setPrice_singleRoomOneChild(HIGH_SINGLEROOM_PLUS1CHILD_PRICE);
        room3.setPrice_singleRoomTwoChildren(HIGH_SINGLEROOM_PLUS2CHILDREN_PRICE);
        room3.setPrice_doubleRoomOneChild(HIGH_DOUBLEROOM_PLUS1CHILD_PRICE);
        room3.setPrice_threePersons(HIGH_THREEPERSON_PRICE);
        roomRepo.save(room3);


        Room room4 = new Room();
        room4.setMaxPersons(3);
        room4.setRoomNbr("004");
        room4.setPrice_doubleRoom(CHEAP_DOUBLEROOM_PRICE);
        room4.setPrice_singleRoom(CHEAP_SINGLEROOM_PRICE);
        room4.setPrice_singleRoomOneChild(CHEAP_SINGLEROOM_PLUS1CHILD_PRICE);
        room4.setPrice_singleRoomTwoChildren(CHEAP_SINGLEROOM_PLUS2CHILDREN_PRICE);
        room4.setPrice_doubleRoomOneChild(CHEAP_DOUBLEROOM_PLUS1CHILD_PRICE);
        room4.setPrice_threePersons(CHEAP_THREEPERSON_PRICE);
        roomRepo.save(room4);

        Room room5 = new Room();
        room5.setMaxPersons(3);
        room5.setRoomNbr("005");
        room5.setPrice_doubleRoom(CHEAP_DOUBLEROOM_PRICE);
        room5.setPrice_singleRoom(CHEAP_SINGLEROOM_PRICE);
        room5.setPrice_singleRoomOneChild(CHEAP_SINGLEROOM_PLUS1CHILD_PRICE);
        room5.setPrice_singleRoomTwoChildren(CHEAP_SINGLEROOM_PLUS2CHILDREN_PRICE);
        room5.setPrice_doubleRoomOneChild(CHEAP_DOUBLEROOM_PLUS1CHILD_PRICE);
        room5.setPrice_threePersons(CHEAP_THREEPERSON_PRICE);
        roomRepo.save(room5);


        Room room6 = new Room();
        room6.setMaxPersons(3);
        room6.setRoomNbr("006");
        room6.setPrice_doubleRoom(MIDDLE_DOUBLEROOM_PRICE);
        room6.setPrice_singleRoom(MIDDLE_SINGLEROOM_PRICE);
        room6.setPrice_singleRoomOneChild(MIDDLE_SINGLEROOM_PLUS1CHILD_PRICE);
        room6.setPrice_singleRoomTwoChildren(MIDDLE_SINGLEROOM_PLUS2CHILDREN_PRICE);
        room6.setPrice_doubleRoomOneChild(MIDDLE_DOUBLEROOM_PLUS1CHILD_PRICE);
        room6.setPrice_threePersons(MIDDLE_THREEPERSON_PRICE);
        roomRepo.save(room6);


        Room room7 = new Room();
        room7.setMaxPersons(3);
        room7.setRoomNbr("007");
        room7.setPrice_doubleRoom(MIDDLE_DOUBLEROOM_PRICE);
        room7.setPrice_singleRoom(MIDDLE_SINGLEROOM_PRICE);
        room7.setPrice_singleRoomOneChild(MIDDLE_SINGLEROOM_PLUS1CHILD_PRICE);
        room7.setPrice_singleRoomTwoChildren(MIDDLE_SINGLEROOM_PLUS2CHILDREN_PRICE);
        room7.setPrice_doubleRoomOneChild(MIDDLE_DOUBLEROOM_PLUS1CHILD_PRICE);
        room7.setPrice_threePersons(MIDDLE_THREEPERSON_PRICE);
        roomRepo.save(room7);


        //-------------------------------------------------create rooms for firstfloor--------------------------------------------------//

        Room room8 = new Room();
        room8.setMaxPersons(3);
        room8.setRoomNbr("101");
        room8.setPrice_doubleRoom(HIGH_DOUBLEROOM_PRICE);
        room8.setPrice_singleRoom(HIGH_SINGLEROOM_PRICE);
        room8.setPrice_singleRoomOneChild(HIGH_SINGLEROOM_PLUS1CHILD_PRICE);
        room8.setPrice_singleRoomTwoChildren(HIGH_SINGLEROOM_PLUS2CHILDREN_PRICE);
        room8.setPrice_doubleRoomOneChild(HIGH_DOUBLEROOM_PLUS1CHILD_PRICE);
        room8.setPrice_threePersons(HIGH_THREEPERSON_PRICE);
        roomRepo.save(room8);


        Room room9 = new Room();
        room9.setMaxPersons(3);
        room9.setRoomNbr("102");
        room9.setPrice_doubleRoom(HIGH_DOUBLEROOM_PRICE);
        room9.setPrice_singleRoom(HIGH_SINGLEROOM_PRICE);
        room9.setPrice_singleRoomOneChild(HIGH_SINGLEROOM_PLUS1CHILD_PRICE);
        room9.setPrice_singleRoomTwoChildren(HIGH_SINGLEROOM_PLUS2CHILDREN_PRICE);
        room9.setPrice_doubleRoomOneChild(HIGH_DOUBLEROOM_PLUS1CHILD_PRICE);
        room9.setPrice_threePersons(HIGH_THREEPERSON_PRICE);
        roomRepo.save(room9);


        Room room10 = new Room();
        room10.setMaxPersons(3);
        room10.setRoomNbr("103");
        room10.setPrice_doubleRoom(HIGH_DOUBLEROOM_PRICE);
        room10.setPrice_singleRoom(HIGH_SINGLEROOM_PRICE);
        room10.setPrice_singleRoomOneChild(HIGH_SINGLEROOM_PLUS1CHILD_PRICE);
        room10.setPrice_singleRoomTwoChildren(HIGH_SINGLEROOM_PLUS2CHILDREN_PRICE);
        room10.setPrice_doubleRoomOneChild(HIGH_DOUBLEROOM_PLUS1CHILD_PRICE);
        room10.setPrice_threePersons(HIGH_THREEPERSON_PRICE);
        roomRepo.save(room10);


        Room room11 = new Room();
        room11.setMaxPersons(3);
        room11.setRoomNbr("104");
        room11.setPrice_doubleRoom(CHEAP_DOUBLEROOM_PRICE);
        room11.setPrice_singleRoom(CHEAP_SINGLEROOM_PRICE);
        room11.setPrice_singleRoomOneChild(CHEAP_SINGLEROOM_PLUS1CHILD_PRICE);
        room11.setPrice_singleRoomTwoChildren(CHEAP_SINGLEROOM_PLUS2CHILDREN_PRICE);
        room11.setPrice_doubleRoomOneChild(CHEAP_DOUBLEROOM_PLUS1CHILD_PRICE);
        room11.setPrice_threePersons(CHEAP_THREEPERSON_PRICE);
        roomRepo.save(room11);

        Room room12 = new Room();
        room12.setMaxPersons(3);
        room12.setRoomNbr("105");
        room12.setPrice_doubleRoom(CHEAP_DOUBLEROOM_PRICE);
        room12.setPrice_singleRoom(CHEAP_SINGLEROOM_PRICE);
        room12.setPrice_singleRoomOneChild(CHEAP_SINGLEROOM_PLUS1CHILD_PRICE);
        room12.setPrice_singleRoomTwoChildren(CHEAP_SINGLEROOM_PLUS2CHILDREN_PRICE);
        room12.setPrice_doubleRoomOneChild(CHEAP_DOUBLEROOM_PLUS1CHILD_PRICE);
        room12.setPrice_threePersons(CHEAP_THREEPERSON_PRICE);
        roomRepo.save(room12);


        Room room13 = new Room();
        room13.setMaxPersons(3);
        room13.setRoomNbr("106");
        room13.setPrice_doubleRoom(MIDDLE_DOUBLEROOM_PRICE);
        room13.setPrice_singleRoom(MIDDLE_SINGLEROOM_PRICE);
        room13.setPrice_singleRoomOneChild(MIDDLE_SINGLEROOM_PLUS1CHILD_PRICE);
        room13.setPrice_singleRoomTwoChildren(MIDDLE_SINGLEROOM_PLUS2CHILDREN_PRICE);
        room13.setPrice_doubleRoomOneChild(MIDDLE_DOUBLEROOM_PLUS1CHILD_PRICE);
        room13.setPrice_threePersons(MIDDLE_THREEPERSON_PRICE);
        roomRepo.save(room13);


        Room room14 = new Room();
        room14.setMaxPersons(3);
        room14.setRoomNbr("107");
        room14.setPrice_doubleRoom(MIDDLE_DOUBLEROOM_PRICE);
        room14.setPrice_singleRoom(MIDDLE_SINGLEROOM_PRICE);
        room14.setPrice_singleRoomOneChild(MIDDLE_SINGLEROOM_PLUS1CHILD_PRICE);
        room14.setPrice_singleRoomTwoChildren(MIDDLE_SINGLEROOM_PLUS2CHILDREN_PRICE);
        room14.setPrice_doubleRoomOneChild(MIDDLE_DOUBLEROOM_PLUS1CHILD_PRICE);
        room14.setPrice_threePersons(MIDDLE_THREEPERSON_PRICE);
        roomRepo.save(room14);


        //----------------------------------------------------------create rooms for secondfloor-------------------------------------------------------------//


        Room room15 = new Room();
        room15.setMaxPersons(3);
        room15.setRoomNbr("201");
        room15.setPrice_doubleRoom(HIGH_DOUBLEROOM_PRICE);
        room15.setPrice_singleRoom(HIGH_SINGLEROOM_PRICE);
        room15.setPrice_singleRoomOneChild(HIGH_SINGLEROOM_PLUS1CHILD_PRICE);
        room15.setPrice_singleRoomTwoChildren(HIGH_SINGLEROOM_PLUS2CHILDREN_PRICE);
        room15.setPrice_doubleRoomOneChild(HIGH_DOUBLEROOM_PLUS1CHILD_PRICE);
        room15.setPrice_threePersons(HIGH_THREEPERSON_PRICE);
        roomRepo.save(room15);


        Room room16 = new Room();
        room16.setMaxPersons(3);
        room16.setRoomNbr("202");
        room16.setPrice_doubleRoom(HIGH_DOUBLEROOM_PRICE);
        room16.setPrice_singleRoom(HIGH_SINGLEROOM_PRICE);
        room16.setPrice_singleRoomOneChild(HIGH_SINGLEROOM_PLUS1CHILD_PRICE);
        room16.setPrice_singleRoomTwoChildren(HIGH_SINGLEROOM_PLUS2CHILDREN_PRICE);
        room16.setPrice_doubleRoomOneChild(HIGH_DOUBLEROOM_PLUS1CHILD_PRICE);
        room16.setPrice_threePersons(HIGH_THREEPERSON_PRICE);
        roomRepo.save(room16);


        Room room17 = new Room();
        room17.setMaxPersons(3);
        room17.setRoomNbr("203");
        room17.setPrice_doubleRoom(HIGH_DOUBLEROOM_PRICE);
        room17.setPrice_singleRoom(HIGH_SINGLEROOM_PRICE);
        room17.setPrice_singleRoomOneChild(HIGH_SINGLEROOM_PLUS1CHILD_PRICE);
        room17.setPrice_singleRoomTwoChildren(HIGH_SINGLEROOM_PLUS2CHILDREN_PRICE);
        room17.setPrice_doubleRoomOneChild(HIGH_DOUBLEROOM_PLUS1CHILD_PRICE);
        room17.setPrice_threePersons(HIGH_THREEPERSON_PRICE);
        roomRepo.save(room17);


        Room room18 = new Room();
        room18.setMaxPersons(3);
        room18.setRoomNbr("204");
        room18.setPrice_doubleRoom(CHEAP_DOUBLEROOM_PRICE);
        room18.setPrice_singleRoom(CHEAP_SINGLEROOM_PRICE);
        room18.setPrice_singleRoomOneChild(CHEAP_SINGLEROOM_PLUS1CHILD_PRICE);
        room18.setPrice_singleRoomTwoChildren(CHEAP_SINGLEROOM_PLUS2CHILDREN_PRICE);
        room18.setPrice_doubleRoomOneChild(CHEAP_DOUBLEROOM_PLUS1CHILD_PRICE);
        room18.setPrice_threePersons(CHEAP_THREEPERSON_PRICE);
        roomRepo.save(room18);

        Room room19 = new Room();
        room19.setMaxPersons(3);
        room19.setRoomNbr("205");
        room19.setPrice_doubleRoom(CHEAP_DOUBLEROOM_PRICE);
        room19.setPrice_singleRoom(CHEAP_SINGLEROOM_PRICE);
        room19.setPrice_singleRoomOneChild(CHEAP_SINGLEROOM_PLUS1CHILD_PRICE);
        room19.setPrice_singleRoomTwoChildren(CHEAP_SINGLEROOM_PLUS2CHILDREN_PRICE);
        room19.setPrice_doubleRoomOneChild(CHEAP_DOUBLEROOM_PLUS1CHILD_PRICE);
        room19.setPrice_threePersons(CHEAP_THREEPERSON_PRICE);
        roomRepo.save(room19);


        Room room20 = new Room();
        room20.setMaxPersons(3);
        room20.setRoomNbr("206");
        room20.setPrice_doubleRoom(MIDDLE_DOUBLEROOM_PRICE);
        room20.setPrice_singleRoom(MIDDLE_SINGLEROOM_PRICE);
        room20.setPrice_singleRoomOneChild(MIDDLE_SINGLEROOM_PLUS1CHILD_PRICE);
        room20.setPrice_singleRoomTwoChildren(MIDDLE_SINGLEROOM_PLUS2CHILDREN_PRICE);
        room20.setPrice_doubleRoomOneChild(MIDDLE_DOUBLEROOM_PLUS1CHILD_PRICE);
        room20.setPrice_threePersons(MIDDLE_THREEPERSON_PRICE);
        roomRepo.save(room20);


        Room room21 = new Room();
        room21.setMaxPersons(3);
        room21.setRoomNbr("207");
        room21.setPrice_doubleRoom(MIDDLE_DOUBLEROOM_PRICE);
        room21.setPrice_singleRoom(MIDDLE_SINGLEROOM_PRICE);
        room21.setPrice_singleRoomOneChild(MIDDLE_SINGLEROOM_PLUS1CHILD_PRICE);
        room21.setPrice_singleRoomTwoChildren(MIDDLE_SINGLEROOM_PLUS2CHILDREN_PRICE);
        room21.setPrice_doubleRoomOneChild(MIDDLE_DOUBLEROOM_PLUS1CHILD_PRICE);
        room21.setPrice_threePersons(MIDDLE_THREEPERSON_PRICE);
        roomRepo.save(room21);


        //---------------------------------CREATE CUSTOMERS -----------------------------------------------------------//
        Customer customer1 = new Customer("Hans", "Huber");
        customer1.setAddress("Weimarer Straße 3/3 1180 Wien");
        customer1.setDiscount(10);
        customer1.setMail("huber@hans.com");
        customer1.setNotes("Stammgast, kommt mehrmals im Jahr");
        customer1.setPhone("+43 7744 1443");
        customerRepo.save(customer1);

        //---------------------------------CREATE CUSTOMERS -----------------------------------------------------------//
        Customer customer2 = new Customer("Sepp", "Maier");
        customer2.setAddress("Munderfing 18 5222 Munderfing");
        customer2.setDiscount(8);
        customer2.setMail("sepp@maier.com");
        customer2.setNotes("Stammgast, kommt mehrmals im Jahr");
        customer2.setPhone("+43 680 1231443");
        customer2.setAvatarUrl("http://ww1.prweb.com/prfiles/2013/08/14/12190096/gI_137366_glenAvatar.png");
        customerRepo.save(customer2);

        /*
        //---------------------------------CREATE RESERVATIONS -----------------------------------------------------------//
        Reservation r1 = new Reservation();
        r1.setCustomer(customer2);
        r1.setDateFrom(CommonUtils.dateFormatter.parse("20.02.2015").getTime());
        r1.setDateTo(CommonUtils.dateFormatter.parse("25.02.2015").getTime());
        r1.setRoomPrice(CHEAP_DOUBLEROOM_PRICE);
        r1.setDiscount(0);
        List<Room> roomList = new ArrayList<Room>();
        roomList.add(room1);
        r1.setRoomList(roomList);
        reservationRepo.save(r1);

        Reservation r2 = new Reservation();
        r2.setCustomer(customer2);
        r2.setDateFrom(CommonUtils.dateFormatter.parse("20.01.2015").getTime());
        r2.setDateTo(CommonUtils.dateFormatter.parse("01.03.2015").getTime());
        r2.setRoomPrice(HIGH_SINGLEROOM_PRICE);
        r2.setDiscount(10);
        roomList = new ArrayList<Room>();
        roomList.add(room2);
        roomList.add(room3);
        roomList.add(room4);
        r2.setRoomList(roomList);
        reservationRepo.save(r2);

        Reservation r3 = new Reservation();
        r3.setCustomer(customer2);
        r3.setDateFrom(CommonUtils.dateFormatter.parse("01.01.2015").getTime());
        r3.setDateTo(CommonUtils.dateFormatter.parse("01.03.2015").getTime());
        r3.setRoomPrice(CHEAP_DOUBLEROOM_PRICE);
        r3.setDiscount(30);
        roomList = new ArrayList<Room>();
        roomList.add(room2);
        roomList.add(room3);
        r3.setRoomList(roomList);
        reservationRepo.save(r3);

        //---------------------------------CREATE INVOICES -----------------------------------------------------------//
        Invoice i1 = new Invoice();
        i1.setCustomer(customer2);
        i1.setPrice(300L);
        i1.setInvoiceDate(CommonUtils.dateFormatter.parse("01.08.2014").getTime());
        invoiceRepo.save(i1);

        Invoice i2 = new Invoice();
        i2.setCustomer(customer2);
        i2.setPrice(350L);
        i2.setInvoiceDate(CommonUtils.dateFormatter.parse("15.06.2014").getTime());
        invoiceRepo.save(i2);

        Invoice i3 = new Invoice();
        i3.setCustomer(customer2);
        i3.setPrice(350L);
        i3.setInvoiceDate(CommonUtils.dateFormatter.parse("23.12.2014").getTime());
        invoiceRepo.save(i3);

        */
    }

}
