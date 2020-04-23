package mate.academy.internetshop.dao;

import java.util.ArrayList;
import java.util.List;
import mate.academy.internetshop.model.Product;

public class Storage {
    public static final List<Product> products = new ArrayList<>();
    private static long productsCount = 0;

    public static Product addNewItem(Product product) {
        product.setId(productsCount);
        productsCount++;
        products.add(product);
        return product;
    }
}
