package mate.academy.internetshop.security;

import java.util.Arrays;
import mate.academy.internetshop.exceptions.AuthenticationException;
import mate.academy.internetshop.lib.Inject;
import mate.academy.internetshop.lib.Service;
import mate.academy.internetshop.model.User;
import mate.academy.internetshop.service.UserService;
import mate.academy.internetshop.util.HashUtil;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    @Inject
    private UserService userService;

    @Override
    public User login(String login, String password) throws AuthenticationException {
        User userFromDb = userService.getByLogin(login).orElseThrow(() ->
                new AuthenticationException("Incorrect login or password"));
        if (Arrays.equals(userFromDb.getPasswordHash(),
                HashUtil.hashPassword(password, userFromDb.getSalt()))) {
            return userFromDb;
        }
        throw new AuthenticationException("Incorrect login or password");
    }
}
