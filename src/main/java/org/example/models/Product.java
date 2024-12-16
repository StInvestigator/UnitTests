package org.example.models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Product {
    private final String storeName;
    private final String name;
    private float price;
    private int amount;
}
