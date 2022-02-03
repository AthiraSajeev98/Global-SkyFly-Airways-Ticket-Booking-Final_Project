package AthiraSajeev.GlobalSkyFlyAirways.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Entity
public class Airlines {
    @Id
    @Column(nullable = false, unique = true)
    @GeneratedValue
    private Long id;
    @Column(nullable = false)
    private String airlineId;
    @Column(nullable = false)
    private String airlineName;
    @Column(nullable = false)
    private Double ticketPrice;
    @Column(nullable = false)
    private String gate;
    @Column(nullable = false)
    private Double TransactionAccount;

    public Airlines() {
    }

    public Airlines(String airlineId, String airlineName, Double ticketPrice, String gate ) {
        this.airlineId = airlineId;
        this.airlineName = airlineName;
        this.ticketPrice = ticketPrice;
        this.gate = gate;

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
        return TransactionAccount;
    }

    public void setTransactionAccount(Double transactionAccount) {
        TransactionAccount = transactionAccount;
    }
}
