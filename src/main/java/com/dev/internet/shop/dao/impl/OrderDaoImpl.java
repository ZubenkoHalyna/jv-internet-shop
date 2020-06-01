package com.dev.internet.shop.dao.impl;

import com.dev.internet.shop.dao.OrderDao;
import com.dev.internet.shop.db.Storage;
import com.dev.internet.shop.model.Order;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class OrderDaoImpl implements OrderDao {
    @Override
    public Order create(Order order) {
        Storage.addNewOrder(order);
        return order;
    }

    @Override
    public Optional<Order> get(Long id) {
        return Storage.orders.stream()
                .filter(it -> it.getId().equals(id))
                .findAny();
    }

    @Override
    public List<Order> getAll() {
        return Storage.orders;
    }

    @Override
    public List<Order> getByUser(Long userId) {
        return Storage.orders.stream()
                .filter(order -> order.getUserId().equals(userId))
                .collect(Collectors.toList());
    }

    @Override
    public Order update(Order order) {
        Order oldOrder = get(order.getId()).get();
        if (!oldOrder.getUserId().equals(order.getUserId())) {
            oldOrder.setUserId(order.getUserId());
        }
        if (!oldOrder.getProducts().equals(order.getProducts())) {
            oldOrder.setProducts(new ArrayList<>(order.getProducts()));
        }
        return oldOrder;
    }

    @Override
    public boolean delete(Long id) {
        Optional<Order> product = get(id);
        product.ifPresent(Storage.orders::remove);
        return product.isPresent();
    }
}
