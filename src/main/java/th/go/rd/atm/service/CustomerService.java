package th.go.rd.atm.service;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;
import th.go.rd.atm.data.CustomerRepository;
import th.go.rd.atm.model.Customer;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class CustomerService {
    private CustomerRepository repository;

    public CustomerService(CustomerRepository repository) {
        this.repository = repository;
    }

    public void createCustomer(Customer customer){
        //...hash pin...
        String hashPin = hash(customer.getPin()); //เอา method hashมาใช้ โดยใส่ค่า customer ที่รับค่ามา ส่งเข้าไป
        customer.setPin(hashPin); //เซ็ทค่า pin
        repository.save(customer);
    }

    public Customer findCustomer(int id) {
        try{
            return repository.findById(id).get();
        } catch (NoSuchElementException e){
            return null;
        }
    }

    public List<Customer> getCustomers() {
        return repository.findAll();
    } // คืนค่าเ็น Arraylist customer


    public Customer checkPin(Customer inputCustomer) {
        // 1. หา customer ที่มี id ตรงกับพารามิเตอร์
        Customer storedCustomer = findCustomer(inputCustomer.getId());

        // 2. ถ้ามี id ตรง ให้เช็ค pin ว่าตรงกันไหม โดยใช้ฟังก์ชันเกี่ยวกับ hash
        if (storedCustomer != null) {
            String storedPin = storedCustomer.getPin();
            if (BCrypt.checkpw(inputCustomer.getPin(), storedPin))
                return storedCustomer;
        }
        // 3. ถ้าไม่ตรง ต้องคืนค่า null
        return null;
    }

    private String hash(String pin){ //ใช้ library ของ BCrypt
        String salt = BCrypt.gensalt(12); // สร้าง salt แล้วเอาไปแปะไว้กับ pin และจะ hash มันอีกรครั้ง
        return  BCrypt.hashpw(pin,salt);
    }
}
