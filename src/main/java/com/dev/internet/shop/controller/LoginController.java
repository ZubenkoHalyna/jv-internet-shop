package com.dev.internet.shop.controller;

import com.dev.internet.shop.exceptions.AuthenticationException;
import com.dev.internet.shop.lib.Injector;
import com.dev.internet.shop.model.User;
import com.dev.internet.shop.security.AuthenticationService;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;

public class LoginController extends HttpServlet {
    private static final String LOGIN = "login";
    private static final String PASSWORD = "password";
    private static final String USER_ID = "user_id";
    private static final Logger LOGGER = Logger.getLogger(LoginController.class);
    private static final Injector INJECTOR =
            Injector.getInstance("com.dev.internet.shop");
    private final AuthenticationService authenticationService =
            (AuthenticationService) INJECTOR.getInstance(AuthenticationService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("WEB-INF/views/users/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        try {
            User user = authenticationService.login(req.getParameter(LOGIN),
                    req.getParameter(PASSWORD));
            LOGGER.info("User login: " + user.getLogin());
            req.getSession().setAttribute(USER_ID, user.getId());
        } catch (AuthenticationException e) {
            req.setAttribute("msg", e.getMessage());
            req.getRequestDispatcher("WEB-INF/views/users/login.jsp").forward(req, resp);
            return;
        }
        resp.sendRedirect(req.getContextPath() + "/");
    }
}
