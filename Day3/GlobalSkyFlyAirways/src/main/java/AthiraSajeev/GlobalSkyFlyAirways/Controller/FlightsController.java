package AthiraSajeev.GlobalSkyFlyAirways.Controller;

import AthiraSajeev.GlobalSkyFlyAirways.Entity.Airlines;
import AthiraSajeev.GlobalSkyFlyAirways.Entity.Flights;
import AthiraSajeev.GlobalSkyFlyAirways.Service.AirlinesService;
import AthiraSajeev.GlobalSkyFlyAirways.Service.FlightsService;
import org.apache.logging.log4j.message.StringFormattedMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.sql.Date;
import java.sql.Time;

@Controller
public class FlightsController {
    @Autowired
    public FlightsService flightsService;

    @Autowired
    public AirlinesService airlinesService;

    public String airlineID;

    //Scheduling Flight
    @RequestMapping("/scheduleFlight/{airlineId}")
    public String scheduleFlight(@PathVariable String airlineId,Model model) {
        model.addAttribute("airlineId",airlineId);
        airlineID=airlinesService.getAirlineById(airlineId).getUsername();
        return "scheduleFlight";
    }

    @RequestMapping("/scheduleFlight")
    public String scheduleFlight(Model model){
        model.addAttribute("airlineId",airlineID);
        return "scheduleFlight";
    }
    String flightID;
    @PostMapping("/scheduleFlight")
    public String scheduleFlight(Model model, HttpServletRequest request) {
        String flightId = request.getParameter("flightId");
        Integer gate = Integer.parseInt(request.getParameter("gate"));
        String origin = request.getParameter("origin");
        Time departureTime = Time.valueOf(request.getParameter("departureTime") + ":00");
        Date departureDate = Date.valueOf(request.getParameter("departureDate"));
        String destination = request.getParameter("destination");
        Time arrivalTime = Time.valueOf(request.getParameter("arrivalTime") + ":00");
        Date arrivalDate = Date.valueOf(request.getParameter("arrivalDate"));
        String duration = request.getParameter("duration");
        Double ticketPrice = Double.parseDouble(request.getParameter("ticketPrice"));

        Flights flight = new Flights(flightId,ticketPrice,gate,origin,departureDate,departureTime,destination,arrivalDate,arrivalTime,duration);
        flightsService.saveFlight(flight);
        Airlines airline=airlinesService.getAirlineById(airlineID);
        flight.setAirlines(airline);
        flightsService.saveFlight(flight);

        model.addAttribute("airlineId",airlineID);
        model.addAttribute("successMessage","Flight Schedule Registered Successfully");
        return "scheduleFlight";
    }


}
