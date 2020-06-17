<%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 2020/6/16
  Time: 20:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>提交作业结果</title>
</head>
<body>
<div align="center">
    <%
        String msg = (String) request.getAttribute("msg");
        String student_number = (String) request.getAttribute("student_number");
    %>
    <p style="margin: 50px; font-family: 'Arial Black'; font-size: 45px; color: darkgreen"><%=msg%></p>
    <form id="back" action="${pageContext.request.contextPath}/jsp/backStudent" method="post">
        <table style="line-height: 40px">
            <input id="stuId" name="student_number" value="<%=student_number%>" type="hidden">
            <tr align="center">
                <td><input type="submit" value="返回主页"></td>
            </tr>
        </table>
    </form>
</div>
</body>
</html>
