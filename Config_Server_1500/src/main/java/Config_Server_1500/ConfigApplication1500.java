package Config_Server_1500;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class ConfigApplication1500 {
    public static void main(String[] args) {
        SpringApplication.run(ConfigApplication1500.class, args);
    }}