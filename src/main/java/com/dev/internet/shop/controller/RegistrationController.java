package com.dev.internet.shop.controller;

import com.dev.internet.shop.lib.Injector;
import com.dev.internet.shop.model.Role;
import com.dev.internet.shop.model.User;
import com.dev.internet.shop.service.UserService;
import java.io.IOException;
import java.util.Set;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RegistrationController extends HttpServlet {
    private static final Injector INJECTOR = Injector.getInstance("com.dev.internet.shop");
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
            userService.create(new User(req.getParameter("name"), login,
                    req.getParameter("password"), Set.of(Role.of("USER"))));
            resp.sendRedirect(req.getContextPath() + "/users");
        }
    }
}
