package rentaroom.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import rentaroom.Utils.CommonUtils;
import rentaroom.dtos.*;
import rentaroom.entities.Customer;
import rentaroom.entities.Reservation;
import rentaroom.entities.Room;
import rentaroom.repositories.ReservationRepository;
import rentaroom.repositories.RoomRepository;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Christian 12.01.2015.
 */
@Service
public class RoomService {

    @Autowired
    private RoomRepository roomRepo;

    public Room findById(String id) {
        return roomRepo.findOne(id);
    }

    public Room editRoom(String id, String roomNbr, int maxPersons, Long price_singleRoom, long price_doubleRoom,
                         long price_threePersons, long price_singleRoomOneChild, long price_singleRoomTwoChildren,
                         long price_doubleRoomOneChild) {
        Room r = roomRepo.findOne(id);
        r.setRoomNbr(roomNbr);
        r.setMaxPersons(maxPersons);
        r.setPrice_singleRoom(price_singleRoom);
        return roomRepo.save(r);
    }
}
