package rentaroom.services;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import rentaroom.entities.Customer;
import rentaroom.repositories.CustomerRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Peter on 03.12.2014.
 */
@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepo;

    public JSONObject getAllCustomerJson(){
        ArrayList<Customer> customerList= (ArrayList<Customer>) customerRepo.findAll();
        JSONArray customerArray=new JSONArray();

        for(Customer customer:customerList){
            JSONObject customerJson=new JSONObject();
            customerJson.put("value",customer.getLastName()+" "+customer.getFirstName());
            customerJson.put("data",customer.getId());
            customerArray.add(customerJson);
        }

        JSONObject jsonObject=new JSONObject();
        jsonObject.put("suggestions",customerArray);

        return jsonObject;
    }

    public Customer findById(String id) {
        return customerRepo.findOne(id);
    }

    public Customer add(String firstName, String lastName, String address, String companyName, String phone, String fax,
                        String mail, String homepage, String avatarUrl, String notes) {
        Customer c = new Customer(firstName, lastName);
        c.setAddress(address);
        c.setCompanyName(companyName);
        c.setPhone(phone);
        c.setFax(fax);
        c.setMail(mail);
        c.setHomepage(homepage);
        c.setAvatarUrl(avatarUrl);
        c.setNotes(notes);
        return customerRepo.save(c);
    }

    public void edit(String id, String firstName, String lastName, String address, String companyName, String phone, String fax,
                     String mail, String homepage, String avatarUrl, String notes) {
        Customer c = customerRepo.findOne(id);
        c.setFirstName(firstName);
        c.setLastName(lastName);
        c.setAddress(address);
        c.setCompanyName(companyName);
        c.setPhone(phone);
        c.setFax(fax);
        c.setMail(mail);
        c.setHomepage(homepage);
        c.setAvatarUrl(avatarUrl);
        c.setNotes(notes);
        customerRepo.save(c);
    }

    public Iterable<Customer> findAll() {
        return customerRepo.findAll(new Sort(Sort.Direction.ASC, "lastName"));
    }
}
