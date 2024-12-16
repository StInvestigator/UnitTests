package org.example.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.example.utils.*;

import java.util.List;

@Data
@AllArgsConstructor
public class Storage {
    private List<Product> products;
    private ProductCSVParserable productCSVParser;
    private ProductDividable productDivider;

    public void readFromFiles(List<String> filenames) {
        filenames.forEach(filename -> products.addAll(productCSVParser.parseAllFromCSV(FileManager.readLines(filename))));
    }
    public void divideAndSave(){
        List<List<Product>> divided = productDivider.divide(products);
        divided.forEach(x -> {
            if (!x.isEmpty()) {
                FileManager.writeLines(x.get(0)
                        .getStoreName() + "_res.csv", productCSVParser
                        .parseALLToCSVWithoutStoreName(x));
            }
        });
    }
}
