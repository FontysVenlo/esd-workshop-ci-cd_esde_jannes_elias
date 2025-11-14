package workshop.api;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.Map;

@RestController
public class BuildInfoController {

    @Value("${app.name:build-info-service}")
    private String appName;

    @Value("${app.commit:unknown}")
    private String commitSha;

    @GetMapping("/api/health")
    public Map<String, String> health() {
        return Map.of("status", "UP");
    }

    @GetMapping("/api/info")
    public Map<String, String> info() {
        return Map.of(
                "app", appName,
                "commit", commitSha,
                "timestamp", Instant.now().toString()
        );
    }
}

