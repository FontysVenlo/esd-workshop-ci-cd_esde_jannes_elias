import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import workshop.ExportPagesData;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertTrue;

class ExportPagesDataTest {

    @TempDir
    Path tempDir;

    @Test
    void generatesDocsFilesWithDefaults() throws Exception {
        String originalUserDir = System.getProperty("user.dir");
        try {
            System.setProperty("user.dir", tempDir.toString());

            ExportPagesData.main(new String[0]);

            Path docsDir = tempDir.resolve("docs");
            Path dataJson = docsDir.resolve("data.json");
            Path indexHtml = docsDir.resolve("index.html");

            assertTrue(Files.isDirectory(docsDir), "docs directory should exist");
            assertTrue(Files.exists(dataJson), "data.json should exist");
            assertTrue(Files.size(dataJson) > 0, "data.json should not be empty");
            assertTrue(Files.exists(indexHtml), "index.html should exist");
            assertTrue(Files.size(indexHtml) > 0, "index.html should not be empty");
        } finally {
            System.setProperty("user.dir", originalUserDir);
        }
    }
}