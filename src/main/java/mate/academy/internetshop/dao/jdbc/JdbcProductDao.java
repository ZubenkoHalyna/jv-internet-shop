package mate.academy.internetshop.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import mate.academy.internetshop.controller.LoginController;
import mate.academy.internetshop.dao.ProductDao;
import mate.academy.internetshop.lib.Dao;
import mate.academy.internetshop.model.Product;
import mate.academy.internetshop.util.ConnectionUtil;
import org.apache.log4j.Logger;

@Dao
public class JdbcProductDao implements ProductDao {
    private static final Logger LOGGER = Logger.getLogger(LoginController.class);

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
            String query = "SELECT * FROM products WHERE id = ?";
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
            String query = "UPDATE products SET name = ?, price = ? WHERE id = ?";
            PreparedStatement statement = con.prepareStatement(query);
            statement.setString(1, product.getName());
            statement.setBigDecimal(2, product.getPrice());
            statement.setLong(3, product.getId());
            if (statement.executeUpdate() > 0) {
                return get(product.getId()).get();
            }
            String msg = "Enable to update product with id " + product.getId();
            LOGGER.error(msg);
            throw new RuntimeException(msg);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean delete(Long id) {
        try (Connection con = ConnectionUtil.getConnection()) {
            String query = "DELETE FROM products WHERE id = ?";
            PreparedStatement statement = con.prepareStatement(query);
            statement.setLong(1, id);
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private Product getProduct(ResultSet resultSet) throws SQLException {
        return new Product(resultSet.getLong("id"),
                resultSet.getString("name"),
                resultSet.getBigDecimal("price"));
    }
}
