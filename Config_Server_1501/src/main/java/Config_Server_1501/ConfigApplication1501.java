package Config_Server_1501;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class ConfigApplication1501 {
    public static void main(String[] args) {
        SpringApplication.run(ConfigApplication1501.class, args);
    }}