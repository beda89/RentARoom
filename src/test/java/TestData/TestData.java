package TestData;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import rentaroom.db.repositories.CustomerRepository;

/**
 * Created by Peter on 28.11.2014.
 */
@RunWith(SpringJUnit4ClassRunner.class)
public class TestData {

    @Autowired
    private CustomerRepository customerRepo;

    @Test
    public void initDBWithTestData(){
        customerRepo.deleteAll();


    }

}
