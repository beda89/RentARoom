package rentaroom.services;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import rentaroom.dtos.RoomDto;
import rentaroom.entities.Customer;
import rentaroom.entities.Reservation;
import rentaroom.entities.Room;
import rentaroom.repositories.CustomerRepository;
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

    @Autowired
    private ReservationRepository reservationRepo;

    @Autowired
    private RoomRepository roomRepo;


    public List<Reservation> findOutstandingByCustomer(Customer c) {
        List<Reservation> reservations = new ArrayList<Reservation>();
        if (c != null) {
            for (Reservation r : reservationRepo.findByDateFromGreaterThan(new Date().getTime())) {
                if (r.getCustomer().getId().equals(c.getId())) {
                    reservations.add(r);
                }
            }
        }
        return reservations;
    }

    public List<RoomDto> getRoomsWithStateByDate(Long beginDate,Long endDate){

        List<RoomDto> roomList= new ArrayList<RoomDto>();
        Iterable<Reservation> reservations= reservationRepo.findAll();
//1424386800000
        List<Reservation>  reservationList= reservationRepo.getReservationsByDate(beginDate,endDate);
        List<Room> reservedRoomList= new ArrayList<Room>();

        //collect all reserved rooms
        for(Reservation r: reservationList){
            reservedRoomList.addAll(r.getRoomList());
        }

        //put reserved rooms in hashmap for performance issues

        HashMap<String,Room> reservedRoomMap = new HashMap<String,Room>();

        for(Room r: reservedRoomList){
            reservedRoomMap.put(r.getId(),r);
        }


        Iterable<Room> allRoomsList= roomRepo.findAll();

        for(Room r: allRoomsList){

            RoomDto roomDto= new RoomDto(r);

            if(reservedRoomMap.get(r.getId())!=null){
                roomDto.isReserved=true;
            }else{
                roomDto.isReserved=false;
            }

            roomList.add(roomDto);
        }

        return roomList;
    }


}
