package rentaroom.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by Peter on 07.11.2014.
 */

@Document
public class Reservation implements Serializable {

    private static final long serialVersionUID = 1111L;

    public static SimpleDateFormat dateFormatter = new SimpleDateFormat("dd.MM.yyyy");

    @Id
    private String id;

    private Customer customer;

    private List<Room> roomList;

    //percentage 0-100
    private Integer discount;

    private long dateFrom;

    private long dateTo;

    private long roomPrice;


    public Reservation() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Integer getDiscount() {
        return discount;
    }

    public void setDiscount(Integer discount) {
        this.discount = discount;
    }

    public List<Room> getRoomList() {
        return roomList;
    }

    public void setRoomList(List<Room> roomList) {
        this.roomList = roomList;
    }

    public long getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(long dateFrom) {
        this.dateFrom = dateFrom;
    }

    public long getDateTo() {
        return dateTo;
    }

    public void setDateTo(long dateTo) {
        this.dateTo = dateTo;
    }

    public long getRoomPrice() {
        return roomPrice;
    }

    public void setRoomPrice(long roomPrice) {
        this.roomPrice = roomPrice;
    }

    public String dateFromAsString() {
        return dateFormatter.format(dateFrom);
    }

    public String dateToAsString() {
        return dateFormatter.format(dateTo);
    }
}
