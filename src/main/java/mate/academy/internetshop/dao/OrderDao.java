package mate.academy.internetshop.dao;

import java.util.List;
import mate.academy.internetshop.model.Order;

public interface OrderDao extends BaseDao<Order, Long> {
    List<Order> getByUser(Long userId);
}
