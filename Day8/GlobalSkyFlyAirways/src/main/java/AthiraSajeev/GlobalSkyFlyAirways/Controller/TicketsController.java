package AthiraSajeev.GlobalSkyFlyAirways.Controller;

import AthiraSajeev.GlobalSkyFlyAirways.Entity.Flights;
import AthiraSajeev.GlobalSkyFlyAirways.Service.FlightsService;
import AthiraSajeev.GlobalSkyFlyAirways.Service.TicketsService;
import AthiraSajeev.GlobalSkyFlyAirways.Service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

public class TicketsController {
    @Autowired
    public FlightsService flightsService;

    @Autowired
    public UsersService usersService;

    @Autowired
    public TicketsService ticketsService;



}
