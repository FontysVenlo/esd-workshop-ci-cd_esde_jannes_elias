package workshop;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;

public class ExportPagesData {

    public static void main(String[] args) throws IOException {
        String buildVersion = System.getenv().getOrDefault("BUILD_VERSION", "local");
        String commit = System.getenv().getOrDefault("GITHUB_SHA", "unknown");
        String repo = System.getenv().getOrDefault("GITHUB_REPOSITORY", "unknown/repo");

        Path docsDir = Path.of("docs");
        Files.createDirectories(docsDir);

        Map<String, Object> data = Map.of(
                "buildVersion", buildVersion,
                "commit", commit,
                "repository", repo,
                "message", "✅ Build successful. Docker image published for this commit."
        );

        ObjectWriter writer = new ObjectMapper().writerWithDefaultPrettyPrinter();
        writer.writeValue(new File(docsDir.toFile(), "data.json"), data);

        Files.writeString(
                docsDir.resolve("index.html"),
                """
                <!DOCTYPE html>
                <html lang="en">
                <head>
                  <meta charset="UTF-8" />
                  <title>Workshop Build Status</title>
                </head>
                <body>
                  <h1>Workshop Build Status</h1>
                  <p>Build version: %s</p>
                  <p>Commit: %s</p>
                  <p>Repository: %s</p>
                  <p>See data.json for structured info.</p>
                </body>
                </html>
                """.formatted(buildVersion, commit, repo)
        );
    }
}
