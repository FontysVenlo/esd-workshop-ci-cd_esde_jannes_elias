import workshop.ExportPagesData;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertTrue;

class ExportPagesDaaTest {

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
                "Expected LINE coverage 90%");
        assertTrue(Files.exists(docs.resolve("index.html")), "index.html should be created");
    }

    @Test
    void parserEdge_noLineCounter_treatedAsZero() throws Exception {
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
        assertTrue(json.contains("\"status\": \"success\""), "Should still succeed without LINE counter");
    }

    @Test
    void coversClassHeader_envDefault_and_totalZero() throws Exception {
        // touches class (covers the class header line)
        new ExportPagesData();

        // zero totals -> hits `if (total == 0) return 0.0;`
        Path xml = Path.of("build/reports/jacoco/test/jacocoTestReport.xml");
        Files.createDirectories(xml.getParent());
        String zeroTotals =
                "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<report name=\"jacoco\">\n" +
                "  <counter type=\"LINE\" missed=\"0\" covered=\"0\"/>\n" +
                "</report>\n";
        Files.writeString(xml, zeroTotals, StandardCharsets.UTF_8);

        Path docs = Path.of("docs");
        clean(docs);

        // Ensure default branch of env() is used by NOT setting BUILD_VERSION
        ExportPagesData.main(new String[0]);

        String json = Files.readString(docs.resolve("data.json"), StandardCharsets.UTF_8);
        assertTrue(json.contains("\"status\": \"success\""), "Should generate data.json successfully");
    }

    @Test
    void envNonBlank_branch_viaChildJvm() throws Exception {
        // Prepare a normal LINE counter so the rest of main runs
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

        // Run ExportPagesData.main in a separate JVM with BUILD_VERSION set
        runExportWithEnv("BUILD_VERSION", "9999");

        String json = Files.readString(docs.resolve("data.json"), StandardCharsets.UTF_8);
        assertTrue(json.contains("\"buildVersion\": \"9999\""),
                "env(...) non-blank branch should be used (9999)");
    }

    // ---- helpers ----

    private static void clean(Path dir) throws Exception {
        if (!Files.exists(dir)) return;
        try (var walk = Files.walk(dir)) {
            walk.sorted((a, b) -> b.getNameCount() - a.getNameCount())
                .forEach(p -> { try { Files.deleteIfExists(p); } catch (Exception ignored) {} });
        }
    }

    /** Launch a child JVM running workshop.ExportPagesData with one extra env var. */
    private static void runExportWithEnv(String key, String value) throws Exception {
        String javaExe = System.getProperty("java.home") +
                java.io.File.separator + "bin" + java.io.File.separator + "java";

        // Gradle test JVM classpath contains compiled test+main classes and dependencies
        String cp = System.getProperty("java.class.path");

        ProcessBuilder pb = new ProcessBuilder(
                javaExe, "-cp", cp, "workshop.ExportPagesData");
        pb.environment().put(key, value);

        Process p = pb.start();

        // Drain outputs (helps debugging on CI if something goes wrong)
        try (var r = new BufferedReader(new InputStreamReader(p.getInputStream()));
             var e = new BufferedReader(new InputStreamReader(p.getErrorStream()))) {
            while (r.readLine() != null) {}
            while (e.readLine() != null) {}
        }

        int exit = p.waitFor();
        if (exit != 0) {
            throw new IllegalStateException("Child JVM exit code " + exit);
        }
    }
}


