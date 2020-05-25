package mate.academy.internet.shop.controller;

import java.io.IOException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mate.academy.internet.shop.lib.Injector;
import mate.academy.internet.shop.model.Order;
import mate.academy.internet.shop.model.ShoppingCart;
import mate.academy.internet.shop.service.OrderService;
import mate.academy.internet.shop.service.ShoppingCartService;

public class CompleteOrderController extends HttpServlet {
    private static final Injector INJECTOR =
            Injector.getInstance("mate.academy.internet.shop");
    private OrderService orderService =
            (OrderService) INJECTOR.getInstance(OrderService.class);
    private ShoppingCartService shoppingCartService =
            (ShoppingCartService) INJECTOR.getInstance(ShoppingCartService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        Long id = (Long) req.getSession().getAttribute("user_id");
        ShoppingCart shoppingCart = shoppingCartService.getByUserId(id);
        Order order = orderService.completeOrder(shoppingCart.getProducts(), id);
        shoppingCartService.clear(shoppingCart);
        resp.sendRedirect(req.getContextPath() + "/order?id=" + order.getId());
    }
}
