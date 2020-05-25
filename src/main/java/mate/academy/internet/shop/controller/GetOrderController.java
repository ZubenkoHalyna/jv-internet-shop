package mate.academy.internet.shop.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mate.academy.internet.shop.lib.Injector;
import mate.academy.internet.shop.model.Order;
import mate.academy.internet.shop.service.OrderService;
import mate.academy.internet.shop.service.UserService;

public class GetOrderController extends HttpServlet {
    private static final Injector INJECTOR =
            Injector.getInstance("mate.academy.internet.shop");
    private OrderService orderService =
            (OrderService) INJECTOR.getInstance(OrderService.class);
    private UserService userService =
            (UserService) INJECTOR.getInstance(UserService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Order order = orderService.get(Long.parseLong(req.getParameter("id")));
        req.setAttribute("order", order);
        req.setAttribute("user", userService.get(order.getUserId()));
        req.getRequestDispatcher("WEB-INF/views/orders/read.jsp").forward(req, resp);
    }
}
