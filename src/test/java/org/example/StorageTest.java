package org.example;

import org.example.models.Product;
import org.example.models.Storage;
import org.example.utils.ProductCSVParser;
import org.example.utils.ProductCSVParserable;
import org.example.utils.ProductDividable;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class StorageTest {

    @BeforeAll
    static void prepareFile(){
        try {
            Files.createFile(Path.of(".","src","files","input","unit_test.csv"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @AfterAll
    static void cleanFiles(){
        try {
            Files.delete(Path.of(".", "src", "files", "output", "unit_test_1_res.csv"));
            Files.delete(Path.of(".", "src", "files", "output", "unit_test_2_res.csv"));
            Files.delete(Path.of(".", "src", "files", "input", "unit_test.csv"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void divideAndSave_shouldSaveDividedProductsToFilesCorrespondingToStoreName_whenCalled(){
        List<Product> products = new ArrayList<>();
        products.add(new Product("unit_test_1", "2", 3, 4));
        products.add(new Product("unit_test_1", "2", 4, 4));
        products.add(new Product("unit_test_2", "3", 5, 5));

        ProductDividable divider = Mockito.mock(ProductDividable.class);

        List<List<Product>> dividerToReturn = new ArrayList<>();

        List<Product> sublist1 = new ArrayList<>();
        sublist1.add(new Product("unit_test_1", "2", 3.5f, 8));

        List<Product> sublist2 = new ArrayList<>();
        sublist2.add(new Product("unit_test_2", "3", 5, 5));

        dividerToReturn.add(sublist1);
        dividerToReturn.add(sublist2);

        when(divider.divide(products)).thenReturn(dividerToReturn);

        ProductCSVParserable parser = Mockito.mock(ProductCSVParser.class);
        List<String> parserToReturn1 = new ArrayList<>();
        parserToReturn1.add("НАИМЕНОВАНИЕ;ЦЕНА;ШТ;");
        parserToReturn1.add("2;3.5;8;");

        List<String> parserToReturn2 = new ArrayList<>();
        parserToReturn2.add("НАИМЕНОВАНИЕ;ЦЕНА;ШТ;");
        parserToReturn2.add("3;5;5;");

        when(parser.parseALLToCSVWithoutStoreName(sublist1)).thenReturn(parserToReturn1);
        when(parser.parseALLToCSVWithoutStoreName(sublist2)).thenReturn(parserToReturn2);

        Storage storage = new Storage(products,parser,divider);
        storage.divideAndSave();

        boolean isFileExists = Files.exists(Path.of(".","src","files","output","unit_test_1_res.csv")) && Files.exists(Path.of(".","src","files","output","unit_test_2_res.csv"));

        Assertions.assertTrue(isFileExists, "Checking if divideAndSave creates files with correct names");
    }

    @Test
    public void readFromFiles_shouldAddProductsToList_whenCalled(){
        List<Product> expected = new ArrayList<>();
        expected.add(new Product("unit_test_1", "2", 3, 4));
        expected.add(new Product("unit_test_1", "2", 3, 4));

        List<Product> toCheck = new ArrayList<>();
        toCheck.add(new Product("unit_test_1", "2", 3, 4));

        ProductCSVParserable parser = Mockito.mock(ProductCSVParserable.class);
        when(parser.parseAllFromCSV(any())).thenReturn(toCheck);

        Storage storage = new Storage(new ArrayList<>(),parser,null);
        storage.readFromFiles(List.of("unit_test.csv","unit_test.csv"));
        Assertions.assertEquals(expected,storage.getProducts(),"Checking reading products from 2 files");
    }
}
