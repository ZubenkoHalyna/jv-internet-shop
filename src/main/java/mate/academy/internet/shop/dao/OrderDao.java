package mate.academy.internet.shop.dao;

import java.util.List;
import mate.academy.internet.shop.model.Order;

public interface OrderDao extends BaseDao<Order, Long> {
    List<Order> getByUser(Long userId);
}
