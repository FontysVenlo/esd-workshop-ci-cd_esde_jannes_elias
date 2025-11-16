package workshop;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Instant;
import java.util.Locale;

public class ExportPagesData {

    public static void main(String[] args) throws Exception {
        String buildVersion = env("BUILD_VERSION", "local");

        Path jacocoXml = Path.of("build/reports/jacoco/test/jacocoTestReport.xml");
        double coverage = Files.exists(jacocoXml) ? readLineCoveragePercent(jacocoXml) : -1.0;


        Path docs = Path.of("docs");
        Files.createDirectories(docs);

        // data.json
        String json =
                "{\n" +
                "  \"status\": \"success\",\n" +
                "  \"buildVersion\": \"" + j(buildVersion) + "\",\n" +
                (coverage >= 0
                        ? "  \"coveragePercent\": " + String.format(Locale.ROOT, "%.2f", coverage) + ",\n"
                        : "") +
                "  \"generatedAt\": \"" + j(Instant.now().toString()) + "\"\n" +
                "}\n";
        Files.writeString(docs.resolve("data.json"), json, StandardCharsets.UTF_8);

        // index.html (minimal)
        String coverageLine = (coverage >= 0)
                ? "<p><strong>Test coverage:</strong> "
                    + h(String.format(Locale.ROOT, "%.2f%%", coverage)) + "</p>\n"
                : "<p><strong>Test coverage:</strong> n/a</p>\n";

        String html = """
                <!doctype html>
                <html lang="en">
                <head>
                  <meta charset="utf-8">
                  <title>Build Status</title>
                  <meta name="viewport" content="width=device-width, initial-scale=1" />
                  <style>
                    :root { font-family: ui-sans-serif, system-ui, -apple-system, Segoe UI, Roboto, Arial; }
                    body { margin:0; padding:2rem; background:#fafafa; }
                    .card{max-width:680px;margin:0 auto;padding:1.25rem;border:1px solid #eee;border-radius:12px;background:#fff;box-shadow:0 2px 16px rgba(0,0,0,.06);}
                    h1{margin:.2rem 0 1rem}
                    .meta{color:#666;font-size:.9rem}
                  </style>
                </head>
                <body>
                  <div class="card">
                    <h1>✅ Build succeeded</h1>
                """;
        html += "<p><strong>Version:</strong> " + h(buildVersion) + "</p>\n";
        html += coverageLine;
        html += "<p class=\"meta\">Generated: " + h(Instant.now().toString()) + "</p>\n";
        html += "<p>Raw data: <a href=\"./data.json\">data.json</a></p>\n";
        html += """
                  </div>
                </body>
                </html>
                """;
        Files.writeString(docs.resolve("index.html"), html, StandardCharsets.UTF_8);
    }

    private static String env(String k, String def) {
        String v = System.getenv(k);
        return (v == null || v.isBlank()) ? def : v;
    }

    private static String h(String s) {
        return s.replace("&", "&amp;")
                .replace("<", "&lt;")
                .replace(">", "&gt;");
    }

    private static String j(String s) {
        return s.replace("\\", "\\\\")
                .replace("\"", "\\\"");
    }

    private static double readLineCoveragePercent(Path xmlPath) throws Exception {
        String xml = Files.readString(xmlPath);
        java.util.regex.Matcher m = java.util.regex.Pattern.compile(
                "<counter\\s+type=\"LINE\"\\s+missed=\"(\\d+)\"\\s+covered=\"(\\d+)\"\\s*/>")
            .matcher(xml);
        if (!m.find()) {
            return 0.0;
        }
        long missed = Long.parseLong(m.group(1));
        long covered = Long.parseLong(m.group(2));
        long total = missed + covered;
        if (total == 0) return 0.0;
            return (covered * 100.0) / total;
}
}
