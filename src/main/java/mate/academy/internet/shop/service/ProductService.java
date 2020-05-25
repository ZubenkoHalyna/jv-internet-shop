package mate.academy.internet.shop.service;

import mate.academy.internet.shop.model.Product;

public interface ProductService extends BaseService<Product, Long> {
    Product create(Product product);

    Product update(Product product);
}
