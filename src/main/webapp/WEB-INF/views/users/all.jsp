<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="users" scope="request" type="java.util.List<com.dev.internet.shop.model.User>"/>
<html>
<head>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh"
          crossorigin="anonymous">
    <title>Users</title>
</head>
<body>
    <h1>All users:</h1>
    <table class="table table-striped">
        <thead class="thead-dark">
            <tr>
                <th>Id</th>
                <th>Name</th>
                <th>Login</th>
                <th>Delete</th>
            </tr>
        </thead>
        <c:forEach var="user" items="${users}">
            <tr>
                <td>
                    <c:out value="${user.id}"/>
                </td>
                <td>
                    <c:out value="${user.name}"/>
                </td>
                <td>
                    <c:out value="${user.login}"/>
                </td>
                <td>
                    <a href="${pageContext.request.contextPath}/user/delete?id=${user.id}">delete</a>
                </td>
            </tr>
        </c:forEach>
    </table>
    <div class="btn-group mt-1">
        <a href="${pageContext.request.contextPath}/" class="btn btn-outline-secondary">Menu</a>
        <a href="${pageContext.request.contextPath}/registration" class="btn btn-primary">Add</a>
    </div>
</body>
</html>
