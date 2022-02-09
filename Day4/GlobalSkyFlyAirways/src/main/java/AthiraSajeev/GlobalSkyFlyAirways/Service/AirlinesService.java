package AthiraSajeev.GlobalSkyFlyAirways.Service;

import AthiraSajeev.GlobalSkyFlyAirways.Entity.Admins;
import AthiraSajeev.GlobalSkyFlyAirways.Entity.Airlines;
import AthiraSajeev.GlobalSkyFlyAirways.Entity.Users;
import AthiraSajeev.GlobalSkyFlyAirways.Repository.AirlinesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

@Service
public class AirlinesService {
    @Autowired
    public AirlinesRepository airlinesRepository;

    public boolean existsAirlineById(String username) {
        return airlinesRepository.existsByUsername(username);
    }

    public Airlines getAirlineById(String username) {
        return airlinesRepository.getByUsername(username);
    }

    public void save(Airlines airline) {
        airlinesRepository.save(airline);
    }
}
