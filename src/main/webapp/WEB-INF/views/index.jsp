<%@page contentType="text/html; ISO-8859-1" language="java" %>
<html>
    <head>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
              integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh"
              crossorigin="anonymous">
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
