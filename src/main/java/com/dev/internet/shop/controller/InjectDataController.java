package com.dev.internet.shop.controller;

import com.dev.internet.shop.lib.Injector;
import com.dev.internet.shop.model.Product;
import com.dev.internet.shop.model.Role;
import com.dev.internet.shop.model.User;
import com.dev.internet.shop.service.ProductService;
import com.dev.internet.shop.service.UserService;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class InjectDataController extends HttpServlet {
    private static final Injector INJECTOR = Injector.getInstance("com.dev.internet.shop");
    private UserService userService = (UserService) INJECTOR.getInstance(UserService.class);
    private ProductService productService =
            (ProductService) INJECTOR.getInstance(ProductService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException, ServletException {
        if (userService.getByLogin("admin").isEmpty()) {
            User user = new User("Admin", "admin", "", Set.of(Role.of("ADMIN")));
            userService.create(user);
            req.setAttribute("user", user);
            List<Product> products = new ArrayList<>();
            products.add(new Product("water", new BigDecimal(15.0)));
            products.add(new Product("tea", new BigDecimal(25.0)));
            products.add(new Product("coffee", new BigDecimal(75.0)));
            products.forEach(productService::create);
            req.setAttribute("products", products);
        }
        req.getRequestDispatcher("WEB-INF/views/dataInjected.jsp").forward(req, resp);
    }
}
