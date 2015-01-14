package rentaroom.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import rentaroom.Utils.CommonUtils;
import rentaroom.dtos.*;
import rentaroom.entities.*;
import rentaroom.repositories.CustomerRepository;
import rentaroom.repositories.ReservationInProgressRepository;
import rentaroom.repositories.InvoiceRepository;
import rentaroom.repositories.ReservationRepository;
import rentaroom.repositories.RoomRepository;

import java.util.*;

/**
 * Created by Peter on 03.12.2014.
 */
@Service
public class ReservationService {

    private final Long DAY_IN_MS=1000*60*60*24L;
    private final Long ONE_WEEK_IN_MS=DAY_IN_MS*7;

    @Autowired
    private ReservationRepository reservationRepo;

    @Autowired
    private RoomRepository roomRepo;

    @Autowired
    private CustomerRepository customerRepo;

    @Autowired
    private ReservationInProgressRepository inProgressRepo;

    @Autowired
    private InvoiceRepository invoiceRepo;

    public List<Reservation> findOutstandingByCustomer(Customer c) {
        List<Reservation> reservations = new ArrayList<Reservation>();
        if (c != null) {
            for (Reservation r : reservationRepo.findByDateToGreaterThanOrderByDateFromAsc(new Date().getTime())) {
                if (r.getCustomer().getId().equals(c.getId())) {
                    reservations.add(r);
                }
            }
        }
        return reservations;
    }

    public RoomOverviewDto getRoomsWithStateByDate(Long chosenDate){

        RoomOverviewDto roomOverview= new RoomOverviewDto();

        //we show 1 week before chosen date and 3 weeks after chosen date
        Long beginDate=chosenDate-ONE_WEEK_IN_MS;
        Long endDate=chosenDate+ONE_WEEK_IN_MS*3;

        List<RoomDto> roomList= new ArrayList<RoomDto>();
        List<DayHeaderDto> dayHeaderList= new ArrayList<DayHeaderDto>();


        //*********** COMPUTE HEADER *****************************
        //get date of every day + weekday for header in webpage
        for(int i=0;i<28;i++){
            DayHeaderDto dayHeaderDto = new DayHeaderDto();

            Date date=new Date(beginDate+i*DAY_IN_MS);

            int day=date.getDay();

            //if weekend
            if(day==0 || day==6){
                dayHeaderDto.setIsWeekend(true);
            }

            dayHeaderDto.setDateString(CommonUtils.getFormattedDateString(date));

            dayHeaderList.add(dayHeaderDto);
        }

        roomOverview.setHeaderList(dayHeaderList);


        //*********** COMPUTE ROOM OVERVIEW*****************************
        //get date of every day + weekday for header in webpage

        Iterable<Room> allRoomsList= roomRepo.findAll(new Sort(Sort.Direction.ASC, "roomNbr"));

        List<Reservation>  reservationList= reservationRepo.getReservationsByDate(beginDate,endDate);

        HashMap<String,ArrayList<ReservationDates>> roomReservationMap= new HashMap<String,ArrayList<ReservationDates>>();

        for(Reservation reservation:reservationList){
            ReservationDates reservationDates= new ReservationDates();
            reservationDates.setBeginDate(new Date(reservation.getDateFrom()));
            reservationDates.setEndDate(new Date(reservation.getDateTo()));

            for(Room room:reservation.getRoomList()) {
                ArrayList<ReservationDates> dates = roomReservationMap.get(room.getId());

                if (dates == null) {
                    dates=new ArrayList<ReservationDates>();
                    dates.add(reservationDates);
                    roomReservationMap.put(room.getId(),dates);

                }else {
                    dates.add(reservationDates);
                }
            }
        }

        for(Room r: allRoomsList){
            ArrayList<DayDto> dayOverview=new ArrayList<DayDto>();



            for(int i=0;i<28;i++){
                DayDto dayDto = new DayDto();

                Date date=new Date(beginDate+i*DAY_IN_MS);

                int day=date.getDay();

                //if weekend
                if(day==0 || day==6){
                    dayDto.setIsWeekend(true);
                }

                dayDto.setSelectBoxId(CommonUtils.getFormDateString(date));


                if(CommonUtils.checkIfReserved(roomReservationMap.get(r.getId()),date)){
                    dayDto.setIsReserved(true);
                }

                dayOverview.add(dayDto);
            }

            RoomDto roomDto= new RoomDto(r);
            roomDto.setDayOverview(dayOverview);
            roomList.add(roomDto);
        }

        roomOverview.setRooms(roomList);

        return roomOverview;
    }

    public Reservation findById(String id) {
        return reservationRepo.findOne(id);
    }

    public ReservationInProgress findInProgressById(String id) {
        return inProgressRepo.findOne(id);
    }

    public ReservationInProgress preReserveSelectedDays(List<String> selectedCheckboxes){

        ReservationInProgress reservationInProgress = new ReservationInProgress();

        HashMap<String,ArrayList<Date>> roomReservationMap = new HashMap<String,ArrayList<Date>>();

        for(String selected:selectedCheckboxes){
            String[] splitted= selected.split("_");

            if(splitted.length!=2){
                //wrong dateformat -> ignore this checkbox
                continue;
            }


            String roomNbr=splitted[0];
            String date=splitted[1];

            ArrayList<Date> dateList=roomReservationMap.get(roomNbr);

            if(dateList==null){
                dateList=new ArrayList<Date>();

                Date parsedDate=CommonUtils.parseCheckboxDate(splitted[1]);

                if(parsedDate!=null) {
                    dateList.add(parsedDate);

                    roomReservationMap.put(roomNbr,dateList);

                }
            }else{
                Date parsedDate=CommonUtils.parseCheckboxDate(splitted[1]);
                dateList.add(parsedDate);
            }
        }


        List<Room> roomList= new ArrayList<Room>();

        for(String key:roomReservationMap.keySet()) {
            Room room = roomRepo.findOneByRoomNbr(key);
            roomList.add(room);
        }

        Collections.sort(roomList);

        Long dateFrom=null;
        Long dateTo=null;

        for(ArrayList<Date> dateList:roomReservationMap.values()){
            if(dateList!=null && dateList.size()>0){
                dateFrom=dateList.get(0).getTime();
                dateTo=dateList.get(dateList.size()-1).getTime();
                break;

            }
        }


        reservationInProgress.setRoomList(roomList);
        reservationInProgress.setDateFrom(dateFrom);
        reservationInProgress.setDateTo(dateTo);

        return inProgressRepo.save(reservationInProgress);
    }

    public ReservationInProgress addNewCustomerToReservationInProgess(String reservationInProgressId, String firstName, String lastName, String address, String companyName, String phone, String fax,
                                                                      String mail, String homepage, String avatarUrl, String notes){
        Customer c = new Customer(firstName, lastName);
        c.setAddress(address);
        c.setCompanyName(companyName);
        c.setPhone(phone);
        c.setFax(fax);
        c.setMail(mail);
        c.setHomepage(homepage);
        c.setAvatarUrl(avatarUrl);
        c.setNotes(notes);
        Customer saved = customerRepo.save(c);

        ReservationInProgress reservationInProgress= inProgressRepo.findOne(reservationInProgressId);

        if(reservationInProgress==null){
            return null;
        }

        reservationInProgress.setCustomer(saved);

        return reservationInProgress;
    }

    public ReservationInProgress addCustomerToReservationInProgess(String reservationInProgressId, String customerId){

        Customer customer=customerRepo.findOne(customerId);

        if(customer==null){
            return null;
        }

        ReservationInProgress reservationInProgress= inProgressRepo.findOne(reservationInProgressId);

        if(reservationInProgress==null){
            return null;
        }

        reservationInProgress.setCustomer(customer);
        

        return inProgressRepo.save(reservationInProgress);
    }

    public void confirmReservation(String reservationInProgressId){

        ReservationInProgress reservationInProgress= inProgressRepo.findOne(reservationInProgressId);
        Reservation reservation=new Reservation(reservationInProgress);

        reservationRepo.save(reservation);
	}
        
    public void cancel(Reservation r) {
        if (r.getDateFrom() > System.currentTimeMillis()) {
            Invoice i = new Invoice();
            i.setCustomer(r.getCustomer());
            i.setPrice(CommonUtils.STORNO_GEBUEHR);
            i.setInvoiceDate(System.currentTimeMillis());
            i.setNotes("storniert");
            i.setReservation(r);
            invoiceRepo.save(i);
            reservationRepo.delete(r.getId());
        }
    }

    public void checkout(Reservation r) {
        if (r.getDateFrom() <= System.currentTimeMillis()) {
            Invoice i = new Invoice();
            i.setCustomer(r.getCustomer());
            i.setInvoiceDate(System.currentTimeMillis());
            double numDays = ((r.getDateTo() - r.getDateFrom()) / 86400000); // gesamtdauer der reservierung
            i.setPrice((long) (r.getRoomPrice() * numDays));
            if (r.getDiscount() != null) {
                i.setPrice((long) (i.getPrice() * (100 - r.getDiscount()) / 100.0));
            }
            i.setReservation(r);
            if (System.currentTimeMillis() < r.getDateTo()) {
                // frühzeitige Abreise -> prozentuelle abrechnung + 15% aufschlag
                i.setNotes("frühzeitige Abreise");
                double spentDays = ((System.currentTimeMillis() - r.getDateFrom()) / 86400000); // anzahl der gebliebenen tage
                i.setPrice((long) ((double) (i.getPrice()) * Math.min(1.0, (spentDays / numDays) + 0.15)));
            }
            String customerId = r.getCustomer().getId();
            invoiceRepo.save(i);
            reservationRepo.delete(r.getId());
        }
    }

    public void setSelectedRooms(String reservationInProgressId, List<String> selectedRooms){

        ReservationInProgress reservationInProgress=inProgressRepo.findOne(reservationInProgressId);

        int i=0;

        Long roomCost=0L;

        Long reservedDays=(reservationInProgress.getDateTo() - reservationInProgress.getDateFrom()) / DAY_IN_MS +1;

        for(Room room: reservationInProgress.getRoomList()){
            String selectedRoom=selectedRooms.get(i);

            try {
                int selectedRoomOrdinal=Integer.parseInt(selectedRoom);
                RoomTypEnum roomTyp=RoomTypEnum.fromOrdinal(selectedRoomOrdinal);
                room.setBookedRoomTyp(roomTyp);
                roomCost+=CommonUtils.getRoomPriceForSelectionAndDays(room,roomTyp,reservedDays);
            } catch (NumberFormatException e) {
            }

            i++;
        }

        reservationInProgress.setRoomPrice(roomCost);
        inProgressRepo.save(reservationInProgress);
    }
}
