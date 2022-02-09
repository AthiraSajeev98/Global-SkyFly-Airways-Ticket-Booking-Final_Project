package AthiraSajeev.GlobalSkyFlyAirways.Service;

import AthiraSajeev.GlobalSkyFlyAirways.Entity.Admins;
import AthiraSajeev.GlobalSkyFlyAirways.Entity.Users;
import AthiraSajeev.GlobalSkyFlyAirways.Repository.AdminsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminsService {
    @Autowired
    public AdminsRepository adminsRepository;

    public void save(Admins admin) {
        adminsRepository.save(admin);
    }

    public boolean existsAdminById(String username) {
        return adminsRepository.existsById(username);
    }

    public Admins getAdminById(String username) {
        return adminsRepository.getById(username);
    }
}
