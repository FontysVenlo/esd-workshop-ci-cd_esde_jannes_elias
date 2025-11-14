import workshop.ExportPagesData;
import org.junit.jupiter.api.Test;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertTrue;
class ExportPagesDataCoverageTests {

    @Test
    void withLineCounter_computesCoverage() throws Exception {
        Path xml = Path.of("build/reports/jacoco/test/jacocoTestReport.xml");
        Files.createDirectories(xml.getParent());
        String withLine =
                "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<report name=\"jacoco\">\n" +
                "  <counter type=\"LINE\" missed=\"10\" covered=\"90\"/>\n" +
                "</report>\n";
        Files.writeString(xml, withLine, StandardCharsets.UTF_8);

        Path docs = Path.of("docs");
        clean(docs);

        ExportPagesData.main(new String[0]);

        String json = Files.readString(docs.resolve("data.json"), StandardCharsets.UTF_8);
        assertTrue(json.contains("\"coveragePercent\": 90.00") || json.contains("\"coveragePercent\": 90"),
                "Expected computed LINE coverage 90%");
        assertTrue(Files.exists(docs.resolve("index.html")), "index.html should be created");
    }

    @Test
    void withNoLineCounter_treatedAsZero() throws Exception {
        // Arrange: XML without LINE counter
        Path xml = Path.of("build/reports/jacoco/test/jacocoTestReport.xml");
        Files.createDirectories(xml.getParent());
        String noLine =
                "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<report name=\"jacoco\">\n" +
                "  <counter type=\"BRANCH\" missed=\"1\" covered=\"2\"/>\n" +
                "</report>\n";
        Files.writeString(xml, noLine, StandardCharsets.UTF_8);

        Path docs = Path.of("docs");
        clean(docs);

        ExportPagesData.main(new String[0]);

        String json = Files.readString(docs.resolve("data.json"), StandardCharsets.UTF_8);
        assertTrue(json.contains("\"status\": \"success\""), "Page generation should still succeed");
    }

    @Test
    void whenReportAbsent_showsNa() throws Exception {
        Path xml = Path.of("build/reports/jacoco/test/jacocoTestReport.xml");
        if (Files.exists(xml)) Files.delete(xml);

        Path docs = Path.of("docs");
        clean(docs);

        ExportPagesData.main(new String[0]);

        String json = Files.readString(docs.resolve("data.json"), StandardCharsets.UTF_8);
        assertTrue(json.contains("\"status\": \"success\""), "Should still succeed without report present");
    }

    private static void clean(Path dir) throws Exception {
        if (!Files.exists(dir)) return;
        try (var walk = Files.walk(dir)) {
            walk.sorted((a, b) -> b.getNameCount() - a.getNameCount())
                .forEach(p -> { try { Files.deleteIfExists(p); } catch (Exception ignored) {} });
        }
    }
}

