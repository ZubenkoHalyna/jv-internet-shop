<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh"
          crossorigin="anonymous">
</head>
<body class="container">
    <jsp:include page="../menu.jsp"/>
    <c:if test="${msg != null}">
        <div class="alert alert-danger"> ${msg} </div>
    </c:if>
    <form method="post" action="${pageContext.request.contextPath}/login">
        <div class="form-group">
            <label for="login">Login</label>
            <input type="text" name="login" id="login" class="form-control">
        </div>
        <div class="form-group">
            <label for="password">Password</label>
            <input type="password" name="password" id="password" class="form-control">
        </div>
        <div class="btn-group mt-1">
            <a href="${pageContext.request.contextPath}/registration" class="btn btn-outline-secondary">Registration</a>
            <button type="submit" class="btn btn-primary">Login</button>
        </div>
    </form>
</body>
</html>
