<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh"
          crossorigin="anonymous">
    <title>Orders</title>
</head>
<body>
    <h1>Orders:</h1>
    <table class="table table-striped">
        <thead class="thead-dark">
            <tr>
                <th>Id</th>
                <th>User id</th>
                <th>User name</th>
                <th>Details</th>
                <th>Delete</th>
            </tr>
        </thead>
        <c:forEach var="order" items="${orders}">
            <tr>
                <td>
                    <c:out value="${order.id}"/>
                </td>
                <td>
                    <c:out value="${user.id}"/>
                </td>
                <td>
                    <c:out value="${user.name}"/>
                </td>
                <td>
                    <a href="${pageContext.request.contextPath}/order?id=${order.id}">
                        Details
                    </a>
                </td>
                <td>
                    <a href="${pageContext.request.contextPath}/order/delete?id=${order.id}">Delete</a>
                </td>
            </tr>
        </c:forEach>
    </table>
    <a href="${pageContext.request.contextPath}/index" class="btn btn-outline-secondary">Menu</a>
</body>
</html>
