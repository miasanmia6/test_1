package Eureka_Server_1300;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer //º§ªÓEureka Server
public class EurekaApplication1300 {
    public static void main(String[] args) {
        SpringApplication.run(EurekaApplication1300.class, args);
    }
}