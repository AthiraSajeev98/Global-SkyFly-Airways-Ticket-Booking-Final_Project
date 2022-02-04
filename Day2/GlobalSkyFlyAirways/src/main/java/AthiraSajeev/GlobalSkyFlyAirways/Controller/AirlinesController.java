package AthiraSajeev.GlobalSkyFlyAirways.Controller;

import AthiraSajeev.GlobalSkyFlyAirways.Service.AirlinesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

@Controller
public class AirlinesController {
    @Autowired
    public AirlinesService airlinesService;

    //User Login
    @RequestMapping("/signInAirline")
    public String signInAirline() {
        return "/signIn";
    }

    private String adminId;

    @PostMapping("/signInAirline")
    public String signInAirline(Model model, HttpServletRequest request) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if (airlinesService.existsAirlineById(username)) {
            if (Objects.equals(airlinesService.getAirlineById(username).getPassword(), password)) {
                adminId = airlinesService.getAirlineById(username).getUsername();
//                model.addAttribute("airlineId", airlinesService.getAirlineById(username).getUsername());
//                model.addAttribute("name", airlinesService.getAirlineById(username).getName());
//                model.addAttribute("age", airlinesService.getAirlineById(username).getAge());
//                model.addAttribute("gender", airlinesService.getAirlineById(username).getGender());
//                model.addAttribute("emailId", airlinesService.getAirlineById(username).getEmailId());
//                model.addAttribute("phoneNumber", airlinesService.getAirlineById(username).getPhoneNumber());
//                model.addAttribute("address", airlinesService.getAirlineById(username).getAddress());

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
    public String profile() {
        return "/airlineProfile";
    }

}
