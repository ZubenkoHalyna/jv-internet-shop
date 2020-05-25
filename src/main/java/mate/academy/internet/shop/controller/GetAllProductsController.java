package mate.academy.internet.shop.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mate.academy.internet.shop.lib.Injector;
import mate.academy.internet.shop.service.ProductService;

public class GetAllProductsController extends HttpServlet {
    private static final Injector INJECTOR =
            Injector.getInstance("mate.academy.internet.shop");
    private ProductService productService =
            (ProductService) INJECTOR.getInstance(ProductService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setAttribute("products", productService.getAll());
        req.getRequestDispatcher("WEB-INF/views/products/all.jsp").forward(req, resp);
    }
}
