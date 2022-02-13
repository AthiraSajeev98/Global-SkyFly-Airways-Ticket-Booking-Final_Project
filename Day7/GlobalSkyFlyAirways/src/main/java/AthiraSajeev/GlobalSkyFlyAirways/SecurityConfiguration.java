package AthiraSajeev.GlobalSkyFlyAirways;

import AthiraSajeev.GlobalSkyFlyAirways.Service.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Autowired
    public MyUserDetailsService myUserDetailsService;

    //authentication
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
     auth.userDetailsService(myUserDetailsService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().disable().authorizeRequests()
        .antMatchers("/userProfile","/userProfile/*",
        "/updateUserProfile",
        "/searchFlights",
        "/addUserWalletBalance",
        "/userBalance",
//        "/selectFlightSeat",
        "/confirmPayment",
        "/enterPassengerDetails",
        "/addPassengerDetail",
        "/previousBookings",
        "/signUp"
        ).hasRole("USER")
        .and().formLogin().loginPage("/login");


        http.csrf().disable().authorizeRequests()
        .antMatchers("/airlineAccount",
        "/airlineProfile",
        "/airlinesDetails",
        "/cancelledFlights",
        "/changeAirlinePassword",
        "/fixedFlightSchedule",
        "/flightCancelledReason",
        "/scheduleFlight",
        "/signUpAirline"
        ).hasRole("AIRLINE")
        .and().formLogin().loginPage("/login");


        http.csrf().disable().authorizeRequests()
        .antMatchers("superAdminProfile",
        "/adminsDetails",
        "/removeAdmin",
        "/signUpAdmin"
        ).hasAnyRole("SADMIN")
        .and().formLogin().loginPage("/login");


        http.csrf().disable().authorizeRequests()
        .antMatchers("/adminProfile",
        "/updateAdminProfile",
        "/signUpAirline",
        "/usersDetails",
        "/airlinesDetails"
        ).hasAnyRole("ADMIN")
        .and().formLogin().loginPage("/login");
        
    }

    @Bean
    public PasswordEncoder getPasswordEncoder(){
        return NoOpPasswordEncoder.getInstance();
    }
}


//http.csrf().disable().authorizeRequests()
//        .antMatchers("/userProfile","/userProfile/*",
//        "/updateUserProfile",
//        "/searchFlights",
//        "/addUserWalletBalance",
//        "/userBalance",
//        "/selectFlightSeat",
//        "/confirmPayment",
//        "/enterPassengerDetails",
//        "/addPassengerDetail",
//        "/previousBookings",
//        "/signUp"
//        ).hasRole("USER")
//        .and().formLogin().loginPage("/login");
//
//
//        http.csrf().disable().authorizeRequests()
//        .antMatchers(
//        "/airlineAccount",
//        "/airlineProfile",
//        "/airlinesDetails",
//        "/cancelledFlights",
//        "/changeAirlinePassword",
//        "/fixedFlightSchedule",
//        "/flightCancelledReason",
//        "/scheduleFlight",
//        "/signUpAirline"
//        ).hasRole("AIRLINE")
//        .and().formLogin().loginPage("/login");
//
//
//        http.csrf().disable().authorizeRequests()
//        .antMatchers("superAdminProfile",
//        "/adminsDetails",
//        "/removeAdmin",
//        "/signUpAdmin"
//        ).hasAnyRole("SADMIN")
//        .and().formLogin().loginPage("/login");
//
//
//        http.csrf().disable().authorizeRequests()
//        .antMatchers("/adminProfile",
//        "/updateAdminProfile",
//        "/signUpAirline",
//        "/usersDetails",
//        "/airlinesDetails"
//        ).hasAnyRole("ADMIN")
//        .and().formLogin().loginPage("/login");