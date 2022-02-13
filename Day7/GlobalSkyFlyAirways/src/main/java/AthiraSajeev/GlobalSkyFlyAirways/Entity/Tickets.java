package AthiraSajeev.GlobalSkyFlyAirways.Entity;

import javax.persistence.*;

@Entity
public class Tickets {
    @Id
    @Column(nullable = false, unique = true)
    @GeneratedValue
    private Long ticketId;
    @Column(nullable = false)
    private String seatId;
    @Column(nullable = false)
    private String flightId;
    @Column(nullable = false)
    private String userId;
    @Column(nullable = false)
    private Integer availableTickets=90;
    private String passengerName;
    private String passportNumber;
    private String nationality;
    private Boolean repaymentStatus=false;
    @ManyToOne
    private Users userTicket;

    @ManyToOne
    private Flights flights;

    public Tickets() {
    }

    public Tickets(String seatId, String flightId, String userId) {
        this.seatId = seatId;
        this.flightId = flightId;
        this.userId = userId;
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

    public Users getUsers() {
        return userTicket;
    }

    public void setUsers(Users users) {
        this.userTicket = users;
    }

    public Flights getFlights() {
        return flights;
    }

    public void setFlights(Flights flights) {
        this.flights = flights;
    }

    public Users getUserTicket() {
        return userTicket;
    }

    public void setUserTicket(Users userTicket) {
        this.userTicket = userTicket;
    }

    public String getPassengerName() {
        return passengerName;
    }

    public void setPassengerName(String passengerName) {
        this.passengerName = passengerName;
    }

    public String getPassportNumber() {
        return passportNumber;
    }

    public void setPassportNumber(String passportNumber) {
        this.passportNumber = passportNumber;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public Boolean getRepaymentStatus() {
        return repaymentStatus;
    }

    public void setRepaymentStatus(Boolean repaymentStatus) {
        this.repaymentStatus = repaymentStatus;
    }
}
