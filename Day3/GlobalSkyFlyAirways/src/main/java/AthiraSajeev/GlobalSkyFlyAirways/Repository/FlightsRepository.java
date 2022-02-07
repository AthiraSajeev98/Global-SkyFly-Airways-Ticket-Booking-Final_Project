package AthiraSajeev.GlobalSkyFlyAirways.Repository;

import AthiraSajeev.GlobalSkyFlyAirways.Entity.Flights;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FlightsRepository extends JpaRepository<Flights,String> {
}
