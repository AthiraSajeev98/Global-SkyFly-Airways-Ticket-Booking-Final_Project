package AthiraSajeev.GlobalSkyFlyAirways;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
public class GlobalSkyFlyAirwaysApplication {

	public static void main(String[] args) {
		SpringApplication.run(GlobalSkyFlyAirwaysApplication.class, args);
		System.out.println("Global SkyFly Airways Application Running..!");
	}

}
