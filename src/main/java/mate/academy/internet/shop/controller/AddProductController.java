package mate.academy.internet.shop.controller;

import java.io.IOException;
import java.math.BigDecimal;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mate.academy.internet.shop.lib.Injector;
import mate.academy.internet.shop.model.Product;
import mate.academy.internet.shop.service.ProductService;

public class AddProductController extends HttpServlet {
    private static final Injector INJECTOR =
            Injector.getInstance("mate.academy.internet.shop");
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
