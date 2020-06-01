package com.dev.internet.shop.dao;

import com.dev.internet.shop.model.User;
import java.util.Optional;

public interface UserDao extends BaseDao<User, Long> {
    Optional<User> getByLogin(String login);
}
