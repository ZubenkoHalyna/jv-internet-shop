package com.dev.internet.shop.controller;

import com.dev.internet.shop.lib.Injector;
import com.dev.internet.shop.model.Product;
import com.dev.internet.shop.service.ProductService;
import java.io.IOException;
import java.math.BigDecimal;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AddProductController extends HttpServlet {
    private static final Injector INJECTOR =
            Injector.getInstance("com.dev.internet.shop");
    private ProductService productService =
            (ProductService) INJECTOR.getInstance(ProductService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/products/add.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        productService.create(new Product(req.getParameter("name"),
                new BigDecimal(req.getParameter("price"))));
        resp.sendRedirect(req.getContextPath() + "/products");
    }
}
