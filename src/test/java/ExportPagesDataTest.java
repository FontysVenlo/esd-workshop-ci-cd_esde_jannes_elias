import workshop.ExportPagesData;
import org.junit.jupiter.api.Test;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Combined tests to fully cover ExportPagesData:
 * - with a valid JaCoCo XML (coverage present)
 * - with parser edge cases (missing attrs / no counters)
 */
class ExportPagesDataCoverageTests {

    @Test
    void generatesCoverageFromJacocoXml() throws Exception {
        // Arrange: minimal JaCoCo XML with INSTRUCTION counter (90/100 => 90%)
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
        deleteRecursivelyIfExists(docs);

        // Act
        ExportPagesData.main(new String[0]);

        // Assert: JSON contains computed 90.00 (or 90), HTML exists
        String json = Files.readString(docs.resolve("data.json"));
        assertTrue(json.contains("\"coveragePercent\": 90.00") || json.contains("\"coveragePercent\": 90"),
                "data.json should include computed coverage 90%");
        assertTrue(Files.exists(docs.resolve("index.html")), "index.html should be created");
    }

    @Test
    void parserEdgeCasesProduceValidOutput() throws Exception {
        Path base = Path.of("build/reports/jacoco/test");
        Files.createDirectories(base);
        Path xml = base.resolve("jacocoTestReport.xml");

        // Case A: INSTRUCTION with missing attrs -> ignored by parser; total stays 0 -> 0.0%
        String noAttrs =
                "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<report name=\"jacoco\">\n" +
                "  <counter type=\"BRANCH\" missed=\"1\" covered=\"2\"/>\n" +   // other type
                "  <counter type=\"INSTRUCTION\"/>\n" +                       // missing missed/covered
                "</report>\n";
        Files.writeString(xml, noAttrs, StandardCharsets.UTF_8);

        Path docs = Path.of("docs");
        deleteRecursivelyIfExists(docs);

        ExportPagesData.main(new String[0]);
        String jsonA = Files.readString(docs.resolve("data.json"));
        assertTrue(jsonA.contains("\"status\": \"success\""),
                "generation should succeed even if INSTRUCTION attrs are missing");

        // Case B: no <counter> elements at all -> total 0 -> handled gracefully
        String noCounters =
                "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<report name=\"jacoco\">\n" +
                "</report>\n";
        Files.writeString(xml, noCounters, StandardCharsets.UTF_8);

        ExportPagesData.main(new String[0]);
        String jsonB = Files.readString(docs.resolve("data.json"));
        assertTrue(jsonB.contains("\"status\": \"success\""),
                "generation should succeed even if there are no counter elements");
    }

    // ---- helpers ----
    private static void deleteRecursivelyIfExists(Path dir) throws Exception {
        if (!Files.exists(dir)) return;
        try (var walk = Files.walk(dir)) {
            walk.sorted((a, b) -> b.getNameCount() - a.getNameCount())
                .forEach(p -> { try { Files.deleteIfExists(p); } catch (Exception ignored) {} });
        }
    }
}
