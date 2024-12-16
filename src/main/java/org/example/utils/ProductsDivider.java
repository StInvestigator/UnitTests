package org.example.utils;

import org.example.models.Product;

import java.util.List;
import java.util.stream.Collectors;

public class ProductsDivider implements ProductDividable{
    public List<List<Product>> divide(List<Product> products) {
        return products.stream()
                .collect(Collectors.groupingBy(Product::getStoreName))
                .values()
                .stream()
                .map(storeProducts -> storeProducts.stream()
                        .collect(Collectors.groupingBy(Product::getName))
                        .entrySet().stream()
                        .map(entry -> {
                            String name = entry.getKey();
                            List<Product> productList = entry.getValue();

                            int totalAmount = productList.stream().mapToInt(Product::getAmount).sum();
                            float averagePrice = (float) productList.stream()
                                    .mapToDouble(Product::getPrice)
                                    .sum() / productList.size();

                            return new Product(productList.get(0).getStoreName(), name, averagePrice, totalAmount);
                        })
                        .collect(Collectors.toList()))
                .collect(Collectors.toList());
    }
}
