package org.example;

import org.example.utils.FileManager;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class FileManagerTest {
    @BeforeAll
    static void prepareFile(){
        try (Writer writer = new FileWriter(Path.of(".", "src", "files", "input", "unit_test1.csv").toFile())) {
            writer.append("""
                    ИМЯ;НАИМЕНОВАНИЕ;ЦЕНА;ШТ;
                    АТБ;Гречка;31.25;120;
                    Сильпо;Мука;22.2;99;""");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    @AfterAll
    static void cleanFiles(){
        try {
            Files.delete(Path.of(".", "src", "files", "input", "unit_test1.csv"));
            Files.delete(Path.of(".", "src", "files", "output", "unit_test2.csv"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void readLines_shouldReturnListOfStrings_whenCalled(){
        List<String> expected = new ArrayList<>();
        expected.add("ИМЯ;НАИМЕНОВАНИЕ;ЦЕНА;ШТ;");
        expected.add("АТБ;Гречка;31.25;120;");
        expected.add("Сильпо;Мука;22.2;99;");

        List<String> actual = FileManager.readLines("unit_test1.csv");

        Assertions.assertLinesMatch(expected, actual, "Checking lines reading from file");
    }

    @Test
    void readLines_shouldReturnEmptyList_whenFileDoesNotExist(){
        List<String> actual = FileManager.readLines("unit_wrong_file.csv");

        Assertions.assertEquals(0, actual.size(), "Checking lines reading from not existing file");
    }

    @Test
    void writeLines_shouldWriteListOfStringsToFile_whenCalled(){
        List<String> expected = new ArrayList<>();
        expected.add("ИМЯ;НАИМЕНОВАНИЕ;ЦЕНА;ШТ;");
        expected.add("АТБ;Гречка;31.25;120;");
        expected.add("Сильпо;Мука;22.2;99;");

        FileManager.writeLines("unit_test2.csv",expected);
        List<String> actual;
        try {
            actual = Files.readAllLines(Path.of(".", "src", "files", "output", "unit_test2.csv"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Assertions.assertLinesMatch(expected, actual, "Checking lines writing to file");
    }
}
