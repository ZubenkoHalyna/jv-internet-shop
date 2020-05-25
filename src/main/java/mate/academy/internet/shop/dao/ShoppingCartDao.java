package mate.academy.internet.shop.dao;

import java.util.Optional;
import mate.academy.internet.shop.model.ShoppingCart;

public interface ShoppingCartDao extends BaseDao<ShoppingCart, Long> {
    Optional<ShoppingCart> getByUser(Long userId);
}
