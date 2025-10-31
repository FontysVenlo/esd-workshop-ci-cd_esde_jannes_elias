package com.example;

import java.nio.file.*;
import java.nio.charset.StandardCharsets;

public class ExportPagesData {
    public static void main(String[] args) throws Exception {
        App app = new App(); // we just use it as a value holder
        String message = app.getStatus();
        String version = System.getenv().getOrDefault("BUILD_VERSION", "dev");
        String imageUrl = "61avdr.webp";

        String json = "{\n" +
                "  \"message\": \"" + escape(message) + "\",\n" +
                "  \"version\": \"" + escape(version) + "\"\n" +
                "  \"image\": \"" + escape(imageUrl) + "\"\n" +
                "}\n";

        Path docs = Paths.get("docs");
        Files.createDirectories(docs);
        Files.write(docs.resolve("data.json"), json.getBytes(StandardCharsets.UTF_8));
        System.out.println("Wrote docs/data.json: " + json);
    }

    private static String escape(String s) {
        return s.replace("\\", "\\\\").replace("\"", "\\\"");
    }
}
