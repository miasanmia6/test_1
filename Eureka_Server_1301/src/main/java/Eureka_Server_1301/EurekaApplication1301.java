package Eureka_Server_1301;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer //º§ªÓEureka Server
@SpringBootApplication
public class EurekaApplication1301 {
    public static void main(String[] args) {
        SpringApplication.run(EurekaApplication1301.class, args);
    }
}