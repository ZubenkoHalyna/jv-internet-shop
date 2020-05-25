package mate.academy.internet.shop.security;

import mate.academy.internet.shop.exceptions.AuthenticationException;
import mate.academy.internet.shop.lib.Inject;
import mate.academy.internet.shop.lib.Service;
import mate.academy.internet.shop.model.User;
import mate.academy.internet.shop.service.UserService;
import mate.academy.internet.shop.util.HashUtil;

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
