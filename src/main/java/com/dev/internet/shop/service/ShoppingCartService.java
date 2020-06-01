package com.dev.internet.shop.service;

import com.dev.internet.shop.model.Product;
import com.dev.internet.shop.model.ShoppingCart;
import java.util.List;

public interface ShoppingCartService extends BaseService<ShoppingCart, Long> {
    ShoppingCart addProduct(ShoppingCart shoppingCart, Product product);

    boolean deleteProduct(ShoppingCart shoppingCart, Product product);

    void clear(ShoppingCart shoppingCart);

    ShoppingCart getByUserId(Long userId);

    List<Product> getAllProducts(ShoppingCart shoppingCart);
}
