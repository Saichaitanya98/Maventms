<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
    <title>Error Page</title>
</head>
<body>
<h1>An error has occurred.</h1>
<div style="color: #F00;">
    Error message: <%= exception %><br><br>
    <b><a href="index.jsp">Go back to Login Page</a></b>
</div>
</body>
</html>
 