package mate.academy.internetshop.web.filters;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mate.academy.internetshop.lib.Injector;
import mate.academy.internetshop.model.Role;
import mate.academy.internetshop.model.Role.RoleName;
import mate.academy.internetshop.model.User;
import mate.academy.internetshop.service.UserService;

public class AuthorisationFilter implements Filter {
    private static final Injector INJECTOR =
            Injector.getInstance("mate.academy.internetshop");
    private final UserService userService =
            (UserService) INJECTOR.getInstance(UserService.class);
    private Map<String, List<RoleName>> protectedUrlsStart;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        protectedUrlsStart = new HashMap<>();
        protectedUrlsStart.put("/users", List.of(RoleName.ADMIN));
        protectedUrlsStart.put("/user/delete", List.of(RoleName.ADMIN));
        protectedUrlsStart.put("/products", List.of(RoleName.ADMIN));
        protectedUrlsStart.put("/product", List.of(RoleName.ADMIN));

        protectedUrlsStart.put("/order", List.of(RoleName.USER));
        protectedUrlsStart.put("/orders", List.of(RoleName.USER));
        protectedUrlsStart.put("/shoppingcart", List.of(RoleName.USER));
        protectedUrlsStart.put("/buyProducts", List.of(RoleName.USER));
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        String requestedUrl = req.getServletPath();

        List<Role.RoleName> requiredRoles = getRequiredRoles(requestedUrl);
        if (requiredRoles == null) {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }

        Long id = (Long) req.getSession().getAttribute("user_id");
        User user = userService.get(id);

        if (isAuthorized(user, requiredRoles)) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            req.getRequestDispatcher("/WEB-INF/views/accessDenied.jsp").forward(req, resp);
        }
    }

    @Override
    public void destroy() {

    }

    private List<RoleName> getRequiredRoles(String url) {
        for (Map.Entry<String, List<Role.RoleName>> entry : protectedUrlsStart.entrySet()) {
            if (url.startsWith(entry.getKey())) {
                return entry.getValue();
            }
        }
        return null;
    }

    private boolean isAuthorized(User user, List<Role.RoleName> authorizedRoles) {
        for (Role.RoleName role : authorizedRoles) {
            if (!user.hasRole(role)) {
                return false;
            }
        }
        return true;
    }
}
