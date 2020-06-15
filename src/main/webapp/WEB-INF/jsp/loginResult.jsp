<%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 2020/6/15
  Time: 20:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登录结果</title>
</head>
<body>
<div align="center">
    <%
        String msg = (String) request.getAttribute("msg");
    %>
    <p style="margin: 50px; font-family: 'Arial Black'; font-size: 45px; color: darkgreen"><%=msg%></p>
    <a href="${pageContext.request.contextPath}/jsp/login">返回登录页</a>
</div>
</body>
</html>
