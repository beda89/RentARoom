package rentaroom.dtos;

import rentaroom.entities.Room;

/**
 * Created by Peter on 08.01.2015.
 */
public class RoomDto {


    public String id;

    public Integer maxPersons;

    public String roomNbr;

    //all prices are in cents 100=1Euro
    public long price_singleRoom;

    public long price_doubleRoom;

    //Price for "Dreifachbelegung"
    public long price_threePersons;

    public long price_singleRoomOneChild;

    public long price_singleRoomTwoChildren;

    public long price_doubleRoomOneChild;

    public boolean isReserved;

    public RoomDto(Room r){
        this.id=r.getId();
        this.maxPersons=r.getMaxPersons();
        this.roomNbr=r.getRoomNbr();
        this.price_doubleRoom=r.getPrice_doubleRoom();
        this.price_doubleRoomOneChild=r.getPrice_doubleRoomOneChild();
        this.price_singleRoom=r.getPrice_singleRoom();
        this.price_singleRoomOneChild=r.getPrice_singleRoomOneChild();
        this.price_singleRoomTwoChildren=r.getPrice_singleRoomTwoChildren();
        this.price_threePersons=r.getPrice_threePersons();
    }


    public String getId() {
        return id;
    }

    public long getPrice_singleRoom() {
        return price_singleRoom;
    }

    public Integer getMaxPersons() {
        return maxPersons;
    }

    public long getPrice_doubleRoom() {
        return price_doubleRoom;
    }

    public long getPrice_threePersons() {
        return price_threePersons;
    }

    public long getPrice_singleRoomOneChild() {
        return price_singleRoomOneChild;
    }

    public long getPrice_doubleRoomOneChild() {
        return price_doubleRoomOneChild;
    }

    public boolean getIsReserved() {
        return isReserved;
    }

    public long getPrice_singleRoomTwoChildren() {
        return price_singleRoomTwoChildren;
    }

    public String getRoomNbr() {
        return roomNbr;
    }

}
