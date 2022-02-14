package AthiraSajeev.GlobalSkyFlyAirways;

import AthiraSajeev.GlobalSkyFlyAirways.Controller.UsersController;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.logging.FileHandler;
import java.util.logging.SimpleFormatter;

@Component
@Aspect
public class LoggerAOP {
    Logger logger = LoggerFactory.getLogger(this.getClass());


    @Pointcut("execution(public String signIn())")
    public void pointcutOne() {
    }

    @After("pointcutOne()")
    public void signInLogger(JoinPoint joinPoint) throws Throwable {
        StringBuilder logMessage = new StringBuilder();

        logMessage.append("User Logged In");
        logMessage.append(" through ");
        logMessage.append(joinPoint.getSignature().getName());
        logger.info(logMessage.toString());

        FileWriter writer= new FileWriter("LoggerAop.txt",true);
        writer.append(logMessage.toString());
        writer.write("\n");
        writer.close();

    }

    @Pointcut("execution(public String update*(..))")
    public void pointcutTwo() {
    }

    @After("pointcutTwo()")
    public void updateLogger(JoinPoint joinPoint) throws Throwable {
        StringBuilder logMessage = new StringBuilder();

        logMessage.append("User updated Profile");
        logMessage.append(" through ");
        logMessage.append(joinPoint.getSignature().getName());
        logger.info(logMessage.toString());

        FileWriter writer= new FileWriter("LoggerAop.txt",true);
        writer.append(logMessage.toString());
        writer.write("\n");
        writer.close();

    }

    @Pointcut("execution(public String addUserWalletBalance(..))")
    public void pointcutThree() {
    }

    @After("pointcutThree()")
    public void userBalanceLogger(JoinPoint joinPoint) throws Throwable {
        StringBuilder logMessage = new StringBuilder();

        logMessage.append("User added Wallet balance");
        logMessage.append(" through ");
        logMessage.append(joinPoint.getSignature().getName());
        logger.info(logMessage.toString());

        FileWriter writer= new FileWriter("LoggerAop.txt",true);
        writer.append(logMessage.toString());
        writer.write("\n");
        writer.close();

    }


    @Pointcut("execution(public String ConfirmPayment(..))")
    public void pointcutFour() {
    }

    @After("pointcutFour()")
    public void ConfirmPaymentLogger(JoinPoint joinPoint) throws Throwable {
        StringBuilder logMessage = new StringBuilder();

        logMessage.append("User booked ticket and confirmed payment");
        logMessage.append(" through ");
        logMessage.append(joinPoint.getSignature().getName());
        logger.info(logMessage.toString());

        FileWriter writer= new FileWriter("LoggerAop.txt",true);
        writer.append(logMessage.toString());
        writer.write("\n");
        writer.close();

    }



}
