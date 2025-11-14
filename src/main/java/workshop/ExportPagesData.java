package workshop;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Instant;

public class ExportPagesData {

    public static void main(String[] args) throws IOException {
        String repository   = env("GITHUB_REPOSITORY", "unknown/repo");
        String branch       = env("GITHUB_REF_NAME",  "local");
        String commit       = env("COMMIT_SHA",       env("GITHUB_SHA", "unknown"));
        String shortSha     = commit.length() >= 12 ? commit.substring(0, 12) : commit;
        String runId        = env("GITHUB_RUN_ID",    "0");
        String runUrl       = env("RUN_URL",          "https://example.invalid");
        String buildVersion = env("BUILD_VERSION",    "local");
        String builtAt      = Instant.now().toString();
        String imageName    = env("IMAGE_NAME",       "ellimen/esd");
        String imageLatest  = imageName + ":latest";
        String imageShaTag  = imageName + ":" + shortSha;
        String trivyHigh    = env("TRIVY_HIGH", "0");
        String trivyCritical= env("TRIVY_CRITICAL", "0");

        Path docs = Path.of("docs");
        Files.createDirectories(docs);

        // Build JSON without format()
        String json =
            "{\n" +
            "  \"repository\": \"" + j(repository) + "\",\n" +
            "  \"branch\": \"" + j(branch) + "\",\n" +
            "  \"commit\": \"" + j(commit) + "\",\n" +
            "  \"shortSha\": \"" + j(shortSha) + "\",\n" +
            "  \"buildVersion\": \"" + j(buildVersion) + "\",\n" +
            "  \"builtAt\": \"" + j(builtAt) + "\",\n" +
            "  \"runId\": \"" + j(runId) + "\",\n" +
            "  \"runUrl\": \"" + j(runUrl) + "\",\n" +
            "  \"imageLatest\": \"" + j(imageLatest) + "\",\n" +
            "  \"imageShaTag\": \"" + j(imageShaTag) + "\",\n" +
            "  \"security\": { \"trivyHigh\": " + trivyHigh + ", \"trivyCritical\": " + trivyCritical + " }\n" +
            "}\n";

        Files.writeString(docs.resolve("data.json"), json, StandardCharsets.UTF_8);

        // Build HTML without format(); keep % literals intact
        String html = """
          <!doctype html>
          <html lang="en">
          <head>
            <meta charset="utf-8">
            <title>Build Dashboard</title>
            <meta name="viewport" content="width=device-width, initial-scale=1" />
            <style>
              :root { font-family: ui-sans-serif, system-ui, -apple-system, Segoe UI, Roboto, Arial; }
              body { margin: 0; padding: 2rem; background: #fafafa; }
              .card { max-width: 920px; margin: 0 auto; padding: 1.5rem; border: 1px solid #eee; border-radius: 12px; background: #fff; box-shadow: 0 2px 16px rgba(0,0,0,.06); }
              h1 { margin: 0 0 .25rem 0; }
              .meta { color: #666; font-size: .9rem; margin: 0 0 1rem 0; }
              table { border-collapse: collapse; width: 100%; margin: 1rem 0; }
              th, td { text-align: left; padding: .5rem .6rem; border-bottom: 1px solid #eee; }
              code, pre { background: #f4f4f4; padding: .2rem .4rem; border-radius: 6px; }
              .row { display: grid; grid-template-columns: 1fr 1fr; gap: 1rem; }
              @media (max-width: 760px) { .row { grid-template-columns: 1fr; } }
              .ok { color: #065f46; background: #ecfdf5; border: 1px solid #a7f3d0; display:inline-block; padding:.2rem .5rem; border-radius:8px; font-weight:600;}
              .warn { color: #92400e; background: #fffbeb; border: 1px solid #fde68a; display:inline-block; padding:.2rem .5rem; border-radius:8px; font-weight:600;}
            </style>
          </head>
          <body>
            <div class="card">
              <h1>✅ Build succeeded</h1>
        """ +
        "<p class=\"meta\">Built at <strong>" + h(builtAt) + "</strong> • Run <a href=\"" + h(runUrl) + "\">#" + h(runId) + "</a></p>\n" +
        """
              <div class="row">
                <div>
                  <table>
        """ +
        row("Repository", h(repository)) +
        row("Branch", "<code>" + h(branch) + "</code>") +
        row("Commit", "<code>" + h(commit) + "</code> (<code>" + h(shortSha) + "</code>)") +
        row("Build version", "<code>" + h(buildVersion) + "</code>") +
        """
                  </table>
                </div>
                <div>
                  <table>
        """ +
        row("Image (latest)", "<code>" + h(imageLatest) + "</code>") +
        row("Image (sha)", "<code>" + h(imageShaTag) + "</code>") +
        row("Security (Trivy)",
            "<span class=\"ok\">High: " + h(trivyHigh) + "</span> &nbsp; " +
            "<span class=\"warn\">Critical: " + h(trivyCritical) + "</span>") +
        """
                  </table>
                </div>
              </div>

              <h3>Pull & run locally</h3>
        """ +
        "<pre>docker pull " + h(imageShaTag) + "\n" +
        "docker run -p 8080:8080 -e APP_COMMIT=" + h(shortSha) + " " + h(imageShaTag) + "</pre>\n" +
        """
              <p>Programmatic data: <a href="./data.json">data.json</a></p>
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
    private static String h(String s) { return s.replace("&","&amp;").replace("<","&lt;").replace(">","&gt;"); }
    private static String j(String s) { return s.replace("\\","\\\\").replace("\"","\\\""); }

    private static String row(String k, String v) {
        return "                    <tr><th>" + k + "</th><td>" + v + "</td></tr>\n";
    }
}
