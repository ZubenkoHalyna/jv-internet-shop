package mate.academy.internetshop.dao;

import java.util.Optional;
import mate.academy.internetshop.model.ShoppingCart;

public interface ShoppingCartDao extends BaseDao<ShoppingCart, Long> {
    Optional<ShoppingCart> getByUser(Long userId);
}
