package mate.academy.internetshop.service;

import java.util.List;
import mate.academy.internetshop.model.Order;
import mate.academy.internetshop.model.Product;

public interface OrderService extends BaseService<Order, Long> {
    Order completeOrder(List<Product> products, Long userId);

    List<Order> getUserOrders(Long userId);
}
