<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html; ISO-8859-1" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh"
          crossorigin="anonymous">
    <title>Internet shop</title>
</head>
<body>
    <c:if test="${user == null}">
        Admin user already exists, password is empty
        <br/>
    </c:if>
    <c:if test="${user != null}">
        <h1>Test data was added</h1>
        Added user with login "<c:out value="${user.login}"/>" and empty password
        <br/>
        Added list of products
        <c:forEach var="product" items="${products}">
            <li>
                <c:out value="${product.name}"/>
            </li>
        </c:forEach>
    </c:if>
    <a href="${pageContext.request.contextPath}/login" class="btn btn-primary mt-1">Login</a>
</body>
</html>