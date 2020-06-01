package com.dev.internet.shop.dao;

import com.dev.internet.shop.model.Product;
import java.util.List;

public interface ProductDao extends BaseDao<Product, Long> {
    List<Product> getByOrder(Long orderId);

    List<Product> getByShoppingCart(Long shoppingCartId);
}
