package rentaroom.db.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by Peter on 07.11.2014.
 */

@Entity
public class Reservation implements Serializable{

    private static final long serialVersionUID = 1111L;

    @Id
    @GeneratedValue
    private Long reservation_id;

    @ManyToOne
    private Customer customer;

    @ManyToMany
    private List<Room> roomList;

    //percentage 0-100
    @Column(nullable=false)
    private Long discount;

    @Column(nullable=false)
    private Date dateFrom;

    @Column(nullable=false)
    private Date dateTo;

    //roomprice for whole reservation period
    @Column(nullable=false)
    private Long roomPrice;


    public Reservation(){}


    public Long getReservation_id() {
        return reservation_id;
    }

    public void setReservation_id(Long reservation_id) {
        this.reservation_id = reservation_id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Long getDiscount() {
        return discount;
    }

    public void setDiscount(Long discount) {
        this.discount = discount;
    }

    public List<Room> getRoomList() {
        return roomList;
    }

    public void setRoomList(List<Room> roomList) {
        this.roomList = roomList;
    }

    public Date getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(Date dateFrom) {
        this.dateFrom = dateFrom;
    }

    public Date getDateTo() {
        return dateTo;
    }

    public void setDateTo(Date dateTo) {
        this.dateTo = dateTo;
    }

    public Long getRoomPrice() {
        return roomPrice;
    }

    public void setRoomPrice(Long roomPrice) {
        this.roomPrice = roomPrice;
    }
}
