package workshop;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Instant;
import java.util.Map;

public class ExportPagesData {

    public static void main(String[] args) throws IOException {
        // Inputs from workflow env (all have safe defaults for local runs)
        String repository   = env("GITHUB_REPOSITORY", "unknown/repo");
        String branch       = env("GITHUB_REF_NAME",  "local");
        String commit       = env("COMMIT_SHA",       env("GITHUB_SHA", "unknown"));
        String shortSha     = commit.length() >= 12 ? commit.substring(0, 12) : commit;
        String runId        = env("GITHUB_RUN_ID",    "0");
        String runUrl       = env("RUN_URL",          "https://example.invalid");
        String buildVersion = env("BUILD_VERSION",    "local");  // we already set this in Gradle task
        String builtAt      = Instant.now().toString();

        // Docker image info (adjust IMAGE_NAME default to your real image on Docker Hub or GHCR)
        String imageName    = env("IMAGE_NAME", "ellimen/esd");
        String imageLatest  = imageName + ":latest";
        String imageShaTag  = imageName + ":" + shortSha;

        // Optional security placeholders (can wire later)
        String trivyHigh    = env("TRIVY_HIGH", "0");
        String trivyCritical= env("TRIVY_CRITICAL", "0");

        // Ensure ./docs exists
        Path docs = Path.of("docs");
        Files.createDirectories(docs);

        // JSON for programmatic consumption
        String json = """
          {
            "repository": "%s",
            "branch": "%s",
            "commit": "%s",
            "shortSha": "%s",
            "buildVersion": "%s",
            "builtAt": "%s",
            "runId": "%s",
            "runUrl": "%s",
            "imageLatest": "%s",
            "imageShaTag": "%s",
            "security": { "trivyHigh": %s, "trivyCritical": %s }
          }
          """.formatted(
                escape(repository), escape(branch), escape(commit), escape(shortSha),
                escape(buildVersion), escape(builtAt), escape(runId), escape(runUrl),
                escape(imageLatest), escape(imageShaTag), trivyHigh, trivyCritical
          );

        Files.writeString(docs.resolve("data.json"), json, StandardCharsets.UTF_8);

        // Simple dashboard HTML (no external JS/CSS)
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
              <p class="meta">Built at <strong>%s</strong> • Run <a href="%s">#%s</a></p>

              <div class="row">
                <div>
                  <table>
                    <tr><th>Repository</th><td>%s</td></tr>
                    <tr><th>Branch</th><td><code>%s</code></td></tr>
                    <tr><th>Commit</th><td><code>%s</code> (<code>%s</code>)</td></tr>
                    <tr><th>Build version</th><td><code>%s</code></td></tr>
                  </table>
                </div>
                <div>
                  <table>
                    <tr><th>Image (latest)</th><td><code>%s</code></td></tr>
                    <tr><th>Image (sha)</th><td><code>%s</code></td></tr>
                    <tr><th>Security (Trivy)</th><td>
                      <span class="%s">High: %s</span>
                      &nbsp; <span class="%s">Critical: %s</span>
                    </td></tr>
                  </table>
                </div>
              </div>

              <h3>Pull & run locally</h3>
              <pre>docker pull %s
docker run -p 8080:8080 -e APP_COMMIT=%s %s</pre>

              <p>Programmatic data: <a href="./data.json">data.json</a></p>
            </div>
          </body>
          </html>
          """.formatted(
                esc(builtAt), esc(runUrl), esc(runId),
                esc(repository), esc(branch), esc(commit), esc(shortSha),
                esc(buildVersion),
                esc(imageLatest), esc(imageShaTag),
                "ok", esc(trivyHigh), "warn", esc(trivyCritical),
                esc(imageShaTag), esc(shortSha), esc(imageShaTag)
          );

        Files.writeString(docs.resolve("index.html"), html, StandardCharsets.UTF_8);
    }

    private static String env(String k, String def) {
        String v = System.getenv(k);
        return (v == null || v.isBlank()) ? def : v;
    }
    // very light escaping for HTML/JSON templates
    private static String esc(String s) { return s.replace("&","&amp;").replace("<","&lt;").replace(">","&gt;"); }
    private static String escape(String s) { return s.replace("\\","\\\\").replace("\"","\\\""); }
}

