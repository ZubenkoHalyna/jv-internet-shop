package com.dev.internet.shop.service.impl;

import com.dev.internet.shop.dao.UserDao;
import com.dev.internet.shop.lib.Inject;
import com.dev.internet.shop.lib.Service;
import com.dev.internet.shop.model.User;
import com.dev.internet.shop.service.UserService;
import com.dev.internet.shop.util.HashUtil;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Inject
    private UserDao userDao;

    @Override
    public User create(User user) {
        byte[] salt = HashUtil.getSalt();
        user.setSalt(HashUtil.toHexString(salt));
        user.setPasswordHash(HashUtil.hashPassword(user.getPasswordHash(), salt));
        return userDao.create(user);
    }

    @Override
    public User get(Long id) {
        return userDao.get(id).get();
    }

    @Override
    public List<User> getAll() {
        return userDao.getAll();
    }

    @Override
    public User update(User user) {
        return userDao.update(user);
    }

    @Override
    public Optional<User> getByLogin(String login) {
        return userDao.getByLogin(login);
    }

    @Override
    public boolean delete(Long id) {
        return userDao.delete(id);
    }
}
