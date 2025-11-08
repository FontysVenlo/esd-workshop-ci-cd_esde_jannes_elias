import workshop.ExportPagesData;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;

import static org.junit.jupiter.api.Assertions.assertTrue;

class ExportPagesDataTest {

    @Test
    void generatesDocsFilesWithDefaults() throws Exception {
        Path docsDir = Path.of("docs");

        // Clean up any existing docs/ so the test is deterministic
        if (Files.exists(docsDir)) {
            try (var paths = Files.walk(docsDir)) {
                paths.sorted(Comparator.reverseOrder())
                        .forEach(path -> {
                            try {
                                Files.deleteIfExists(path);
                            } catch (IOException ignored) {
                            }
                        });
            }
        }

        // Run the generator; it must recreate docs/ and files
        ExportPagesData.main(new String[0]);

        Path dataJson = docsDir.resolve("data.json");
        Path indexHtml = docsDir.resolve("index.html");

        assertTrue(Files.isDirectory(docsDir), "docs directory should exist");
        assertTrue(Files.exists(dataJson), "data.json should exist");
        assertTrue(Files.size(dataJson) > 0, "data.json should not be empty");
        assertTrue(Files.exists(indexHtml), "index.html should exist");
        assertTrue(Files.size(indexHtml) > 0, "index.html should not be empty");
    }
}
