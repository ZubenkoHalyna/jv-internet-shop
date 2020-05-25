package mate.academy.internet.shop.security;

import mate.academy.internet.shop.exceptions.AuthenticationException;
import mate.academy.internet.shop.model.User;

public interface AuthenticationService {
    User login(String login, String password) throws AuthenticationException;
}
