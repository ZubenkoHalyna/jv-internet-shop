package mate.academy.internetshop.dao;

import java.util.Optional;
import mate.academy.internetshop.model.User;

public interface UserDao extends BaseDao<User, Long> {
    Optional<User> getByLogin(String login);
}
