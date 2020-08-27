package th.go.rd.atm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import th.go.rd.atm.model.Customer;
import th.go.rd.atm.service.CustomerService;


@Controller
@RequestMapping("/customer") //path url name
public class CustomerController {
    private CustomerService customerService;

    public CustomerController(CustomerService customerService){
        this.customerService =customerService;
    }

    @GetMapping
    public String getCustomerPage(Model model){

        model.addAttribute("allCustomers",customerService.getCustomers());
        return "customer"; //return file customer.html
    }

    @PostMapping
    public String registerCustomer(@ModelAttribute Customer customer, Model model) {
        customerService.createCustomer(customer);
        model.addAttribute("allCustomers", customerService.getCustomers());
        return "redirect:customer"; //จะreditrect กลับมาที่เดิม ข้อมูลจะหายไป
    }
}
