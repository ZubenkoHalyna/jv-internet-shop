package mate.academy.internet.shop.service.impl;

import java.util.List;
import mate.academy.internet.shop.dao.OrderDao;
import mate.academy.internet.shop.lib.Inject;
import mate.academy.internet.shop.lib.Service;
import mate.academy.internet.shop.model.Order;
import mate.academy.internet.shop.model.Product;
import mate.academy.internet.shop.service.OrderService;
import mate.academy.internet.shop.service.ShoppingCartService;

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
