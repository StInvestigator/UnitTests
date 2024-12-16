package org.example;

import org.example.models.Product;
import org.example.utils.ProductsDivider;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class ProductsDividerTest {
    @Test
    public void divide_shouldDivideProductListByStoreNameAndMergeSameProducts_whenCalled(){
        List<List<Product>> expected = new ArrayList<>();

        List<Product> sublist1 = new ArrayList<>();
        sublist1.add(new Product("1", "6", 7.5f, 18));

        List<Product> sublist2 = new ArrayList<>();
        sublist2.add(new Product("2", "2", 3, 4));

        expected.add(sublist1);
        expected.add(sublist2);

        List<Product> toCheck = new ArrayList<>();
        toCheck.add(new Product("1", "6", 7, 8));
        toCheck.add(new Product("1", "6", 8, 10));
        toCheck.add(new Product("2", "2", 3, 4));

        ProductsDivider divider = new ProductsDivider();
        var actual = divider.divide(toCheck);

        Assertions.assertEquals(expected, actual,"Checking product dividing");
    }
}
