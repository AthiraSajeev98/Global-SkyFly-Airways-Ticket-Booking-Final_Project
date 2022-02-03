package AthiraSajeev.GlobalSkyFlyAirways.Controller;

import AthiraSajeev.GlobalSkyFlyAirways.Entity.Users;
import AthiraSajeev.GlobalSkyFlyAirways.Service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class UsersController {
    @Autowired
    private UsersService usersService;

    @RequestMapping("/home")
    public String home() {
        return "/home";
    }

    @RequestMapping("/signIn")
    public String signIn() {
        return "/signIn";
    }

    @RequestMapping("/signUp")
    public String signUp() {
        return "/signUp";
    }

    @PostMapping("/signUp")
    public String signUp(Model model, HttpServletRequest request) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String name = request.getParameter("name");
        String gender = request.getParameter("gender");
        Integer age = Integer.parseInt(request.getParameter("age"));
        Long phoneNumber = Long.parseLong(request.getParameter("phoneNumber"));
        String emailId = request.getParameter("emailId");
        String address = request.getParameter("address");

        Users user = new Users(username,password,name,gender,age,phoneNumber,emailId,address);
        System.out.println(user);
        usersService.save(user);

        model.addAttribute("successMessage", "Successfully Registered User");
        return "register";
    }

}
