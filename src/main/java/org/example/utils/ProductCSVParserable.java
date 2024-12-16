package org.example.utils;

import org.example.models.Product;

import java.util.List;

public interface ProductCSVParserable {
    List<Product> parseAllFromCSV(List<String> csv);
    List<String> parseALLToCSVWithoutStoreName(List<Product> products);
}
