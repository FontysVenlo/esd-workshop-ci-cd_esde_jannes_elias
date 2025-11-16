package workshop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.Instant;

@SpringBootApplication
public class BuildInfoApplication {
    public static final Instant STARTED_AT = Instant.now();

    public static void main(String[] args) {
        SpringApplication.run(BuildInfoApplication.class, args);
    }
}
