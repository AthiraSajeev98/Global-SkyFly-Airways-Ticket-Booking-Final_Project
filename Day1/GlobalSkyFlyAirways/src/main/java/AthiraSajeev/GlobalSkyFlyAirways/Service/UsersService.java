package AthiraSajeev.GlobalSkyFlyAirways.Service;

import AthiraSajeev.GlobalSkyFlyAirways.Entity.Users;
import AthiraSajeev.GlobalSkyFlyAirways.Repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsersService {
    @Autowired
    private UsersRepository usersRepository;


    public void save(Users user) {
        usersRepository.save(user);
    }
    public String remove(Users user){
        usersRepository.delete(user);
        return "User removed..!";
    }

}
