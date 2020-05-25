package mate.academy.internet.shop.service;

import java.util.Optional;
import mate.academy.internet.shop.model.User;

public interface UserService extends BaseService<User, Long> {
    User create(User user);

    User update(User user);

    Optional<User> getByLogin(String login);
}
