package mate.academy.internet.shop.service;

import java.util.List;
import mate.academy.internet.shop.model.Product;
import mate.academy.internet.shop.model.ShoppingCart;

public interface ShoppingCartService extends BaseService<ShoppingCart, Long> {
    ShoppingCart addProduct(ShoppingCart shoppingCart, Product product);

    boolean deleteProduct(ShoppingCart shoppingCart, Product product);

    void clear(ShoppingCart shoppingCart);

    ShoppingCart getByUserId(Long userId);

    List<Product> getAllProducts(ShoppingCart shoppingCart);
}
