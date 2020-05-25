package mate.academy.internet.shop.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mate.academy.internet.shop.lib.Injector;
import mate.academy.internet.shop.service.OrderService;
import mate.academy.internet.shop.service.UserService;

public class GetUserOrdersController extends HttpServlet {
    private static final Injector INJECTOR =
            Injector.getInstance("mate.academy.internet.shop");
    private OrderService orderService =
            (OrderService) INJECTOR.getInstance(OrderService.class);
    private UserService userService =
            (UserService) INJECTOR.getInstance(UserService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Long id = (Long) req.getSession().getAttribute("user_id");
        req.setAttribute("user", userService.get(id));
        req.setAttribute("orders", orderService.getUserOrders(id));
        req.getRequestDispatcher("WEB-INF/views/orders/all.jsp").forward(req, resp);
    }
}
