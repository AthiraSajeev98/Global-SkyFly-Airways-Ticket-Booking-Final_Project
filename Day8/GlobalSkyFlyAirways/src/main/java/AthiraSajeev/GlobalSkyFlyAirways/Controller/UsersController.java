package AthiraSajeev.GlobalSkyFlyAirways.Controller;

import AthiraSajeev.GlobalSkyFlyAirways.Entity.Airlines;
import AthiraSajeev.GlobalSkyFlyAirways.Entity.Flights;
import AthiraSajeev.GlobalSkyFlyAirways.Entity.Tickets;
import AthiraSajeev.GlobalSkyFlyAirways.Entity.Users;
import AthiraSajeev.GlobalSkyFlyAirways.Service.*;
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
public class UsersController {
    @Autowired
    public FlightsService flightsService;

    @Autowired
    public UsersService usersService;

    @Autowired
    public TicketsService ticketsService;

    @Autowired
    public AirlinesService airlinesService;

    @Autowired
    public AdminsService adminsService;


    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/login")
    public String login(Model model, HttpServletRequest request) {
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
                return "home";

            } else {
                model.addAttribute("errorMessage", "Invalid credentials");
                return "login";
            }
        } else {
            model.addAttribute("errorMessage", "Invalid credentials");
            return "login";
        }
    }

    //Main home page
    @RequestMapping("/")
    public String home() {
        return "home";
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
        return "flights";
    }

    //User registration
    @RequestMapping("/signUp")
    public String signUp() {
        return "signUp";
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
        return "signIn";
    }

    String userId, adminId, airlineId;

    @PostMapping("/signIn")
    public String signIn(Model model, HttpServletRequest request) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if (usersService.existsUserById(username) && Objects.equals(usersService.getUserById(username).getPassword(), password)) {
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
        } else if (adminsService.existsAdminById(username) &&
                Objects.equals(adminsService.getAdminById(username).getPassword(), password)) {
            adminId = username;

            if (adminsService.getAdminById(username).getRole().equals("SA")) {
                System.out.println();
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

                return "superAdminProfile";
            }
            if (adminsService.getAdminById(username).getRole().equals("A")) {
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
            }
        }
        if (airlinesService.existsAirlineById(username) &&
        (Objects.equals(airlinesService.getAirlineById(username).getPassword(), password))) {

            airlineId = username;
            model.addAttribute("airlineId", airlineId);
            model.addAttribute("username", airlinesService.getAirlineById(username).getUsername());
            model.addAttribute("organization", airlinesService.getAirlineById(username).getOrganization());
            model.addAttribute("airlineID", airlineId);
            return "airlineProfile";
        } else{
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
        int j = 0;
        String[] allExistingSeats = new String[90];
        List<Tickets> allTickets = ticketsService.getTicketsByFlightId(flightId);
        for (Tickets ticket : allTickets) {
            allExistingSeats[j] = ticket.getSeatId();
            j++;
        }
        model.addAttribute("allExistingSeats", allExistingSeats);
        model.addAttribute("flightList", flightList);
        model.addAttribute("userId", userID);
        return "searchFlights";
    }

    //Selecting seat
    @RequestMapping("/selectFlightSeat")
    public String selectFlightSeat(Model model) {
        int j = 0;
        String[] allExistingSeats = new String[90];
        List<Tickets> allTickets = ticketsService.getTicketsByFlightId(flightId);
        for (Tickets ticket : allTickets) {
            allExistingSeats[j] = ticket.getSeatId();
            j++;
        }
        model.addAttribute("allExistingSeats", allExistingSeats);
        model.addAttribute("userId", userId);
        model.addAttribute("flightId", flightId);
        model.addAttribute("organization", flightsService.getFlightById(flightId).getAirlines().getOrganization());
        return "selectFlightSeat";
    }

    String flightId;

    @PostMapping("/selectFlightSeat")
    public String selectSeat(Model model, HttpServletRequest request) {

        int j = 0;
        String[] allExistingSeats = new String[90];
        List<Tickets> allTickets = ticketsService.getTicketsByFlightId(flightId);
        for (Tickets ticket : allTickets) {
            allExistingSeats[j] = ticket.getSeatId();
            j++;
        }
        model.addAttribute("allExistingSeats", allExistingSeats);
        flightId = request.getParameter("flightId");
        userId = request.getParameter("userId");
        model.addAttribute("userId", userId);
        model.addAttribute("flightId", flightId);
        model.addAttribute("organization", flightsService.getFlightById(flightId).getAirlines().getOrganization());
        return "selectFlightSeat";
    }

    int numberOfSeats = 0;
    String[] seatsBooked = new String[90];
    String[] existingSeat = new String[90];
    String bookedSeats;

    //Book Flight Tickets
    @RequestMapping("/bookFlightTickets")
    public String bookFlightTickets(Model model) {

        int j = 0;
        String[] allExistingSeats = new String[90];
        List<Tickets> allTickets = ticketsService.getTicketsByFlightId(flightId);
        for (Tickets ticket : allTickets) {
            allExistingSeats[j] = ticket.getSeatId();
            j++;
        }
        model.addAttribute("allExistingSeats", allExistingSeats);
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

        int j = 0;
        String[] allExistingSeats = new String[90];
        List<Tickets> allTickets = ticketsService.getTicketsByFlightId(flightId);
        for (Tickets ticket : allTickets) {
            allExistingSeats[j] = ticket.getSeatId();
            j++;
        }


        for (String seatId : seatsBooked) {
            for (Tickets ticket : tickets) {
                if (ticket.getSeatId().equals(seatId) && ticket.getFlights().getFlightId().equals(flightId)) {
                    model.addAttribute("userId", userId);
                    model.addAttribute("flightId", flightId);
                    model.addAttribute("organization", flightsService.getFlightById(flightId).getAirlines().getOrganization());
                    model.addAttribute("existingSeat", existingSeat);
                    model.addAttribute("allExistingSeats", allExistingSeats);
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
                    tickets.setTicketStatus("Active");
                    tickets.setFlights(flights);
                    tickets.setUsers(user);
                    ticketsService.save(tickets);
                    ticketList.add(ticketsService.getTicketBySeatIdAndFlightId(seatId, flightId));
                }
            } else {
                model.addAttribute("userId", userId);
                model.addAttribute("flightId", flightId);
                model.addAttribute("amount",amount);
                model.addAttribute("errorMessage", "Wallet balance is insufficient, please add wallet balance and try again");
                return "addUserWalletBalance";
            }
        }

        Flights flights = flightsService.getFlightById(flightId);
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


        model.addAttribute("successMessage", "Passenger details updated successfully");
        model.addAttribute("userId", userId);
        model.addAttribute("flightId", flightId);

        return "previousBookings";
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
        ticket = ticketsService.getTicketsByUserId(userId);
        model.addAttribute("tickets", ticket);
        model.addAttribute("userId", userId);
        model.addAttribute("flightId", flightId);
        return "previousBookings";
    }

    @RequestMapping("/cancelBooking/{userId}")
    public String cancelBooking(@PathVariable String userId, Model model) {
        List<Tickets> ticket = new ArrayList<Tickets>();
        ticket = ticketsService.getTicketsByUserId(userId);
        List<Tickets> activeFlightTickets = new ArrayList<Tickets>();
        for(Tickets ticketInstance: ticket){
            if(ticketInstance.getFlights().getStatus().equals("Active") && ticketInstance.getTicketStatus().equals("Active")){
                activeFlightTickets.add(ticketInstance);
            }
        }

        model.addAttribute("tickets", activeFlightTickets);
        model.addAttribute("userId", userId);
        model.addAttribute("flightId", flightId);
        return "cancelBooking";
    }

    @PostMapping("/cancelBooking")
    public String cancelBooking(Model model,HttpServletRequest request) {
        String userId = request.getParameter("userId");
        String flightId = request.getParameter("flightId");
        String seatId = request.getParameter("seatId");


        Tickets getTicket=ticketsService.getTicketBySeatIdAndFlightId(seatId,flightId);
        getTicket.setTicketStatus("Cancelled");
        ticketsService.save(getTicket);


        List<Tickets> ticket = new ArrayList<Tickets>();
        ticket = ticketsService.getTicketsByUserId(userId);
        List<Tickets> activeFlightTickets = new ArrayList<Tickets>();
        for(Tickets ticketInstance: ticket){
            if(ticketInstance.getFlights().getStatus().equals("Active") && ticketInstance.getTicketStatus().equals("Active")){
                activeFlightTickets.add(ticketInstance);
            }
        }

        model.addAttribute("tickets", activeFlightTickets);
        model.addAttribute("userId", userId);
        model.addAttribute("flightId", flightId);
        model.addAttribute("successMessage", "Cancellation successful");
        return "cancelBooking";
    }

}