package AthiraSajeev.GlobalSkyFlyAirways.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Admins {
    @Id
    @Column(nullable = false, unique = true)
    private String username;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private String organizationName;
    @Column(nullable = false)
    private String employeeId;
    @Column(nullable = false)
    private String name;
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
    private String role;



    public Admins() {
    }
    public void updateAdmin( String password, Long phoneNumber, String emailId, String address) {
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.emailId = emailId;
        this.address = address;
    }

    public Admins(String username, String password, String organizationName, String employeeId, String name,String nationality, Integer age, Long phoneNumber, String emailId, String address) {
        this.username = username;
        this.password = password;
        this.organizationName = organizationName;
        this.employeeId = employeeId;
        this.name = name;
        this.nationality = nationality;
        this.age = age;
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

    public String getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }
}
