package rentaroom.db.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by Peter on 07.11.2014.
 */

@Entity
public class Invoice implements Serializable {

    private static final long serialVersionUID = 12321L;

    @Id
    @GeneratedValue
    private Long invoice_id;

    @ManyToOne
    private Customer customer;

    @Column(nullable=false)
    private Date invoiceDate;

    //price in cent 100=1Euro
    @Column(nullable=false)
    private Long price;

    public Invoice(){}

    public Long getInvoice_id() {
        return invoice_id;
    }

    public void setInvoice_id(Long invoice_id) {
        this.invoice_id = invoice_id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Date getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(Date invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

}
