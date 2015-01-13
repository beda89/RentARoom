package rentaroom.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

/**
 * Created by Peter on 07.11.2014.
 */

@Document
public class Room implements Serializable,Comparable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    private Integer maxPersons;

    private String roomNbr;

    //all prices are in cents 100=1Euro
    private long price_singleRoom;

    private long price_doubleRoom;

    //Price for "Dreifachbelegung"
    private long price_threePersons;

    private long price_singleRoomOneChild;

    private long price_singleRoomTwoChildren;

    private long price_doubleRoomOneChild;

    private RoomTypEnum bookedRoom;

    public Room() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public long getPrice_singleRoom() {
        return price_singleRoom;
    }

    public void setPrice_singleRoom(long price_singleRoom) {
        this.price_singleRoom = price_singleRoom;
    }

    public Integer getMaxPersons() {
        return maxPersons;
    }

    public void setMaxPersons(Integer maxPersons) {
        this.maxPersons = maxPersons;
    }

    public long getPrice_doubleRoom() {
        return price_doubleRoom;
    }

    public void setPrice_doubleRoom(long price_doubleRoom) {
        this.price_doubleRoom = price_doubleRoom;
    }

    public long getPrice_threePersons() {
        return price_threePersons;
    }

    public void setPrice_threePersons(long price_threePersons) {
        this.price_threePersons = price_threePersons;
    }

    public long getPrice_singleRoomOneChild() {
        return price_singleRoomOneChild;
    }

    public void setPrice_singleRoomOneChild(long price_singleRoomOneChild) {
        this.price_singleRoomOneChild = price_singleRoomOneChild;
    }

    public long getPrice_doubleRoomOneChild() {
        return price_doubleRoomOneChild;
    }

    public void setPrice_doubleRoomOneChild(long price_doubleRoomOneChild) {
        this.price_doubleRoomOneChild = price_doubleRoomOneChild;
    }

    public long getPrice_singleRoomTwoChildren() {
        return price_singleRoomTwoChildren;
    }

    public void setPrice_singleRoomTwoChildren(long price_singleRoomTwoChildren) {
        this.price_singleRoomTwoChildren = price_singleRoomTwoChildren;
    }

    public String getRoomNbr() {
        return roomNbr;
    }

    public void setRoomNbr(String roomNbr) {
        this.roomNbr = roomNbr;
    }

    public RoomTypEnum getBookedRoomTyp() {
        return bookedRoom;
    }

    public void setBookedRoomTyp(RoomTypEnum bookedRoom) {
        this.bookedRoom = bookedRoom;
    }

    public int compareTo(Object room2){

        if(room2 instanceof Room) {
            Room r2=(Room) room2;

            return this.getRoomNbr().compareTo(r2.getRoomNbr());

        }else{
            return 1;
        }

    }
}
