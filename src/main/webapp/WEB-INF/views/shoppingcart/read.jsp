<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Shopping cart</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh"
          crossorigin="anonymous">
</head>
<body>
    <h1>Products in shopping cart</h1>
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
                    <a href="${pageContext.request.contextPath}/shoppingcart/deleteProduct?id=${product.id}">
                        delete
                    </a>
                </td>
            </tr>
        </c:forEach>
    </table>

    <div class="btn-group mt-1">
        <a href="${pageContext.request.contextPath}/productsToBuy" class="btn btn-outline-secondary">Buy</a>
        <a href="${pageContext.request.contextPath}/order/complete" class="btn btn-primary">Complete order</a>
    </div>
</body>
</html>
