<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<nav class="navbar navbar-expand-lg navbar-light bg-light mb-5">
    <a href="${pageContext.request.contextPath}/" class="nav-link">Home</a>
    <a href="${pageContext.request.contextPath}/users" class="nav-link">Users</a>
    <a href="${pageContext.request.contextPath}/products" class="nav-link">Products</a>
    <a href="${pageContext.request.contextPath}/buyProducts" class="nav-link">Buy</a>
    <a href="${pageContext.request.contextPath}/shoppingcart" class="nav-link">Shopping cart</a>
    <a href="${pageContext.request.contextPath}/orders" class="nav-link mr-auto">Orders</a>
    <% if (session.getAttribute("user_id") == null) {%>
        <a href="${pageContext.request.contextPath}/login" class="nav-link">Login</a>
    <% } else { %>
        <a href="${pageContext.request.contextPath}/logout" class="nav-link">Log out</a>
    <% } %>
</nav>
