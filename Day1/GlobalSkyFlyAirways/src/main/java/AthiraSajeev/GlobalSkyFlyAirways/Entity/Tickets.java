package AthiraSajeev.GlobalSkyFlyAirways.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Tickets {
    @Id
    @Column(nullable = false, unique = true)
    @GeneratedValue
    private Long ticketId;
    @Column(nullable = false)
    private String seatId;
    @Column(nullable = false)
    private Integer availableTickets=60;

    public Tickets() {
    }

    public Tickets(String seatId) {
        this.seatId = seatId;
    }

    public String getSeatId() {
        return seatId;
    }

    public void setSeatId(String seatId) {
        this.seatId = seatId;
    }

    public Integer getAvailableTickets() {
        return availableTickets;
    }

    public void setAvailableTickets(Integer availableTickets) {
        this.availableTickets = availableTickets;
    }
}
