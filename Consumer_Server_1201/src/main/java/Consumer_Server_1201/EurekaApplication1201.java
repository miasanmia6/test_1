package Consumer_Server_1201;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;



@SpringBootApplication
@EnableFeignClients
public class EurekaApplication1201 {
    public static void main(String[] args) {
        SpringApplication.run(EurekaApplication1201.class, args);
    }
}