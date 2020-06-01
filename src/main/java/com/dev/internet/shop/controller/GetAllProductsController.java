package com.dev.internet.shop.controller;

import com.dev.internet.shop.lib.Injector;
import com.dev.internet.shop.service.ProductService;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GetAllProductsController extends HttpServlet {
    private static final Injector INJECTOR =
            Injector.getInstance("com.dev.internet.shop");
    private ProductService productService =
            (ProductService) INJECTOR.getInstance(ProductService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setAttribute("products", productService.getAll());
        req.getRequestDispatcher("WEB-INF/views/products/all.jsp").forward(req, resp);
    }
}
