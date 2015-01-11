package rentaroom.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import rentaroom.Utils.CommonUtils;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Peter on 07.11.2014.
 */

@Document
public class Invoice implements Serializable {

    private static final long serialVersionUID = 12321L;

    @Id
    private String id;

    private Customer customer;

    private long invoiceDate;

    //price in cent 100=1Euro
    private long price;

    private Reservation reservation;

    private String notes;

    public Invoice() {
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

    public long getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(long invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public String invoiceDateAsString() {
        return CommonUtils.dateFormatter.format(invoiceDate);
    }

    public Reservation getReservation() {
        return reservation;
    }

    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

}
