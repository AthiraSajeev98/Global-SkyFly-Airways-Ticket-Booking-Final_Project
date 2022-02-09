package AthiraSajeev.GlobalSkyFlyAirways.Controller;

import AthiraSajeev.GlobalSkyFlyAirways.Entity.Airlines;
import AthiraSajeev.GlobalSkyFlyAirways.Service.AirlinesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

@Controller
public class AirlinesController {
    @Autowired
    public AirlinesService airlinesService;

    //Airline Login
    @RequestMapping("/signInAirline")
    public String signInAirline() {
        return "/signInAirline";
    }

    String airlineID;
    String adminID;

    @PostMapping("/signInAirline")
    public String signInAirline(Model model, HttpServletRequest request) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if (airlinesService.existsAirlineById(username)) {
            if (Objects.equals(airlinesService.getAirlineById(username).getPassword(), password)) {

                airlineID = username;
                model.addAttribute("airlineId", airlineID);
                model.addAttribute("username", airlinesService.getAirlineById(username).getUsername());
                model.addAttribute("organization", airlinesService.getAirlineById(username).getOrganization());
                model.addAttribute("airlineID", airlineID);
                return "airlineProfile";

            } else {
                model.addAttribute("errorMessage", "Invalid credentials");
                return "signInAirline";
            }
        } else {
            model.addAttribute("errorMessage", "Invalid credentials");
            return "signInAirline";
        }
    }

    //Airline profile
    @RequestMapping("/airlineProfile")
    public String profile(Model model) {
        model.addAttribute("airlineID", airlineID);
        return "/airlineProfile";
    }

    @RequestMapping("/airlineProfile/{airlineId}")
    public String profile(Model model, @PathVariable String airlineId) {
        model.addAttribute("username", airlinesService.getAirlineById(airlineId).getUsername());
        model.addAttribute("organization", airlinesService.getAirlineById(airlineId).getOrganization());
        model.addAttribute("airlineId", airlineId);
        return "/airlineProfile";
    }

    //Airline Register
    @RequestMapping("/signUpAirline")
    public String signUpAirline(Model model) {
        model.addAttribute("adminId", adminID);
        model.addAttribute("airlineID", airlineID);
        return "/signUpAirline";
    }

    @RequestMapping("/signUpAirline/{adminId}")
    public String signUpAirline(Model model, @PathVariable String adminId) {
        adminID = adminId;
        model.addAttribute("adminId", adminID);
        model.addAttribute("airlineID", airlineID);
        return "/signUpAirline";
    }

    @PostMapping("/signUpAirline")
    public String signUpAirline(Model model, HttpServletRequest request) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String organization = request.getParameter("organization");

        Airlines airline = new Airlines(username, password, organization);
        airlinesService.save(airline);
        model.addAttribute("adminId", adminID);
        model.addAttribute("airlineId", airlineID);
        model.addAttribute("successMessage", "Successfully Registered Airline");
        return "signUpAirline";
    }

    //Update profile
    String idAirline;
    @RequestMapping("/changeAirlinePassword")
    public String updateUserProfile(Model model) {
        model.addAttribute("airline", airlinesService.getAirlineById(idAirline));
        model.addAttribute("airlineId", idAirline);
        return "changeAirlinePassword";
    }

    @RequestMapping("/changeAirlinePassword/{airlineId}")
    public String updateUserProfileById(@PathVariable String airlineId, Model model) {
        idAirline=airlineId;
        model.addAttribute("airline", airlinesService.getAirlineById(idAirline));
        model.addAttribute("airlineId", idAirline);
        return "changeAirlinePassword";
    }

    @PostMapping("/changeAirlinePassword")
    public String updateUserProfile(Model model, HttpServletRequest request) {
        String password = request.getParameter("password");
        airlinesService.getAirlineById(idAirline).setPassword(password);
        airlinesService.save(airlinesService.getAirlineById(idAirline));
        model.addAttribute("successMessage", "Password updated");
        model.addAttribute("airline", airlinesService.getAirlineById(idAirline));
        model.addAttribute("airlineId", idAirline);
        return "changeAirlinePassword";
    }

    //Airline Account
    @RequestMapping("/airlineAccount")
    public String userBalance(Model model) {
        model.addAttribute("airline", airlinesService.getAirlineById(airlineID));
        model.addAttribute("airlineId", airlineID);
        return "airlineAccount";
    }

    @RequestMapping("/airlineAccount/{airlineId}")
    public String userBalance(@PathVariable String airlineId, Model model) {
        model.addAttribute("airline", airlinesService.getAirlineById(airlineId));
        model.addAttribute("airlineId", airlineId);
        return "airlineAccount";
    }


}

