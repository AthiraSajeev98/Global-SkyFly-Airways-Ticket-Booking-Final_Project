package AthiraSajeev.GlobalSkyFlyAirways;

import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LoggerAOP {
    Logger logger = LoggerFactory.getLogger(this.getClass());


}
