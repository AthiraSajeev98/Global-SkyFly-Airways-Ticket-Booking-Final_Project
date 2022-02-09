package AthiraSajeev.GlobalSkyFlyAirways.Repository;

import AthiraSajeev.GlobalSkyFlyAirways.Entity.Flights;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.List;

public interface FlightsRepository extends JpaRepository<Flights,String> {

}
