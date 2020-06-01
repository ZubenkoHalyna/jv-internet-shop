package com.dev.internet.shop.service;

import com.dev.internet.shop.model.Order;
import com.dev.internet.shop.model.Product;
import java.util.List;

public interface OrderService extends BaseService<Order, Long> {
    Order completeOrder(List<Product> products, Long userId);

    List<Order> getUserOrders(Long userId);
}
