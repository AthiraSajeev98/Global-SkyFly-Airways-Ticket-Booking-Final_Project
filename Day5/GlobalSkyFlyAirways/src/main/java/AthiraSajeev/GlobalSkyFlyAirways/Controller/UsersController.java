package AthiraSajeev.GlobalSkyFlyAirways.Controller;

import AthiraSajeev.GlobalSkyFlyAirways.Entity.Airlines;
import AthiraSajeev.GlobalSkyFlyAirways.Entity.Flights;
import AthiraSajeev.GlobalSkyFlyAirways.Entity.Tickets;
import AthiraSajeev.GlobalSkyFlyAirways.Entity.Users;
import AthiraSajeev.GlobalSkyFlyAirways.Service.AirlinesService;
import AthiraSajeev.GlobalSkyFlyAirways.Service.FlightsService;
import AthiraSajeev.GlobalSkyFlyAirways.Service.TicketsService;
import AthiraSajeev.GlobalSkyFlyAirways.Service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Controller
public class UsersController {
    @Autowired
    public FlightsService flightsService;

    @Autowired
    public UsersService usersService;

    @Autowired
    public TicketsService ticketsService;

    @Autowired
    public AirlinesService airlinesService;


    //Main home page
    @RequestMapping("/home")
    public String home() {
        return "/home";
    }

    //Main home page
    @RequestMapping("/flights")
    public String flights(Model model) {
        List<Flights> flights = flightsService.getAllFlights();
        ArrayList<Flights> flightList = new ArrayList<Flights>();
        for (Flights flight : flights) {
            if (flight.getCancelStatus().equals(false)) {
                flightList.add(flight);
            }
        }
        model.addAttribute("flightList", flightList);
        return "/flights";
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

    //Search Flights
    @RequestMapping("/searchFlights")
    public String searchFlights(Model model) {
        model.addAttribute("userId", userID);
        return "searchFlights";
    }

    String userID;

    @RequestMapping("/searchFlights/{userId}")
    public String searchFlights(@PathVariable String userId, Model model) {
        userID = userId;
        List<Flights> flights = flightsService.getAllFlights();
        ArrayList<Flights> flightList = new ArrayList<Flights>();
        for (Flights flight : flights) {
            if (flight.getCancelStatus().equals(false)) {
                flightList.add(flight);
            }
        }
        model.addAttribute("flightList", flightList);
        model.addAttribute("userId", userID);
        return "searchFlights";
    }

    //Selecting seat
    @RequestMapping("/selectFlightSeat")
    public String selectFlightSeat(Model model) {
        model.addAttribute("userId", userId);
        model.addAttribute("flightId", flightId);
        model.addAttribute("organization", flightsService.getFlightById(flightId).getAirlines().getOrganization());
        return "selectFlightSeat";
    }

    String flightId;

    @PostMapping("/selectFlightSeat")
    public String selectSeat(Model model, HttpServletRequest request) {
        flightId = request.getParameter("flightId");
        userId = request.getParameter("userId");
        model.addAttribute("userId", userId);
        model.addAttribute("flightId", flightId);
        model.addAttribute("organization", flightsService.getFlightById(flightId).getAirlines().getOrganization());
        return "selectFlightSeat";
    }

    int numberOfSeats = 0;
    String[] seatsBooked = new String[100];
    String[] existingSeat = new String[100];
    String bookedSeats;

    //Book Flight Tickets
    @RequestMapping("/bookFlightTickets")
    public String bookFlightTickets(Model model) {
        model.addAttribute("userId", userId);
        model.addAttribute("flightId", flightId);
        return "bookFlightTickets";
    }

    @PostMapping("/bookFlightTickets")
    public String bookFlightTickets(Model model, HttpServletRequest request) {
        bookedSeats = request.getParameter("bookedSeats");
        seatsBooked = bookedSeats.split(" ");
        numberOfSeats = seatsBooked.length;
        int i = 0;
        List<Tickets> tickets = ticketsService.getAllTickets();
        for (String seatId : seatsBooked) {
            for (Tickets ticket : tickets) {
                if (ticket.getSeatId().equals(seatId) && ticket.getFlights().getFlightId().equals(flightId)) {
                    existingSeat[i] = seatId;
                    i++;
                    System.out.println(existingSeat[i]);
                }
            }
        }


        for (String seatId : seatsBooked) {
            for (Tickets ticket : tickets) {
                if (ticket.getSeatId().equals(seatId) && ticket.getFlights().getFlightId().equals(flightId)) {
                    model.addAttribute("userId", userId);
                    model.addAttribute("flightId", flightId);
                    model.addAttribute("organization", flightsService.getFlightById(flightId).getAirlines().getOrganization());
                    model.addAttribute("existingSeat", existingSeat);
                    model.addAttribute("errorMessage", "Already Booked. Please Select another seat");
                    return "selectFlightSeat";
                }
            }
        }
        model.addAttribute("numberOfSeats", numberOfSeats);
        model.addAttribute("totalAmount", numberOfSeats * flightsService.getFlightById(flightId).getTicketPrice());
        model.addAttribute("name", usersService.getUserById(userId).getName());
        model.addAttribute("seats", seatsBooked);
        model.addAttribute("userId", userId);
        model.addAttribute("flightId", flightId);
        model.addAttribute("airlines", flightsService.getFlightById(flightId).getAirlines().getOrganization());
        return "confirmPayment";
    }

    //Confirm Payment
    @RequestMapping("/confirmPayment")
    public String ConfirmPayment(Model model) {
        model.addAttribute("userId", userId);
        model.addAttribute("flightId", flightId);
        return "confirmPayment";
    }


    @PostMapping("/confirmPayment")
    public String ConfirmPayment(Model model, HttpServletRequest request) {
        Double amount = Double.parseDouble(request.getParameter("amount"));
        Users user = usersService.getUserById(userId);
        List<Tickets> ticketList = new ArrayList<Tickets>();
        Airlines airline = flightsService.getFlightById(flightId).getAirlines();
        if (amount == (numberOfSeats * flightsService.getFlightById(flightId).getTicketPrice())) {
            if (user.getWalletBalance() >= amount) {
                user.setWalletBalance(user.getWalletBalance() - amount);
                usersService.save(user);
                airline.setTransactionAccount(airline.getTransactionAccount() + amount);
                airlinesService.save(airline);

                for (String seatId : seatsBooked) {
                    Tickets tickets = new Tickets(seatId, flightId, userId);
                    Flights flights = flightsService.getFlightById(flightId);

                    int ticketCount = ticketsService.getTicketsByUserIdAndFlightId(userId, flightId).size();
                    tickets.setAvailableTickets(tickets.getAvailableTickets() - ticketCount);

                    tickets.setFlights(flights);
                    tickets.setUsers(user);
                    ticketsService.save(tickets);
                    ticketList.add(ticketsService.getTicketBySeatIdAndFlightId(seatId, flightId));
                }
            }else{
                model.addAttribute("userId", userId);
                model.addAttribute("flightId", flightId);
                model.addAttribute("errorMessage", "Wallet balance is insufficient for making transaction");
                return "confirmPayment";
            }
        }

        Flights flights=flightsService.getFlightById(flightId);
        flights.setTickets(ticketList);
        flightsService.saveFlight(flights);

        model.addAttribute("userId", userId);
        model.addAttribute("flightId", flightId);
        model.addAttribute("tickets", ticketList);
        return "addPassengerDetails";
    }

    //Adding passenger details
    @RequestMapping("/addPassengerDetails")
    public String addPassengerDetails(Model model) {
        model.addAttribute("userId", userId);
        model.addAttribute("flightId", flightId);
        return "addPassengerDetails";
    }

    String seatId;

    @PostMapping("/addPassengerDetails")
    public String addPassengerDetails(Model model, HttpServletRequest request) {
        String userId = request.getParameter("userId");
        seatId = request.getParameter("seatId");
        String flightId = request.getParameter("flightId");

        model.addAttribute("seatId", seatId);
        model.addAttribute("userId", userId);
        model.addAttribute("flightId", flightId);
        return "enterPassengerDetails";
    }

    //Enter passenger details
    @RequestMapping("/enterPassengerDetails")
    public String enterPassengerDetails(Model model) {
        model.addAttribute("userId", userId);
        model.addAttribute("flightId", flightId);
        return "enterPassengerDetails";
    }


    @PostMapping("/enterPassengerDetails")
    public String enterPassengerDetails(Model model, HttpServletRequest request) {
        String passengerName = request.getParameter("passengerName");
        String passportNumber = request.getParameter("passportNumber");
        String nationality = request.getParameter("nationality");

        Tickets tickets = ticketsService.getTicketBySeatIdAndFlightId(seatId, flightId);
        tickets.setPassengerName(passengerName);
        tickets.setPassportNumber(passportNumber);
        tickets.setNationality(nationality);
        ticketsService.save(tickets);

        List<Tickets> ticket = new ArrayList<Tickets>();
        for (String seatId : seatsBooked) {
            if (ticketsService.getTicketsBySeatId(seatId).getPassengerName() == null) {
                ticket.add(ticketsService.getTicketsBySeatId(seatId));

            }
        }
        model.addAttribute("successMessage", "Passenger details updated successfully");
        model.addAttribute("userId", userId);
        model.addAttribute("flightId", flightId);
        model.addAttribute("tickets", ticket);
        return "addPassengerDetails";
    }

    //Previous Bookings
    @RequestMapping("/previousBookings")
    public String previousBookings(Model model) {
        model.addAttribute("userId", userId);
        model.addAttribute("flightId", flightId);
        return "previousBookings";
    }

    @RequestMapping("/previousBookings/{userId}")
    public String previousBookings(@PathVariable String userId, Model model) {
        List<Tickets> ticket = new ArrayList<Tickets>();
        ticket=ticketsService.getTicketsByUserId(userId);

        model.addAttribute("tickets", ticket);
        model.addAttribute("userId", userId);
        model.addAttribute("flightId", flightId);
        return "previousBookings";
    }

}