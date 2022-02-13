package AthiraSajeev.GlobalSkyFlyAirways.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Users")
public class Users {
    @Id
    @Column(unique = true)
    private String username;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    public String name;
    @Column(nullable = false)
    private String nationality;
    @Column(nullable = false)
    private Integer age;
    @Column(nullable = false, unique = true)
    private Long phoneNumber;
    @Column(nullable = false, unique = true)
    private String emailId;
    @Column(nullable = false)
    private String address;
    private Double walletBalance=0.0;

    @JsonIgnore
    @OneToMany(mappedBy = "userTicket")
    private List<Tickets> tickets;

    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private List<Airlines> airlines;

    public Users() {
    }

    public Users(String username, String password, String name, String nationality, Integer age, Long phoneNumber, String emailId, String address) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.nationality = nationality;
        this.age = age;
        this.phoneNumber = phoneNumber;
        this.emailId = emailId;
        this.address = address;
    }

    public void updateUser( String password, Long phoneNumber, String emailId, String address) {
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.emailId = emailId;
        this.address = address;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(Long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Double getWalletBalance() {
        return walletBalance;
    }

    public void setWalletBalance(Double walletBalance) {
        this.walletBalance = walletBalance;
    }

    public List<Tickets> getTickets() {
        return tickets;
    }

    public void setTickets(List<Tickets> tickets) {
        this.tickets = tickets;
    }

    public List<Airlines> getAirlines() {
        return airlines;
    }

    public void setAirlines(List<Airlines> airlines) {
        this.airlines = airlines;
    }
}
