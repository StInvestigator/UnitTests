package org.example;

import org.example.models.Product;
import org.example.utils.ProductCSVParser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class ProductCSVParserTest {
    @Test
    void parseAllFromCSV_shouldReturnListOfProducts_whenCalled() {
        List<Product> expected = new ArrayList<>();
        expected.add(new Product("1", "2", 3, 4));
        expected.add(new Product("5", "6", 7, 8));

        List<String> toCheck = new ArrayList<>();
        toCheck.add("name;name;name;name;");
        toCheck.add("1;2;3;4;");
        toCheck.add("5;6;7;8;");

        ProductCSVParser parser = new ProductCSVParser();

        List<Product> actual = parser.parseAllFromCSV(toCheck);
        Assertions.assertEquals(expected, actual, "Checking parsing from cvs");
    }

    @Test
    void parseAllFromCSV_shouldReadOnlyCorrectlyWritten_whenWrongFormatGiven() {
        List<Product> expected = new ArrayList<>();
        expected.add(new Product("5", "6", 7, 8));

        List<String> toCheck = new ArrayList<>();
        toCheck.add("name;name;name;name;");
        toCheck.add("1;3;4");
        toCheck.add("5;6;7;8;");

        ProductCSVParser parser = new ProductCSVParser();

        List<Product> actual = parser.parseAllFromCSV(toCheck);
        Assertions.assertEquals(expected, actual, "Checking parsing from cvs when wrong format given");
    }

    @Test
    void parseAllFromCSV_shouldReturnEmptyList_whenEmptyCSVGiven() {
        ProductCSVParser parser = new ProductCSVParser();

        Assertions.assertEquals(0,
                parser.parseAllFromCSV(new ArrayList<>()).size(),
                "Checking parsing from cvs when empty cvs given");
    }

    @Test
    void parseALLToCSVWithoutStoreName_shouldReturnCSVStringListWithoutStoreName_whenCalled() {
        List<String> expected = new ArrayList<>();
        expected.add("НАИМЕНОВАНИЕ;ЦЕНА;ШТ;");
        expected.add("1;2.0;3;");

        List<Product> toCheck = new ArrayList<>();
        toCheck.add(new Product("0", "1", 2, 3));

        ProductCSVParser parser = new ProductCSVParser();

        List<String> actual = parser.parseALLToCSVWithoutStoreName(toCheck);

        Assertions.assertEquals(expected, actual, "Checking parsing products to cvs");
    }
}
