package com.dev.internet.shop.security;

import com.dev.internet.shop.exceptions.AuthenticationException;
import com.dev.internet.shop.lib.Inject;
import com.dev.internet.shop.lib.Service;
import com.dev.internet.shop.model.User;
import com.dev.internet.shop.service.UserService;
import com.dev.internet.shop.util.HashUtil;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    @Inject
    private UserService userService;

    @Override
    public User login(String login, String password) throws AuthenticationException {
        User userFromDb = userService.getByLogin(login).orElseThrow(() ->
                new AuthenticationException("Incorrect login or password"));
        if (userFromDb.getPasswordHash().equals(
                HashUtil.hashPassword(password, userFromDb.getSalt()))) {
            return userFromDb;
        }
        throw new AuthenticationException("Incorrect login or password");
    }
}
