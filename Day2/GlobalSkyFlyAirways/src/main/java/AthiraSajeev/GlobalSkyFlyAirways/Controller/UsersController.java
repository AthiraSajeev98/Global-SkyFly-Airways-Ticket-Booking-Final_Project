package AthiraSajeev.GlobalSkyFlyAirways.Controller;

import AthiraSajeev.GlobalSkyFlyAirways.Entity.Users;
import AthiraSajeev.GlobalSkyFlyAirways.Service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

@Controller
public class UsersController {
    @Autowired
    public UsersService usersService;

    //Main home page
    @RequestMapping("/home")
    public String home() {
        return "/home";
    }

    //User registration
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

        Users user = new Users(username, password, name, gender, age, phoneNumber, emailId, address);

        usersService.save(user);

        model.addAttribute("successMessage", "Successfully Registered User");
        return "signUp";
    }

    //User Login
    @RequestMapping("/signIn")
    public String signIn() {
        return "/signIn";
    }

    private String userId;

    @PostMapping("/signIn")
    public String signIn(Model model, HttpServletRequest request) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if (usersService.existsUserById(username)) {
            if (Objects.equals(usersService.getUserById(username).getPassword(), password)) {
                userId = usersService.getUserById(username).getUsername();
                model.addAttribute("username", usersService.getUserById(username).getUsername());
                model.addAttribute("name", usersService.getUserById(username).getName());
                model.addAttribute("age", usersService.getUserById(username).getAge());
                model.addAttribute("gender", usersService.getUserById(username).getGender());
                model.addAttribute("emailId", usersService.getUserById(username).getEmailId());
                model.addAttribute("phoneNumber", usersService.getUserById(username).getPhoneNumber());
                model.addAttribute("address", usersService.getUserById(username).getAddress());

                return "userProfile";

            } else {
                model.addAttribute("errorMessage", "Invalid credentials");
                return "signIn";
            }
        } else {
            model.addAttribute("errorMessage", "Invalid credentials");
            return "signIn";
        }
    }

    //User profile
    @RequestMapping("/userProfile")
    public String profile() {
        return "/userProfile";
    }


}