package com.dev.internet.shop.service;

import com.dev.internet.shop.model.Product;

public interface ProductService extends BaseService<Product, Long> {
    Product create(Product product);

    Product update(Product product);
}
