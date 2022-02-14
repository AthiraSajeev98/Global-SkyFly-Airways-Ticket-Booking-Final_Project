package AthiraSajeev.GlobalSkyFlyAirways;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import java.io.IOException;

@SpringBootApplication
@EnableAspectJAutoProxy
public class GlobalSkyFlyAirwaysApplication {

	public static void main(String[] args) throws IOException {
		SpringApplication.run(GlobalSkyFlyAirwaysApplication.class, args);
		System.out.println("Global SkyFly Airways Application Running..!");
	}

}
