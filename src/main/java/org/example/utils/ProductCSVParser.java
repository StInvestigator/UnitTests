package org.example.utils;

import org.example.models.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ProductCSVParser implements ProductCSVParserable {
    public List<Product> parseAllFromCSV(List<String> csv) {
        if (csv.isEmpty()) {
            return new ArrayList<>();
        }
        csv.remove(0);
        return csv.stream().filter(s -> s.split(";").length == 4).map(s -> {
            String[] parts = s.split(";");
            return new Product(parts[0], parts[1],
                    Float.parseFloat(parts[2]), Integer.parseInt(parts[3]));
        }).collect(Collectors.toList());
    }

    public List<String> parseALLToCSVWithoutStoreName(List<Product> products) {
        List<String> csv = new ArrayList<>();
        csv.add("НАИМЕНОВАНИЕ;ЦЕНА;ШТ;");
        products.forEach(product -> csv.add(product.getName() + ";" + product.getPrice() + ";" + product.getAmount() + ";"));
        return csv;
    }
}
