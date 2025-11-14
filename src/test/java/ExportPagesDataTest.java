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

        deleteRecursivelyIfExists(docsDir);

        ExportPagesData.main(new String[0]);

        Path dataJson = docsDir.resolve("data.json");
        Path indexHtml = docsDir.resolve("index.html");

        assertTrue(Files.isDirectory(docsDir), "docs directory should exist");
        assertTrue(Files.exists(dataJson) && Files.size(dataJson) > 0, "data.json should exist and be non-empty");
        assertTrue(Files.exists(indexHtml) && Files.size(indexHtml) > 0, "index.html should exist and be non-empty");
    }

    private static void deleteRecursivelyIfExists(Path dir) throws IOException {
        if (!Files.exists(dir)) return;
        try (var paths = Files.walk(dir)) {
            paths.sorted(Comparator.reverseOrder())
                 .forEach(p -> {
                     try { Files.deleteIfExists(p); } catch (IOException ignored) {}
                 });
        }
    }
}
