package AthiraSajeev.GlobalSkyFlyAirways.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Date;
import java.sql.Time;

@Entity
public class Airlines {

    @Column(unique = true, nullable = false)
    private String username;
    @Column(nullable = false)
    private String password;
    @Id
    @Column(unique = true, nullable = false)
    private String airlineId;
    @Column(nullable = false)
    private String airlineName;
    @Column(nullable = false)
    private Double ticketPrice;
    @Column(nullable = false)
    private String gate;
    @Column(nullable = false)
    private Double transactionAccount;
    @Column(nullable = false)
    private String departure;
    @Column(nullable = false)
    private Date departureDate;
    @Column(nullable = false)
    private Time departureTime;
    @Column(nullable = false)
    private String arrival;
    @Column(nullable = false)
    private Date arrivalDate;
    @Column(nullable = false)
    private Time arrivalTime;

    public Airlines() {
    }

    public Airlines(String username, String password, String airlineId, String airlineName, Double ticketPrice, String gate, String departure, Date departureDate, Time departureTime, String arrival, Date arrivalDate, Time arrivalTime) {
        this.username = username;
        this.password = password;
        this.airlineId = airlineId;
        this.airlineName = airlineName;
        this.ticketPrice = ticketPrice;
        this.gate = gate;
        this.departure = departure;
        this.departureDate = departureDate;
        this.departureTime = departureTime;
        this.arrival = arrival;
        this.arrivalDate = arrivalDate;
        this.arrivalTime = arrivalTime;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAirlineId() {
        return airlineId;
    }

    public void setAirlineId(String airlineId) {
        this.airlineId = airlineId;
    }

    public String getAirlineName() {
        return airlineName;
    }

    public void setAirlineName(String airlineName) {
        this.airlineName = airlineName;
    }

    public Double getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(Double ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    public String getGate() {
        return gate;
    }

    public void setGate(String gate) {
        this.gate = gate;
    }

    public Double getTransactionAccount() {
        return transactionAccount;
    }

    public void setTransactionAccount(Double transactionAccount) {
        this.transactionAccount = transactionAccount;
    }

    public String getDeparture() {
        return departure;
    }

    public void setDeparture(String departure) {
        this.departure = departure;
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

    public String getArrival() {
        return arrival;
    }

    public void setArrival(String arrival) {
        this.arrival = arrival;
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
}
