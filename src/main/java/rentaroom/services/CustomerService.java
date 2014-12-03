package rentaroom.services;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
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
            customerJson.put("data",customer.getCostumer_id());
            customerArray.add(customerJson);
        }

        JSONObject jsonObject=new JSONObject();
        jsonObject.put("suggestions",customerArray);

        return jsonObject;
    }
}
