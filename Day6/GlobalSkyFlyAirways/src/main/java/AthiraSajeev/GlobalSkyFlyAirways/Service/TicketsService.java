package AthiraSajeev.GlobalSkyFlyAirways.Service;

import AthiraSajeev.GlobalSkyFlyAirways.Entity.Flights;
import AthiraSajeev.GlobalSkyFlyAirways.Entity.Tickets;
import AthiraSajeev.GlobalSkyFlyAirways.Repository.TicketsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
@Service
public class TicketsService {
    @Autowired
    private TicketsRepository ticketsRepository;

    public List<Tickets> getAllTickets() {
        return ticketsRepository.findAll();
    }


    public Tickets getTicketsBySeatId(String seatId) {
        return ticketsRepository.findBySeatId(seatId);
    }


    public void save(Tickets tickets) {
        ticketsRepository.save(tickets);

    }

    public List<Tickets> getTicketByFlightIdAndUserId(String flightId, String userId) {
        return ticketsRepository.findTicketsByFlightIdAndUserId(flightId, userId);
    }

    public Tickets getTicketBySeatIdAndFlightId(String seatId, String flightId) {
        return ticketsRepository.findTicketsBySeatIdAndFlightId(seatId, flightId);
    }

    public List<Tickets> getTicketsByUserIdAndFlightId(String userId, String flightId) {
        return ticketsRepository.findTicketsByUserIdAndFlightId(userId, flightId);
    }


    public List<Tickets> getTicketsByUserId(String userId) {
        return ticketsRepository.findTicketsByUserId(userId);
    }

    public List<Tickets> getTicketsByFlightId(String flightId) {
        return ticketsRepository.findTicketsByFlightId(flightId);
    }
}


