package th.go.rd.atm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
    @RequestMapping("/home") //หมายถึง home.html
    public String getHomePage(Model model){
        model.addAttribute("greeting","Sawaddee Ka!");
        //return home.html
        return "home";
    }
}
