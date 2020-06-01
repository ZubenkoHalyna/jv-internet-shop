package com.dev.internet.shop.controller;

import com.dev.internet.shop.lib.Injector;
import com.dev.internet.shop.service.ShoppingCartService;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GetShoppingCartController extends HttpServlet {
    private static final Injector INJECTOR =
            Injector.getInstance("com.dev.internet.shop");
    private ShoppingCartService shoppingCartService =
            (ShoppingCartService) INJECTOR.getInstance(ShoppingCartService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Long id = (Long) req.getSession().getAttribute("user_id");
        req.setAttribute("products", shoppingCartService.getByUserId(id).getProducts());
        req.getRequestDispatcher("WEB-INF/views/shoppingcart/read.jsp").forward(req, resp);
    }
}
