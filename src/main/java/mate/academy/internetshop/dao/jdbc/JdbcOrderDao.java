package mate.academy.internetshop.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import mate.academy.internetshop.dao.OrderDao;
import mate.academy.internetshop.dao.ProductDao;
import mate.academy.internetshop.lib.Dao;
import mate.academy.internetshop.lib.Inject;
import mate.academy.internetshop.model.Order;
import mate.academy.internetshop.model.Product;
import mate.academy.internetshop.util.ConnectionUtil;

@Dao
public class JdbcOrderDao implements OrderDao {
    @Inject
    private ProductDao productDao;

    @Override
    public List<Order> getByUser(Long userId) {
        try (Connection con = ConnectionUtil.getConnection()) {
            String query = "SELECT order_id FROM orders WHERE user_id = ?";
            PreparedStatement statement = con.prepareStatement(query);
            statement.setLong(1, userId);
            ResultSet resultSet = statement.executeQuery();
            List<Order> orders = new ArrayList<>();
            while (resultSet.next()) {
                orders.add(getOrder(resultSet.getLong("order_id"), userId));
            }
            return orders;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Order create(Order order) {
        try (Connection con = ConnectionUtil.getConnection()) {
            String query = "INSERT INTO orders (user_id) VALUE (?)";
            PreparedStatement statement = con.prepareStatement(query,
                    PreparedStatement.RETURN_GENERATED_KEYS);
            statement.setLong(1, order.getUserId());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                order.setId(resultSet.getLong(1));
            }
            saveProducts(order, con);
            return order;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<Order> get(Long id) {
        try (Connection con = ConnectionUtil.getConnection()) {
            String query = "SELECT user_id FROM orders WHERE order_id = ?";
            PreparedStatement statement = con.prepareStatement(query);
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return Optional.of(getOrder(id, resultSet.getLong("user_id")));
            }
            return Optional.empty();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Order> getAll() {
        try (Connection con = ConnectionUtil.getConnection()) {
            String query = "SELECT * FROM orders";
            Statement statement = con.createStatement();
            List<Order> orders = new ArrayList<>();
            if (statement.execute(query)) {
                ResultSet resultSet = statement.getResultSet();
                while (resultSet.next()) {
                    orders.add(getOrder(resultSet.getLong("order_id"),
                            resultSet.getLong("user_id")));
                }
            }
            return orders;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Order update(Order order) {
        try (Connection con = ConnectionUtil.getConnection()) {
            String query = "DELETE FROM orders_products WHERE order_id = ?";
            PreparedStatement statement = con.prepareStatement(query);
            statement.setLong(1, order.getId());
            statement.executeUpdate();
            saveProducts(order, con);
            return order;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean delete(Long id) {
        try (Connection con = ConnectionUtil.getConnection()) {
            String query = "DELETE FROM orders WHERE order_id = ?";
            PreparedStatement statement = con.prepareStatement(query);
            statement.setLong(1, id);
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private Order getOrder(Long id, Long userId) {
        return new Order(id, productDao.getByOrder(id), userId);
    }

    private boolean saveProducts(Order order, Connection con) throws SQLException {
        String query = "INSERT INTO orders_products (order_id, product_id) VALUES (?, ?)";
        PreparedStatement statement = con.prepareStatement(query);
        for (Product product : order.getProducts()) {
            statement.setLong(1, order.getId());
            statement.setLong(2, product.getId());
            statement.executeUpdate();
        }
        return true;
    }
}
