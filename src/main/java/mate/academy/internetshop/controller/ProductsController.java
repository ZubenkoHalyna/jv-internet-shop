package mate.academy.internetshop.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mate.academy.internetshop.lib.Inject;
import mate.academy.internetshop.lib.Injector;
import mate.academy.internetshop.service.ProductService;

public class ProductsController extends HttpServlet {
    private static final Injector injector =
            Injector.getInstance("mate.academy.internetshop");
    @Inject
    private ProductService productService =
            (ProductService) injector.getInstance(ProductService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setAttribute("products", productService.getAll());
        req.getRequestDispatcher("WEB-INF/views/products/all.jsp").forward(req, resp);
    }
}
