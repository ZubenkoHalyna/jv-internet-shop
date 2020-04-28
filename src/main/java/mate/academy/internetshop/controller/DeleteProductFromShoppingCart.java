package mate.academy.internetshop.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mate.academy.internetshop.lib.Injector;
import mate.academy.internetshop.service.ProductService;
import mate.academy.internetshop.service.ShoppingCartService;

public class DeleteProductFromShoppingCart extends HttpServlet {
    private static final long USER_ID = 0;
    private static final Injector INJECTOR =
            Injector.getInstance("mate.academy.internetshop");
    private ProductService productService =
            (ProductService) INJECTOR.getInstance(ProductService.class);
    private ShoppingCartService shoppingCartService =
            (ShoppingCartService) INJECTOR.getInstance(ShoppingCartService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        shoppingCartService.deleteProduct(shoppingCartService.getByUserId(USER_ID),
                productService.get(Long.parseLong(req.getParameter("id"))));
        resp.sendRedirect(req.getContextPath() + "/shoppingcart");
    }
}
