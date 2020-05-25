package mate.academy.internet.shop.dao;

import java.util.Optional;
import mate.academy.internet.shop.model.User;

public interface UserDao extends BaseDao<User, Long> {
    Optional<User> getByLogin(String login);
}
