package AthiraSajeev.GlobalSkyFlyAirways.Repository;

import AthiraSajeev.GlobalSkyFlyAirways.Entity.Tickets;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TicketsRepository extends JpaRepository<Tickets,Long> {
    Tickets findBySeatId(String seatId);

    List<Tickets> findTicketsByFlights(String flightId);

    List<Tickets> findTicketsByFlightIdAndUserId(String flightId, String userId);

    Tickets findTicketsBySeatIdAndFlightId(String seatId, String flightId);

    List<Tickets> findTicketsByUserIdAndFlightId(String userId, String flightId);

    List<Tickets> findTicketsByUserId(String userId);
}
