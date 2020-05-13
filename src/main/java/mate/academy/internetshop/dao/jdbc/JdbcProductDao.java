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
import mate.academy.internetshop.lib.Dao;
import mate.academy.internetshop.model.Product;
import mate.academy.internetshop.util.ConnectionUtil;

@Dao
public class JdbcProductDao implements ProductDao {
    @Override
    public Product create(Product product) {
        try (Connection con = ConnectionUtil.getConnection()) {
            String query = "INSERT INTO products (name, price) VALUES (?, ?)";
            PreparedStatement statement = con.prepareStatement(query,
                    PreparedStatement.RETURN_GENERATED_KEYS);
            statement.setString(1, product.getName());
            statement.setBigDecimal(2, product.getPrice());
            statement.executeUpdate();
            ResultSet result = statement.getGeneratedKeys();
            result.next();
            product.setId(result.getLong(1));
            return product;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<Product> get(Long id) {
        try (Connection con = ConnectionUtil.getConnection()) {
            String query = "SELECT * FROM products WHERE product_id = ?";
            PreparedStatement statement = con.prepareStatement(query);
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return Optional.of(getProduct(resultSet));
            }
            return Optional.empty();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Product> getAll() {
        try (Connection con = ConnectionUtil.getConnection()) {
            String query = "SELECT * FROM products";
            Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            List<Product> list = new ArrayList<>();
            while (resultSet.next()) {
                list.add(getProduct(resultSet));
            }
            return list;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Product update(Product product) {
        try (Connection con = ConnectionUtil.getConnection()) {
            String query = "UPDATE products SET name = ?, price = ? WHERE product_id = ?";
            PreparedStatement statement = con.prepareStatement(query);
            statement.setString(1, product.getName());
            statement.setBigDecimal(2, product.getPrice());
            statement.setLong(3, product.getId());
            statement.executeUpdate();
            return product;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean delete(Long id) {
        try (Connection con = ConnectionUtil.getConnection()) {
            String query = "DELETE FROM products WHERE product_id = ?";
            PreparedStatement statement = con.prepareStatement(query);
            statement.setLong(1, id);
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Product> getByOrder(Long orderId) {
        return getProductsBySomeId("SELECT product_id, name, price FROM products "
                        + "JOIN orders_products USING (product_id) WHERE order_id = ?",
                orderId);
    }

    @Override
    public List<Product> getByShoppingCart(Long shoppingCartId) {
        return getProductsBySomeId("SELECT products.product_id, name, price "
                + "FROM products JOIN shopping_carts_products USING (product_id) "
                + "WHERE shopping_cart_id = ?", shoppingCartId);
    }

    private List<Product> getProductsBySomeId(String query, Long id) {
        try (Connection con = ConnectionUtil.getConnection()) {
            PreparedStatement statement = con.prepareStatement(query);
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            List<Product> list = new ArrayList<>();
            while (resultSet.next()) {
                list.add(getProduct(resultSet));
            }
            return list;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private Product getProduct(ResultSet resultSet) throws SQLException {
        return new Product(resultSet.getLong("product_id"),
                resultSet.getString("name"),
                resultSet.getBigDecimal("price"));
    }
}
