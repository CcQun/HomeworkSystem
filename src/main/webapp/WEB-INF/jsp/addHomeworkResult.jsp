<%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 2020/6/16
  Time: 13:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>添加作业结果</title>
</head>
<body>
<div align="center">
    <%
        String msg = (String) request.getAttribute("msg");
        String teacher_number = (String) request.getAttribute("teacher_number");
    %>
    <p style="margin: 50px; font-family: 'Arial Black'; font-size: 45px; color: darkgreen"><%=msg%></p>
    <form id="back" action="${pageContext.request.contextPath}/jsp/backTeacher" method="post">
        <table style="line-height: 40px">
            <input id="teaId" name="teacher_number" value="<%=teacher_number%>" type="hidden">
            <tr align="center">
                <td><input type="submit" value="返回主页"></td>
            </tr>
        </table>
    </form>
</div>
</body>
</html>
