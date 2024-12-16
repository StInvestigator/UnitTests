package org.example.utils;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class FileManager {
    static public List<String> readLines(String filename) {
        List<String> lines = new ArrayList<>();
        try {
            lines = Files.readAllLines(Path.of(".", "src", "files", "input", filename));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return lines;
    }

    static public void writeLines(String filename, List<String> lines) {
        try (Writer writer = new FileWriter(Path.of(".", "src", "files", "output", filename).toFile())) {
            lines.forEach(x -> {
                try {
                    writer.append(x).append("\n");
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            });
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
