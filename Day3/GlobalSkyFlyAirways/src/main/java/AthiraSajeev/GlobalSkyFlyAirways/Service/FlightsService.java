package AthiraSajeev.GlobalSkyFlyAirways.Service;

import AthiraSajeev.GlobalSkyFlyAirways.Entity.Flights;
import AthiraSajeev.GlobalSkyFlyAirways.Repository.FlightsRepository;
import org.aspectj.lang.annotation.After;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FlightsService {
    @Autowired
    public FlightsRepository flightsRepository;

    public void saveFlight(Flights flight){
        flightsRepository.save(flight);
    }

    public Flights getFlightById(String flightId) {
        return flightsRepository.getById(flightId);
    }
}
