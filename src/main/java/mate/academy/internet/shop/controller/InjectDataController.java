package mate.academy.internet.shop.controller;

import java.io.IOException;
import java.util.Set;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mate.academy.internet.shop.lib.Injector;
import mate.academy.internet.shop.model.Role;
import mate.academy.internet.shop.model.User;
import mate.academy.internet.shop.service.UserService;

public class InjectDataController extends HttpServlet {
    private static final Injector INJECTOR = Injector.getInstance("mate.academy.internet.shop");
    private UserService userService = (UserService) INJECTOR.getInstance(UserService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        userService.create(new User("Admin", "admin", "", Set.of(Role.of("ADMIN"))));
        resp.sendRedirect(req.getContextPath());
    }
}
