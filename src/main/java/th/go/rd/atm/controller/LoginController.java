package th.go.rd.atm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import th.go.rd.atm.model.BankAccount;
import th.go.rd.atm.model.Customer;
import th.go.rd.atm.service.BankAccountService;
import th.go.rd.atm.service.CustomerService;

import java.util.List;

//controller มีหน้าที่เป๋นเพียง router

@Controller
@RequestMapping("/login")
public class LoginController {

    private CustomerService customerService;
    private BankAccountService bankAccountService;

    public LoginController(CustomerService customerService , BankAccountService bankAccountService) {
        this.customerService = customerService;
        this.bankAccountService = bankAccountService;
    }

    @GetMapping
    public String getLoginPage() {
        return "login";   // return หน้าเพจ login.html
    }

    @PostMapping
    public String login(@ModelAttribute Customer customer, Model model) {
        // 1. เอา id กับ pin ไปเช็คกับข้อมูล customer ที่มีอยู่ ว่าตรงกันบ้างไหม
        Customer matchingCustomer = customerService.checkPin(customer);

        // 2. ถ้าตรง ส่งข้อมูล customer กลับไปแสดงผล
        if (matchingCustomer != null) {
            int customerid = matchingCustomer.getId();
            List<BankAccount> accounts = bankAccountService.getBankAccount(customerid);
            model.addAttribute("bankaccount",accounts);
            return "bankaccount";

//            customer.setPin("");
//            customer.setName(matchingCustomer.getName());
//            model.addAttribute("customer", customer);
//            model.addAttribute("customermessage",
//                    customer.getName() + " Bank Accounts");
//            model.addAttribute("bankaccounts",
//                    bankAccountService.getBankAccount(customer.getId()));
//            return "bankaccount";
        } else {
            // 3. ถ้าไม่ตรง แจ้งว่าไม่มีข้อมูล customer นี้
            model.addAttribute("greeting", "Can't find customer");
        }
        return "home";
    }

}
