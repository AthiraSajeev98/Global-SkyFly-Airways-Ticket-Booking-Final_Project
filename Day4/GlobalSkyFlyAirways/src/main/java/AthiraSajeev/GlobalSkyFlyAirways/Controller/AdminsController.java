package AthiraSajeev.GlobalSkyFlyAirways.Controller;

import AthiraSajeev.GlobalSkyFlyAirways.Entity.Admins;
import AthiraSajeev.GlobalSkyFlyAirways.Entity.Users;
import AthiraSajeev.GlobalSkyFlyAirways.Service.AdminsService;
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

        model.addAttribute("successMessage", "Successfully Registered Admin");
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
                adminId = username;
                model.addAttribute("username", adminsService.getAdminById(username).getUsername());
                model.addAttribute("organizationName", adminsService.getAdminById(username).getOrganizationName());
                model.addAttribute("employeeId", adminsService.getAdminById(username).getEmployeeId());
                model.addAttribute("name", adminsService.getAdminById(username).getName());
                model.addAttribute("age", adminsService.getAdminById(username).getAge());
                model.addAttribute("gender", adminsService.getAdminById(username).getGender());
                model.addAttribute("emailId", adminsService.getAdminById(username).getEmailId());
                model.addAttribute("phoneNumber", adminsService.getAdminById(username).getPhoneNumber());
                model.addAttribute("address", adminsService.getAdminById(username).getAddress());
                model.addAttribute("adminId", adminId);

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
    public String adminProfile(Model model) {
        model.addAttribute("adminId", adminId);
        return "adminProfile";
    }

    @RequestMapping("/adminProfile/{adminId}")
    public String adminProfile(@PathVariable String adminId, Model model) {
        model.addAttribute("username", adminsService.getAdminById(adminId).getUsername());
        model.addAttribute("organizationName", adminsService.getAdminById(adminId).getOrganizationName());
        model.addAttribute("employeeId", adminsService.getAdminById(adminId).getEmployeeId());
        model.addAttribute("name", adminsService.getAdminById(adminId).getName());
        model.addAttribute("age", adminsService.getAdminById(adminId).getAge());
        model.addAttribute("gender", adminsService.getAdminById(adminId).getGender());
        model.addAttribute("emailId", adminsService.getAdminById(adminId).getEmailId());
        model.addAttribute("phoneNumber", adminsService.getAdminById(adminId).getPhoneNumber());
        model.addAttribute("address", adminsService.getAdminById(adminId).getAddress());
        model.addAttribute("adminId", adminId);
        return "adminProfile";
    }

    //Update profile
    @RequestMapping("/updateAdminProfile")
    public String updateAdminProfile(Model model) {
        model.addAttribute("admin", adminsService.getAdminById(adminId));
        model.addAttribute("adminId", adminId);

        return "updateAdminProfile";
    }

    @RequestMapping("/updateAdminProfile/{adminId}")
    public String updateAdminProfile(@PathVariable String adminId, Model model) {
        model.addAttribute("admin", adminsService.getAdminById(adminId));
        model.addAttribute("adminId",adminId);

        return "updateAdminProfile";
    }

    @PostMapping("/updateAdminProfile")
    public String updateAdminProfile(Model model, HttpServletRequest request) {
        String password = request.getParameter("password");
        String emailId = request.getParameter("emailId");
        Long phoneNumber = Long.parseLong(request.getParameter("phoneNumber"));
        String address = request.getParameter("address");
        adminsService.getAdminById(adminId).updateAdmin(password, phoneNumber, emailId, address);
        adminsService.save(adminsService.getAdminById(adminId));
        model.addAttribute("successMessage", "Profile updated");
        model.addAttribute("admin", adminsService.getAdminById(adminId));
        model.addAttribute("adminId", adminId);
        return "updateAdminProfile";
    }

}
