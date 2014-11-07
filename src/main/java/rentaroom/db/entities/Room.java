package rentaroom.db.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by Peter on 07.11.2014.
 */

@Entity
public class Room implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private Long room_id;

    @Column(nullable=false)
    private Long maxPersons;

    //all prices are in cents 100=1Euro
    @Column(nullable=false)
    private Long price_singleRoom;

    @Column(nullable=false)
    private Long price_doubleRoom;

    //Price for "Dreifachbelegung"
    @Column(nullable=false)
    private Long price_threePersons;

    @Column(nullable=false)
    private Long price_singleRoomOneChild;

    @Column(nullable=false)
    private Long price_singleRoomTwoChildren;

    @Column(nullable=false)
    private Long price_doubleRoomOneChild;


    public Room(){}

    public Long getRoom_id() {
        return room_id;
    }

    public void setRoom_id(Long room_id) {
        this.room_id = room_id;
    }

    public Long getPrice_singleRoom() {
        return price_singleRoom;
    }

    public void setPrice_singleRoom(Long price_singleRoom) {
        this.price_singleRoom = price_singleRoom;
    }

    public Long getMaxPersons() {
        return maxPersons;
    }

    public void setMaxPersons(Long maxPersons) {
        this.maxPersons = maxPersons;
    }

    public Long getPrice_doubleRoom() {
        return price_doubleRoom;
    }

    public void setPrice_doubleRoom(Long price_doubleRoom) {
        this.price_doubleRoom = price_doubleRoom;
    }

    public Long getPrice_threePersons() {
        return price_threePersons;
    }

    public void setPrice_threePersons(Long price_threePersons) {
        this.price_threePersons = price_threePersons;
    }

    public Long getPrice_singleRoomOneChild() {
        return price_singleRoomOneChild;
    }

    public void setPrice_singleRoomOneChild(Long price_singleRoomOneChild) {
        this.price_singleRoomOneChild = price_singleRoomOneChild;
    }

    public Long getPrice_doubleRoomOneChild() {
        return price_doubleRoomOneChild;
    }

    public void setPrice_doubleRoomOneChild(Long price_doubleRoomOneChild) {
        this.price_doubleRoomOneChild = price_doubleRoomOneChild;
    }

    public Long getPrice_singleRoomTwoChildren() {
        return price_singleRoomTwoChildren;
    }

    public void setPrice_singleRoomTwoChildren(Long price_singleRoomTwoChildren) {
        this.price_singleRoomTwoChildren = price_singleRoomTwoChildren;
    }
}
