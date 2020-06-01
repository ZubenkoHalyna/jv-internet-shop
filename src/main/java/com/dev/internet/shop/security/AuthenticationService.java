package com.dev.internet.shop.security;

import com.dev.internet.shop.exceptions.AuthenticationException;
import com.dev.internet.shop.model.User;

public interface AuthenticationService {
    User login(String login, String password) throws AuthenticationException;
}
