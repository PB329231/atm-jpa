package th.go.rd.atm.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import th.go.rd.atm.model.Customer;
import th.go.rd.atm.service.CustomerService;

import java.util.List;

@RestController
@RequestMapping("/api/customer")
public class CustomerRestController {

    private  CustomerService customerService; //ประกาศตัวแปร customerService

    public CustomerRestController(CustomerService customerService) { //Generate Constructor
        this.customerService =customerService;
    }

    @GetMapping
    public List<Customer> getAll(){ //get คืนค่าเป็น list ข้อมลลูกค้าทุกคน
        return customerService.getCustomers();
    }

    @GetMapping("/{id}") //เรียกว่า path variable
    public Customer getOne(@PathVariable int id){ //get คืนค่าข้อมูลเฉพาะรายการ
        return customerService.findCustomer(id);
    }
}
