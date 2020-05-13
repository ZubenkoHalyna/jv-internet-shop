package mate.academy.internetshop.dao;

import java.util.List;
import mate.academy.internetshop.model.Product;

public interface ProductDao extends BaseDao<Product, Long> {
    public List<Product> getByOrder(Long orderId);

    public List<Product> getByShoppingCart(Long shoppingCartId);
}
