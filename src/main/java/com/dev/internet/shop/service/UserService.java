package com.dev.internet.shop.service;

import com.dev.internet.shop.model.User;
import java.util.Optional;

public interface UserService extends BaseService<User, Long> {
    User create(User user);

    User update(User user);

    Optional<User> getByLogin(String login);
}
