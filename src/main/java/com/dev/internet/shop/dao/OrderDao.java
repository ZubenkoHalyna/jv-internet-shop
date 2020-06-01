package com.dev.internet.shop.dao;

import com.dev.internet.shop.model.Order;
import java.util.List;

public interface OrderDao extends BaseDao<Order, Long> {
    List<Order> getByUser(Long userId);
}
