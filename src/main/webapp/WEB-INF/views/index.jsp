<%@page contentType="text/html; ISO-8859-1" language="java" %>
<html>
    <head>
        <title>Internet shop</title>
    </head>
    <body>
        <h1>Menu</h1>
        <a href="${pageContext.request.contextPath}/users">Users</a>
        <br/>
        <a href="${pageContext.request.contextPath}/products">Products</a>
        <br/>
        <a href="${pageContext.request.contextPath}/productsToBuy">Buy</a>
        <br/>
        <a href="${pageContext.request.contextPath}/shoppingcart">Shopping cart</a>
        <br/>
        <a href="${pageContext.request.contextPath}/orders">Orders</a>
    </body>
</html>
