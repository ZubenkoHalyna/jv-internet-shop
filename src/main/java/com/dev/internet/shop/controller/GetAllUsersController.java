package com.dev.internet.shop.controller;

import com.dev.internet.shop.lib.Injector;
import com.dev.internet.shop.service.UserService;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GetAllUsersController extends HttpServlet {
    private static final Injector INJECTOR = Injector.getInstance("com.dev.internet.shop");
    private UserService userService = (UserService) INJECTOR.getInstance(UserService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setAttribute("users", userService.getAll());
        req.getRequestDispatcher("WEB-INF/views/users/all.jsp").forward(req, resp);
    }
}
