<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="order" scope="request" type="com.dev.internet.shop.model.Order"/>
<jsp:useBean id="user" scope="request" type="com.dev.internet.shop.model.User"/>
<html>
<head>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh"
          crossorigin="anonymous">
    <title>Order</title>
</head>
<body class="container">
    <jsp:include page="../menu.jsp"/>
    <h1>Order details</h1>
    <div class="input-group mb-3">
        <div class="input-group-prepend">
            <span class="input-group-text">Id</span>
        </div>
        <div class="form-control">
            <c:out value="${order.id}"/>
        </div>
    </div>
    <div class="input-group mb-3">
        <div class="input-group-prepend">
            <span class="input-group-text">User</span>
        </div>
        <div class="form-control">
            <c:out value="${user.name}"/>
        </div>
    </div>
    <h2>Order products</h2>
    <table class="table table-striped">
        <thead class="thead-dark">
            <tr>
                <th>Id</th>
                <th>Name</th>
                <th>Price</th>
            </tr>
        </thead>
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
        <a href="${pageContext.request.contextPath}/order/delete?id=${order.id}"
           class="btn btn-outline-secondary mt-1">Delete</a>
</body>
</html>
