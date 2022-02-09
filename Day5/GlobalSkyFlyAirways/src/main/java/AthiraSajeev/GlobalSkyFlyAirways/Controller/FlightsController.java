package AthiraSajeev.GlobalSkyFlyAirways.Controller;

import AthiraSajeev.GlobalSkyFlyAirways.Entity.Airlines;
import AthiraSajeev.GlobalSkyFlyAirways.Entity.Flights;
import AthiraSajeev.GlobalSkyFlyAirways.Entity.Tickets;
import AthiraSajeev.GlobalSkyFlyAirways.Entity.Users;
import AthiraSajeev.GlobalSkyFlyAirways.Repository.TicketsRepository;
import AthiraSajeev.GlobalSkyFlyAirways.Service.AirlinesService;
import AthiraSajeev.GlobalSkyFlyAirways.Service.FlightsService;
import AthiraSajeev.GlobalSkyFlyAirways.Service.TicketsService;
import AthiraSajeev.GlobalSkyFlyAirways.Service.UsersService;
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
import java.util.ArrayList;
import java.util.List;

@Controller
public class FlightsController {
    @Autowired
    public FlightsService flightsService;

    @Autowired
    public AirlinesService airlinesService;

    @Autowired
    public TicketsService ticketsService;

    @Autowired
    public UsersService usersService;

    public String airlineID;

    //Scheduling Flight
    @RequestMapping("/scheduleFlight/{airlineId}")
    public String scheduleFlight(@PathVariable String airlineId, Model model) {
        model.addAttribute("airlineId", airlineId);
        airlineID = airlinesService.getAirlineById(airlineId).getUsername();
        return "scheduleFlight";
    }

    @RequestMapping("/scheduleFlight")
    public String scheduleFlight(Model model) {
        model.addAttribute("airlineId", airlineID);
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

        Flights flight = new Flights(flightId, ticketPrice, gate, origin, departureDate, departureTime, destination, arrivalDate, arrivalTime, duration);
        flightsService.saveFlight(flight);
        Airlines airline = airlinesService.getAirlineById(airlineID);
        flight.setAirlines(airline);
        flightsService.saveFlight(flight);

        model.addAttribute("airlineId", airlineID);
        model.addAttribute("successMessage", "Flight Schedule Registered Successfully");
        return "scheduleFlight";
    }

    //Flight schedule
    @RequestMapping("/fixedFlightSchedule")
    public String userBalance(Model model) {
        model.addAttribute("airline", airlinesService.getAirlineById(airlineID));
        model.addAttribute("airlineId", airlineID);
        model.addAttribute("flightId", flightID);
        return "fixedFlightSchedule";
    }

    @RequestMapping("/fixedFlightSchedule/{airlineId}")
    public String userBalance(@PathVariable String airlineId, Model model) {
        List<Flights> flights = flightsService.getAllFlights();
        ArrayList<Flights> flightList = new ArrayList<Flights>();
        for (Flights flight : flights) {
            if (flight.getAirlines().getUsername().contains(airlineId) && flight.getCancelStatus().equals(false)) {
                flightList.add(flight);
            }
        }
        model.addAttribute("airline", airlinesService.getAirlineById(airlineId));
        model.addAttribute("airlineId", airlineId);
        model.addAttribute("flightList", flightList);
        model.addAttribute("flightId", flightID);
        return "fixedFlightSchedule";
    }

    String cancelledFlightId;

    @PostMapping("/fixedFlightSchedule")
    public String fixedFlightSchedule(Model model, HttpServletRequest request) {
        cancelledFlightId = request.getParameter("flightId");
        model.addAttribute("airline", flightsService.getFlightById(cancelledFlightId).getAirlines());
        model.addAttribute("airlineId", flightsService.getFlightById(cancelledFlightId).getAirlines().getUsername());
        model.addAttribute("flightId", cancelledFlightId);
        model.addAttribute("flight", flightsService.getFlightById(cancelledFlightId));
        return "flightCancelledReason";
    }

    //Flight Cancelled Reason
    @RequestMapping("/flightCancelledReason")
    public String flightCancelledReason(Model model) {
        model.addAttribute("airline", flightsService.getFlightById(cancelledFlightId).getAirlines());
        model.addAttribute("airlineId", flightsService.getFlightById(cancelledFlightId).getAirlines().getUsername());
        model.addAttribute("flightId", cancelledFlightId);
        model.addAttribute("flight", flightsService.getFlightById(cancelledFlightId));

        return "flightCancelledReason";
    }

    @PostMapping("/flightCancelledReason")
    public String flightCancelledReason(Model model, HttpServletRequest request) {
        String reason = request.getParameter("reason");
        String flightId = request.getParameter("flightId");
        Flights flight = flightsService.getFlightById(flightId);
        flight.setCancelStatus(true);
        flight.setCancelReason(reason);
        flight.setStatus("Cancelled");
        flightsService.saveFlight(flight);

        List<Flights> flights = flightsService.getAllFlights();
        ArrayList<Flights> flightList = new ArrayList<Flights>();
        for (Flights flightInstance : flights) {
            if (flightInstance.getAirlines().getUsername().contains(flightInstance.getAirlines().getUsername()) && flightInstance.getCancelStatus()) {
                flightList.add(flightInstance);
            }
        }

        model.addAttribute("airline", flight.getAirlines());
        model.addAttribute("airlineId", flight.getAirlines().getUsername());
        model.addAttribute("flightId", flight.getFlightId());
        model.addAttribute("flightList", flightList);
        model.addAttribute("successMessage", "Flight Cancelled");
        return "cancelledFlights";
    }


    //Cancelled Flight
    @RequestMapping("/cancelledFlights")
    public String cancelledFlights(Model model) {
        model.addAttribute("airline", airlinesService.getAirlineById(airlineID));
        model.addAttribute("airlineId", airlineID);
        model.addAttribute("flightId", flightID);
        return "cancelledFlights";
    }

    @RequestMapping("/cancelledFlights/{airlineId}")
    public String cancelledFlights(@PathVariable String airlineId, Model model) {
        List<Flights> flights = flightsService.getAllFlights();
        ArrayList<Flights> flightList = new ArrayList<Flights>();
        for (Flights flight : flights) {
            if (flight.getAirlines().getUsername().contains(airlineId) && flight.getCancelStatus()) {
                flightList.add(flight);
            }
        }
        model.addAttribute("airline", airlinesService.getAirlineById(airlineId));
        model.addAttribute("airlineId", airlineId);
        model.addAttribute("flightList", flightList);
        model.addAttribute("flightId", flightID);
        return "cancelledFlights";
    }

    @PostMapping("/cancelledFlights")
    public String cancelledFlights(Model model, HttpServletRequest request) {
        String flightId = request.getParameter("flightId");
        model.addAttribute("airline", airlinesService.getAirlineById(airlineID));
        model.addAttribute("airlineId", airlineID);
        model.addAttribute("flightId", flightId);
        return "cancelledFlights";
    }

    //refunding user when flight is cancelled
    @RequestMapping("/airlineRefund")
    public String airlineRefund(Model model) {
        model.addAttribute("airline", airlinesService.getAirlineById(airlineID));
        model.addAttribute("airlineId", airlineID);
        return "airlineRefund";
    }

    @RequestMapping("/airlineRefund/{airlineId}")
    public String airlineRefund(@PathVariable String airlineId, Model model) {
        List<Flights> flights = flightsService.getAllFlights();
        ArrayList<Tickets> cancelledTicketList = new ArrayList<Tickets>();
        for (Flights flight : flights) {
            if (flight.getAirlines().getUsername().contains(airlineId) && flight.getCancelStatus()) {
                List<Tickets> tickets = ticketsService.getTicketsByFlightId(flight.getFlightId());
                for (Tickets ticket : tickets) {
                    if (!ticket.getRepaymentStatus()) {
                        cancelledTicketList.add(ticket);
                    }
                }
            }
        }
        model.addAttribute("cancelledTicketList", cancelledTicketList);
        model.addAttribute("airline", airlinesService.getAirlineById(airlineId));
        model.addAttribute("airlineId", airlineId);
        model.addAttribute("organization", airlinesService.getAirlineById(airlineId).getOrganization());
        return "airlineRefund";

    }

    @PostMapping("/airlineRefund")
    public String airlineRefund(Model model, HttpServletRequest request) {
        String userId = request.getParameter("userId");
        String flightId = request.getParameter("flightId");
        String seatId = request.getParameter("seatId");

        Users user = usersService.getUserById(userId);
        Flights flight = flightsService.getFlightById(flightId);
        Airlines airlines = flight.getAirlines();
        String  airlineId= flight.getAirlines().getUsername();

        Double refundAmount = flight.getTicketPrice();

        airlines.setTransactionAccount(airlines.getTransactionAccount() - refundAmount);
        airlinesService.save(airlines);
        user.setWalletBalance(user.getWalletBalance() + refundAmount);
        usersService.save(user);
        Tickets ticket = ticketsService.getTicketBySeatIdAndFlightId(seatId,flightId);
        ticket.setRepaymentStatus(true);
        ticketsService.save(ticket);


        List<Flights> flightsList = flightsService.getAllFlights();
        ArrayList<Tickets> cancelledTicketList = new ArrayList<Tickets>();
        for (Flights flightInstance : flightsList) {
            if (flightInstance.getAirlines().getUsername().contains(airlineId) && flightInstance.getCancelStatus()) {
                List<Tickets> ticketsList = ticketsService.getTicketsByFlightId(flightInstance.getFlightId());
                for (Tickets ticketsInstance : ticketsList) {
                    if (ticketsInstance.getRepaymentStatus().equals(false)) {
                        cancelledTicketList.add(ticketsInstance);
                    }
                }
            }
        }
        model.addAttribute("cancelledTicketList", cancelledTicketList);
        model.addAttribute("airlineId", airlineId);
        model.addAttribute("organization", airlinesService.getAirlineById(airlineId).getOrganization());
        model.addAttribute("successMessage", "Refund Successful");
        return "airlineRefund";
    }

}

