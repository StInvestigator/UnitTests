package org.example.utils;

import org.example.models.Product;

import java.util.List;

public interface ProductDividable {
    List<List<Product>> divide(List<Product> products);
}
