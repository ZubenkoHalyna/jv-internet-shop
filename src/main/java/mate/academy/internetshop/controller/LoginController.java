package mate.academy.internetshop.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mate.academy.internetshop.exceptions.AuthenticationException;
import mate.academy.internetshop.lib.Injector;
import mate.academy.internetshop.model.User;
import mate.academy.internetshop.security.AuthenticationService;

public class LoginController extends HttpServlet {
    private static final String LOGIN = "login";
    private static final String PASSWORD = "password";
    private static final Injector INJECTOR =
            Injector.getInstance("mate.academy.internetshop");
    private final AuthenticationService authenticationService =
            (AuthenticationService) INJECTOR.getInstance(AuthenticationService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("WEB-INF/views/users/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        try {
            User user = authenticationService.login(req.getParameter(LOGIN),
                    req.getParameter(PASSWORD));
            req.getSession().setAttribute("user_id", user.getId());
        } catch (AuthenticationException e) {
            req.setAttribute("msg", e.getMessage());
            req.getRequestDispatcher("WEB-INF/views/users/login.jsp").forward(req, resp);
            return;
        }
        resp.sendRedirect(req.getContextPath() + "/");
    }
}
