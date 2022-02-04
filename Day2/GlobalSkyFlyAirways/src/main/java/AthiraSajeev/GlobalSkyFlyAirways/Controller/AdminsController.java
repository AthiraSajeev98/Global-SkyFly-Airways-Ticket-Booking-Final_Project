package AthiraSajeev.GlobalSkyFlyAirways.Controller;

import AthiraSajeev.GlobalSkyFlyAirways.Entity.Admins;
import AthiraSajeev.GlobalSkyFlyAirways.Entity.Users;
import AthiraSajeev.GlobalSkyFlyAirways.Service.AdminsService;
import AthiraSajeev.GlobalSkyFlyAirways.Service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

@Controller
public class AdminsController {

    @Autowired
    public AdminsService adminsService;

    //Admin registration
    @RequestMapping("/signUpAdmin")
    public String signUpAdmin() {
        return "/signUpAdmin";
    }

    @PostMapping("/signUpAdmin")
    public String signUpAdmin(Model model, HttpServletRequest request) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String organization = request.getParameter("organizationName");
        String employeeId = request.getParameter("employeeId");
        String name = request.getParameter("name");
        String gender = request.getParameter("gender");
        Integer age = Integer.parseInt(request.getParameter("age"));
        Long phoneNumber = Long.parseLong(request.getParameter("phoneNumber"));
        String emailId = request.getParameter("emailId");
        String address = request.getParameter("address");

        Admins admin = new Admins(username, password, organization, employeeId, name, gender, age, phoneNumber, emailId, address);

        adminsService.save(admin);

        model.addAttribute("successMessage", "Successfully Registered User");
        return "signUpAdmin";
    }

    //Admin Login
    @RequestMapping("/signInAdmin")
    public String signInAdmin() {
        return "/signInAdmin";
    }

    private String adminId;

    @PostMapping("/signInAdmin")
    public String signInAdmin(Model model, HttpServletRequest request) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if (adminsService.existsAdminById(username)) {
            if (Objects.equals(adminsService.getAdminById(username).getPassword(), password)) {
                adminId = adminsService.getAdminById(username).getUsername();
                model.addAttribute("username", adminsService.getAdminById(username).getUsername());
                model.addAttribute("organizationName", adminsService.getAdminById(username).getOrganizationName());
                model.addAttribute("employeeId", adminsService.getAdminById(username).getEmployeeId());
                model.addAttribute("name", adminsService.getAdminById(username).getName());
                model.addAttribute("age", adminsService.getAdminById(username).getAge());
                model.addAttribute("gender", adminsService.getAdminById(username).getGender());
                model.addAttribute("emailId", adminsService.getAdminById(username).getEmailId());
                model.addAttribute("phoneNumber", adminsService.getAdminById(username).getPhoneNumber());
                model.addAttribute("address", adminsService.getAdminById(username).getAddress());

                return "adminProfile";

            } else {
                model.addAttribute("errorMessage", "Invalid credentials");
                return "signInAdmin";
            }
        } else {
            model.addAttribute("errorMessage", "Invalid credentials");
            return "signInAdmin";
        }
    }

    //Admin profile
    @RequestMapping("/adminProfile")
    public String adminProfile() {
        return "/adminProfile";
    }


}
