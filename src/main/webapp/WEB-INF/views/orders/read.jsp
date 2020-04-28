<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Order</title>
</head>
<body>
    <h1>Order details</h1>
    Order id: <c:out value="${order.id}"/>
    User id: <c:out value="${order.user.id}"/>
    User name: <c:out value="${order.user.name}"/>
    <h2>Order products:</h2>
    <table border="1">
        <tr>
            <th>Id</th>
            <th>Name</th>
            <th>Price</th>
        </tr>
        <c:forEach var="product" items="${order.products}">
            <tr>
                <td>
                    <c:out value="${product.id}"/>
                </td>
                <td>
                    <c:out value="${product.name}"/>
                </td>
                <td>
                    <c:out value="${product.price}"/>
                </td>
            </tr>
        </c:forEach>
    </table>
    <a href="${pageContext.request.contextPath}/order/delete?id=${order.id}">Delete</a>
    <a href="${pageContext.request.contextPath}/orders">Orders</a>
    <a href="${pageContext.request.contextPath}/index">Menu</a>
</body>
</html>
