<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="products" scope="request" type="java.util.List<mate.academy.internet.shop.model.Product>"/>
<html>
<head>
    <title>Products</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh"
          crossorigin="anonymous">
</head>
<body>
    <h1>Products:</h1>
    <table class="table table-striped">
        <thead class="thead-dark">
            <tr>
                <th>Id</th>
                <th>Name</th>
                <th>Price</th>
                <th>Delete</th>
            </tr>
        </thead>
        <c:forEach var="product" items="${products}">
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
                <td>
                    <a href="${pageContext.request.contextPath}/product/delete?id=${product.id}">
                        delete
                    </a>
                </td>
            </tr>
        </c:forEach>
    </table>
    <div class="btn-group mt-1">
        <a href="${pageContext.request.contextPath}/" class="btn btn-outline-secondary">Menu</a>
        <a href="${pageContext.request.contextPath}/product/add" class="btn btn-primary">Add</a>
    </div>
</body>
</html>
