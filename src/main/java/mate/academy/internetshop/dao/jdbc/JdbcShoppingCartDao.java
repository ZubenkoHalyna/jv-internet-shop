package mate.academy.internetshop.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import mate.academy.internetshop.dao.ProductDao;
import mate.academy.internetshop.dao.ShoppingCartDao;
import mate.academy.internetshop.dao.UserDao;
import mate.academy.internetshop.lib.Dao;
import mate.academy.internetshop.lib.Inject;
import mate.academy.internetshop.model.Product;
import mate.academy.internetshop.model.ShoppingCart;
import mate.academy.internetshop.util.ConnectionUtil;

@Dao
public class JdbcShoppingCartDao implements ShoppingCartDao {
    @Inject
    private UserDao userDao;
    @Inject
    private ProductDao productDao;

    @Override
    public Optional<ShoppingCart> getByUser(Long userId) {
        try (Connection conn = ConnectionUtil.getConnection()) {
            String query = "SELECT shopping_cart_id FROM shopping_carts WHERE user_id = ?";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setLong(1, userId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return Optional.of(getShoppingCart(
                        resultSet.getLong("shopping_cart_id"),
                        userId));
            }
            return Optional.empty();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ShoppingCart create(ShoppingCart shoppingCart) {
        try (Connection conn = ConnectionUtil.getConnection()) {
            String query = "INSERT INTO shopping_carts (user_id) VALUES (?)";
            PreparedStatement statement = conn.prepareStatement(query,
                    PreparedStatement.RETURN_GENERATED_KEYS);
            statement.setLong(1, shoppingCart.getUser().getId());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                shoppingCart.setId(resultSet.getLong(1));
            }
            saveProducts(shoppingCart, conn);
            return shoppingCart;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<ShoppingCart> get(Long id) {
        try (Connection conn = ConnectionUtil.getConnection()) {
            String query = "SELECT * FROM shopping_carts WHERE shopping_cart_id = ?";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return Optional.of(getShoppingCart(id, resultSet.getLong("user_id")));
            }
            return Optional.empty();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<ShoppingCart> getAll() {
        try (Connection conn = ConnectionUtil.getConnection()) {
            String query = "SELECT * FROM shopping_carts";
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            List<ShoppingCart> result = new ArrayList<>();
            while (resultSet.next()) {
                result.add(getShoppingCart(resultSet.getLong("shopping_cart_id"),
                        resultSet.getLong("user_id")));
            }
            return result;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ShoppingCart update(ShoppingCart shoppingCart) {
        try (Connection con = ConnectionUtil.getConnection()) {
            String query = "UPDATE shopping_carts SET user_id = ? "
                    + "WHERE shopping_cart_id = ?";
            PreparedStatement statement = con.prepareStatement(query);
            statement.setLong(1, shoppingCart.getUser().getId());
            statement.setLong(2, shoppingCart.getId());
            statement.executeUpdate();
            query = "DELETE FROM shopping_carts_products WHERE shopping_cart_id = ?";
            statement = con.prepareStatement(query);
            statement.setLong(1, shoppingCart.getId());
            statement.executeUpdate();
            saveProducts(shoppingCart, con);
            return get(shoppingCart.getId()).get();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean delete(Long id) {
        try (Connection conn = ConnectionUtil.getConnection()) {
            String query = "DELETE FROM shopping_carts WHERE shopping_cart_id = ?";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setLong(1, id);
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private ShoppingCart getShoppingCart(Long id, long userId) {
        return new ShoppingCart(id,
                productDao.getByShoppingCart(id),
                userDao.get(userId).get());
    }

    private boolean saveProducts(ShoppingCart cart, Connection con)
            throws SQLException {
        String query = "INSERT INTO shopping_carts_products "
                + "(shopping_cart_id, product_id) VALUES (?, ?)";
        PreparedStatement statement = con.prepareStatement(query);
        for (Product product : cart.getProducts()) {
            statement.setLong(1, cart.getId());
            statement.setLong(2, product.getId());
            statement.executeUpdate();
        }
        return true;
    }
}
