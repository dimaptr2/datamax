package ru.velkomfood.datamax.print;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class Launcher {

     public static void main(String[] args) {

          System.getProperties().put("server.port", 9595);
          SpringApplication.run(Launcher.class);

     }
     
}
