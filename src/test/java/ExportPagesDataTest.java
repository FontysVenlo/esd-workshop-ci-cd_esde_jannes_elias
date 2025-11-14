import workshop.ExportPagesData;
import org.junit.jupiter.api.Test;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertTrue;

class ExportPagesDataWithCoverageTest {

    @Test
    void generatesCoverageFromJacocoXml() throws Exception {
        // Arrange: write a minimal JaCoCo XML the app expects
        Path xml = Path.of("build/reports/jacoco/test/jacocoTestReport.xml");
        Files.createDirectories(xml.getParent());
        String fake =
                "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<report name=\"jacoco\">\n" +
                "  <counter type=\"INSTRUCTION\" missed=\"10\" covered=\"90\"/>\n" +
                "</report>\n";
        Files.writeString(xml, fake, StandardCharsets.UTF_8);

        // Clean docs/ to keep assertions deterministic
        Path docs = Path.of("docs");
        if (Files.exists(docs)) {
            try (var walk = Files.walk(docs)) {
                walk.sorted((a,b) -> b.getNameCount()-a.getNameCount()).forEach(p -> {
                    try { Files.deleteIfExists(p); } catch (Exception ignored) {}
                });
            }
        }

        // Act
        ExportPagesData.main(new String[0]);

        // Assert
        Path dataJson = docs.resolve("data.json");
        String json = Files.readString(dataJson, StandardCharsets.UTF_8);
        assertTrue(json.contains("\"coveragePercent\": 90.00") || json.contains("\"coveragePercent\": 90"),
                "data.json should include computed coverage 90%");
    }
}
