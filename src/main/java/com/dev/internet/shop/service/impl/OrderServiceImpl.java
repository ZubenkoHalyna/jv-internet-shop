package com.dev.internet.shop.service.impl;

import com.dev.internet.shop.dao.OrderDao;
import com.dev.internet.shop.lib.Inject;
import com.dev.internet.shop.lib.Service;
import com.dev.internet.shop.model.Order;
import com.dev.internet.shop.model.Product;
import com.dev.internet.shop.service.OrderService;
import com.dev.internet.shop.service.ShoppingCartService;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Inject
    private OrderDao orderDao;
    @Inject
    private ShoppingCartService shoppingCartService;

    @Override
    public Order completeOrder(List<Product> products, Long userId) {
        Order order = orderDao.create(new Order(products, userId));
        shoppingCartService.clear(shoppingCartService.getByUserId(userId));
        return order;
    }

    @Override
    public List<Order> getUserOrders(Long userId) {
        return orderDao.getByUser(userId);
    }

    @Override
    public Order get(Long id) {
        return orderDao.get(id).get();
    }

    @Override
    public List<Order> getAll() {
        return orderDao.getAll();
    }

    @Override
    public boolean delete(Long id) {
        return orderDao.delete(id);
    }
}
