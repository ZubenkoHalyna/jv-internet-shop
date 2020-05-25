package mate.academy.internet.shop.service;

import java.util.List;
import mate.academy.internet.shop.model.Order;
import mate.academy.internet.shop.model.Product;

public interface OrderService extends BaseService<Order, Long> {
    Order completeOrder(List<Product> products, Long userId);

    List<Order> getUserOrders(Long userId);
}
