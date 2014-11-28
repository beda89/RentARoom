package rentaroom.db.entities;

import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by Peter on 07.11.2014.
 */

@Document
public class Customer implements Serializable {

    private static final long serialVersionUID = 1444L;

    @Id
    @GeneratedValue
    private Long costumer_id;

    @Column(nullable=false)
    private String name;

    @Column(nullable=false)
    private String address;

    private String companyName;


    private String notes;

    //percentage 0-100
    @Column(nullable=false)
    private Integer discount;

    @Column(nullable=false)
    private String phone;

    private String mail;

    private String homepage;

    private String fax;

    public Customer(){}

    public Long getCostumer_id() {
        return costumer_id;
    }

    public void setCostumer_id(Long costumer_id) {
        this.costumer_id = costumer_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getDiscount() {
        return discount;
    }

    public void setDiscount(Integer discount) {
        this.discount = discount;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getHomepage() {
        return homepage;
    }

    public void setHomepage(String homepage) {
        this.homepage = homepage;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }


}
