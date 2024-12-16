package org.example;

import org.example.models.Product;
import org.example.models.Storage;
import org.example.utils.FileManager;
import org.example.utils.ProductCSVParser;
import org.example.utils.ProductsDivider;

import java.util.ArrayList;
import java.util.List;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        Storage storage = new Storage(new ArrayList<Product>(),new ProductCSVParser(), new ProductsDivider());
        storage.readFromFiles(List.of("order_1.csv","order_2.csv"));
        storage.divideAndSave();
    }
}
