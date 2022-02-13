package AthiraSajeev.GlobalSkyFlyAirways.Service;

import AthiraSajeev.GlobalSkyFlyAirways.Entity.Admins;
import AthiraSajeev.GlobalSkyFlyAirways.Entity.Airlines;
import AthiraSajeev.GlobalSkyFlyAirways.Entity.MyUserDetails;
import AthiraSajeev.GlobalSkyFlyAirways.Entity.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    public UsersService usersService;

    @Autowired
    public AdminsService adminsService;

    @Autowired AirlinesService airlinesService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Users user = usersService.getUserById(username);

        if(user==null){
            Admins admin=adminsService.getAdminById(username);
            if (admin == null) {
                Airlines airline = airlinesService.getAirlineById(username);
                return new MyUserDetails(airline.getUsername(),airline.getPassword(),"ROLE_AIRLINE");
            }
            return new MyUserDetails(
                    admin.getUsername(),
                    admin.getPassword(),
                    admin.getRole().equals("A")?"ROLE_ADMIN":"ROLE_SADMIN"
            );
        }
        return new MyUserDetails(user.getUsername(),user.getPassword(),"ROLE_USER");
    }


}
