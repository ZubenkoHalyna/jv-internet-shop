package mate.academy.internet.shop.controller;

import java.io.IOException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mate.academy.internet.shop.lib.Injector;
import mate.academy.internet.shop.service.ProductService;
import mate.academy.internet.shop.service.ShoppingCartService;

public class AddProductToShoppingCartController extends HttpServlet {
    private static final Injector INJECTOR =
            Injector.getInstance("mate.academy.internet.shop");
    private ProductService productService =
            (ProductService) INJECTOR.getInstance(ProductService.class);
    private ShoppingCartService shoppingCartService =
            (ShoppingCartService) INJECTOR.getInstance(ShoppingCartService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        Long id = (Long) req.getSession().getAttribute("user_id");
        shoppingCartService.addProduct(shoppingCartService.getByUserId(id),
                productService.get(Long.parseLong(req.getParameter("id"))));
        resp.sendRedirect(req.getContextPath() + "/shoppingcart");
    }
}
