package AthiraSajeev.GlobalSkyFlyAirways.Controller;

import AthiraSajeev.GlobalSkyFlyAirways.Entity.Admins;
import AthiraSajeev.GlobalSkyFlyAirways.Entity.Airlines;
import AthiraSajeev.GlobalSkyFlyAirways.Entity.Flights;
import AthiraSajeev.GlobalSkyFlyAirways.Entity.Users;
import AthiraSajeev.GlobalSkyFlyAirways.Service.AdminsService;
import AthiraSajeev.GlobalSkyFlyAirways.Service.AirlinesService;
import AthiraSajeev.GlobalSkyFlyAirways.Service.FlightsService;
import AthiraSajeev.GlobalSkyFlyAirways.Service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Controller
public class AdminsController {

    @Autowired
    public AdminsService adminsService;

    @Autowired
    public UsersService usersService;

    @Autowired
    public AirlinesService airlinesService;

    @Autowired
    public FlightsService flightsService;

    private String adminID;
    //Admin registration
    @RequestMapping("/signUpAdmin")
    public String signUpAdmin(Model model) {
        model.addAttribute("adminId", adminID);
        return "signUpAdmin";
    }

    @RequestMapping("/signUpAdmin/{adminId}")
    public String signUpAdmin(@PathVariable String adminId, Model model) {
        adminID=adminId;
        model.addAttribute("adminId", adminID);
        return "signUpAdmin";
    }

    @PostMapping("/signUpAdmin")
    public String signUpAdmin(Model model, HttpServletRequest request) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String organization = request.getParameter("organizationName");
        String employeeId = request.getParameter("employeeId");
        String name = request.getParameter("name");
        String nationality = request.getParameter("nationality");
        Integer age = Integer.parseInt(request.getParameter("age"));
        Long phoneNumber = Long.parseLong(request.getParameter("phoneNumber"));
        String emailId = request.getParameter("emailId");
        String address = request.getParameter("address");

        Admins admin = new Admins(username, password, organization, employeeId,name,nationality,age, phoneNumber, emailId, address);
        admin.setRole("A");
        adminsService.save(admin);

        model.addAttribute("adminId", adminID);
        model.addAttribute("successMessage", "Successfully Registered Admin");
        return "signUpAdmin";
    }



//    //Admin Login
//    @RequestMapping("/signInAdmin")
//    public String signInAdmin() {
//        return "signInAdmin";
//    }

//    @PostMapping("/signInAdmin")
//    public String signInAdmin(Model model, HttpServletRequest request) {
//        String username = request.getParameter("username");
//        String password = request.getParameter("password");
//
//        if (adminsService.existsAdminById(username)) {
//            if (Objects.equals(adminsService.getAdminById(username).getPassword(), password)) {
//                adminId = username;
//
//                if (adminsService.getAdminById(username).getRole().equals("SA")) {
//                    System.out.println();
//                    model.addAttribute("username", adminsService.getAdminById(username).getUsername());
//                    model.addAttribute("organizationName", adminsService.getAdminById(username).getOrganizationName());
//                    model.addAttribute("employeeId", adminsService.getAdminById(username).getEmployeeId());
//                    model.addAttribute("name", adminsService.getAdminById(username).getName());
//                    model.addAttribute("age", adminsService.getAdminById(username).getAge());
//                    model.addAttribute("gender", adminsService.getAdminById(username).getGender());
//                    model.addAttribute("emailId", adminsService.getAdminById(username).getEmailId());
//                    model.addAttribute("phoneNumber", adminsService.getAdminById(username).getPhoneNumber());
//                    model.addAttribute("address", adminsService.getAdminById(username).getAddress());
//                    model.addAttribute("adminId", adminId);
//
//                    return "superAdminProfile";
//                }
//                if (adminsService.getAdminById(username).getRole().equals("A")) {
//                    model.addAttribute("username", adminsService.getAdminById(username).getUsername());
//                    model.addAttribute("organizationName", adminsService.getAdminById(username).getOrganizationName());
//                    model.addAttribute("employeeId", adminsService.getAdminById(username).getEmployeeId());
//                    model.addAttribute("name", adminsService.getAdminById(username).getName());
//                    model.addAttribute("age", adminsService.getAdminById(username).getAge());
//                    model.addAttribute("gender", adminsService.getAdminById(username).getGender());
//                    model.addAttribute("emailId", adminsService.getAdminById(username).getEmailId());
//                    model.addAttribute("phoneNumber", adminsService.getAdminById(username).getPhoneNumber());
//                    model.addAttribute("address", adminsService.getAdminById(username).getAddress());
//                    model.addAttribute("adminId", adminId);
//
//                    return "adminProfile";
//                }
//
//            } else {
//                model.addAttribute("errorMessage", "Invalid credentials");
//                return "signInAdmin";
//            }
//        } else {
//            model.addAttribute("errorMessage", "Invalid credentials");
//            return "signInAdmin";
//        }
//        return "";
//    }


    //superAdminProfile
    @RequestMapping("/superAdminProfile")
    public String superAdminProfile(Model model) {
        model.addAttribute("adminId", adminID);
        return "superAdminProfile";
    }

    @RequestMapping("/superAdminProfile/{adminId}")
    public String superAdminProfile(@PathVariable String adminId, Model model) {
        adminID=adminId;
        model.addAttribute("username", adminsService.getAdminById(adminID).getUsername());
        model.addAttribute("organizationName", adminsService.getAdminById(adminID).getOrganizationName());
        model.addAttribute("employeeId", adminsService.getAdminById(adminID).getEmployeeId());
        model.addAttribute("name", adminsService.getAdminById(adminID).getName());
        model.addAttribute("age", adminsService.getAdminById(adminID).getAge());
        model.addAttribute("nationality", adminsService.getAdminById(adminID).getNationality());
        model.addAttribute("emailId", adminsService.getAdminById(adminID).getEmailId());
        model.addAttribute("phoneNumber", adminsService.getAdminById(adminID).getPhoneNumber());
        model.addAttribute("address", adminsService.getAdminById(adminID).getAddress());
        model.addAttribute("adminId", adminID);
        return "superAdminProfile";
    }


    //Admin profile
    @RequestMapping("/adminProfile")
    public String adminProfile(Model model) {
        model.addAttribute("adminId", adminID);
        return "adminProfile";
    }

    @RequestMapping("/adminProfile/{adminId}")
    public String adminProfile(@PathVariable String adminId, Model model) {
        adminID=adminId;
        model.addAttribute("username", adminsService.getAdminById(adminID).getUsername());
        model.addAttribute("organizationName", adminsService.getAdminById(adminID).getOrganizationName());
        model.addAttribute("employeeId", adminsService.getAdminById(adminID).getEmployeeId());
        model.addAttribute("name", adminsService.getAdminById(adminID).getName());
        model.addAttribute("age", adminsService.getAdminById(adminID).getAge());
        model.addAttribute("nationality", adminsService.getAdminById(adminID).getNationality());
        model.addAttribute("emailId", adminsService.getAdminById(adminID).getEmailId());
        model.addAttribute("phoneNumber", adminsService.getAdminById(adminID).getPhoneNumber());
        model.addAttribute("address", adminsService.getAdminById(adminID).getAddress());
        model.addAttribute("adminId", adminID);
        return "adminProfile";
    }

    //Update profile
    @RequestMapping("/updateAdminProfile")
    public String updateAdminProfile(Model model) {
        model.addAttribute("admin", adminsService.getAdminById(adminID));
        model.addAttribute("adminId", adminID);

        return "updateAdminProfile";
    }

    @RequestMapping("/updateAdminProfile/{adminId}")
    public String updateAdminProfile(@PathVariable String adminId, Model model) {
        adminID=adminId;
        model.addAttribute("admin", adminsService.getAdminById(adminID));
        model.addAttribute("adminId", adminID);

        return "updateAdminProfile";
    }

    @PostMapping("/updateAdminProfile")
    public String updateAdminProfile(Model model, HttpServletRequest request) {
        String password = request.getParameter("password");
        String emailId = request.getParameter("emailId");
        Long phoneNumber = Long.parseLong(request.getParameter("phoneNumber"));
        String address = request.getParameter("address");
        adminsService.getAdminById(adminID).updateAdmin(password, phoneNumber, emailId, address);
        adminsService.save(adminsService.getAdminById(adminID));
        model.addAttribute("successMessage", "Profile updated");
        model.addAttribute("admin", adminsService.getAdminById(adminID));
        model.addAttribute("adminId", adminID);
        return "updateAdminProfile";
    }


    //Users Details
    @RequestMapping("/usersDetails")
    public String usersDetails(Model model) {
        List<Users> user = usersService.getAllUsers();
        model.addAttribute("userList", user);
        model.addAttribute("admin", adminsService.getAdminById(adminID));
        model.addAttribute("adminId", adminID);
        return "usersDetails";
    }

    @RequestMapping("/usersDetails/{adminId}")
    public String usersDetails(@PathVariable String adminId, Model model) {
        adminID=adminId;
        List<Users> user = usersService.getAllUsers();
        model.addAttribute("userList", user);
        model.addAttribute("admin", adminsService.getAdminById(adminID));
        model.addAttribute("adminId", adminID);
        return "usersDetails";
    }

    //Admins Details
    @RequestMapping("/adminsDetails")
    public String adminsDetails(Model model) {
        List<Admins> admin = adminsService.getAllAdmins();
        model.addAttribute("adminList", admin);
        model.addAttribute("admin", adminsService.getAdminById(adminID));
        model.addAttribute("adminId", adminID);
        return "adminsDetails";
    }


    @RequestMapping("/adminsDetails/{adminId}")
    public String adminsDetails(@PathVariable String adminId, Model model) {
        adminID=adminId;
        List<Admins> admin = adminsService.getAllAdmins();
        model.addAttribute("adminList", admin);
        model.addAttribute("admin", adminsService.getAdminById(adminID));
        model.addAttribute("adminId", adminID);
        return "adminsDetails";
    }

    //Airlines Details
    @RequestMapping("/airlinesDetails")
    public String airlinesDetails(Model model) {
        List<Airlines> airlines = airlinesService.getAllAirlines();
        model.addAttribute("airlineList", airlines);
        model.addAttribute("admin", adminsService.getAdminById(adminID));
        model.addAttribute("adminId", adminID);
        return "adminsDetails";
    }

    @RequestMapping("/airlinesDetails/{adminId}")
    public String airlinesDetails(@PathVariable String adminId, Model model) {
        adminID=adminId;
        List<Airlines> airlines = airlinesService.getAllAirlines();
        List<Flights> flights = flightsService.getAllFlights();

//        List<String> flightList = new ArrayList<String>();
//        for (Airlines airlineInstance : airlines) {
//            for (Flights flightInstance : flights) {
//                if (flightInstance.getAirlines().equals(airlineInstance)) {
//                    flightList.add(flightInstance.getFlightId());
//                }
//            }
//        }
//        model.addAttribute("flightList", flightList);
        model.addAttribute("airlineList", airlines);
        model.addAttribute("admin", adminsService.getAdminById(adminID));
        model.addAttribute("adminId", adminID);
        return "airlinesDetails";
    }
//Remove users
    @RequestMapping("/removeUser")
    public String removeUser(Model model) {
        model.addAttribute("adminId", adminID);
        return "removeUser";
    }

    @RequestMapping("/removeUser/{adminId}")
    public String removeUser(@PathVariable String adminId, Model model) {
        model.addAttribute("adminId", adminId);
        return "removeUser";
    }

    @PostMapping("/removeUser")
    public String removeUser(HttpServletRequest request, Model model) {
        String userIdToRemove = request.getParameter("id");

        Users user = usersService.getUserById(userIdToRemove);

        usersService.removeUser(user);

        model.addAttribute("successMessage", "Successfully removed User");
        model.addAttribute("adminId", adminID);
        return "removeUser";
    }

    //Remove Admin
    @RequestMapping("/removeAdmin")
    public String removeAdmin(Model model) {
        model.addAttribute("adminId", adminID);
        return "removeAdmin";
    }

    @RequestMapping("/removeAdmin/{adminId}")
    public String removeAdmin(@PathVariable String adminId, Model model) {
        model.addAttribute("adminId", adminId);
        return "removeAdmin";
    }

    @PostMapping("/removeAdmin")
    public String removeAdmin(HttpServletRequest request, Model model) {
        String userIdToRemove = request.getParameter("id");

        Admins admin = adminsService.getAdminById(userIdToRemove);

        adminsService.removeAdmin(admin);

        model.addAttribute("successMessage", "Successfully removed Admin");
        model.addAttribute("adminId", adminID);
        return "removeAdmin";
    }

}
