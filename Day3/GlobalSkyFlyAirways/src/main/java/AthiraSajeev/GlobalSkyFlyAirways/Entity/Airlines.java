package AthiraSajeev.GlobalSkyFlyAirways.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;
import java.util.List;


@Entity
public class Airlines {
    @Id
    @Column(unique = true, nullable = false)
    private String username;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private String organization;
    private Double transactionAccount = 0.0;

    @JsonIgnore
    @OneToMany(mappedBy = "airlines")
    private List<Flights> flights;

    public Airlines() {
    }

    public Airlines(String username, String password, String organization) {
        this.username = username;
        this.password = password;
        this.organization = organization;
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

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public List<Flights> getFlights() {
        return flights;
    }

    public void setFlights(List<Flights> flights) {
        this.flights = flights;
    }

    public Double getTransactionAccount() {
        return transactionAccount;
    }

    public void setTransactionAccount(Double transactionAccount) {
        this.transactionAccount = transactionAccount;
    }
}

