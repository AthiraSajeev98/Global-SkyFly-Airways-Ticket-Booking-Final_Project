package AthiraSajeev.GlobalSkyFlyAirways.Repository;

import AthiraSajeev.GlobalSkyFlyAirways.Entity.Admins;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminsRepository extends JpaRepository<Admins,String> {
    Admins getByUsername(String username);
}
