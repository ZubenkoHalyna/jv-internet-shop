package mate.academy.internet.shop.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mate.academy.internet.shop.lib.Injector;
import mate.academy.internet.shop.service.ShoppingCartService;

public class GetShoppingCartController extends HttpServlet {
    private static final Injector INJECTOR =
            Injector.getInstance("mate.academy.internet.shop");
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
