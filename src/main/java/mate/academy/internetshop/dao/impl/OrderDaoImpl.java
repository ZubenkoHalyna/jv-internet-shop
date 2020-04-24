package mate.academy.internetshop.dao.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import mate.academy.internetshop.dao.OrderDao;
import mate.academy.internetshop.db.Storage;
import mate.academy.internetshop.lib.Dao;
import mate.academy.internetshop.model.Order;

@Dao
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
                .filter(order -> order.getUser().getId().equals(userId))
                .collect(Collectors.toList());
    }

    @Override
    public Order update(Order order) {
        delete(order.getId());
        Storage.orders.add(order);
        return order;
    }

    @Override
    public boolean delete(Long id) {
        Optional<Order> product = get(id);
        product.ifPresent(Storage.orders::remove);
        return product.isPresent();
    }
}