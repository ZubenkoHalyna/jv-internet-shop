package mate.academy.internetshop.dao;

import java.util.List;
import java.util.Optional;
import mate.academy.internetshop.model.Order;

public interface OrderDao {
    Order create(Order order);

    Optional<Order> get(Long id);

    List<Order> getAll();

    List<Order> getByUser(Long userId);

    Order update(Order order);

    boolean delete(Long id);
}
