package workshop.api;

import workshop.BuildInfoApplication;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.management.ManagementFactory;
import java.time.Duration;
import java.util.LinkedHashMap;
import java.util.Map;

@RestController
public class BuildInfoController {

    @Value("${app.name:workshop-service}")          private String appName;
    @Value("${app.version:${BUILD_VERSION:local}}") private String appVersion;
    @Value("${app.commit:${APP_COMMIT:unknown}}")   private String appCommit;

    @GetMapping("/api/health")
    public Map<String, String> health() {
        return Map.of("status", "UP");
    }

    @GetMapping("/api/info")
    public Map<String, Object> info() {
        var m = new LinkedHashMap<String, Object>();
        m.put("app", appName);
        m.put("version", appVersion);
        m.put("commit", appCommit);
        m.put("java", System.getProperty("java.version"));
        m.put("os", System.getProperty("os.name") + " " + System.getProperty("os.version"));
        m.put("startedAt", BuildInfoApplication.STARTED_AT.toString()); // works across packages
        long uptimeMs = ManagementFactory.getRuntimeMXBean().getUptime();
        m.put("uptime", Duration.ofMillis(uptimeMs).toString());
        return m;
    }
}