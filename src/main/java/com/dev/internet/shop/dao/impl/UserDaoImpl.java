package com.dev.internet.shop.dao.impl;

import com.dev.internet.shop.dao.UserDao;
import com.dev.internet.shop.db.Storage;
import com.dev.internet.shop.model.User;
import java.util.List;
import java.util.Optional;

public class UserDaoImpl implements UserDao {
    @Override
    public User create(User user) {
        Storage.addNewUser(user);
        return user;
    }

    @Override
    public Optional<User> get(Long id) {
        return Storage.users.stream()
                .filter(it -> it.getId().equals(id))
                .findAny();
    }

    @Override
    public List<User> getAll() {
        return Storage.users;
    }

    @Override
    public User update(User user) {
        User oldUser = get(user.getId()).get();
        if (!oldUser.getName().equals(user.getName())) {
            oldUser.setName(user.getName());
        }
        if (!oldUser.getPasswordHash().equals(user.getPasswordHash())) {
            oldUser.setPasswordHash(user.getPasswordHash());
        }
        if (!oldUser.getSalt().equals(user.getSalt())) {
            oldUser.setSalt(user.getSalt());
        }
        if (!oldUser.getLogin().equals(user.getLogin())) {
            oldUser.setLogin(user.getLogin());
        }
        return oldUser;
    }

    @Override
    public boolean delete(Long id) {
        Optional<User> user = get(id);
        user.ifPresent(Storage.users::remove);
        return user.isPresent();
    }

    @Override
    public Optional<User> getByLogin(String login) {
        return Storage.users.stream()
                .filter(user -> user.getLogin().equals(login))
                .findAny();
    }
}
