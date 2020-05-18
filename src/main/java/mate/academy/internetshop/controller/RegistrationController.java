package mate.academy.internetshop.controller;

import java.io.IOException;
import java.util.Set;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mate.academy.internetshop.lib.Injector;
import mate.academy.internetshop.model.Role;
import mate.academy.internetshop.model.User;
import mate.academy.internetshop.service.UserService;
import mate.academy.internetshop.util.HashUtil;

public class RegistrationController extends HttpServlet {
    private static final Injector INJECTOR = Injector.getInstance("mate.academy.internetshop");
    private UserService userService = (UserService) INJECTOR.getInstance(UserService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("WEB-INF/views/users/registration.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String login = req.getParameter("login");
        if (userService.getByLogin(login).isPresent()) {
            req.setAttribute("msg", "Login already exists");
            req.setAttribute("login", login);
            req.setAttribute("name", (req.getParameter("name")));
            req.getRequestDispatcher("WEB-INF/views/users/registration.jsp").forward(req, resp);
        } else {
            byte[] salt = HashUtil.getSalt();
            userService.create(new User(req.getParameter("name"), login,
                    salt,
                    HashUtil.hashPassword(req.getParameter("password"), salt),
                    Set.of(Role.of("USER"))));
            resp.sendRedirect(req.getContextPath() + "/users");
        }
    }
}
