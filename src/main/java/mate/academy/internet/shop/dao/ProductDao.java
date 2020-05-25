package mate.academy.internet.shop.dao;

import java.util.List;
import mate.academy.internet.shop.model.Product;

public interface ProductDao extends BaseDao<Product, Long> {
    List<Product> getByOrder(Long orderId);

    List<Product> getByShoppingCart(Long shoppingCartId);
}
