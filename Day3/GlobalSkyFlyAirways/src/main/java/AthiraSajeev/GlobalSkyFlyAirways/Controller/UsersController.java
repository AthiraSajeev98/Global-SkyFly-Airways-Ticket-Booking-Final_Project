package AthiraSajeev.GlobalSkyFlyAirways.Controller;

import AthiraSajeev.GlobalSkyFlyAirways.Entity.Users;
import AthiraSajeev.GlobalSkyFlyAirways.Service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
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
        String nationality = request.getParameter("nationality");
        Integer age = Integer.parseInt(request.getParameter("age"));
        Long phoneNumber = Long.parseLong(request.getParameter("phoneNumber"));
        String emailId = request.getParameter("emailId");
        String address = request.getParameter("address");

        Users user = new Users(username, password, name, nationality, age, phoneNumber, emailId, address);

        usersService.save(user);

        model.addAttribute("successMessage", "Successfully Registered User");
        return "signUp";
    }

    //User Login
    @RequestMapping("/signIn")
    public String signIn() {
        return "/signIn";
    }

    String userId;

    @PostMapping("/signIn")
    public String signIn(Model model, HttpServletRequest request) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if (usersService.existsUserById(username)) {
            if (Objects.equals(usersService.getUserById(username).getPassword(), password)) {
                userId = usersService.getUserById(username).getUsername();
                System.out.println(userId);
                model.addAttribute("username", usersService.getUserById(username).getUsername());
                model.addAttribute("name", usersService.getUserById(username).getName());
                model.addAttribute("age", usersService.getUserById(username).getAge());
                model.addAttribute("nationality", usersService.getUserById(username).getNationality());
                model.addAttribute("emailId", usersService.getUserById(username).getEmailId());
                model.addAttribute("phoneNumber", usersService.getUserById(username).getPhoneNumber());
                model.addAttribute("address", usersService.getUserById(username).getAddress());
                model.addAttribute("userId", userId);
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
    public String profile(Model model) {
        model.addAttribute("username", usersService.getUserById(userId).getUsername());
        model.addAttribute("name", usersService.getUserById(userId).getName());
        model.addAttribute("age", usersService.getUserById(userId).getAge());
        model.addAttribute("nationality", usersService.getUserById(userId).getNationality());
        model.addAttribute("emailId", usersService.getUserById(userId).getEmailId());
        model.addAttribute("phoneNumber", usersService.getUserById(userId).getPhoneNumber());
        model.addAttribute("address", usersService.getUserById(userId).getAddress());
        model.addAttribute("userId", userId);
        return "userProfile";
    }

    @RequestMapping("/userProfile/{userId}")
    public String userProfile(Model model, @PathVariable String userId) {
        model.addAttribute("username", usersService.getUserById(userId).getUsername());
        model.addAttribute("name", usersService.getUserById(userId).getName());
        model.addAttribute("age", usersService.getUserById(userId).getAge());
        model.addAttribute("nationality", usersService.getUserById(userId).getNationality());
        model.addAttribute("emailId", usersService.getUserById(userId).getEmailId());
        model.addAttribute("phoneNumber", usersService.getUserById(userId).getPhoneNumber());
        model.addAttribute("address", usersService.getUserById(userId).getAddress());
        model.addAttribute("userId", userId);
        return "userProfile";
    }

    //Update profile
    @RequestMapping("/updateUserProfile")
    public String updateUserProfile(Model model) {
        model.addAttribute("user", usersService.getUserById(userId));
        model.addAttribute("userId", userId);
        return "updateUserProfile";
    }

    @RequestMapping("/updateUserProfile/{userId}")
    public String updateUserProfileById(@PathVariable String userId, Model model) {
        model.addAttribute("user", usersService.getUserById(userId));
        model.addAttribute("userId", userId);
        return "updateUserProfile";
    }

    @PostMapping("/updateUserProfile")
    public String updateUserProfile(Model model, HttpServletRequest request) {
        String password = request.getParameter("password");
        String emailId = request.getParameter("emailId");
        Long phoneNumber = Long.parseLong(request.getParameter("phoneNumber"));
        String address = request.getParameter("address");
        usersService.getUserById(userId).updateUser(password, phoneNumber, emailId, address);
        usersService.save(usersService.getUserById(userId));
        model.addAttribute("successMessage", "Profile updated");
        model.addAttribute("user", usersService.getUserById(userId));
        model.addAttribute("userId", userId);
        return "updateUserProfile";
    }

    //Adding wallet balance
    @RequestMapping("/addUserWalletBalance")
    public String addUserWalletBalance(Model model) {
        model.addAttribute("user", usersService.getUserById(userId));
        model.addAttribute("userId", userId);
        return "addUserWalletBalance";
    }

    @RequestMapping("/addUserWalletBalance/{userId}")
    public String addUserWalletBalance(@PathVariable String userId, Model model) {
        model.addAttribute("user", usersService.getUserById(userId));
        model.addAttribute("userId", userId);
        return "addUserWalletBalance";
    }

    @PostMapping("/addUserWalletBalance")
    public String addUserWalletBalance(Model model, HttpServletRequest request) {
        Double amount = Double.parseDouble(request.getParameter("amount"));
        usersService.getUserById(userId).setWalletBalance(usersService.getUserById(userId).getWalletBalance() + amount);
        usersService.save(usersService.getUserById(userId));
        model.addAttribute("successMessage", "Transaction complete");
        model.addAttribute("user", usersService.getUserById(userId));
        model.addAttribute("userId", userId);
        return "addUserWalletBalance";
    }

    //User Balance
    @RequestMapping("/userBalance")
    public String userBalance(Model model) {
        model.addAttribute("user", usersService.getUserById(userId));
        model.addAttribute("userId", userId);
        return "userBalance";
    }

    @RequestMapping("/userBalance/{userId}")
    public String userBalance(@PathVariable String userId, Model model) {
        model.addAttribute("user", usersService.getUserById(userId));
        model.addAttribute("userId", userId);
        return "userBalance";
    }
}