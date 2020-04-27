package mate.academy.internetshop.service;

import mate.academy.internetshop.model.User;

public interface UserService extends BaseService<User, Long> {
    User create(User user);

    User update(User user);
}
