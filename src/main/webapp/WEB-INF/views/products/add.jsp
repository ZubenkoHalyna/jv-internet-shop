<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add product</title>
</head>
<body>
    <form method="post" action="${pageContext.request.contextPath}/product/add">
        Name: <input type="text" name="name">
        Price: <input type="text" name="price">
        <button type="submit">Add</button>
    </form>
</body>
</html>
