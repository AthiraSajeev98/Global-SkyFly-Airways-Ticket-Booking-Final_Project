package AthiraSajeev.GlobalSkyFlyAirways.Service;

import AthiraSajeev.GlobalSkyFlyAirways.Entity.Users;
import AthiraSajeev.GlobalSkyFlyAirways.Repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsersService {
    @Autowired
    public UsersRepository usersRepository;
    public void save(Users user) {
        usersRepository.save(user);
    }

    public boolean existsUserById(String username) {
        return usersRepository.existsById(username);
    }

    public Users getUserById(String username) {
        return usersRepository.getByUsername(username);
    }

    public List<Users> getAllUsers() {
        return usersRepository.findAll();
    }

    public void removeUser(Users user) {
        usersRepository.delete(user);
    }
}
