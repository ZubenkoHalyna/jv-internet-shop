package com.dev.internet.shop.dao;

import com.dev.internet.shop.model.ShoppingCart;
import java.util.Optional;

public interface ShoppingCartDao extends BaseDao<ShoppingCart, Long> {
    Optional<ShoppingCart> getByUser(Long userId);
}
