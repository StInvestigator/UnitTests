package org.example;

import org.example.models.Product;
import org.example.utils.FileManager;
import org.example.utils.ProductCSVParser;
import org.example.utils.ProductsDivider;

import java.util.List;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {/*
        List<Product> products = ProductCSVParser.parseAllFromCSV(FileManager.readLines("order_1.csv"));
        products.addAll(ProductCSVParser.parseAllFromCSV(FileManager.readLines("order_2.csv")));
        List<List<Product>> divided = ProductsDivider.divide(products);
        divided.forEach(x -> {
            if (!x.isEmpty()) {
                FileManager.writeLines(x.get(0)
                        .getStoreName() + "_res.csv", ProductCSVParser
                        .parseALLToCSVWithoutStoreName(x));
            }
        });*/
    }
}
