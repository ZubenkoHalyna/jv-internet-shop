package com.dev.internet.shop.dao.jdbc;

import com.dev.internet.shop.dao.UserDao;
import com.dev.internet.shop.lib.Dao;
import com.dev.internet.shop.model.Role;
import com.dev.internet.shop.model.User;
import com.dev.internet.shop.util.ConnectionUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Dao
public class UserDaoJdbc implements UserDao {
    @Override
    public Optional<User> getByLogin(String login) {
        try (Connection conn = ConnectionUtil.getConnection()) {
            String query = "SELECT * FROM users WHERE login = ?";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, login);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return Optional.of(getUser(resultSet));
            }
            return Optional.empty();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public User create(User user) {
        try (Connection conn = ConnectionUtil.getConnection()) {
            String query = "INSERT INTO users (name, login, salt, password) "
                    + "VALUES (?, ?, ?, ?)";
            PreparedStatement statement = conn.prepareStatement(query,
                    PreparedStatement.RETURN_GENERATED_KEYS);
            statement.setString(1, user.getName());
            statement.setString(2, user.getLogin());
            statement.setString(3, user.getSalt());
            statement.setString(4, user.getPasswordHash());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            resultSet.next();
            user.setId(resultSet.getLong(1));
            statement.close();
            saveRoles(user, conn);
            return user;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<User> get(Long id) {
        try (Connection conn = ConnectionUtil.getConnection()) {
            String query = "SELECT * FROM users WHERE user_id = ?";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return Optional.of(getUser(resultSet));
            }
            return Optional.empty();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<User> getAll() {
        try (Connection conn = ConnectionUtil.getConnection()) {
            String query = "SELECT * FROM users";
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            List<User> users = new ArrayList<>();
            while (resultSet.next()) {
                users.add(getUser(resultSet));
            }
            return users;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public User update(User user) {
        try (Connection conn = ConnectionUtil.getConnection()) {
            String query = "UPDATE users SET name = ?, login = ?, salt = ?, "
                    + "password = ? WHERE user_id = ?";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, user.getName());
            statement.setString(2, user.getLogin());
            statement.setString(3, user.getSalt());
            statement.setString(4, user.getPasswordHash());
            statement.setLong(5, user.getId());
            statement.executeUpdate();
            statement.close();
            query = "DELETE FROM users_roles WHERE user_id = ?";
            statement = conn.prepareStatement(query);
            statement.setLong(1, user.getId());
            statement.executeUpdate();
            statement.close();
            saveRoles(user, conn);
            return user;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean delete(Long id) {
        try (Connection conn = ConnectionUtil.getConnection()) {
            String query = "DELETE FROM users WHERE user_id = ?";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setLong(1, id);
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private Set<Role> getUserRoles(Long id) {
        try (Connection conn = ConnectionUtil.getConnection()) {
            String query = "SELECT users_roles.role_id, name FROM users_roles "
                    + "JOIN roles USING (role_id) WHERE user_id = ?";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            Set<Role> result = new HashSet<>();
            if (resultSet.next()) {
                Role role = Role.of(resultSet.getString("name"));
                role.setId(resultSet.getLong("role_id"));
                result.add(role);
            }
            return result;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void saveRoles(User user, Connection con) throws SQLException {
        String query = "INSERT INTO users_roles (user_id, role_id) VALUES (?, ?)";
        PreparedStatement statement = con.prepareStatement(query);
        for (Role role : user.getRoles()) {
            statement.setLong(1, user.getId());
            if (role.getId() == null) {
                role.setId(getRoleIdByName(role.getRoleName(), con));
            }
            statement.setLong(2, role.getId());
            statement.executeUpdate();
        }
    }

    private Long getRoleIdByName(Role.RoleName name, Connection con)
            throws SQLException {
        String query = "SELECT role_id FROM roles WHERE name = ?";
        PreparedStatement statement = con.prepareStatement(query);
        statement.setString(1, name.toString());
        ResultSet resultSet = statement.executeQuery();
        resultSet.next();
        return resultSet.getLong("role_id");
    }

    private User getUser(ResultSet resultSet) throws SQLException {
        Long id = resultSet.getLong("user_id");
        Set<Role> roles = getUserRoles(id);
        return new User(id, resultSet.getString("name"),
                resultSet.getString("login"),
                resultSet.getString("salt"),
                resultSet.getString("password"),
                roles);
    }
}
