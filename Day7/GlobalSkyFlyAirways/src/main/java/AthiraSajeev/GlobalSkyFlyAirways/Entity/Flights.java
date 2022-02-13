package AthiraSajeev.GlobalSkyFlyAirways.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;
import java.util.List;

@Entity
public class Flights {
    @Id
    @Column(unique = true,nullable = false)
    private String flightId;
    @Column(nullable = false)
    private Double ticketPrice;
    @Column(nullable = false)
    private Integer gate;
    @Column(nullable = false)
    private String origin;
    @Column(nullable = false)
    private Date departureDate;
    @Column(nullable = false)
    private Time departureTime;
    @Column(nullable = false)
    private String destination;
    @Column(nullable = false)
    private Date arrivalDate;
    @Column(nullable = false)
    private Time arrivalTime;
    @Column(nullable = false)
    private String duration;
    private Boolean cancelStatus=false;
    private String status= "Active";
    private String cancelReason;

    @JsonIgnore
    @OneToMany
    private List<Tickets> tickets;
    @ManyToOne
    private Airlines airlines;

    public Flights() {
    }

    public Flights(String flightId, Double ticketPrice, Integer gate, String origin, Date departureDate, Time departureTime, String destination, Date arrivalDate, Time arrivalTime, String duration) {
        this.flightId = flightId;
        this.ticketPrice = ticketPrice;
        this.gate = gate;
        this.origin = origin;
        this.departureDate = departureDate;
        this.departureTime = departureTime;
        this.destination = destination;
        this.arrivalDate = arrivalDate;
        this.arrivalTime = arrivalTime;
        this.duration = duration;
    }

    public String getFlightId() {
        return flightId;
    }

    public void setFlightId(String flightId) {
        this.flightId = flightId;
    }

    public Double getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(Double ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    public Integer getGate() {
        return gate;
    }

    public void setGate(Integer gate) {
        this.gate = gate;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public Date getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(Date departureDate) {
        this.departureDate = departureDate;
    }

    public Time getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(Time departureTime) {
        this.departureTime = departureTime;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public Date getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(Date arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public Time getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(Time arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public Airlines getAirlines() {
        return airlines;
    }

    public void setAirlines(Airlines airlines) {
        this.airlines = airlines;
    }

    public Boolean getCancelStatus() {
        return cancelStatus;
    }

    public void setCancelStatus(Boolean cancelStatus) {
        this.cancelStatus = cancelStatus;
    }


    public String getCancelReason() {
        return cancelReason;
    }

    public void setCancelReason(String cancelReason) {
        this.cancelReason = cancelReason;
    }

    public List<Tickets> getTickets() {
        return tickets;
    }

    public void setTickets(List<Tickets> tickets) {
        this.tickets = tickets;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
