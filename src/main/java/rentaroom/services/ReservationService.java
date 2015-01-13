package rentaroom.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import rentaroom.Utils.CommonUtils;
import rentaroom.dtos.*;
import rentaroom.entities.Customer;
import rentaroom.entities.Reservation;
import rentaroom.entities.ReservationInProgress;
import rentaroom.entities.Room;
import rentaroom.repositories.ReservationInProgressRepository;
import rentaroom.repositories.ReservationRepository;
import rentaroom.repositories.RoomRepository;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

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
    private ReservationInProgressRepository inProgressRepo;


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

    public void delete(String id) {
        reservationRepo.delete(id);
    }


    public Reservation findById(String id) {
        return reservationRepo.findOne(id);
    }

    public ReservationInProgress preReserveSelectedDays(List<String> selectedCheckboxes){

        ReservationInProgress reservationInProgress = new ReservationInProgress();

        HashMap<String,List<Date>> roomReservationMap = new HashMap<String,List<Date>>();

        for(String selected:selectedCheckboxes){
            String[] splitted= selected.split("_");

            if(splitted.length!=2){
                //wrong dateformat -> ignore this checkbox
                continue;
            }


            String roomNbr=splitted[0];
            String date=splitted[1];

            List<Date> dateList=roomReservationMap.get(roomNbr);

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

        for(String key:roomReservationMap.keySet()){
            Room room=roomRepo.findOneByRoomNbr(key);
            roomList.add(room);
        }



        reservationInProgress.setRoomList(roomList);
    //    reservationInProgress.setDateFrom();
    //    reservationInProgress.setDateTo();


        return inProgressRepo.save(reservationInProgress);
    }
}
